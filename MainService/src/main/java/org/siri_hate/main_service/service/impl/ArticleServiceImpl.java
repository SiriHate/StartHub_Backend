package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.repository.ArticleRepository;
import org.siri_hate.main_service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public void createArticle(String username, Article article) {
        article.setOwner(username);
        articleRepository.save(article);
    }

    @Override
    @Transactional
    public Article getArticleById(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article not found");
        }

        return article.get();
    }

    @Override
    public List<Article> getArticlesByUsername(String username) {

        List<Article> articleList = articleRepository.findAll();

        if (articleList.isEmpty()) {
            throw new NoSuchElementException("No articles found for username " + username);
        }

        return articleList;
    }

    @Override
    public List<Article> getAllArticles() {

        List<Article> articleList = articleRepository.findAll();

        if (articleList.isEmpty()) {
            throw new NoSuchElementException("No articles found");
        }

        return articleList;
    }

    @Override
    public List<Article> searchArticlesByOwnerUsername(String username) {

        List<Article> articles = articleRepository.findArticleByOwner(username);

        if (articles.isEmpty()) {
            throw new NoSuchElementException("No articles found for username " + username);
        }

        return articles;
    }

    @Override
    @Transactional
    public void updateArticle(Long id, Article articleDetails) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article with id = " + id + "not found");
        }

        article.get().setId(id);

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
