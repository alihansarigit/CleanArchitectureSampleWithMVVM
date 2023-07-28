package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

sealed class FeedEffect(){
    object onBack:FeedEffect()

    data class showMessage(val message:String): FeedEffect()
}
