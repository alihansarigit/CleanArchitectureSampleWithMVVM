package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

sealed class EditEffect {
    object onBack:EditEffect()
    data class showMessage(val message:String):EditEffect()
}