package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_detail

data class NewsDetailState(
    val id:Int? = null,
    val title:String = "",
    val description:String = "",
    val viewCount:Int = 0,
    val imageUrl:String = "",
)