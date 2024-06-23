package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchNewsFoundException;
import org.siri_hate.main_service.model.dto.mapper.NewsMapper;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.repository.NewsRepository;
import org.siri_hate.main_service.repository.adapters.NewsSpecification;
import org.siri_hate.main_service.service.NewsCategoryService;
import org.siri_hate.main_service.service.NewsService;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the NewsService interface.
 * It provides methods for creating, retrieving, updating, and deleting News entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class NewsServiceImpl implements NewsService {

    final private NewsRepository newsRepository;

    final private NewsMapper newsMapper;

    final private NewsCategoryService newsCategoryService;

    final private UserService userService;

    /**
     * Constructor for NewsServiceImpl.
     *
     * @param newsRepository      the NewsRepository to use for database operations.
     * @param newsMapper          the NewsMapper to use for converting between DTOs and entities.
     * @param newsCategoryService the NewsCategoryService to use for handling NewsCategory entities.
     * @param userService         the UserService to use for handling User entities.
     */
    @Autowired
    public NewsServiceImpl(
            NewsRepository newsRepository,
            NewsMapper newsMapper,
            NewsCategoryService newsCategoryService,
            UserService userService
                          ) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.newsCategoryService = newsCategoryService;
        this.userService = userService;
    }

    /**
     * This method creates a new News entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the News.
     * @param news     the NewsFullRequest DTO containing the data for the new News.
     */
    @Override
    @Transactional
    public void createNews(String username, NewsFullRequest news) {
        News newsEntity = newsMapper.toNews(news);
        newsEntity.setUser(userService.findOrCreateUser(username));
        newsEntity.setPublicationDate(LocalDate.now());
        newsEntity.setCategory(newsCategoryService.getNewsCategoryEntityById(news.getCategoryId()));
        newsEntity.setModerationPassed(false);
        newsRepository.save(newsEntity);
    }

    /**
     * This method retrieves a News entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the News to retrieve.
     * @return a NewsFullResponse DTO.
     */
    @Override
    public NewsFullResponse getNewsById(Long id) {

        Optional<News> news = newsRepository.findById(id);

        if (news.isEmpty()) {
            throw new NoSuchElementException("News not found");
        }

        return newsMapper.toNewsFullResponse(news.get());
    }

    /**
     * This method retrieves all News entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    public Page<NewsSummaryResponse> getNewsByCategoryAndSearchQuery(String category, String query, Pageable pageable) {

        Specification<News> spec = Specification.where(NewsSpecification.titleStartsWith(query))
                .and(NewsSpecification.hasCategory(category));

        Page<News> news = newsRepository.findAll(spec, pageable);

        if (news.isEmpty()) {
            throw new NoSuchNewsFoundException("No news found");
        }

        return newsMapper.toNewsSummaryResponsePage(news);
    }

    /**
     * This method retrieves all News entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    @Override
    public Page<NewsSummaryResponse> getAllNews(Pageable pageable) {

        Page<News> news = newsRepository.findAll(pageable);

        if (news.isEmpty()) {
            throw new NoSuchElementException("No news found");
        }

        return newsMapper.toNewsSummaryResponsePage(news);
    }

    /**
     * This method updates an existing News entity with data from a provided News entity and saves it in the database.
     *
     * @param id          the ID of the News to update.
     * @param newsDetails the News containing the new data for the News.
     */
    @Override
    @Transactional
    public void updateNews(Long id, News newsDetails) {

        Optional<News> news = newsRepository.findById(id);

        if (news.isEmpty()) {
            throw new NoSuchElementException("News with id = " + id + " not found");
        }

        newsDetails.setId(id);
        newsRepository.save(newsDetails);
    }

    /**
     * This method deletes a News entity by its ID from the database.
     *
     * @param id the ID of the News to delete.
     */
    @Override
    @Transactional
    public void deleteNews(Long id) {

        Optional<News> news = newsRepository.findById(id);

        if (news.isEmpty()) {
            throw new NoSuchElementException("News with id = " + id + " not found");
        }

        newsRepository.delete(news.get());
    }

}