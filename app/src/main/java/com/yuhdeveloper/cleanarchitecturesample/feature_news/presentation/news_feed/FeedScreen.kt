package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yuhdeveloper.cleanarchitecturesample.R


@Composable
fun FeedScreen(
    navigate: NavController,
    viewModel: FeedViewModel = hiltViewModel()
) {

    val items = viewModel.feedList
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { navigate.navigate("AddNote") }) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add"
                )
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(items.value.newsList) { item ->
                var popUpControl by remember { mutableStateOf(false) }

                Column(modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable {
                        navigate.navigate("NewsDetail?newsId=${item.id}")
                    }) {

                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "sdfsdfsdfsdfsdsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdff",
                        modifier = Modifier.weight(1f))

                        Icon(imageVector = Icons.Default.More,
                            contentDescription ="",
                        modifier = Modifier.weight(1f).clickable {
                            popUpControl = true
                        })

                        DropdownMenu(expanded = popUpControl,
                            onDismissRequest = { popUpControl = false }) {
                            DropdownMenuItem(onClick = {

                            }) {
                                Text(text = "Edit")
                            }
                        }

                        /*Column {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                contentDescription = "edit",
                                modifier = Modifier.clickable {
                                    navigate.navigate("EditNews?newsId=${item.id}")
                                })

                            Spacer(modifier = Modifier.size(10.dp))

                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = "remove",
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(FeedViewModel.FeedEvent.RemoveItem(item))
                                })
                        }*/
                    }
                    AsyncImage(
                        model = item.imageUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),

                    )
                    Divider(thickness = 2.dp)
                }
            }
        }
    }
}