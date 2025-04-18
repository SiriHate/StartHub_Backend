package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchArticleFoundException;
import org.siri_hate.main_service.exception.NoSuchNewsFoundException;
import org.siri_hate.main_service.exception.NoSuchProjectFoundException;
import org.siri_hate.main_service.exception.NoSuchUserException;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.User;
import org.siri_hate.main_service.repository.UserRepository;
import org.siri_hate.main_service.service.ArticleService;
import org.siri_hate.main_service.service.NewsService;
import org.siri_hate.main_service.service.ProjectService;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ArticleService articleService;
  private final NewsService newsService;
  private final ProjectService projectService;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      @Lazy ArticleService articleService,
      NewsService newsService,
      ProjectService projectService) {
    this.userRepository = userRepository;
    this.articleService = articleService;
    this.newsService = newsService;
    this.projectService = projectService;
  }

  @Override
  public User findOrCreateUser(String username) {
    return userRepository
        .findUserByUsername(username)
        .orElseGet(
            () -> {
              User newUser = new User();
              newUser.setUsername(username);
              return userRepository.save(newUser);
            });
  }

  @Override
  @Transactional
  public Page<ArticleSummaryResponse> getMyArticles(String username, Pageable pageable) {
    User user =
        userRepository
            .findUserByUsername(username)
            .orElseThrow(() -> new NoSuchUserException("User not found: " + username));
    Page<ArticleSummaryResponse> articles = articleService.getArticlesByUser(username, pageable);
    if (articles.isEmpty()) {
      throw new NoSuchArticleFoundException("No articles found for user: " + username);
    }
    return articles;
  }

  @Override
  @Transactional
  public Page<NewsSummaryResponse> getMyNews(String username, Pageable pageable) {
    User user =
        userRepository
            .findUserByUsername(username)
            .orElseThrow(() -> new NoSuchUserException("User not found: " + username));
    Page<NewsSummaryResponse> news = newsService.getNewsByUser(username, pageable);
    if (news.isEmpty()) {
      throw new NoSuchNewsFoundException("No news found for user: " + username);
    }
    return news;
  }

  @Override
  @Transactional
  public Page<ProjectSummaryResponse> getProjectsAsOwner(String username, Pageable pageable) {
    User user =
        userRepository
            .findUserByUsername(username)
            .orElseThrow(() -> new NoSuchUserException("User not found: " + username));
    Page<ProjectSummaryResponse> projects = projectService.getProjectsByOwner(username, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No projects found for user: " + username);
    }
    return projects;
  }

  @Override
  @Transactional
  public Page<ProjectSummaryResponse> getProjectsAsMember(String username, Pageable pageable) {
    User user =
        userRepository
            .findUserByUsername(username)
            .orElseThrow(() -> new NoSuchUserException("User not found: " + username));
    Page<ProjectSummaryResponse> projects = projectService.getProjectsByMember(username, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No projects found for user: " + username);
    }
    return projects;
  }
}
