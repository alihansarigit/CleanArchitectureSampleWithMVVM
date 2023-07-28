package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto

interface NewsRepository {
    suspend fun getNews(): ArrayList<NewsDto>

    suspend fun getNewsById(id:Int):NewsDto

    suspend fun insertNews(NewsDto:NewsDto):NewsDto

    suspend fun deleteNews(id:Int):NewsDto

    suspend fun updateNews(NewsDto:NewsDto):NewsDto
}