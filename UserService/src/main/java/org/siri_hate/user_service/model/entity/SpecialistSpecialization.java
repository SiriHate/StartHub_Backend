package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;

/**
 * Entity for SpecialistSpecialization.
 * This class is used to map the specialist_specializations table in the database.
 * It provides fields and methods specific to a SpecialistSpecialization.
 */
@Entity
@Table(name = "specialist_specializations")
public class SpecialistSpecialization {

    /**
     * The ID of the specialist specialization.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the specialist specialization.
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * Default constructor.
     */
    public SpecialistSpecialization() { }

    /**
     * Constructor with parameters.
     *
     * @param id The ID of the specialist specialization.
     * @param name The name of the specialist specialization.
     */
    public SpecialistSpecialization(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the specialist specialization.
     *
     * @return The ID of the specialist specialization.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the specialist specialization.
     *
     * @param id The ID of the specialist specialization.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the specialist specialization.
     *
     * @return The name of the specialist specialization.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the specialist specialization.
     *
     * @param name The name of the specialist specialization.
     */
    public void setName(String name) {
        this.name = name;
    }

}