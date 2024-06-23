package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.admin.AdminFullRequest;
import org.siri_hate.user_service.model.dto.response.admin.AdminFullResponse;
import org.siri_hate.user_service.model.dto.response.admin.AdminSummaryResponse;
import org.siri_hate.user_service.model.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Admin service interface.
 * This interface defines the contract for admin operations.
 * It provides methods for creating, retrieving, updating, and deleting admins.
 */
public interface AdminService {

    /**
     * Creates an admin.
     * This method takes an AdminFullRequest DTO, maps it to an Admin entity, and saves it to the database.
     *
     * @param admin the admin full request DTO
     * @return the admin full response DTO
     */
    AdminFullResponse createAdmin(AdminFullRequest admin);

    /**
     * Retrieves all admins.
     * This method retrieves all admins from the database and maps them to AdminSummaryResponse DTOs.
     *
     * @param pageable the pagination information
     * @return a page of admin summary response DTOs
     */
    Page<AdminSummaryResponse> getAllAdmins(Pageable pageable);

    /**
     * Retrieves an admin by ID.
     * This method retrieves an admin from the database by ID and maps it to an AdminFullResponse DTO.
     *
     * @param id the ID of the admin
     * @return the admin full response DTO
     */
    AdminFullResponse getAdminById(Long id);

    /**
     * Updates an admin by ID.
     * This method retrieves an admin from the database by ID, updates the admin with the information from the AdminFullRequest DTO, and saves the updated admin to the database.
     *
     * @param id the ID of the admin
     * @param admin the admin full request DTO
     * @return the admin full response DTO
     */
    AdminFullResponse updateAdminById(Long id, AdminFullRequest admin);

    /**
     * Deletes an admin by ID.
     * This method retrieves an admin from the database by ID and deletes it.
     *
     * @param id the ID of the admin
     */
    void deleteAdminById(Long id);

}