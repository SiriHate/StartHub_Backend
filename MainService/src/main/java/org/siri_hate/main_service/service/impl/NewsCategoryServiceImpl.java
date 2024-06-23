package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchNewsCategoryException;
import org.siri_hate.main_service.model.entity.category.NewsCategory;
import org.siri_hate.main_service.model.dto.mapper.NewsCategoryMapper;
import org.siri_hate.main_service.model.dto.request.category.NewsCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.NewsCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.NewsCategorySummaryResponse;
import org.siri_hate.main_service.repository.NewsCategoryRepository;
import org.siri_hate.main_service.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the NewsCategoryService interface.
 * It provides methods for creating, retrieving, updating, and deleting NewsCategory entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsCategoryMapper newsCategoryMapper;

    /**
     * Constructor for NewsCategoryServiceImpl.
     *
     * @param newsCategoryRepository the NewsCategoryRepository to use for database operations.
     * @param newsCategoryMapper     the NewsCategoryMapper to use for converting between DTOs and entities.
     */
    @Autowired
    public NewsCategoryServiceImpl(
            NewsCategoryRepository newsCategoryRepository,
            NewsCategoryMapper newsCategoryMapper
                                  ) {
        this.newsCategoryRepository = newsCategoryRepository;
        this.newsCategoryMapper = newsCategoryMapper;
    }

    /**
     * This method creates a new NewsCategory entity from a request DTO and saves it in the database.
     *
     * @param request the NewsCategoryRequest DTO containing the data for the new NewsCategory.
     */
    @Override
    @Transactional
    public void createNewsCategory(NewsCategoryRequest request) {
        NewsCategory newsCategoryEntity = newsCategoryMapper.toNewsCategory(request);
        newsCategoryRepository.save(newsCategoryEntity);
    }

    /**
     * This method retrieves a NewsCategory entity by its ID.
     *
     * @param id the ID of the NewsCategory to retrieve.
     * @return a NewsCategory entity.
     */
    @Override
    public NewsCategory getNewsCategoryEntityById(Long id) {
        return newsCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchNewsCategoryException("No news category with id: " + id));
    }

    /**
     * This method retrieves all NewsCategory entities and converts them to summary response DTOs.
     *
     * @return a List of NewsCategorySummaryResponse DTOs.
     */
    @Override
    public List<NewsCategorySummaryResponse> getAllNewsCategory() {
        List<NewsCategory> newsCategories = newsCategoryRepository.findAll();

        if (newsCategories.isEmpty()) {
            throw new NoSuchNewsCategoryException("No news categories found!");
        }

        return newsCategoryMapper.toNewsCategorySummaryResponseList(newsCategories);
    }

    /**
     * This method retrieves a NewsCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the NewsCategory to retrieve.
     * @return a NewsCategoryFullResponse DTO.
     */
    @Override
    public NewsCategoryFullResponse getNewsCategoryById(Long id) {
        NewsCategory newsCategory = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchNewsCategoryException("No news category with id: " + id));

        return newsCategoryMapper.toNewsCategoryFullResponse(newsCategory);
    }

    /**
     * This method updates an existing NewsCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the NewsCategory to update.
     * @param request the NewsCategoryRequest DTO containing the new data for the NewsCategory.
     */
    @Override
    @Transactional
    public void updateNewsCategory(Long id, NewsCategoryRequest request) {
        NewsCategory newsCategory = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchNewsCategoryException("No news category with id: " + id));

        newsCategoryMapper.updateNewsCategoryFromRequest(request, newsCategory);
        newsCategoryRepository.save(newsCategory);
    }

    /**
     * This method deletes a NewsCategory entity by its ID from the database.
     *
     * @param id the ID of the NewsCategory to delete.
     */
    @Override
    @Transactional
    public void deleteNewsCategory(Long id) {
        NewsCategory newsCategory = newsCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchNewsCategoryException("No news category with id: " + id));

        newsCategoryRepository.delete(newsCategory);
    }

}