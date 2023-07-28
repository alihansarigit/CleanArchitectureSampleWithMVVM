package com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsAddScreen(
    viewModel:NewsAddViewModel = hiltViewModel(),
    navController:NavController
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {
            when(it){
                AddEffect.onBack->{
                    navController.popBackStack()
                }

                is AddEffect.showMessage -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()                    
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(5.dp)) {

        Spacer(modifier = Modifier.size(20.dp))

        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "",
            modifier = Modifier.clickable{
                navController.popBackStack()
            })

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(value = state.value.title,
            onValueChange = { viewModel.onEvent(AddEvent.ChangeTitle(it)) },
            label = { Text(text = stringResource(id = viewModel.state.value.titlePlaceholder))},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(value = state.value.description,
            onValueChange = { viewModel.onEvent(AddEvent.ChangeDescription(it)) },
            label = { Text(text = stringResource(id = viewModel.state.value.descriptionPlaceholder))},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(value = state.value.imageUrl,
            onValueChange = { viewModel.onEvent(AddEvent.ChangeImageUrl(it)) },
            label = { Text(text = stringResource(id = viewModel.state.value.imageUrlPlaceHolder))},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(10.dp))

        Button(onClick = {
            viewModel.onEvent(AddEvent.saveNews)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Send")
        }
    }
}
