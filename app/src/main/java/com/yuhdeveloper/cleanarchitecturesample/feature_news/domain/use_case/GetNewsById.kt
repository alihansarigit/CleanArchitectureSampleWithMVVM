package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository

class GetNewsById(val repository: NewsRepository){
    suspend operator fun invoke(id:Int):News{
        return repository.getNewsById(id)
    }
}