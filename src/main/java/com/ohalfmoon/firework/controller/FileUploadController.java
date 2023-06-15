package com.ohalfmoon.firework.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : FileUploadController
 * author         : banghansol
 * date           : 2023/06/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/14        banghansol       최초 생성
 */
@RequestMapping("/upload")
@Controller
@Slf4j
public class FileUploadController {
    @Value("${upload.path}")
    private String uploadDir;

    @PostMapping(value = "/ajax")
    @ResponseBody
    public ResponseEntity<?> fileUploadCKEditor(@RequestParam("upload") MultipartFile uploadFile) throws IOException {
        log.info("파일정보 : {}", uploadFile);

        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        String uploadId = "/" + uuid + "." + ext;

        log.info("경로명 : {}", uploadDir + uploadId);

         uploadFile.transferTo(new File(uploadDir + uploadId));

        Map<String, Object> map = new HashMap<>();

        String url = "http://localhost:8080" + "/upload/imageView?uuid=" + uuid + "&ext=" + ext;
        map.put("url", url);


        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@RequestParam String uuid, @RequestParam String ext) throws MalformedURLException {

        log.info("uuid : {}", uuid);
        // DB 조회용

//        String ext = ".jpeg";
        Resource resource = new UrlResource("file:" + uploadDir + "/" + uuid + ext);

        log.info("urlResource : {}", "file:" + uploadDir + uuid + ext);

        if(resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION
                            , "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/imageView")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@RequestParam String uuid,@RequestParam String ext) throws MalformedURLException {

        log.info("uuid : {}", uuid);

//        String ext = ".jpeg";

        Resource resource = new UrlResource("file:" + uploadDir + "/" + uuid + "." + ext);

        log.info("urlResource : {}", "file:" + uploadDir + uuid + ext);

//        Path.

        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentType(MediaType.IMAGE_GIF);
//            headers.setContentDispositionFormData("attachment", resource.getFilename());
            headers.setContentDisposition(ContentDisposition.inline().build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
