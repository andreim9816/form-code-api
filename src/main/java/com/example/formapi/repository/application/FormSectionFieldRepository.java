package com.example.formapi.repository.application;

import com.example.formapi.domain.application.FormSectionField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSectionFieldRepository extends JpaRepository<FormSectionField, Long> {
}
