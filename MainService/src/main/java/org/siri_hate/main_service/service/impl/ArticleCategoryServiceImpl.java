package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchArticleCategoryException;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;
import org.siri_hate.main_service.model.dto.mapper.ArticleCategoryMapper;
import org.siri_hate.main_service.model.dto.request.category.ArticleCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategorySummaryResponse;
import org.siri_hate.main_service.repository.ArticleCategoryRepository;
import org.siri_hate.main_service.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * This class implements the ArticleCategoryService interface.
 * It provides methods for creating, retrieving, updating, and deleting ArticleCategory entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    final private ArticleCategoryRepository articleCategoryRepository;

    final private ArticleCategoryMapper articleCategoryMapper;

    /**
     * Constructor for ArticleCategoryServiceImpl.
     *
     * @param articleCategoryRepository the ArticleCategoryRepository to use for database operations.
     * @param articleCategoryMapper     the ArticleCategoryMapper to use for converting between DTOs and entities.
     */
    @Autowired
    public ArticleCategoryServiceImpl(
            ArticleCategoryRepository articleCategoryRepository,
            ArticleCategoryMapper articleCategoryMapper
                                     ) {
        this.articleCategoryRepository = articleCategoryRepository;
        this.articleCategoryMapper = articleCategoryMapper;
    }

    /**
     * This method creates a new ArticleCategory entity from a request DTO and saves it in the database.
     *
     * @param request the ArticleCategoryRequest DTO containing the data for the new ArticleCategory.
     */
    @Override
    @Transactional
    public void createArticleCategory(@RequestBody ArticleCategoryRequest request) {
        ArticleCategory articleCategoryEntity = articleCategoryMapper.toArticleCategory(request);
        articleCategoryRepository.save(articleCategoryEntity);
    }

    /**
     * This method retrieves all ArticleCategory entities from the database and converts them to summary response DTOs.
     *
     * @return a List of ArticleCategorySummaryResponse DTOs.
     */
    @Override
    public List<ArticleCategorySummaryResponse> getAllArticleCategory() {

        List<ArticleCategory> articleCategories = articleCategoryRepository.findAll();

        if (articleCategories.isEmpty()) {
            throw new NoSuchArticleCategoryException("No article categories found!");
        }

        return articleCategoryMapper.toArticleCategorySummaryResponseList(articleCategories);
    }

    /**
     * This method retrieves an ArticleCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the ArticleCategory to retrieve.
     * @return an ArticleCategoryFullResponse DTO.
     */
    @Override
    public ArticleCategoryFullResponse getArticleCategoryById(Long id) {

        ArticleCategory articleCategory = articleCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchArticleCategoryException("No article category with id: " + id));

        return articleCategoryMapper.toArticleCategoryFullResponse(articleCategory);
    }

    /**
     * This method retrieves an ArticleCategory entity by its ID.
     *
     * @param id the ID of the ArticleCategory to retrieve.
     * @return an ArticleCategory entity.
     */
    @Override
    public ArticleCategory getArticleCategoryEntityById(Long id) {
        return articleCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchArticleCategoryException("No article category with id: " + id));
    }

    /**
     * This method updates an existing ArticleCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the ArticleCategory to update.
     * @param request the ArticleCategoryRequest DTO containing the new data for the ArticleCategory.
     */
    @Override
    @Transactional
    public void updateArticleCategory(Long id, ArticleCategoryRequest request) {
        ArticleCategory articleCategory = articleCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchArticleCategoryException("No article category with id: " + id));

        articleCategoryMapper.updateArticleCategoryFromRequest(request, articleCategory);
        articleCategoryRepository.save(articleCategory);
    }

    /**
     * This method deletes an ArticleCategory entity by its ID from the database.
     *
     * @param id the ID of the ArticleCategory to delete.
     */
    @Override
    @Transactional
    public void deleteArticleCategory(Long id) {
        ArticleCategory articleCategory = articleCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchArticleCategoryException("No article category with id: " + id));

        articleCategoryRepository.delete(articleCategory);
    }

}