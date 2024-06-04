package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.dto.mapper.ArticleMapper;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.repository.ArticleRepository;
import org.siri_hate.main_service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final private ArticleRepository articleRepository;

    final private ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    @Transactional
    public void createArticle(String username, ArticleFullRequest article) {
        Article articleEntity = articleMapper.toArticle(article);
        articleEntity.setOwner(username);
        articleEntity.setPublicationDate(LocalDate.now());
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
    public Page<ArticleSummaryResponse> getArticlesByUsername(String username, Pageable pageable) {

        Page<Article> articlePage = articleRepository.findArticleByOwner(username, pageable);

        if (articlePage.isEmpty()) {
            throw new NoSuchElementException("No articles found for username " + username);
        }

        return articleMapper.toArticleSummaryResponsePage(articlePage);
    }

    @Override
    public Page<ArticleSummaryResponse> getArticlesByTitle(String title, Pageable pageable) {
        return null;
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
    public Page<ArticleSummaryResponse> searchArticlesByOwnerUsername(String username, Pageable pageable) {

        Page<Article> articlePage = articleRepository.findArticleByOwner(username, pageable);

        if (articlePage.isEmpty()) {
            throw new NoSuchElementException("No articles found for username " + username);
        }

        return articleMapper.toArticleSummaryResponsePage(articlePage);
    }

    @Override
    @Transactional
    public void updateArticle(Long id, Article articleDetails) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article with id = " + id + "not found");
        }

        articleRepository.save(article.get());
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

}
