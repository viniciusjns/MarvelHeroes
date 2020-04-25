package com.vinicius.marvelheroes.di.app

import com.vinicius.marvelheroes.repository.MainRepositoryImpl
import com.vinicius.marvelheroes.viewmodel.MainUseCase
import dagger.Module
import dagger.Provides

@Module
open class UseCaseModule {

    @Provides
    fun provideMainUseCase(mainRepository: MainRepositoryImpl):
            MainUseCase = MainUseCase(mainRepository)
}