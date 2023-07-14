package com.yuhdeveloper.cleanarchitecturesample.feature_news.data.source.local

import androidx.room.*
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("select * from News")
    fun getNews():Flow<List<News>>

    @Query("select * from News where id = :id")
    suspend fun getNewsById(id:Int):News

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(news:News)

    @Delete
    suspend fun deleteNews(news:News)

    @Update
    suspend fun updateNews(news:News)
}