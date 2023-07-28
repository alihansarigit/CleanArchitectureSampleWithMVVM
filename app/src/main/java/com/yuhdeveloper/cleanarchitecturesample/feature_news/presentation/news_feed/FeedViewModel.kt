package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.preferences.DataStoreManager
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
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

    val _eventFlow = MutableSharedFlow<FeedEffect>()
    val eventFlow = _eventFlow

    init {
    }

    fun getFeed(){
        viewModelScope.launch {
            useCases.getNews().onEach{
                when(it){
                    is Resource.Loading->{

                    }
                    is Resource.Success ->{
                        _state.value.items.clear()
                        _state.value.items.addAll(it.data)
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
                    useCases.deleteNews(event.news.id).onEach {
                        when(it){
                            is Resource.Error -> {
                                _state.value.isLoading = false

                                _eventFlow.emit(FeedEffect.showMessage(it.error))
                            }
                            Resource.Loading -> {
                                _state.value.isLoading = true
                            }
                            is Resource.Success -> {
                                _state.value.isLoading = false
                                _state.value.items.remove(event.news)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}