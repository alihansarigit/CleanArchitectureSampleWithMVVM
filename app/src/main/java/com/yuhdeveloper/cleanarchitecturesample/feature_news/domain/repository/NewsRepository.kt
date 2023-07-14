package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): ArrayList<NewsDto>

    suspend fun getNewsById(id:Int):NewsDto

    suspend fun insertNews(NewsDto:NewsDto)

    suspend fun deleteNews(NewsDto:NewsDto)

    suspend fun updateNews(NewsDto:NewsDto):NewsDto
}