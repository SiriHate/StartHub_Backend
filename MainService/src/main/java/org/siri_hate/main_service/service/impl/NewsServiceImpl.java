package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.dto.mapper.NewsMapper;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.repository.NewsRepository;
import org.siri_hate.main_service.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    final private NewsRepository newsRepository;

    final private NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    @Transactional
    public void createNews(String username, NewsFullRequest news) {
        News newsEntity = newsMapper.toNews(news);
        newsEntity.setOwner(username);
        newsEntity.setPublicationDate(LocalDate.now());
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

    @Override
    public Page<NewsSummaryResponse> getNewsByCategory(String category, Pageable pageable) {

        Page<News> news = null;

        if (news.isEmpty()) {
            throw new NoSuchElementException("No news found for category " + category);
        }

        return null;
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
    public Page<NewsSummaryResponse> searchNewsByOwnerUsername(String username, Pageable pageable) {

        Page<News> news = newsRepository.findNewsByOwner(username, pageable);

        if (news.isEmpty()) {
            throw new NoSuchElementException("No news found for username " + username);
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
