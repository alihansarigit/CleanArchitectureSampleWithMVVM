package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsAddScreen(
    viewModel:NewsAddViewModel = hiltViewModel(),
    navigate:NavController
) {

    val title = viewModel.title
    val description = viewModel.description
    val imageUrl = viewModel.imageUrl
    val viewCount = viewModel.viewCount

    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {
            when(it){
                AddEffect.onBack->{
                    navigate.popBackStack()
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(5.dp)) {

        TextField(value = state.value.title,
            placeholder = { Text(text = "Set Title")},
            onValueChange = { viewModel.onEvent(AddEvent.ChangeTitle(it)) },
            modifier = Modifier.fillMaxWidth())

        TextField(value = state.value.description,
            placeholder = { Text(text = "Set Description")},
            onValueChange = { viewModel.onEvent(AddEvent.ChangeDescription(it)) },
            modifier = Modifier.fillMaxWidth())

        TextField(value = state.value.imageUrl,
            onValueChange = { viewModel.onEvent(AddEvent.ChangeImageUrl(it)) },
            placeholder = { Text(text = "Set Image Url")},
            modifier = Modifier.fillMaxWidth())

        Text(text = "This news read ${state.value.viewCount} times")

        Button(onClick = {
            viewModel.onEvent(AddEvent.saveNews)
        }) {
            Text(text = "Send")
        }
    }
}
