package com.example.formapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "template")
    List<Form> forms;
}
