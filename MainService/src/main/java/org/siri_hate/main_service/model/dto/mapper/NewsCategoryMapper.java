package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.entity.category.NewsCategory;
import org.siri_hate.main_service.model.dto.request.category.NewsCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.NewsCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.NewsCategorySummaryResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsCategoryMapper {

    NewsCategoryMapper INSTANCE = Mappers.getMapper(NewsCategoryMapper.class);

    NewsCategory toNewsCategory(NewsCategoryRequest newsCategoryRequest);

    NewsCategoryFullResponse toNewsCategoryFullResponse(NewsCategory newsCategory);

    List<NewsCategorySummaryResponse> toNewsCategorySummaryResponseList(List<NewsCategory> newsCategories);

    NewsCategory updateNewsCategoryFromRequest(
            NewsCategoryRequest request,
            @MappingTarget NewsCategory newsCategory
                                              );

}
