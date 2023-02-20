package com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.use_case

import com.yuhdeveloper.cleanarchitecturesample.feature_news.domain.repository.NewsRepository

class GetNewsById(val repository: NewsRepository)