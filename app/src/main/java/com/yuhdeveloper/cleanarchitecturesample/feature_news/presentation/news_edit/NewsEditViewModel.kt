package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.feature_news.data.dto.NewsDto
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
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
                val data = useCases.getNewsById(savedId)
                _state.value = _state.value.copy(
                    id = data.newsId,
                    title = data.newsTitle,
                    description = data.newsDescription,
                    imageUrl = data.newsPicture
                )
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
                        news = NewsDto(
                            newsId = _state.value.id,
                            newsTitle = _state.value.title,
                            newsDescription = _state.value.description,
                            newsPicture = _state.value.imageUrl,
                            userId = _state.value.userId,
                            createdAt = _state.value.createdAt,
                            username = _state.value.username,
                        )
                    )
                    eventFlow.emit(EditEffect.onBack)
                }
            }
        }
    }
}