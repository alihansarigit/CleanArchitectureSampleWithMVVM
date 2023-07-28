package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.toNews
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsById @Inject constructor(val repository: NewsRepository){
    suspend operator fun invoke(id:Int): Flow<Resource<News>> {
        return flow {
            try {
                emit(Resource.Loading)
                val data = Resource.Success(repository.getNewsById(id).toNews())
                emit(data)
            }catch (ex:Exception){
                emit(Resource.Error(ex.localizedMessage!!))
            }
        }
    }
}