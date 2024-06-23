package org.siri_hate.main_service.controller.category;

import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.category.NewsCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.NewsCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.NewsCategorySummaryResponse;
import org.siri_hate.main_service.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for handling requests related to News Categories.
 * It provides endpoints for creating, retrieving, updating, and deleting News Categories.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/news_categories")
public class NewsCategoryController {

    final private NewsCategoryService newsCategoryService;

    /**
     * Constructor for the NewsCategoryController class.
     * It initializes the NewsCategoryService.
     *
     * @param newsCategoryService the service for handling business logic related to News Categories
     */
    @Autowired
    public NewsCategoryController(NewsCategoryService newsCategoryService) {
        this.newsCategoryService = newsCategoryService;
    }

    /**
     * This method handles the creation of a new News Category.
     *
     * @param request the request body containing the details of the new News Category
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createNewsCategory(@RequestBody NewsCategoryRequest request) {
        newsCategoryService.createNewsCategory(request);
        return new ResponseEntity<>("News category was successfully created!", HttpStatus.CREATED);
    }

    /**
     * This method retrieves all News Categories.
     *
     * @return a response entity with a list of all News Categories and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<NewsCategorySummaryResponse>> getAllNewsCategory() {
        List<NewsCategorySummaryResponse> newsCategories = newsCategoryService.getAllNewsCategory();
        return new ResponseEntity<>(newsCategories, HttpStatus.OK);
    }

    /**
     * This method retrieves a News Category by its ID.
     *
     * @param id the ID of the News Category to retrieve
     * @return a response entity with the requested News Category and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryFullResponse> getNewsCategoryById(
            @Positive @PathVariable Long id
                                                                       ) {
        NewsCategoryFullResponse newsCategory = newsCategoryService.getNewsCategoryById(id);
        return new ResponseEntity<>(newsCategory, HttpStatus.OK);
    }

    /**
     * This method updates an existing News Category.
     *
     * @param id      the ID of the News Category to update
     * @param request the request body containing the new details of the News Category
     * @return a response entity with a success message and HTTP status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateNewsCategory(
            @Positive @PathVariable Long id,
            @RequestBody NewsCategoryRequest request
                                                    ) {
        newsCategoryService.updateNewsCategory(id, request);
        return new ResponseEntity<>("News category was successfully updated!", HttpStatus.OK);
    }

    /**
     * This method deletes an existing News Category.
     *
     * @param id the ID of the News Category to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNewsCategory(@Positive @PathVariable Long id) {
        newsCategoryService.deleteNewsCategory(id);
        return new ResponseEntity<>("News category was successfully deleted!", HttpStatus.NO_CONTENT);
    }

}