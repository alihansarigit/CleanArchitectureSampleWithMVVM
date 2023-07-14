package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.preferences.DataStoreManager
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
    private val useCases : NewsUseCases,
    private val dataStore: DataStoreManager
    ):ViewModel() {

    private var _state = mutableStateOf(FeedState())
    var state = _state

    /*private var _items = mutableStateListOf<News>()
    val items = _items*/

    val _eventFlow = MutableSharedFlow<FeedEffect>()
    val eventFlow = _eventFlow


    init {
        getFeed()
    }

    fun getFeed(){
        viewModelScope.launch {
            useCases.getNews().onEach{
                when(it){
                    is Resource.Loading->{

                    }
                    is Resource.Success ->{
                       /* _feedList.value = _feedList.value.copy(
                            it.data
                        )*/
                        // _items.addAll(it.data)
                        _state.value._items.addAll(it.data)
                    }
                    is Resource.Error -> {

                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun onEvent(event:FeedEvent){
        when(event){
            is FeedEvent.RemoveItem -> {
                viewModelScope.launch {
                     // _feedList.value.newsList.remove(event.news)
                     // _items.remove(event.news)
                    _state.value._items.remove(event.news)
                }
            }
        }
    }
}