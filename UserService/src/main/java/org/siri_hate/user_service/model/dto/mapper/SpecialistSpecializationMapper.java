package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.model.entity.SpecialistSpecialization;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialistSpecializationMapper {

    SpecialistSpecializationMapper INSTANCE = Mappers.getMapper(SpecialistSpecializationMapper.class);

    SpecialistSpecialization toSpecialistSpecialization(SpecialistSpecializationRequest request);

    SpecialistSpecializationSummaryResponse toSpecialistSpecializationSummaryResponse(
            SpecialistSpecialization specialization
                                                                                     );

    List<SpecialistSpecializationSummaryResponse> toSpecialistSpecializationSummaryResponseList(
            List<SpecialistSpecialization> specializations
                                                                                               );

    SpecialistSpecializationFullResponse toSpecialistSpecializationFullResponse(
            SpecialistSpecialization specialization
                                                                               );


}
