package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News

data class NewsEditState(
    val id:Int? = 0,
    val title:String = "",
    val description:String = "",
    val imageUrl:String = "",
    val viewCount:Int = 0,
)
