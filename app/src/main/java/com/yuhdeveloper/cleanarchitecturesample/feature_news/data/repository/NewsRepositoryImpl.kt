package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.repository

import com.google.gson.JsonObject
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.source.remote.MockApi
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


class NewsRepositoryImpl  @Inject constructor(private val mockApi: MockApi): NewsRepository {
    override suspend fun getNews(): ArrayList<NewsDto> {
        return mockApi.getNewsList()
    }

    override suspend fun getNewsById(id: Int): NewsDto {
        return mockApi.getNewsById(id)
    }

    override suspend fun insertNews(news: NewsDto):NewsDto {
        val jsonObject = JsonObject()
        jsonObject.addProperty("newsTitle",news.newsTitle)
        jsonObject.addProperty("newsDescription",news.newsDescription)
        jsonObject.addProperty("newsPicture",news.newsPicture)
        jsonObject.addProperty("newsId",news.newsId)
        return mockApi.insertNews(
            requestBody = jsonObject.toString().toRequestBody(
                "application/json; charset=utf-8".toMediaTypeOrNull()
            )
        )
    }

    override suspend fun deleteNews(id:Int):NewsDto {
        return mockApi.deleteNews(id)
    }

    override suspend fun updateNews(news: NewsDto): NewsDto {
        val jsonObject = JsonObject()
        jsonObject.addProperty("newsTitle",news.newsTitle)
        jsonObject.addProperty("newsDescription",news.newsDescription)
        jsonObject.addProperty("newsPicture",news.newsPicture)
        jsonObject.addProperty("newsId",news.newsId)
        return  mockApi.updateNews(
            id = news.newsId,
            requestBody = jsonObject.toString().toRequestBody(
                "application/json; charset=utf-8".toMediaTypeOrNull()
            ))
    }

}
/*
class NewsRepositoryImpl(private val dao: NewsDao) : NewsRepository {

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

    override suspend fun updateNews(news: News) {
        dao.updateNews(news)
    }
}*/
