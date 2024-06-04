package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    News toNews(NewsFullRequest news);

    NewsSummaryResponse toNewsSummaryResponse(News news);

    NewsFullResponse toNewsFullResponse(News news);

    List<NewsSummaryResponse> toNewsSummaryResponseList(List<News> news);

    News newsUpdate(NewsFullRequest newsFullRequest, @MappingTarget News news);

    default Page<NewsSummaryResponse> toNewsSummaryResponsePage(Page<News> newsPage) {
        List<NewsSummaryResponse> summaryResponses = newsPage.stream()
                .map(this::toNewsSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, newsPage.getPageable(), newsPage.getTotalElements());
    }

}
