package com.anderson.pruebaai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String review;

    @ManyToOne
    @JoinColumn(nullable = false,name = "id_author")
    private Author author;

    @Column(nullable = false,length = 500)
    private String urlCover;

    @Column(nullable = false)
    private Boolean enabled;
}
