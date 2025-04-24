package com.example.formapi.repository.client;

import com.example.formapi.domain.client.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {
}
