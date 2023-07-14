package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News

sealed class FeedEvent(){
    data class RemoveItem(val news:News):FeedEvent()
}