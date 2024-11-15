package ru.hogwarts.school.REST_APP.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Entity
public class Avatar implements Serializable {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String path;

    @OneToOne
    private Student student;

    @Lob
    @Getter
    @Setter
    private byte[] data;
}
