package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.SpecialistSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the SpecialistSpecialization entity.
 * This interface is used to perform CRUD operations on the SpecialistSpecialization entity in the database.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), etc.
 */
@Repository
public interface SpecialistSpecializationRepository extends JpaRepository<SpecialistSpecialization, Long> {

    /**
     * Method to find a SpecialistSpecialization entity by its name.
     *
     * @param name The name of the SpecialistSpecialization entity to be found.
     * @return The SpecialistSpecialization entity with the given name.
     */
    SpecialistSpecialization findSpecialistSpecializationsByName(String name);

}