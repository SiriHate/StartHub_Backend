package org.siri_hate.user_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.entity.Admin;
import org.siri_hate.user_service.repository.AdminRepository;
import org.siri_hate.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    final private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    @Transactional
    public Admin createAdmin(Admin admin) {

        String username = admin.getUsername();
        Optional<Admin> adminOptional = adminRepository.findAdminByUsername(username);

        if (adminOptional.isEmpty()) {
            throw new UserAlreadyExistsException("Admin with provided username already exists!");
        }

        adminRepository.save(adminOptional.get());
        return adminOptional.get();
    }

    @Override
    public List<Admin> getAllAdmins() {

        List<Admin> adminList = adminRepository.findAll();

        if (adminList.isEmpty()) {
            throw new NoSuchUserException("No admins found!");
        }

        return adminList;
    }

    @Override
    public Admin getAdminById(Long id) {

        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isEmpty()) {
            throw new NoSuchUserException("No admin with provided id exists!");
        }

        return adminOptional.get();
    }

    @Override
    @Transactional
    public Admin updateAdminById(Long id, Admin admin) {

        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isEmpty()) {
            throw new NoSuchUserException("No admin with provided id exists!");
        }

        admin.setId(id);
        adminRepository.save(admin);
        return adminOptional.get();
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
