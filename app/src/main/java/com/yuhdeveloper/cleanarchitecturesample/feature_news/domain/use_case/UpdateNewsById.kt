package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import javax.inject.Inject

class UpdateNewsById @Inject constructor(val repository: NewsRepository) {

    suspend operator fun invoke(news:NewsDto):NewsDto{
        return repository.updateNews(news)
    }
}