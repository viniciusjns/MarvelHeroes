package com.vinicius.marvelheroes.di.app

import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun provideMainRepository(mainRepository: MainRepositoryImpl):
            MainRepository = mainRepository
}