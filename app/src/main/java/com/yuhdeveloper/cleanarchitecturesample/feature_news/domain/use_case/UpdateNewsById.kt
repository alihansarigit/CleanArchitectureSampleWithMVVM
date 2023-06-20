package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository

class UpdateNewsById(val repository: NewsRepository) {

    suspend operator fun invoke(news:News?){
        news?.let {
            repository.updateNews(it)
        }
    }
}