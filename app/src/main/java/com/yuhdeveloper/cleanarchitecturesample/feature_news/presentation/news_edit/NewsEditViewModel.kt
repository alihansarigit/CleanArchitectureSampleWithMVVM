package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

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
class NewsEditViewModel @Inject constructor(
    private val useCases: NewsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<EditEffect>()
    val eventFlow = _eventFlow

    private val _state = mutableStateOf(NewsEditState())
    val state = _state

    init {
        val savedId = savedStateHandle.get<Int>("newsId")
        if (savedId != -1 && savedId != null) {
            viewModelScope.launch {
                useCases.getNewsById(savedId).onEach {
                    when(it){
                        is Resource.Error -> {
                            _state.value.isLoading = false
                            _eventFlow.emit(EditEffect.showMessage("${it.error}"))
                        }
                        Resource.Loading -> {
                            _state.value.isLoading = true

                        }
                        is Resource.Success -> {
                            _state.value.isLoading = false

                            _state.value = _state.value.copy(
                                id = it.data.id,
                                title = it.data.title,
                                description = it.data.description,
                                imageUrl = it.data.imageUrl
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.ChangeTitle -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        title = event.title
                    )
                }
            }

            is EditEvent.ChangeDescription -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        description = event.description
                    )
                }
            }

            is EditEvent.ChangeImageUrl -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        imageUrl = event.imageUrl
                    )
                }
            }

            is EditEvent.Update -> {
                viewModelScope.launch {
                    useCases.updateNewsById(
                        news = News(
                            id = _state.value.id,
                            title = _state.value.title,
                            description = _state.value.description,
                            imageUrl = _state.value.imageUrl,
                        )
                    ).onEach {
                        when(it){
                            is Resource.Error -> {
                                _state.value.isLoading = false
                                eventFlow.emit(EditEffect.showMessage("${it.error}"))
                            }
                            Resource.Loading -> {
                                _state.value.isLoading = true
                            }
                            is Resource.Success -> {
                                _state.value.isLoading = false
                                _eventFlow.emit(EditEffect.onBack)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}