package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<List<News>>

    suspend fun getNewsById(id:Int):News

    suspend fun insertNews(news:News)

    suspend fun deleteNews(news:News)

}