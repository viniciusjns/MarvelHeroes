package com.vinicius.marvelheroes.di.app

import com.vinicius.marvelheroes.viewmodel.MainUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMainUseCase(
        mainUseCase: MainUseCase
    ): MainUseCase
}