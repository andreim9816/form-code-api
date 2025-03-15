package com.example.formapi.repository.client;

import com.example.formapi.domain.client.ContentString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentStringRepository extends JpaRepository<ContentString, Long> {
}
