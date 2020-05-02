package com.vinicius.marvelheroes.di.app

import android.app.Application
import android.content.Context
import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.repository.MainRepositoryImpl
import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.service.APIClientImpl
import com.vinicius.marvelheroes.service.HeroesService
import com.vinicius.marvelheroes.viewmodel.MainUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context =
            app.applicationContext
}