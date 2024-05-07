package org.siri_hate.user_service.controller;

import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.entity.Admin;
import org.siri_hate.user_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/users/admin")
public class AdminController {

    final private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Validated @RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@Positive @PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    ResponseEntity<Admin> updateAdminById(@PathVariable Long id, @Validated @RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdminById(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAdminById(@Positive @PathVariable Long id) {
        adminService.deleteAdminById(id);
        return new ResponseEntity<>("Administrator has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
