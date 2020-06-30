package com.vinicius.marvelheroes.di.app

import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.service.APIClientImpl
import com.vinicius.marvelheroes.service.HeroesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @Provides
    fun providesApiClient(): APIClient = APIClientImpl()

    @Provides
    fun providesHeroesService(retrofit: Retrofit): HeroesService =
        retrofit.create(HeroesService::class.java)

    @Provides
    fun provideRetrofit(apiClient: APIClient): Retrofit =
        apiClient.configure("https://gateway.marvel.com/")

}