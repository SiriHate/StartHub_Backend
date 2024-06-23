package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;
import org.siri_hate.main_service.model.dto.request.category.ArticleCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategorySummaryResponse;

import java.util.List;

/**
 * This interface represents a mapper for ArticleCategory.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface ArticleCategoryMapper {

    /**
     * An instance of the ArticleCategoryMapper.
     */
    ArticleCategoryMapper INSTANCE = Mappers.getMapper(ArticleCategoryMapper.class);

    /**
     * Maps from ArticleCategoryRequest to ArticleCategory.
     *
     * @param articleCategoryRequest the ArticleCategoryRequest object
     * @return the mapped ArticleCategory object
     */
    ArticleCategory toArticleCategory(ArticleCategoryRequest articleCategoryRequest);

    /**
     * Maps from ArticleCategory to ArticleCategoryFullResponse.
     *
     * @param articleCategory the ArticleCategory object
     * @return the mapped ArticleCategoryFullResponse object
     */
    ArticleCategoryFullResponse toArticleCategoryFullResponse(ArticleCategory articleCategory);

    /**
     * Maps from a list of ArticleCategory to a list of ArticleCategorySummaryResponse.
     *
     * @param articleCategories the list of ArticleCategory objects
     * @return the mapped list of ArticleCategorySummaryResponse objects
     */
    List<ArticleCategorySummaryResponse> toArticleCategorySummaryResponseList(List<ArticleCategory> articleCategories);

    /**
     * Updates an existing ArticleCategory object from an ArticleCategoryRequest object.
     *
     * @param request         the ArticleCategoryRequest object
     * @param articleCategory the ArticleCategory object to be updated
     * @return the updated ArticleCategory object
     */
    ArticleCategory updateArticleCategoryFromRequest(
            ArticleCategoryRequest request,
            @MappingTarget ArticleCategory articleCategory
                                                    );

}