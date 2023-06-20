package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun NewsDetailScreen(
     viewModel:NewsDetailViewModel = hiltViewModel(),
     navigate:NavController
) {

     val state = viewModel.state

     Column(modifier = Modifier.fillMaxSize()
          .background(Color.White)
          .padding(5.dp)) {
          Text(text = state.value.title)
          Text(text = state.value.description)
          Text(text = "${state.value.viewCount} kez okundu" )
          AsyncImage(
               model = state.value.imageUrl,
               contentDescription = "",
               modifier = Modifier.size(100.dp)
          )
     }
}