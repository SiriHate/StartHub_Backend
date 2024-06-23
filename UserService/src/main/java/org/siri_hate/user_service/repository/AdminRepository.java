package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Admin entity.
 * This interface is used to perform CRUD operations on the Admin entity in the database.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), etc.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Method to find an Admin entity by its username.
     *
     * @param username The username of the Admin entity to be found.
     * @return The Admin entity with the given username.
     */
    Admin findAdminByUsername(String username);

}