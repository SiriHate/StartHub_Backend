package org.siri_hate.main_service.model.entity.category;

import jakarta.persistence.*;

/**
 * This class represents the ArticleCategory entity.
 * It includes the id and name of the article category.
 * It is mapped to the "article_categories" table in the database.
 */
@Entity
@Table(name = "article_categories")
public class ArticleCategory {

    /**
     * The id of the article category.
     * It is the primary key in the "article_categories" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the article category.
     * It is a unique field in the "article_categories" table.
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * Default constructor.
     */
    public ArticleCategory() { }

    /**
     * Constructor with all fields.
     *
     * @param id   the id of the article category
     * @param name the name of the article category
     */
    public ArticleCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id of the article category
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
     * @return the name of the article category
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
     * Compares this ArticleCategory to the specified object.
     * The result is true if and only if the argument is not null and is an ArticleCategory object that has the same id and name as this object.
     *
     * @param o the object to compare this ArticleCategory against
     * @return true if the given object represents an ArticleCategory equivalent to this ArticleCategory, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleCategory articleCategory = (ArticleCategory) o;
        return id.equals(articleCategory.id) && name.equals(articleCategory.name);
    }

    /**
     * Returns a hash code for this ArticleCategory.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return 0;
    }

}