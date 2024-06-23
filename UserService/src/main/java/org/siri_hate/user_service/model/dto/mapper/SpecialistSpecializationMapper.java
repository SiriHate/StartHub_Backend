package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.specialist_specialization.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.model.entity.SpecialistSpecialization;

import java.util.List;

/**
 * Mapper interface for SpecialistSpecialization entity and its corresponding DTOs.
 * This interface is responsible for mapping between SpecialistSpecialization entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface SpecialistSpecializationMapper {

    /**
     * Instance of the SpecialistSpecializationMapper.
     */
    SpecialistSpecializationMapper INSTANCE = Mappers.getMapper(SpecialistSpecializationMapper.class);

    /**
     * Maps from SpecialistSpecializationRequest DTO to SpecialistSpecialization entity.
     *
     * @param request The SpecialistSpecializationRequest DTO to be mapped.
     * @return The mapped SpecialistSpecialization entity.
     */
    SpecialistSpecialization toSpecialistSpecialization(SpecialistSpecializationRequest request);

    /**
     * Maps from SpecialistSpecialization entity to SpecialistSpecializationSummaryResponse DTO.
     *
     * @param specialization The SpecialistSpecialization entity to be mapped.
     * @return The mapped SpecialistSpecializationSummaryResponse DTO.
     */
    SpecialistSpecializationSummaryResponse toSpecialistSpecializationSummaryResponse(
            SpecialistSpecialization specialization
                                                                                     );

    /**
     * Maps from a list of SpecialistSpecialization entities to a list of SpecialistSpecializationSummaryResponse DTOs.
     *
     * @param specializations The list of SpecialistSpecialization entities to be mapped.
     * @return The list of mapped SpecialistSpecializationSummaryResponse DTOs.
     */
    List<SpecialistSpecializationSummaryResponse> toSpecialistSpecializationSummaryResponseList(
            List<SpecialistSpecialization> specializations
                                                                                               );

    /**
     * Maps from SpecialistSpecialization entity to SpecialistSpecializationFullResponse DTO.
     *
     * @param specialization The SpecialistSpecialization entity to be mapped.
     * @return The mapped SpecialistSpecializationFullResponse DTO.
     */
    SpecialistSpecializationFullResponse toSpecialistSpecializationFullResponse(
            SpecialistSpecialization specialization
                                                                               );

}