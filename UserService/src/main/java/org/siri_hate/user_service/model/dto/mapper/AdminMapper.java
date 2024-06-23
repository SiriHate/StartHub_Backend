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

/**
 * Mapper interface for Admin entity and its corresponding DTOs.
 * This interface is responsible for mapping between Admin entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

    /**
     * Instance of the AdminMapper.
     */
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    /**
     * Maps from AdminFullRequest DTO to Admin entity.
     *
     * @param admin The AdminFullRequest DTO to be mapped.
     * @return The mapped Admin entity.
     */
    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    Admin toAdmin(AdminFullRequest admin);

    /**
     * Maps from Admin entity to AdminFullResponse DTO.
     *
     * @param admin The Admin entity to be mapped.
     * @return The mapped AdminFullResponse DTO.
     */
    AdminFullResponse toAdminFullResponse(Admin admin);

    /**
     * Maps from Admin entity to AdminSummaryResponse DTO.
     *
     * @param admin The Admin entity to be mapped.
     * @return The mapped AdminSummaryResponse DTO.
     */
    AdminSummaryResponse toAdminSummaryResponse(Admin admin);

    /**
     * Maps from a list of Admin entities to a list of AdminSummaryResponse DTOs.
     *
     * @param admins The list of Admin entities to be mapped.
     * @return The list of mapped AdminSummaryResponse DTOs.
     */
    List<AdminSummaryResponse> toAdminSummaryResponseList(List<Admin> admins);

    /**
     * Updates an Admin entity with the data from an AdminFullRequest DTO.
     *
     * @param request The AdminFullRequest DTO with the data for the update.
     * @param admin The Admin entity to be updated.
     * @return The updated Admin entity.
     */
    Admin adminUpdate(AdminFullRequest request, @MappingTarget Admin admin);

    /**
     * Maps from a Page of Admin entities to a Page of AdminSummaryResponse DTOs.
     *
     * @param admins The Page of Admin entities to be mapped.
     * @return The Page of mapped AdminSummaryResponse DTOs.
     */
    default Page<AdminSummaryResponse> toAdminSummaryResponsePage(Page<Admin> admins) {
        List<AdminSummaryResponse> summaryResponses = toAdminSummaryResponseList(admins.getContent());
        return new PageImpl<>(summaryResponses, admins.getPageable(), admins.getTotalElements());
    }

}
