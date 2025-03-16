package com.example.formapi.dto.content;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContentDateDto {
    private Long id;
    private LocalDate value;
}
