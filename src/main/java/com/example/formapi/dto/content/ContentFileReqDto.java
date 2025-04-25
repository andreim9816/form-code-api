package com.example.formapi.dto.content;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ContentFileReqDto {
    private Long id;
    private MultipartFile content;
    private String isNullFile;
}
