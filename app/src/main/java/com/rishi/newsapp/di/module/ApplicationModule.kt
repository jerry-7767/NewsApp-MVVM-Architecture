package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.data.api.ApiKeyInterceptor
import com.rishi.newsapp.data.api.CacheInterceptor
import com.rishi.newsapp.data.api.ForceCacheInterceptor
import com.rishi.newsapp.data.api.Networkservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseurl(): String = "https://newsapi.org/v2/"

    @NetworkApiKey
    @Provides
    fun provideNetworkApiKey(): String = "c5a9a8f090c64af09ccb4397ffc9390f"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApiKeyInterceptor(@NetworkApiKey networkApiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(networkApiKey)

    @Singleton
    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
        apiKeyInterceptor: ApiKeyInterceptor,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor,
        loggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .cache(Cache(File(appContext.cacheDir, "http-cache"), 10L * 1024L * 1024L))
        .addInterceptor(apiKeyInterceptor)
        .addNetworkInterceptor(cacheInterceptor)
        .addInterceptor(forceCacheInterceptor)
        .addInterceptor(loggerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideCacheInterceptor(): CacheInterceptor = CacheInterceptor()

    @Provides
    @Singleton
    fun provideForceInterceptor(@ApplicationContext appContext: Context): ForceCacheInterceptor =
        ForceCacheInterceptor(appContext)


    @Provides
    @Singleton
    fun provideNetworkservice(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Networkservice {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(Networkservice::class.java)
    }

}