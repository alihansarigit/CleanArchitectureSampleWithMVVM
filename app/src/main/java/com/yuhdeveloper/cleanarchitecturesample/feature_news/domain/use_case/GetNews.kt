package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.toNews
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNews @Inject constructor(val repository: NewsRepository){
    suspend operator fun invoke(): Flow<Resource<ArrayList<News>>> {
        return flow {
           try {
               emit(Resource.Loading)
               val news = repository.getNews().map {
                   it.toNews()
               } as ArrayList
               emit(Resource.Success(data = news))
           }catch (ex:Exception){
               emit(Resource.Error(error = "${ex.message}"))
           }
        }
    }
}