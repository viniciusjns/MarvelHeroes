package com.vinicius.marvelheroes.di.app

import android.app.Application
import android.content.Context
import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.repository.MainRepositoryImpl
import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.service.APIClientImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {
    @Provides
    @Singleton
    fun provideContext(app: Application): Context =
            app.applicationContext

    @Provides
    @Singleton
    fun providesApiClient(): APIClient = APIClientImpl()

    @Provides
    fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl):
            MainRepository = mainRepositoryImpl
}