package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.siri_hate.main_service.exception.NoSuchArticleFoundException;
import org.siri_hate.main_service.model.dto.mapper.ArticleMapper;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.repository.ArticleRepository;
import org.siri_hate.main_service.repository.adapters.ArticleSpecification;
import org.siri_hate.main_service.service.ArticleCategoryService;
import org.siri_hate.main_service.service.ArticleService;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;
  private final ArticleCategoryService articleCategoryService;
  private final ArticleMapper articleMapper;
  private final UserService userService;

  @Autowired
  public ArticleServiceImpl(
      ArticleRepository articleRepository,
      ArticleMapper articleMapper,
      ArticleCategoryService articleCategoryService,
      UserService userService) {
    this.articleRepository = articleRepository;
    this.articleMapper = articleMapper;
    this.articleCategoryService = articleCategoryService;
    this.userService = userService;
  }

  @Override
  @Transactional
  public void createArticle(String username, ArticleFullRequest article) {
    Article articleEntity = articleMapper.toArticle(article);
    articleEntity.setUser(userService.findOrCreateUser(username));
    articleEntity.setPublicationDate(LocalDate.now());
    articleEntity.setCategory(
        articleCategoryService.getArticleCategoryEntityById(article.getCategory().getId()));
    articleEntity.setModerationPassed(false);
    articleRepository.save(articleEntity);
  }

  @Override
  @Transactional
  public ArticleFullResponse getArticleById(Long id) {
    Optional<Article> article = articleRepository.findById(id);
    if (article.isEmpty()) {
      throw new NoSuchElementException("Article not found");
    }
    return articleMapper.toArticleFullResponse(article.get());
  }

  @Override
  public Page<ArticleSummaryResponse> getArticlesByCategoryAndSearchQuery(
      String category, String query, Pageable pageable) {
    Specification<Article> spec =
        Specification.where(ArticleSpecification.titleStartsWith(query))
            .and(ArticleSpecification.hasCategory(category));
    Page<Article> articles = articleRepository.findAll(spec, pageable);
    if (articles.isEmpty()) {
      throw new NoSuchArticleFoundException("No articles found");
    }
    return articleMapper.toArticleSummaryResponsePage(articles);
  }

  @Override
  public Page<ArticleSummaryResponse> getAllArticles(Pageable pageable) {
    Page<Article> articlePage = articleRepository.findAll(pageable);
    if (articlePage.isEmpty()) {
      throw new NoSuchElementException("No articles found");
    }
    return articleMapper.toArticleSummaryResponsePage(articlePage);
  }

  @Override
  @Transactional
  public Page<ArticleSummaryResponse> getModeratedArticles(Pageable pageable) {
    Page<Article> articlePage = articleRepository.findByModerationPassedTrue(pageable);
    if (articlePage.isEmpty()) {
      throw new NoSuchElementException("No moderated articles found");
    }
    return articleMapper.toArticleSummaryResponsePage(articlePage);
  }

  @Override
  @Transactional
  public Page<ArticleSummaryResponse> getUnmoderatedArticles(Pageable pageable) {
    Page<Article> articlePage = articleRepository.findByModerationPassedFalse(pageable);
    if (articlePage.isEmpty()) {
      throw new NoSuchElementException("No unmoderated articles found");
    }
    return articleMapper.toArticleSummaryResponsePage(articlePage);
  }

  @Override
  @Transactional
  public void updateArticle(Long id, ArticleFullRequest articleFullRequest) {
    Article existingArticle =
        articleRepository
            .findById(id)
            .orElseThrow(
                () -> new NoSuchElementException("Article with id = " + id + " not found"));

    articleMapper.articleUpdate(articleFullRequest, existingArticle);
    articleRepository.save(existingArticle);
  }

  @Override
  @Transactional
  public void deleteArticle(Long id) {
    Optional<Article> article = articleRepository.findById(id);
    if (article.isEmpty()) {
      throw new NoSuchElementException("Article with id = " + id + "not found");
    }
    articleRepository.delete(article.get());
  }

  @Override
  @Transactional
  public void updateArticleModerationStatus(Long id, Boolean moderationPassed) {
    Article article =
        articleRepository
            .findById(id)
            .orElseThrow(
                () -> new NoSuchElementException("Article with id = " + id + " not found"));
    article.setModerationPassed(moderationPassed);
    articleRepository.save(article);
  }
}
