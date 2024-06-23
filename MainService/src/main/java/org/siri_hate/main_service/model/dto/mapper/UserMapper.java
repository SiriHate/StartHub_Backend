package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.response.user.UserFullResponse;
import org.siri_hate.main_service.model.entity.User;

/**
 * This interface represents a mapper for User.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * An instance of the UserMapper.
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Maps from User to UserFullResponse.
     *
     * @param user the User object
     * @return the mapped UserFullResponse object
     */
    UserFullResponse toUserFullResponse(User user);

}