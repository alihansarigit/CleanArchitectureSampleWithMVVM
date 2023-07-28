package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News

data class NewsDto(
    val newsId: Int,
    val newsPicture:String,
    val newsDescription:String,
    val newsTitle:String,
)


fun NewsDto.toNews():News{
    return News(
        id = newsId,
        title = newsTitle,
        description = newsDescription,
        imageUrl = newsPicture
    )
}

fun News.toDto():NewsDto{
    return NewsDto(
        newsId = id,
        newsPicture = imageUrl,
        newsDescription = description,
        newsTitle = title
    )
}