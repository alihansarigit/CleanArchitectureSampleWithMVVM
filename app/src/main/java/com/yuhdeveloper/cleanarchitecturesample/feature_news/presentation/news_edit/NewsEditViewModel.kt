package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsEditViewModel @Inject constructor(
    val useCases: NewsUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _eventFlow = MutableSharedFlow<EditEffect>()
    val eventFlow = _eventFlow

    private val _title = mutableStateOf("")
    val title = _title

    private val _description = mutableStateOf("")
    val description = _description

    private val _imageUrl = mutableStateOf("")
    val imageUrl = _imageUrl

    private val _viewCount = mutableStateOf(0)
    val viewCount = _viewCount

    private val _state = mutableStateOf(NewsEditState())
    val state = _state

    init {
        val savedId = savedStateHandle.get<Int>("newsId")
        if(savedId!=-1){
            viewModelScope.launch {
               /* _data.value.data = useCases.getNewsById(savedId!!)
                _data.value.data?.let {
                    _title.value = it.title
                    _description.value = it.description
                    _imageUrl.value = it.imageUrl
                    _viewCount.value = it.viewCount
                }*/
                val data = useCases.getNewsById(savedId!!)
                _state.value = _state.value.copy(
                    id = data.id,
                    title = data.title,
                            description = data.description,
                            imageUrl = data.imageUrl,
                            viewCount = data.viewCount
                )
            }
        }
    }

    fun onEvent(event:EditEvent){
        when(event){
            is EditEvent.ChangeTitle ->{
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
            is EditEvent.ChangeViewCount -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        viewCount = event.viewCount
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
                            viewCount = _state.value.viewCount
                        )
                    )
                    eventFlow.emit(EditEffect.onBack)
                }
            }
        }
    }
}