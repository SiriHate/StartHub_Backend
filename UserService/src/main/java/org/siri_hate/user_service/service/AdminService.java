package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.admin.AdminFullRequest;
import org.siri_hate.user_service.model.dto.response.admin.AdminFullResponse;
import org.siri_hate.user_service.model.dto.response.admin.AdminSummaryResponse;
import org.siri_hate.user_service.model.entity.Admin;
import java.util.List;

public interface AdminService {

    AdminFullResponse createAdmin(AdminFullRequest admin);

    List<AdminSummaryResponse> getAllAdmins();

    AdminFullResponse getAdminById(Long id);

    AdminFullResponse updateAdminById(Long id, AdminFullRequest admin);

    void deleteAdminById(Long id);

}
