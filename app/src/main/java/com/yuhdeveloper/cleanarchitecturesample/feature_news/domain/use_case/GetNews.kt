package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository

class GetNews(val repository: NewsRepository)