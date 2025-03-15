package com.example.formapi.repository.client;

import com.example.formapi.domain.client.ContentNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentNumberRepository extends JpaRepository<ContentNumber, Long> {
}
