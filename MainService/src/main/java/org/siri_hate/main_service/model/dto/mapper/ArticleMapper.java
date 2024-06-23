package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This interface represents a mapper for Article.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {

    /**
     * An instance of the ArticleMapper.
     */
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    /**
     * Maps from ArticleFullRequest to Article.
     *
     * @param article the ArticleFullRequest object
     * @return the mapped Article object
     */
    Article toArticle(ArticleFullRequest article);

    /**
     * Maps from Article to ArticleFullResponse.
     * The category name is mapped to the category field in the response.
     *
     * @param article the Article object
     * @return the mapped ArticleFullResponse object
     */
    @Mapping(source = "category.name", target = "category")
    ArticleFullResponse toArticleFullResponse(Article article);

    /**
     * Maps from Article to ArticleSummaryResponse.
     * The category name is mapped to the category field in the response.
     *
     * @param article the Article object
     * @return the mapped ArticleSummaryResponse object
     */
    @Mapping(source = "category.name", target = "category")
    ArticleSummaryResponse toArticleSummaryResponse(Article article);

    /**
     * Maps from a list of Article to a list of ArticleSummaryResponse.
     *
     * @param articles the list of Article objects
     * @return the mapped list of ArticleSummaryResponse objects
     */
    List<ArticleSummaryResponse> toArticleSummaryResponseList(List<Article> articles);

    /**
     * Updates an existing Article object from an ArticleFullRequest object.
     *
     * @param articleFullRequest the ArticleFullRequest object
     * @param article            the Article object to be updated
     * @return the updated Article object
     */
    Article articleUpdate(ArticleFullRequest articleFullRequest, @MappingTarget Article article);

    /**
     * Converts a Page of Article objects to a Page of ArticleSummaryResponse objects.
     *
     * @param articles the Page of Article objects
     * @return the converted Page of ArticleSummaryResponse objects
     */
    default Page<ArticleSummaryResponse> toArticleSummaryResponsePage(Page<Article> articles) {
        List<ArticleSummaryResponse> summaryResponses = articles.stream()
                .map(this::toArticleSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, articles.getPageable(), articles.getTotalElements());
    }

    /**
     * Converts a Set of Article objects to a Page of ArticleSummaryResponse objects.
     *
     * @param articles the Set of Article objects
     * @param pageable the Pageable object
     * @return the converted Page of ArticleSummaryResponse objects
     */
    default Page<ArticleSummaryResponse> toArticleSummaryResponsePage(Set<Article> articles, Pageable pageable) {
        List<ArticleSummaryResponse> summaryResponses = articles.stream()
                .map(this::toArticleSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, pageable, articles.size());
    }

}