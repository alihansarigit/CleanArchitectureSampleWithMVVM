package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val useCases : NewsUseCases
    ):ViewModel() {

    private var _feedList = mutableStateOf(FeedState(listOf()))
    var feedList = _feedList

    val _eventFlow = MutableSharedFlow<FeedEvent>()
    val eventFlow = _eventFlow


    init {
        viewModelScope.launch {
            useCases.getNews().onEach{
                _feedList.value = _feedList.value.copy(
                    newsList = it
                )
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event:FeedEvent){
        when(event){
            is FeedEvent.RemoveItem -> {
                viewModelScope.launch {
                    useCases.deleteNews(event.news)
                }
            }
        }
    }

    sealed class FeedEvent(){
        data class RemoveItem(val news:News):FeedEvent()
    }
}