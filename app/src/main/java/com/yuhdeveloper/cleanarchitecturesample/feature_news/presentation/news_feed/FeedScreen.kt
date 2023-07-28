package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yuhdeveloper.cleanarchitecturesample.R
import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.model.News
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FeedScreen(
    navController: NavController,
    viewModel: FeedViewModel = hiltViewModel()
) {

    val lifecycle = rememberLifecycleEvent()
    val state = viewModel.state
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = lifecycle){
        if(lifecycle == Lifecycle.Event.ON_RESUME){
            viewModel.getFeed()
        }

        viewModel.eventFlow.collectLatest {
            when (it) {
                is FeedEffect.onBack -> {
                    navController.popBackStack()
                }

                is FeedEffect.showMessage -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("AddNote")
            }) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add"
                )
            }
        }
    ) { padding ->

        Column(modifier = Modifier.fillMaxSize()) {
            if(state.value.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

        LazyColumn(modifier = Modifier.padding(padding),
        state = lazyListState) {
            
            items(items = state.value.items,
                key = { todoItem -> todoItem.id}) { item ->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToStart){
                            viewModel.onEvent(FeedEvent.RemoveItem(item))
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    modifier = Modifier,
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                                .padding(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd),
                                contentDescription = ""
                            )
                        }
                    },
                    dismissContent = {
                        Box(modifier = Modifier.background(Color.White)) {
                            NewsItem(item) {
                                navController.navigate("EditNews?newsId=$it")
                            }
                        }
                    }
                )
            }
        }
        }

    }
}

@Composable
fun NewsItem(item:News,onClick:(Int) -> (Unit)) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .clickable { onClick(item.id) }) {
        val (image,title,description) = createRefs()
        AsyncImage(
            model = item.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(image) {
                    start.linkTo(parent.start, margin = 5.dp)
                    top.linkTo(parent.top, margin = 20.dp)
                },
            )

        Text(text = item.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        modifier = Modifier
            .constrainAs(title) {
                start.linkTo(image.end, margin = 20.dp)
                top.linkTo(parent.top, margin = 15.dp)
            })

        Text(text = item.description,
        fontSize = 15.sp,
        modifier = Modifier
            .constrainAs(description){
                top.linkTo(title.bottom)
                start.linkTo(image.end, margin = 20.dp)
                end.linkTo(parent.end, margin = 5.dp)
                width = Dimension.fillToConstraints
            })
    }
}

@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}

@ExperimentalMaterialApi
@Composable
fun swipeToDismiss() {

}