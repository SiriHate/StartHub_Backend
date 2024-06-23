package org.siri_hate.main_service.model.entity.category;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * This class represents the NewsCategory entity.
 * It includes the id and name of the news category.
 * It is mapped to the "news_categories" table in the database.
 */
@Entity
@Table(name = "news_categories")
public class NewsCategory {

    /**
     * The id of the news category.
     * It is the primary key in the "news_categories" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the news category.
     * It is a unique field in the "news_categories" table.
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * Default constructor.
     */
    public NewsCategory() { }

    /**
     * Constructor with all fields.
     *
     * @param id   the id of the news category
     * @param name the name of the news category
     */
    public NewsCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id of the news category
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name of the news category
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this NewsCategory to the specified object.
     * The result is true if and only if the argument is not null and is a NewsCategory object that has the same id and name as this object.
     *
     * @param o the object to compare this NewsCategory against
     * @return true if the given object represents a NewsCategory equivalent to this NewsCategory, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCategory newsCategory = (NewsCategory) o;
        return Objects.equals(id, newsCategory.id) && Objects.equals(name, newsCategory.name);
    }

    /**
     * Returns a hash code for this NewsCategory.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}