package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.entity.category.NewsCategory;
import org.siri_hate.main_service.model.dto.request.category.NewsCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.NewsCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.NewsCategorySummaryResponse;

import java.util.List;

/**
 * This interface represents a mapper for NewsCategory.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface NewsCategoryMapper {

    /**
     * An instance of the NewsCategoryMapper.
     */
    NewsCategoryMapper INSTANCE = Mappers.getMapper(NewsCategoryMapper.class);

    /**
     * Maps from NewsCategoryRequest to NewsCategory.
     *
     * @param newsCategoryRequest the NewsCategoryRequest object
     * @return the mapped NewsCategory object
     */
    NewsCategory toNewsCategory(NewsCategoryRequest newsCategoryRequest);

    /**
     * Maps from NewsCategory to NewsCategoryFullResponse.
     *
     * @param newsCategory the NewsCategory object
     * @return the mapped NewsCategoryFullResponse object
     */
    NewsCategoryFullResponse toNewsCategoryFullResponse(NewsCategory newsCategory);

    /**
     * Maps from a list of NewsCategory to a list of NewsCategorySummaryResponse.
     *
     * @param newsCategories the list of NewsCategory objects
     * @return the mapped list of NewsCategorySummaryResponse objects
     */
    List<NewsCategorySummaryResponse> toNewsCategorySummaryResponseList(List<NewsCategory> newsCategories);

    /**
     * Updates an existing NewsCategory object from a NewsCategoryRequest object.
     *
     * @param request      the NewsCategoryRequest object
     * @param newsCategory the NewsCategory object to be updated
     * @return the updated NewsCategory object
     */
    NewsCategory updateNewsCategoryFromRequest(
            NewsCategoryRequest request,
            @MappingTarget NewsCategory newsCategory
                                              );

}