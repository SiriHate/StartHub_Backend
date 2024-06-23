package org.siri_hate.main_service.controller.category;

import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.category.ArticleCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategorySummaryResponse;
import org.siri_hate.main_service.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for handling requests related to Article Categories.
 * It provides endpoints for creating, retrieving, updating, and deleting Article Categories.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/article_categories")
public class ArticleCategoryController {

    final private ArticleCategoryService articleCategoryService;

    /**
     * Constructor for the ArticleCategoryController class.
     * It initializes the ArticleCategoryService.
     *
     * @param articleCategoryService the service for handling business logic related to Article Categories
     */
    @Autowired
    public ArticleCategoryController(ArticleCategoryService articleCategoryService) {
        this.articleCategoryService = articleCategoryService;
    }

    /**
     * This method handles the creation of a new Article Category.
     *
     * @param request the request body containing the details of the new Article Category
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createArticleCategory(@RequestBody ArticleCategoryRequest request) {
        articleCategoryService.createArticleCategory(request);
        return new ResponseEntity<>("Article category was successfully created!", HttpStatus.CREATED);
    }

    /**
     * This method retrieves all Article Categories.
     *
     * @return a response entity with a list of all Article Categories and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<ArticleCategorySummaryResponse>> getAllArticleCategory() {
        List<ArticleCategorySummaryResponse> articleCategories = articleCategoryService.getAllArticleCategory();
        return new ResponseEntity<>(articleCategories, HttpStatus.OK);
    }

    /**
     * This method retrieves an Article Category by its ID.
     *
     * @param id the ID of the Article Category to retrieve
     * @return a response entity with the requested Article Category and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleCategoryFullResponse> getArticleCategoryById(
            @Positive @PathVariable Long id
                                                                             ) {
        ArticleCategoryFullResponse articleCategory = articleCategoryService.getArticleCategoryById(id);
        return new ResponseEntity<>(articleCategory, HttpStatus.OK);
    }

    /**
     * This method updates an existing Article Category.
     *
     * @param id      the ID of the Article Category to update
     * @param request the request body containing the new details of the Article Category
     * @return a response entity with a success message and HTTP status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateArticleCategory(
            @Positive @PathVariable Long id,
            @RequestBody ArticleCategoryRequest request
                                                       ) {
        articleCategoryService.updateArticleCategory(id, request);
        return new ResponseEntity<>("Article category was successfully updated!", HttpStatus.OK);
    }

    /**
     * This method deletes an existing Article Category.
     *
     * @param id the ID of the Article Category to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleCategory(@Positive @PathVariable Long id) {
        articleCategoryService.deleteArticleCategory(id);
        return new ResponseEntity<>("Article category was successfully deleted!", HttpStatus.NO_CONTENT);
    }

}