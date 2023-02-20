package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.repository

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.data_source.NewsDao
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val dao:NewsDao) : NewsRepository {

    override fun getNews(): Flow<List<News>> {
        return dao.getNews()
    }

    override suspend fun getNewsById(id: Int): News {
        return dao.getNewsById(id)
    }

    override suspend fun insertNews(news: News) {
        dao.addNews(news)
    }

    override suspend fun deleteNews(news: News) {
        dao.deleteNews(news)
    }
}