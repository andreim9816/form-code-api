package com.example.formapi.repository.client;

import com.example.formapi.domain.client.ContentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDateRepository extends JpaRepository<ContentDate, Long> {
}
