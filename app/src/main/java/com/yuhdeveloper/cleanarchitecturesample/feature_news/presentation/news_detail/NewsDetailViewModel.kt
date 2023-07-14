package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel
@Inject constructor(private val useCases: NewsUseCases,
    savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = mutableStateOf(NewsDetailState())
    val state = _state


    /*init {
        val newsId = savedStateHandle.get<Int>("newsId")
        if(newsId != -1){
            viewModelScope.launch {
                var data = useCases.getNewsById(newsId!!)
                data = data.copy(viewCount = data.viewCount + 1)

                useCases.updateNewsById(data)
                _state.value = _state.value.copy(
                    id = null,
                    title = data.title,
                    description = data.description,
                    viewCount = data.viewCount,
                    imageUrl = data.imageUrl
                )
            }
        }
    }*/

}