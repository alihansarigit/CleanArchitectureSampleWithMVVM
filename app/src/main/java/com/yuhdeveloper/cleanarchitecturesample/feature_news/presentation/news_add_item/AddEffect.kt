package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit.EditEffect

sealed class AddEffect {
    object onBack:AddEffect()
    data class showMessage(val message:String): AddEffect()

}