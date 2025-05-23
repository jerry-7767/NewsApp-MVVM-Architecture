package com.rishi.newsapp.data.repository

import com.rishi.newsapp.data.Local.DatabaseService
import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable
import com.rishi.newsapp.data.api.Networkservice
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.model.Language
import com.rishi.newsapp.data.model.toArticleTable
import com.rishi.newsapp.data.model.toSourcelistTable
import com.rishi.newsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopheadlineRepository @Inject constructor(
    private val networkservice: Networkservice,
    private val databaseService: DatabaseService
) {

    fun getSourceslits(): Flow<List<SourceListTable>> {
        return flow {
            emit(networkservice.getNewsSources())
        }.map {
            it.sources.map { sourcesList ->
                sourcesList.toSourcelistTable()
            }
        }.flatMapConcat { sourceListTable ->
            flow { emit(databaseService.deleteandinsertallSourcelist(sourceListTable)) }
        }.flatMapConcat {
            databaseService.getSourcelist()
        }
    }

    fun getSourcelistFromDB(): Flow<List<SourceListTable>> {
        return databaseService.getSourcelist()
    }

    fun getNewsByCountryOffline(): Flow<List<ArticleTable>> {
        return flow {
            emit(networkservice.getNewsbyCountry("us"))
        }.map {
            it.articles.map { sourcesList ->
                sourcesList.toArticleTable()
            }
        }.flatMapConcat { sourceListTable ->
            flow { emit(databaseService.deleteAllAndInsertAllNews(sourceListTable)) }
        }.flatMapConcat {
            databaseService.getArticles()
        }
    }

    fun getNewsFromDB(): Flow<List<ArticleTable>> {
        return databaseService.getArticles()
    }


    fun getNewsByCountry(country: String): Flow<List<ArticleTable>> {
        return flow {
            emit(networkservice.getNewsbyCountry(country))
        }.map {
            it.articles.map{article ->
                article.toArticleTable()
            }
        }
    }

    fun getSearchNews(query: String): Flow<List<ArticleTable>> {
        return flow {
            emit(networkservice.getTopHeadlinesBySearch(query))
        }.map {
            it.articles.map{article ->
                article.toArticleTable()
            }
        }
    }

    fun getNewsby_Language(language: String): Flow<List<ArticleTable>> {
        return flow {
            emit(networkservice.getNewsbyLanguage(language))
        }.map {
            it.articles.map{article ->
                article.toArticleTable()
            }
        }
    }

    fun gettopheadlinesbySources(sources: String): Flow<List<ArticleTable>> {
        return flow {
            emit(networkservice.getTopheadlinesbySources(sources))
        }.map {
            it.articles.map{article ->
                article.toArticleTable()
            }
        }
    }

    fun getCountrylist(): Flow<List<Country>> {
        return flow {
            emit(Constants.COUNTRY_LIST)
        }.map {
            it
        }
    }

    fun getLanguageList(): Flow<List<Language>> {
        return flow {
            emit(Constants.LANGUAGE_LIST)
        }.map {
            it
        }
    }

}