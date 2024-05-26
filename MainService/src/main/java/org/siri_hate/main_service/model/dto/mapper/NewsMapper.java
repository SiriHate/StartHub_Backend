package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    News toNews(NewsFullRequest news);

    NewsSummaryResponse toNewsSummaryResponse(News news);

    NewsFullResponse toNewsFullResponse(News news);

    List<NewsSummaryResponse> toNewsSummaryResponseList(List<News> news);

    News newsUpdate(NewsFullRequest newsFullRequest, @MappingTarget News news);

}
