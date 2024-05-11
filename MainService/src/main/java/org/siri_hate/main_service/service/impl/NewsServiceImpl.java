package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.repository.NewsRepository;
import org.siri_hate.main_service.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    final private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional
    public News createNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News getNewsById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new NoSuchElementException("News not found");
        }
        return news.get();
    }

    @Override
    public List<News> getNewsByCategory(String category) {

        List<News> newsList = null;

        if (newsList.isEmpty()) {
            throw new NoSuchElementException("No news found for category " + category);
        }

        return newsList;
    }

    @Override
    public List<News> getAllNews() {

        List<News> newsList = newsRepository.findAll();

        if (newsList.isEmpty()) {
            throw new NoSuchElementException("No news found");
        }

        return newsList;
    }

    @Override
    public List<News> searchNewsByOwnerUsername(String username) {

        List<News> news = newsRepository.findNewsByOwner(username);

        if (news.isEmpty()) {
            throw new NoSuchElementException("No news found for username " + username);
        }

        return news;
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
