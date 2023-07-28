package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.common.Resource
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsAddViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(NewsAddState())
    val state = _state
    private val _eventFlow = MutableSharedFlow<AddEffect>()
    val eventFlow = _eventFlow

    init {

    }

    fun onEvent(event: AddEvent){
        when(event){
            is AddEvent.saveNews->{
                viewModelScope.launch {
                    newsUseCases.addNews(news = News(
                        id = 0,
                        title = _state.value.title,
                        description = _state.value.description,
                        imageUrl = _state.value.imageUrl,
                    )).onEach {
                        when(it){
                            is Resource.Error -> {
                                _state.value.isLoading = false
                                _eventFlow.emit(AddEffect.showMessage(it.error))
                            }
                            Resource.Loading -> {
                                _state.value.isLoading = true
                            }
                            is Resource.Success -> {
                                _state.value.isLoading = true
                                _eventFlow.emit(AddEffect.onBack)
                            }
                        }

                    }.launchIn(viewModelScope)
                }
            }
            is AddEvent.ChangeTitle -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        title = event.title
                    )
                }
            }
            is AddEvent.ChangeDescription -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        description = event.description
                    )
                }
            }
            is AddEvent.ChangeImageUrl -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        imageUrl = event.imageUrl
                    )
                }
            }
        }
    }
}