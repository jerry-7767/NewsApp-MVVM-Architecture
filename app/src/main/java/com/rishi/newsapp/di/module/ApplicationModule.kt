package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.data.api.ApiKeyInterceptor
import com.rishi.newsapp.data.api.CacheInterceptor
import com.rishi.newsapp.data.api.ForceCacheInterceptor
import com.rishi.newsapp.data.api.Networkservice
import com.rishi.newsapp.di.ApplicationContext
import com.rishi.newsapp.di.BaseUrl
import com.rishi.newsapp.di.NetworkApiKey
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MVVMApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

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
        apiKeyInterceptor: ApiKeyInterceptor,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor,
        loggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient()
            .newBuilder()
            .cache(Cache(File(application.cacheDir, "http-cache"), 10L * 1024L * 1024L))
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
    fun provideForceInterceptor(): ForceCacheInterceptor = ForceCacheInterceptor(application)


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