package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

import android.util.Log
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
class NewsAddViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(NewsAddState())
    val state = _state
    private val _title = mutableStateOf("")
    val title = _title
    private val _description = mutableStateOf("")
    val description = _description
    private val _imageUrl = mutableStateOf("")
    val imageUrl = _imageUrl
    private val _viewCount = mutableStateOf(0)
    val viewCount = _viewCount

    private val _eventFlow = MutableSharedFlow<AddEffect>()
    val eventFlow = _eventFlow

    private val currentNewsId:Int = -1

    init {
        val id = savedStateHandle.get<Int>("noteId")?.let {
            if(it!=-1){
                // Edit Area
            }
        }

        Log.d("TAG",id.toString() )
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
                    ))
                    _eventFlow.emit(AddEffect.onBack)
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
            is AddEvent.ChangeViewCount -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        viewCount = event.viewCount
                    )
                }
            }
        }
    }
}