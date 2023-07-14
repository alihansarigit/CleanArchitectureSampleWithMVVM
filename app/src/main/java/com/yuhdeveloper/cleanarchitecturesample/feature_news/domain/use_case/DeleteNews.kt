package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository

class DeleteNews(val repository: NewsRepository){

    suspend operator fun invoke(news: News){
        //repository.deleteNews(news)
    }
}