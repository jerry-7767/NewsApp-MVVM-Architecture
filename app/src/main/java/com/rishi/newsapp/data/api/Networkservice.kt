package com.rishi.newsapp.data.api

import com.rishi.newsapp.data.model.NewsSourceResponse
import com.rishi.newsapp.data.model.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface Networkservice {

    @GET("top-headlines")
    suspend fun getNewsbyCountry(@Query("country") country: String): TopHeadlineResponse

    @GET("top-headlines")
    suspend fun getNewsbyLanguage(@Query("language") language: String): TopHeadlineResponse

      /*  @GET("top-headlines")
        suspend fun getTopheadlines(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String = "c5a9a8f090c64af09ccb4397ffc9390f"
        ): TopHeadlineResponse*/

    @GET("top-headlines")
    suspend fun getTopheadlinesbySources(@Query("sources") sources: String): TopHeadlineResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourceResponse

    @GET("everything")
    suspend fun getTopHeadlinesBySearch(@Query("q") query: String): TopHeadlineResponse

}