package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchArticleFoundException;
import org.siri_hate.main_service.exception.NoSuchNewsFoundException;
import org.siri_hate.main_service.exception.NoSuchProjectFoundException;
import org.siri_hate.main_service.exception.NoSuchUserException;
import org.siri_hate.main_service.model.dto.mapper.ArticleMapper;
import org.siri_hate.main_service.model.dto.mapper.NewsMapper;
import org.siri_hate.main_service.model.dto.mapper.ProjectMapper;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.model.entity.User;
import org.siri_hate.main_service.repository.UserRepository;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    final private ArticleMapper articleMapper;

    final private NewsMapper newsMapper;

    final private ProjectMapper projectMapper;


    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ArticleMapper articleMapper,
            NewsMapper newsMapper,
            ProjectMapper projectMapper
                          ) {
        this.userRepository = userRepository;
        this.articleMapper = articleMapper;
        this.newsMapper = newsMapper;
        this.projectMapper = projectMapper;
    }


    @Override
    public User findOrCreateUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    return userRepository.save(newUser);
                });
    }


    @Override
    @Transactional
    public Page<ArticleSummaryResponse> getMyArticles(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));

        Set<Article> articles = user.getArticles();

        if (articles.isEmpty()) {
            throw new NoSuchArticleFoundException("No articles found for user: " + username);
        }

        return articleMapper.toArticleSummaryResponsePage(articles, pageable);
    }


    @Override
    @Transactional
    public Page<NewsSummaryResponse> getMyNews(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));

        Set<News> news = user.getNews();

        if (news.isEmpty()) {
            throw new NoSuchNewsFoundException("No news found for user: " + username);
        }

        return newsMapper.toNewsSummaryResponsePage(news, pageable);
    }


    @Override
    @Transactional
    public Page<ProjectSummaryResponse> getProjectsAsOwner(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));


        Set<Project> projects = user.getOwnedProjects();

        if (projects.isEmpty()) {
            throw new NoSuchProjectFoundException("No projects found for user: " + username);
        }

        return projectMapper.toProjectSummaryResponsePage(projects, pageable);
    }


    @Override
    @Transactional
    public Page<ProjectSummaryResponse> getProjectsAsMember(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));


        Set<Project> projects = user.getMemberProjects();

        if (projects.isEmpty()) {
            throw new NoSuchProjectFoundException("No projects found for user: " + username);
        }

        return projectMapper.toProjectSummaryResponsePage(projects, pageable);
    }

}