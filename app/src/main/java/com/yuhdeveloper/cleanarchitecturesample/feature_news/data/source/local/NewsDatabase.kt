package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News


@Database(
    entities = [News::class],
    version = 1
)
abstract class NewsDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao

    companion object{
        const val DB_NAME = "dbNews"
    }
}