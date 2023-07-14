package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun NewsEditScreen(
    viewModel: NewsEditViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is EditEffect.onBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp)
    ) {

        TextField(
            value = state.value.title,
            placeholder = { Text(text = "Set Title") },
            onValueChange = { viewModel.onEvent(EditEvent.ChangeTitle(it))  },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.value.description,
            placeholder = { Text(text = "Set Description") },
            onValueChange = { viewModel.onEvent(EditEvent.ChangeDescription(it))  },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.value.imageUrl,
            onValueChange = { viewModel.onEvent(EditEvent.ChangeImageUrl(it))  },
            placeholder = { Text(text = "Set Image Url") },
            modifier = Modifier.fillMaxWidth()
        )


        Button(onClick = {
            viewModel.onEvent(EditEvent.Update)
        }) {
            Text(text = "Update")
        }
    }
}