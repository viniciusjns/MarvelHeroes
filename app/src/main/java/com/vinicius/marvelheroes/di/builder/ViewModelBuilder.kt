package com.vinicius.marvelheroes.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinicius.marvelheroes.di.ViewModelKey
import com.vinicius.marvelheroes.di.ViewModelProviderFactory
import com.vinicius.marvelheroes.viewmodel.HeroDetailViewModel
import com.vinicius.marvelheroes.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeroDetailViewModel::class)
    abstract fun bindHeroDetailViewModel(heroDetailViewModel: HeroDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}