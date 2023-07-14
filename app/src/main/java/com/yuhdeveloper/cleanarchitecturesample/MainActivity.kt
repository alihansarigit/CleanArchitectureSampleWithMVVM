package com.yuhdeveloper.cleanarchitecturesample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item.NewsAddScreen
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_detail.NewsDetailScreen
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit.NewsEditScreen
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed.FeedScreen
import com.yuhdeveloper.cleanarchitecturesample.ui.theme.CleanArchitectureSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.onBackground
            ) {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Home" ){
                    composable("Home"){
                        FeedScreen(navController = navController)
                    }

                    composable("AddNote"){
                        NewsAddScreen(navController = navController)
                    }

                    composable("EditNews?newsId={newsId}",
                    arguments = listOf(
                        navArgument("newsId"){
                            type = NavType.IntType
                            defaultValue = -1
                        })){
                        NewsEditScreen(navController = navController)
                    }

                    composable("NewsDetail?newsId={newsId}",
                    arguments = listOf(navArgument("newsId"){
                        type = NavType.IntType
                        defaultValue = -1
                    })){
                        NewsDetailScreen(navController = navController)
                    }
                }


            }
        }
    }
}
