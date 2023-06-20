package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

data class NewsUseCases(
    val addNews:AddNews,
    val deleteNews:DeleteNews,
    val getNews:GetNews,
    val getNewsById: GetNewsById,
    val updateNewsById:UpdateNewsById,
)