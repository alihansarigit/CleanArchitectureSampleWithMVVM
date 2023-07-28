package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.source.remote

import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @DELETE("news/{id}")
    suspend fun deleteNews(
        @Path("id") id:Int
    ):NewsDto

    @POST("news")
    suspend fun insertNews(
         @Body requestBody: RequestBody
    ):NewsDto
}