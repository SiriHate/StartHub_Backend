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


@Service
public class AdminServiceImpl implements AdminService {

    // Admin repository instance
    final private AdminRepository adminRepository;

    // Admin mapper instance
    final private AdminMapper adminMapper;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }


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


    @Override
    public Page<AdminSummaryResponse> getAllAdmins(Pageable pageable) {

        Page<Admin> admins = adminRepository.findAll(pageable);

        if (admins.isEmpty()) {
            throw new NoSuchUserException("No admins found!");
        }

        return adminMapper.toAdminSummaryResponsePage(admins);
    }


    @Override
    public AdminFullResponse getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with id: " + id + " not found!"));

        return adminMapper.toAdminFullResponse(admin);
    }


    @Override
    @Transactional
    public AdminFullResponse updateAdminById(Long id, AdminFullRequest admin) {

        Admin currentAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with id: " + id + " not found!"));

        Admin updatedAdmin = adminMapper.adminUpdate(admin, currentAdmin);
        adminRepository.save(updatedAdmin);
        return adminMapper.toAdminFullResponse(updatedAdmin);
    }


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