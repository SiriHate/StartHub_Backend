package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.Admin;
import java.util.List;

public interface AdminService {

    Admin createAdmin(Admin admin);

    List<Admin> getAllAdmins();

    Admin getAdminById(Long id);

    Admin updateAdminById(Long id, Admin admin);

    void deleteAdminById(Long id);

}
