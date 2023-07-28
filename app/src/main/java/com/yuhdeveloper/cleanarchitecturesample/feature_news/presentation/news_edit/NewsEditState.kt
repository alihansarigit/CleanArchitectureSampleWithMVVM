package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

import androidx.annotation.StringRes
import com.yuhdeveloper.cleanarchitecturesample.R

data class NewsEditState(
    val id:Int = 0,
    val title:String = "",
    @StringRes val titlePlaceholder: Int = R.string.titlePlaceHolder,
    val description:String = "",
    @StringRes val descriptionPlaceholder: Int = R.string.descriptionPlaceHolder,
    val imageUrl:String = "",
    @StringRes val imageUrlPlaceHolder: Int = R.string.imagePlaceHolder,

    var isLoading:Boolean = false
)
