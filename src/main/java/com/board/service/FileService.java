package com.board.service;

import com.board.entity.File;
import com.board.entity.User;
import com.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public Long saveFile(MultipartFile files) throws IOException {
        if (files.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        // 파일 엔티티 생성
        File file = File.builder()
                .orgNm(origName)
                .savedNm(savedName)
                .savedPath(savedPath)
                .build();

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new java.io.File(savedPath));

        // 데이터베이스에 파일 정보 저장
        File savedFile = fileRepository.save(file);

        return savedFile.getId();
    }

    @Transactional
    public Long uploadUserImg(MultipartFile files, User user) throws IOException {
        if (files.isEmpty()) {
            return null;
        }

        // 기본 프로필 사진 삭제
        File findUserImg = fileRepository.findByUserId(user.getId());
        if (findUserImg != null) {
            fileRepository.delete(findUserImg);
            fileRepository.flush();
        }

        // 원래 파일 이름 추출
        String originalFileName = StringUtils.cleanPath(files.getOriginalFilename());

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String uuidFileName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + uuidFileName;

        // 파일 엔티티 생성
        File file = File.builder()
                .orgNm(originalFileName)
                .savedNm(uuidFileName)
                .savedPath(savedPath)
                .user(user)
                .build();

        // 실제로 로컬에 uuid를 파일명으로 저장
        Path path = Paths.get(savedPath);
        Files.write(path, files.getBytes());

        // 데이터베이스에 파일 정보 저장
        File savedFile = fileRepository.save(file);

        return savedFile.getId();
    }

    public Resource loadImageAsResource(String uuidFileName) throws IOException {
        String filePath = fileDir + uuidFileName;
        Path path = Paths.get(filePath);
        Resource resource = new FileSystemResource(path.toFile());

        return resource;
    }
}
