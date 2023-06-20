package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News

data class FeedState(
    val newsList:List<News>
)