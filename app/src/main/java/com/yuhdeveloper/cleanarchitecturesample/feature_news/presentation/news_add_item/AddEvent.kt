package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

sealed class AddEvent {
    object saveNews:AddEvent()
    data class ChangeTitle(val title:String):AddEvent()
    data class ChangeDescription(val description:String):AddEvent()
    data class ChangeImageUrl(val imageUrl:String):AddEvent()
    data class ChangeViewCount(val viewCount:Int):AddEvent()
}