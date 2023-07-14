package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News

data class FeedState(
    val newsList:ArrayList<News> = arrayListOf(),
    val _items: SnapshotStateList<News> = mutableStateListOf()
)