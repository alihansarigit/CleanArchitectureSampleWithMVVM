package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(val repository: NewsRepository){

    suspend operator fun invoke(): Flow<List<News>> {
        return repository.getNews()
    }
}