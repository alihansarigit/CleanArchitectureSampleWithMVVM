package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.toNews
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteNews(val repository: NewsRepository){
    suspend operator fun invoke(id:Int): Flow<Resource<News>> {
        return flow {
            try {
                emit(Resource.Loading)
                emit(Resource.Success(repository.deleteNews(id).toNews()))
            }catch (ex:Exception){
                emit(Resource.Error(ex.localizedMessage!!))
            }
        }
    }
}