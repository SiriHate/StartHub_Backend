package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This interface represents a mapper for News.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface NewsMapper {

    /**
     * An instance of the NewsMapper.
     */
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    /**
     * Maps from NewsFullRequest to News.
     *
     * @param news the NewsFullRequest object
     * @return the mapped News object
     */
    News toNews(NewsFullRequest news);

    /**
     * Maps from News to NewsSummaryResponse.
     * The category name is mapped to the category field in the response.
     *
     * @param news the News object
     * @return the mapped NewsSummaryResponse object
     */
    @Mapping(source = "category.name", target = "category")
    NewsSummaryResponse toNewsSummaryResponse(News news);

    /**
     * Maps from News to NewsFullResponse.
     * The category name is mapped to the category field in the response.
     *
     * @param news the News object
     * @return the mapped NewsFullResponse object
     */
    @Mapping(source = "category.name", target = "category")
    NewsFullResponse toNewsFullResponse(News news);

    /**
     * Maps from a list of News to a list of NewsSummaryResponse.
     *
     * @param news the list of News objects
     * @return the mapped list of NewsSummaryResponse objects
     */
    List<NewsSummaryResponse> toNewsSummaryResponseList(List<News> news);

    /**
     * Updates an existing News object from a NewsFullRequest object.
     *
     * @param newsFullRequest the NewsFullRequest object
     * @param news            the News object to be updated
     * @return the updated News object
     */
    News newsUpdate(NewsFullRequest newsFullRequest, @MappingTarget News news);

    /**
     * Converts a Page of News objects to a Page of NewsSummaryResponse objects.
     *
     * @param newsPage the Page of News objects
     * @return the converted Page of NewsSummaryResponse objects
     */
    default Page<NewsSummaryResponse> toNewsSummaryResponsePage(Page<News> newsPage) {
        List<NewsSummaryResponse> summaryResponses = newsPage.stream()
                .map(this::toNewsSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, newsPage.getPageable(), newsPage.getTotalElements());
    }

    /**
     * Converts a Set of News objects to a Page of NewsSummaryResponse objects.
     *
     * @param newsSet  the Set of News objects
     * @param pageable the Pageable object
     * @return the converted Page of NewsSummaryResponse objects
     */
    default Page<NewsSummaryResponse> toNewsSummaryResponsePage(Set<News> newsSet, Pageable pageable) {
        List<NewsSummaryResponse> summaryResponses = newsSet.stream()
                .map(this::toNewsSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, pageable, newsSet.size());
    }

}