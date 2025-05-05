package com.rishi.newsapp.data.repository

import com.rishi.newsapp.data.api.Networkservice
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.model.TopHeadlineResponse
import com.rishi.newsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.rishi.newsapp.data.model.SourcesList
import javax.inject.Inject

class TopheadlineRepository @Inject constructor(private val networkservice: Networkservice) {

/*    fun getTopheadlinenews(country: String): Flow<List<Article>> {
        return flow {
            emit(networkservice.getTopheadlines(country))
        }.map {
            it.articles
        }
    }*/

    fun getSourceslits(): Flow<List<SourcesList>>{
        return flow {
            emit(networkservice.getNewsSources())
        }.map {
            it.sources
        }
    }

    fun getTotalresult(country: String): Flow<TopHeadlineResponse> {
        return flow {
            emit(networkservice.getNewsbyCountry(country))
        }.map {
            it
        }
    }

    fun getSearchNews(query: String): Flow<TopHeadlineResponse> {
        return flow {
            emit(networkservice.getTopHeadlinesBySearch(query))
        }.map {
            it
        }
    }

    fun getNewsby_Language(language: String): Flow<TopHeadlineResponse> {
        return flow {
            emit(networkservice.getNewsbyLanguage(language))
        }.map {
            it
        }
    }

    fun gettopheadlinesbySources(sources: String): Flow<TopHeadlineResponse> {
        return flow {
            emit(networkservice.getTopheadlinesbySources(sources))
        }.map {
            it
        }
    }

    fun getCountrylist(): Flow<List<Country>>{
        return flow {
            emit(Constants.COUNTRY_LIST)
        }.map {
            it
        }
    }

}