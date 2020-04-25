package com.vinicius.marvelheroes.di.app

import android.util.Log
import com.vinicius.marvelheroes.BuildConfig
import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.service.APIClientImpl
import com.vinicius.marvelheroes.service.HeroesService
import com.vinicius.marvelheroes.utils.Utils
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun providesApiClient(): APIClient = APIClientImpl()

    @Singleton
    @Provides
    fun providesHeroesService(retrofit: Retrofit): HeroesService =
        retrofit.create(HeroesService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(apiClient: APIClient): Retrofit =
        apiClient.configure("https://gateway.marvel.com/")

}