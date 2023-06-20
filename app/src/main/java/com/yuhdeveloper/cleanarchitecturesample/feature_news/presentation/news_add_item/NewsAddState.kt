package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

data class NewsAddState(
    val title:String = "",
    val description:String = "",
    val imageUrl:String = "",
    val viewCount:Int = 0
)
