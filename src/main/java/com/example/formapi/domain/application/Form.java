package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "FORM")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate // works??
    private Date startedOn;

    @LastModifiedDate
    private Date finishedOn;

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;
}
