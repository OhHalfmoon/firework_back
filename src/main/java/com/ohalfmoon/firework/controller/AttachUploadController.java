package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.fileUpload.AttachDto;
import com.ohalfmoon.firework.dto.fileUpload.AttachResponseDto;
import com.ohalfmoon.firework.service.AttachService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : AttachUploadController
 * author         : 방한솔
 * date           : 2023/06/14
 * description    : 파일업로드 저장 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/14        방한솔           최초 생성
 */
@RequestMapping("/upload")
@RequiredArgsConstructor
@Controller
@Slf4j
public class AttachUploadController {
    private final AttachService service;

    @PostMapping(value = "/ajax")
    @ResponseBody
    public ResponseEntity<?> fileUploadCKEditor(@RequestParam("upload") MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        log.info("파일정보 : {}", uploadFile);

        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());

        AttachDto dto = new AttachDto(uploadFile);

        Long fileNo = service.upload(uploadFile, dto);


        Map<String, Object> map = new HashMap<>();

        String url = request.getScheme()
                + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + "/upload/imageView?fileNo=" + fileNo;

        map.put("url", url);


        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@RequestParam Long fileNo) throws MalformedURLException, FileNotFoundException {

        AttachResponseDto dto = service.getFile(fileNo, false);

        File file = new File(dto.getFile().getAbsolutePath());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders
                .setContentDisposition(ContentDisposition.builder("attachment")
                        .filename(dto.getOriginName(), StandardCharsets.UTF_8)
                        .build());

        FileSystemResource resource = new FileSystemResource(file);

        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/imageView")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@RequestParam Long fileNo) throws IOException {

       AttachResponseDto dto = service.getFile(fileNo, true);

        if (dto != null) {

            HttpHeaders headers = new HttpHeaders();
            String extension = dto.getExt();

            switch (extension.toLowerCase()) {
                case "png":
                    headers.setContentType(MediaType.IMAGE_PNG);
                    break;
                case "jpeg":
                case "jpg":
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    break;
                case "gif":
                    headers.setContentType(MediaType.IMAGE_GIF);
                    break;
            }

            headers.setContentDisposition(ContentDisposition.inline()
                    .filename(dto.getOriginName(), StandardCharsets.UTF_8).build());

            Resource resource = new FileSystemResource(dto.getFile());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
