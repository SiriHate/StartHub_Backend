package org.siri_hate.main_service.controller;

import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a controller for handling requests related to Users.
 * It provides endpoints for retrieving Projects, Articles, and News related to the authenticated User.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/users")
public class UserController {

    final private UserService userService;

    /**
     * Constructor for the UserController class.
     * It initializes the UserService.
     *
     * @param userService the service for handling business logic related to Users
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method retrieves Projects where the authenticated User is the owner.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of Projects and HTTP status code
     */
    @GetMapping("/my/projects/owned")
    public ResponseEntity<Page<ProjectSummaryResponse>> getProjectsAsOwner(
            @PageableDefault(size = 10) Pageable pageable
                                                                          ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Page<ProjectSummaryResponse> projects = userService.getProjectsAsOwner(username, pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * This method retrieves Projects where the authenticated User is a member.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of Projects and HTTP status code
     */
    @GetMapping("/my/projects/member")
    public ResponseEntity<Page<ProjectSummaryResponse>> getProjectsAsMember(
            @PageableDefault(size = 10) Pageable pageable
                                                                           ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Page<ProjectSummaryResponse> projects = userService.getProjectsAsMember(username, pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * This method retrieves Articles created by the authenticated User.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of Articles and HTTP status code
     */
    @GetMapping("/my/articles")
    public ResponseEntity<Page<ArticleSummaryResponse>> getMyArticles(@PageableDefault(size = 10) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Page<ArticleSummaryResponse> articles = userService.getMyArticles(username, pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * This method retrieves News created by the authenticated User.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of News and HTTP status code
     */
    @GetMapping("/my/news")
    public ResponseEntity<Page<NewsSummaryResponse>> getMyNews(@PageableDefault(size = 10) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Page<NewsSummaryResponse> news = userService.getMyNews(username, pageable);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

}