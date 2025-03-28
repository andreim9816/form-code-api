package com.example.formapi.repository.application;

import com.example.formapi.domain.application.SectionField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionFieldRepository extends JpaRepository<SectionField, Long> {
}
