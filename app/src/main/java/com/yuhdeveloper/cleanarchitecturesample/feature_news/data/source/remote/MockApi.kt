package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.source.remote

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MockApi {
    @GET("news")
    suspend fun getNewsList():  ArrayList<NewsDto>

    @GET("news/{id}")
    suspend fun getNewsById(
        @Path("id") id:Int
    ): NewsDto

    @PUT("news/{id}")
    suspend fun updateNews(
        @Path("id") id:Int,
        @Body requestBody:RequestBody
    ):NewsDto
}