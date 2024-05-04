package org.siri_hate.main_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_publication")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "publication_owner")
    String publicationOwner;

    @Column(name = "publication_preview")
    Byte publicationPreview;

    

}