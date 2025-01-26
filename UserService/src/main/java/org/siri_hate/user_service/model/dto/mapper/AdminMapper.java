package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.admin.AdminFullRequest;
import org.siri_hate.user_service.model.dto.response.admin.AdminFullResponse;
import org.siri_hate.user_service.model.dto.response.admin.AdminSummaryResponse;
import org.siri_hate.user_service.model.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AdminMapper {


    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);


    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    Admin toAdmin(AdminFullRequest admin);


    AdminFullResponse toAdminFullResponse(Admin admin);


    AdminSummaryResponse toAdminSummaryResponse(Admin admin);


    List<AdminSummaryResponse> toAdminSummaryResponseList(List<Admin> admins);


    Admin adminUpdate(AdminFullRequest request, @MappingTarget Admin admin);


    default Page<AdminSummaryResponse> toAdminSummaryResponsePage(Page<Admin> admins) {
        List<AdminSummaryResponse> summaryResponses = toAdminSummaryResponseList(admins.getContent());
        return new PageImpl<>(summaryResponses, admins.getPageable(), admins.getTotalElements());
    }

}
