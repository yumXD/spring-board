package com.board.controller;

import com.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/images/{uuidFileName}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String uuidFileName) throws IOException {

        // 파일을 Resource로 읽어오는 코드
        Resource resource = fileService.loadImageAsResource(uuidFileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
