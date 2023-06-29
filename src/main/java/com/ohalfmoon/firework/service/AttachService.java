package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.fileUpload.AttachResponseDto;
import com.ohalfmoon.firework.dto.fileUpload.AttachDto;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.spec.AttachSpec;
import com.ohalfmoon.firework.persistence.AttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : FileUploadService
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AttachService {
//    @Value("${upload.path}")
//    private String uploadDir;

    public static String uploadDir = "upload";

    private final AttachRepository attachRepository;

    private final HttpServletRequest request;

    // 업로드 기준 경로
    private static final String projectPath = new File("").getAbsolutePath();

//    public String getProjectPath() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:");
//        return resource.getFile().getAbsolutePath();
//    }


    public String filePath(String uuid, String ext){
        // 프로젝트 루트 경로 확인용

        // 실제 업로드 폴더 경로
        File uploadFolder = new File(projectPath + uploadDir);

        log.info("uploadFolder path : {}", projectPath + uploadDir);

        if(!uploadFolder.exists()){
            boolean mkdirs = uploadFolder.mkdirs();
            log.info("업로드 폴더 생성 : {}", mkdirs);
        }

        return File.separator + uploadDir + File.separator + uuid + "." + ext;
    }

    public void saveFile(MultipartFile multipartFile, AttachDto dto) throws IOException {
        File path = new File(uploadDir, dto.getPath());
        log.info("save dto : {}", dto);
        File file = dto.getFile();

        if(!path.exists()){
            boolean mkdirs = path.mkdirs();
        }

        String absolutePath = file.getAbsolutePath();

        log.info("save absolutePath : {}", absolutePath);

//        multipartFile.transferTo(new File(absolutePath));
        multipartFile.transferTo(new File(absolutePath));
    }

    /**
     * File upload long.
     *
     * @param dto        the dto
     * @return the long
     * @throws IOException the io exception
     */
    @Transactional
    public Long upload(MultipartFile multipartFile, AttachDto dto) throws IOException {
        // 파일 저장 시작
//        String filePath = filePath(dto.getUuid(), dto.getExt());
//        dto.setPath(filePath);

//        File path = new File(UPLOAD_PATH, dto.getPath());

//        log.info("경로명 : {}", filePath);

//        dto.getFile().transferTo(new File(projectPath + filePath));

        try{
            saveFile(multipartFile, dto);
            // 파일 정보 저장
            AttachEntity attachEntity = dto.toEntity();
            attachRepository.save(attachEntity);

            return attachEntity.getAttachNo();

        } catch (IOException e){
            throw new RuntimeException("파일 저장시 오류가 발생했습니다!");
        }

    }

    /**
     * Gets file resource.
     *
     * @param attachNo the attach no
     * @return the file resource
     * @throws MalformedURLException the malformed url exception
     */
    @Transactional(readOnly = true)
    public Resource getFileResource(Long attachNo) throws MalformedURLException {
        AttachEntity attachEntity = attachRepository.findById(attachNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 파일이 존재하지 않습니다. attachNo = " + attachNo));

        String filePath = attachEntity.getPath();

        Resource resource = new UrlResource("file:" + projectPath + filePath);

        if(resource.exists()){
            return resource;
        } else {
            return null;
        }

    }

    /**
     * Get file list list.
     *
     * @param approvalNo the approval no
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<AttachResponseDto> getFileList(Long approvalNo){
//        List<AttachEntity> fileDtos = attachRepository.findAttachEntitiesByApprovalEntity_ApprovalNo(approvalNo);
        List<AttachEntity> fileDtos = attachRepository.findAll(AttachSpec.approvalNo(approvalNo));

        return fileDtos.stream().map(item -> entityToDto(item, false)).collect(Collectors.toList());
    }

    public List<AttachResponseDto> getBoardFileList(Long boardNo){
//        List<AttachEntity> fileDtos = attachRepository.findAttachEntitiesByApprovalEntity_ApprovalNo(approvalNo);
        List<AttachEntity> fileDtos = attachRepository.findAll(AttachSpec.boardNo(boardNo));

        return fileDtos.stream()
                .map(item -> entityToDto(item, false)).collect(Collectors.toList());
    }

    /**
     * Get file attach response dto.
     *
     * @param attachNo the attach no
     * @param isImage  the is image
     * @return the attach response dto
     */
    @Transactional(readOnly = true)
    public AttachResponseDto getFile(Long attachNo, boolean isImage){
        AttachEntity attachEntity = attachRepository.findById(attachNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 파일이 존재하지 않습니다. attachNo = " + attachNo));

        return entityToDto(attachEntity, isImage);
    }

    /**
     * Entity to dto attach response dto.
     *
     * @param entity  the entity
     * @param isImage the is image
     * @return the attach response dto
     */
    public AttachResponseDto entityToDto(AttachEntity entity, boolean isImage){
        String url;

        if(isImage){
            url = "imageView";
        } else{
            url = "download";
        }

        return AttachResponseDto.builder()
                .attachNo(entity.getAttachNo())
                .originName(entity.getOriginName())
                .ext(entity.getExt())
                .uuid(entity.getUuid())
                .path(entity.getPath())
                .url(request.getScheme()
                        + "://" + request.getServerName()
                        + ":" + request.getServerPort()
                        + "/upload/" + url + "?fileNo=" + entity.getAttachNo())
                .build();

    }

    /**
     * Delete all long.
     *
     * @param approvalNo the approval no
     * @return the long
     */
    @Transactional
    public Long deleteAllApprovalNo(Long approvalNo) throws IOException {
        Long result = 0L;

        List<AttachEntity> fileDtos = attachRepository.findAll(AttachSpec.approvalNo(approvalNo));

        return fileDeleteAll(result, fileDtos);
    }

    @Transactional
    public Long deleteAllBoardNo(Long boardNo) throws IOException {
        Long result = 0L;

        List<AttachEntity> fileDtos = attachRepository.findAll(AttachSpec.boardNo(boardNo));

        return fileDeleteAll(result, fileDtos);
    }

    private Long fileDeleteAll(Long result, List<AttachEntity> fileDtos) throws IOException {
        for(AttachEntity a : fileDtos){
            String path = a.getPath();

            Path resourcePath = Paths.get(projectPath + path);
            Resource resource = new PathResource(resourcePath);

            // 파일 DB삭제
            attachRepository.deleteById(a.getAttachNo());

            // 실제 파일 삭제
            if(resource.exists()){
                File file = resource.getFile();

                boolean isDelete = file.delete();

                if(!isDelete) {
                    throw new RuntimeException("파일 삭제에 실패했습니다.");
                } else {
                    result++;
                }
            } else {
                throw new FileNotFoundException("파일을 찾을 수 없습니다!");
            }

        }

        return result;
    }


    @Transactional
    public void fileListSave(List<MultipartFile> fileList, Long boardNo) {
        // 파일 저장 시작

//        try{

            for (MultipartFile multipartFile : fileList) {
                AttachDto dto = new AttachDto(multipartFile);
                dto.setBoardNo(boardNo);
                try {
                    saveFile(multipartFile, dto);
                } catch (IOException e){
                    e.printStackTrace();
                    throw new IllegalStateException("파일 저장 중 문제가 발생하였습니다.");
                }
                // 파일 정보 저장

                AttachEntity attachEntity = dto.toEntity();
                attachRepository.save(attachEntity);

            }
        //        }
//        catch (IOException e){
//            throw new IllegalStateException("파일 저장 중 문제가 발생하였습니다.");
//        }
    }

}
