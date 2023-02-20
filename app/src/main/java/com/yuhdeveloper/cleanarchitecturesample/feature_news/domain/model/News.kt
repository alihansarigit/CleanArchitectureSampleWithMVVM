package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class News(
    @PrimaryKey val id:Int,
    val title:String,
    val description:String,
    val imageUrl:String,
    val viewCount:Int = 0,
)
