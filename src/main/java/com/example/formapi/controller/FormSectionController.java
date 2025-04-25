package com.example.formapi.controller;

import com.example.formapi.domain.client.ContentFile;
import com.example.formapi.dto.FormSectionDto;
import com.example.formapi.dto.content.ContentFileReqDto;
import com.example.formapi.dto.content.ContentFilesDto;
import com.example.formapi.dto.input.ReqFormDto;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.mapper.FormSectionMapper;
import com.example.formapi.repository.client.ContentFileRepository;
import com.example.formapi.service.FormSectionFieldService;
import com.example.formapi.service.FormSectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forms/sections")
public class FormSectionController {

    private final FormSectionFieldService formSectionFieldService;
    private final FormSectionService formSectionService;
    private final FormSectionMapper formSectionMapper;
    private final ContentFileRepository contentFileRepository;

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        return contentFileRepository.findById(id)
                .map(file -> ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(file.getContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                        .body(file.getValue()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateFiles(@ModelAttribute ContentFilesDto contentFilesDto) throws IOException {
        for (ContentFileReqDto dto : contentFilesDto.getDtos()) {

            ContentFile contentFile = contentFileRepository.findById(dto.getId())
                    .orElseThrow(() -> new InvalidEntityException(dto.getId()));

            if (dto.getIsNullFile() != null && dto.getIsNullFile().equals("true")) {
                contentFile.setValue(null);
                contentFile.setContentType(null);
                contentFile.setName(null);
            } else {
                contentFile.setValue(dto.getContent().getBytes());
                contentFile.setContentType(dto.getContent().getContentType());

                String uuid = UUID.randomUUID().toString();
                String newName = uuid.substring(0, uuid.length() / 2);

                String extension = dto.getContent().getOriginalFilename().substring(dto.getContent().getOriginalFilename().lastIndexOf('.') + 1);
                contentFile.setName(/*newName + "." + extension*/ dto.getContent().getOriginalFilename());
            }

            contentFileRepository.save(contentFile);
        }
    }

    @PatchMapping
    public List<FormSectionDto> updateForm(@RequestBody ReqFormDto formDto) {
        return formSectionService.update(formDto.getFormSections()).stream()
                .map(formSectionMapper::toDto)
                .collect(toList());
    }

    @PatchMapping("/reject")
    public List<FormSectionDto> rejectForm(@RequestBody ReqFormDto formDto) {
        return formSectionService.rejectForm(formDto.getFormSections()).stream()
                .map(formSectionMapper::toDto)
                .collect(toList());
    }
}
