package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.dto.mapper.AdminMapper;
import org.siri_hate.user_service.model.dto.request.admin.AdminFullRequest;
import org.siri_hate.user_service.model.dto.response.admin.AdminFullResponse;
import org.siri_hate.user_service.model.dto.response.admin.AdminSummaryResponse;
import org.siri_hate.user_service.model.entity.Admin;
import org.siri_hate.user_service.repository.AdminRepository;
import org.siri_hate.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Admin service implementation class.
 * This class implements the AdminService interface and provides the business logic for admin operations.
 * It uses the AdminRepository and AdminMapper to interact with the database and map between DTOs and entities.
 */
@Service
public class AdminServiceImpl implements AdminService {

    // Admin repository instance
    final private AdminRepository adminRepository;

    // Admin mapper instance
    final private AdminMapper adminMapper;

    /**
     * Constructor for the AdminServiceImpl class.
     * This constructor initializes the AdminRepository and AdminMapper.
     *
     * @param adminRepository the admin repository
     * @param adminMapper the admin mapper
     */
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    /**
     * Creates a new admin.
     * This method maps the AdminFullRequest DTO to an Admin entity, checks if an admin with the same username already exists, and saves the new admin to the database.
     *
     * @param admin the admin full request DTO
     * @return the admin full response DTO
     */
    @Override
    @Transactional
    public AdminFullResponse createAdmin(AdminFullRequest admin) {

        Admin adminEntity = adminMapper.toAdmin(admin);

        if (adminRepository.findAdminByUsername(adminEntity.getUsername()) != null) {
            throw new UserAlreadyExistsException("Admin with provided username already exists!");
        }

        adminRepository.save(adminEntity);
        return adminMapper.toAdminFullResponse(adminEntity);
    }

    /**
     * Retrieves all admins.
     * This method retrieves all admins from the database and maps them to AdminSummaryResponse DTOs.
     *
     * @param pageable the pagination information
     * @return a page of admin summary response DTOs
     */
    @Override
    public Page<AdminSummaryResponse> getAllAdmins(Pageable pageable) {

        Page<Admin> admins = adminRepository.findAll(pageable);

        if (admins.isEmpty()) {
            throw new NoSuchUserException("No admins found!");
        }

        return adminMapper.toAdminSummaryResponsePage(admins);
    }

    /**
     * Retrieves an admin by ID.
     * This method retrieves an admin from the database by ID and maps it to an AdminFullResponse DTO.
     *
     * @param id the ID of the admin
     * @return the admin full response DTO
     */
    @Override
    public AdminFullResponse getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with id: " + id + " not found!"));

        return adminMapper.toAdminFullResponse(admin);
    }

    /**
     * Updates an admin by ID.
     * This method retrieves an admin from the database by ID, updates it with the information from the AdminFullRequest DTO, and saves the updated admin to the database.
     *
     * @param id the ID of the admin
     * @param admin the admin full request DTO
     * @return the admin full response DTO
     */
    @Override
    @Transactional
    public AdminFullResponse updateAdminById(Long id, AdminFullRequest admin) {

        Admin currentAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with id: " + id + " not found!"));

        Admin updatedAdmin = adminMapper.adminUpdate(admin, currentAdmin);
        adminRepository.save(updatedAdmin);
        return adminMapper.toAdminFullResponse(updatedAdmin);
    }

    /**
     * Deletes an admin by ID.
     * This method retrieves an admin from the database by ID and deletes it.
     *
     * @param id the ID of the admin
     */
    @Override
    @Transactional
    public void deleteAdminById(Long id) {

        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isEmpty()) {
            throw new NoSuchUserException("No admin with provided id exists!");
        }

        adminRepository.delete(adminOptional.get());
    }

}