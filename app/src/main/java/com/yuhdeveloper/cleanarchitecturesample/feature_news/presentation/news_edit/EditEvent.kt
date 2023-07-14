package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

sealed class EditEvent(){
    data class ChangeTitle(val title:String):EditEvent()
    data class ChangeDescription(val description:String):EditEvent()
    data class ChangeImageUrl(val imageUrl:String):EditEvent()
    object Update:EditEvent()
}