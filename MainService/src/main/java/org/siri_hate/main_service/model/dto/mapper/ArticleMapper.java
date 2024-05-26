package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article toArticle(ArticleFullRequest article);

    ArticleFullResponse toArticleFullResponse(Article article);

    ArticleSummaryResponse toArticleSummaryResponse(Article article);

    List<ArticleSummaryResponse> toArticleSummaryResponseList(List<Article> articles);

    Article articleUpdate(ArticleFullRequest articleFullRequest, @MappingTarget Article article);

}
