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

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article toArticle(ArticleFullRequest article);

    @Mapping(source = "category.name", target = "category")
    ArticleFullResponse toArticleFullResponse(Article article);

    @Mapping(source = "category.name", target = "category")
    ArticleSummaryResponse toArticleSummaryResponse(Article article);

    List<ArticleSummaryResponse> toArticleSummaryResponseList(List<Article> articles);

    Article articleUpdate(ArticleFullRequest articleFullRequest, @MappingTarget Article article);

    default Page<ArticleSummaryResponse> toArticleSummaryResponsePage(Page<Article> articles) {
        List<ArticleSummaryResponse> summaryResponses = articles.stream()
                .map(this::toArticleSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, articles.getPageable(), articles.getTotalElements());
    }

    default Page<ArticleSummaryResponse> toArticleSummaryResponsePage(Set<Article> articles, Pageable pageable) {
        List<ArticleSummaryResponse> summaryResponses = articles.stream()
                .map(this::toArticleSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, pageable, articles.size());
    }

}
