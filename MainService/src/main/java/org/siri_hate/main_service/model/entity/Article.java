package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    String title;

    @Column(name = "owner")
    String owner;

    @Column(name = "preview_url")
    String previewUrl;

    @Column(name = "category")
    String category;

    @Lob
    @Column(name = "content")
    String content;

    @JsonIgnore
    @Column(name = "publication_date")
    LocalDate publicationDate = LocalDate.now();

}
