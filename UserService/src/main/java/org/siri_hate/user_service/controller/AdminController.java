package org.siri_hate.user_service.controller;

import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.dto.request.admin.AdminFullRequest;
import org.siri_hate.user_service.model.dto.response.admin.AdminFullResponse;
import org.siri_hate.user_service.model.dto.response.admin.AdminSummaryResponse;
import org.siri_hate.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for handling admin-related operations in the API.
 * Provides endpoints for creating, retrieving, updating, and deleting admins.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/admins")
public class AdminController {

    private final AdminService adminService;

    /**
     * Constructs an AdminController with the provided AdminService.
     *
     * @param adminService The service responsible for admin-related operations.
     */
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Endpoint to create a new admin.
     *
     * @param admin The admin details to be created.
     * @return ResponseEntity containing the created admin and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<AdminFullResponse> createAdmin(@Validated @RequestBody AdminFullRequest admin) {
        AdminFullResponse createdAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all admins with pagination support.
     *
     * @param pageable Pagination information to specify page size and number.
     * @return ResponseEntity containing a page of AdminSummaryResponse and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<Page<AdminSummaryResponse>> getAllAdmins(
            @PageableDefault(size = 1) Pageable pageable
                                                                  ) {
        Page<AdminSummaryResponse> admins = adminService.getAllAdmins(pageable);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve an admin by ID.
     *
     * @param id The ID of the admin to retrieve.
     * @return ResponseEntity containing the admin details and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdminFullResponse> getAdminById(@Positive @PathVariable Long id) {
        AdminFullResponse admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing admin by ID.
     *
     * @param id    The ID of the admin to update.
     * @param admin The updated admin details.
     * @return ResponseEntity containing the updated admin and HTTP status OK.
     */
    @PatchMapping("/{id}")
    ResponseEntity<AdminFullResponse> updateAdminById(
            @PathVariable Long id,
            @Validated @RequestBody AdminFullRequest admin
                                                     ) {
        AdminFullResponse updatedAdmin = adminService.updateAdminById(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    /**
     * Endpoint to delete an admin by ID.
     *
     * @param id The ID of the admin to delete.
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAdminById(@Positive @PathVariable Long id) {
        adminService.deleteAdminById(id);
        return new ResponseEntity<>("Administrator has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
