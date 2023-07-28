package com.yuhdeveloper.cleanarchitecturesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_add_item.NewsAddScreen
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_edit.NewsEditScreen
import com.yuhdeveloper.cleanarchitecturesample.feature_news.presentation.news_feed.FeedScreen
import dagger.hilt.android.AndroidEntryPoint

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

                }
            }
        }
    }
}
