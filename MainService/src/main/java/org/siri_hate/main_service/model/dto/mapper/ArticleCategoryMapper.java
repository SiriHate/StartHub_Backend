package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.category.ArticleCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleCategoryMapper {

    ArticleCategoryMapper INSTANCE = Mappers.getMapper(ArticleCategoryMapper.class);

    ArticleCategory toArticleCategory(ArticleCategoryRequest articleCategoryRequest);

    ArticleCategoryFullResponse toArticleCategoryFullResponse(ArticleCategory articleCategory);

    List<ArticleCategorySummaryResponse> toArticleCategorySummaryResponseList(
            List<ArticleCategory> articleCategories);

    ArticleCategory updateArticleCategoryFromRequest(
            ArticleCategoryRequest request, @MappingTarget ArticleCategory articleCategory);
}
