package org.siri_hate.main_service.model.entity.category;

import jakarta.persistence.*;

/**
 * This class represents the ProjectCategory entity.
 * It includes the id and name of the project category.
 * It is mapped to the "project_categories" table in the database.
 */
@Entity
@Table(name = "project_categories")
public class ProjectCategory {

    /**
     * The id of the project category.
     * It is the primary key in the "project_categories" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the project category.
     * It is a unique field in the "project_categories" table.
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * Default constructor.
     */
    public ProjectCategory() { }

    /**
     * Constructor with all fields.
     *
     * @param id   the id of the project category
     * @param name the name of the project category
     */
    public ProjectCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id of the project category
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
     * @return the name of the project category
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
     * Compares this ProjectCategory to the specified object.
     * The result is true if and only if the argument is not null and is a ProjectCategory object that has the same id and name as this object.
     *
     * @param o the object to compare this ProjectCategory against
     * @return true if the given object represents a ProjectCategory equivalent to this ProjectCategory, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCategory projectCategory = (ProjectCategory) o;
        return id.equals(projectCategory.id) && name.equals(projectCategory.name);
    }

    /**
     * Returns a hash code for this ProjectCategory.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return 0;
    }

}