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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : FileUploadController
 * author         : 방한솔
 * date           : 2023/06/14
 * description    : 파일업로드 저장 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/14        방한솔           최초 생성
 */
@RequestMapping("/upload")
@Controller
@Slf4j
public class FileUploadController {
    @Value("${upload.path}")
    private String uploadDir;

    private String filePath(String uuid, String ext){
        // 프로젝트 루트 경로 확인용
        String projectPath = new File("").getAbsolutePath();

        // 실제 업로드 폴더 경로
        File uploadFolder = new File(projectPath + uploadDir);

        log.info("uploadFolder path : {}", projectPath + uploadDir);

        if(!uploadFolder.exists()){
            boolean mkdirs = uploadFolder.mkdirs();
            log.info("업로드 폴더 생성 : {}", mkdirs);
        }

        return projectPath + File.separator + uploadDir + File.separator + uuid + "." + ext;
    }

    @PostMapping(value = "/ajax")
    @ResponseBody
    public ResponseEntity<?> fileUploadCKEditor(@RequestParam("upload") MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        log.info("파일정보 : {}", uploadFile);

        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
//        String uploadId = "/" + uuid + "." + ext;

        String filePath = filePath(uuid, ext);
        log.info("경로명 : {}", filePath);

        uploadFile.transferTo(new File(filePath(uuid, ext)));


        Map<String, Object> map = new HashMap<>();

        String url = request.getScheme()
                + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + "/upload/imageView?uuid=" + uuid + "&ext=" + ext;

        map.put("url", url);


        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@RequestParam String uuid, @RequestParam String ext) throws MalformedURLException {

        log.info("uuid : {}", uuid);
        // DB 조회용
        Resource resource = new UrlResource("file:" + uploadDir + "/" + uuid + ext);

        log.info("urlResource : {}", "file:" + uploadDir + uuid + "." + ext);

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
    public ResponseEntity<Resource> showImage(@RequestParam String uuid,@RequestParam String ext) throws IOException {

        log.info("uuid : {}", uuid);

        Resource resource = new UrlResource("file:" + filePath(uuid, ext));


        log.info("filePath : {}", filePath(uuid, ext));

        File file = new File("");

        String absolutePath = file.getAbsolutePath();
        log.info("absolutePath : {}", absolutePath);


        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentType(MediaType.IMAGE_GIF);

            headers.setContentDisposition(ContentDisposition.inline().build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
