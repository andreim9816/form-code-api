package com.example.formapi.repository.application;

import com.example.formapi.domain.application.FormSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSectionRepository extends JpaRepository<FormSection, Long> {
}
