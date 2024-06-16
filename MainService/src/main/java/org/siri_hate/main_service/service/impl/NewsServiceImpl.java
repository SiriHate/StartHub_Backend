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

@Service
public class NewsServiceImpl implements NewsService {

    final private NewsRepository newsRepository;

    final private NewsMapper newsMapper;

    final private NewsCategoryService newsCategoryService;

    final private UserService userService;

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

    @Override
    public NewsFullResponse getNewsById(Long id) {

        Optional<News> news = newsRepository.findById(id);

        if (news.isEmpty()) {
            throw new NoSuchElementException("News not found");
        }

        return newsMapper.toNewsFullResponse(news.get());
    }

    public Page<NewsSummaryResponse> getNewsByCategoryAndSearchQuery(String category, String query, Pageable pageable) {

        Specification<News> spec = Specification.where(NewsSpecification.titleStartsWith(query))
                .and(NewsSpecification.hasCategory(category));

        Page<News> news = newsRepository.findAll(spec, pageable);

        if (news.isEmpty()) {
            throw new NoSuchNewsFoundException("No news found");
        }

        return newsMapper.toNewsSummaryResponsePage(news);
    }

    @Override
    public Page<NewsSummaryResponse> getAllNews(Pageable pageable) {

        Page<News> news = newsRepository.findAll(pageable);

        if (news.isEmpty()) {
            throw new NoSuchElementException("No news found");
        }

        return newsMapper.toNewsSummaryResponsePage(news);
    }

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
