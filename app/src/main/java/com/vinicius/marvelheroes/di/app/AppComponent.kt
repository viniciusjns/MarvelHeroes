package com.vinicius.marvelheroes.di.app

import android.app.Application
import com.vinicius.marvelheroes.di.builder.ActivityBuilder
import com.vinicius.marvelheroes.di.builder.ViewModelBuilder
import com.vinicius.marvelheroes.MarvelHeroesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton


@Singleton
@Component(
        modules = [AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            AppModule::class,
            RepositoryModule::class,
            UseCaseModule::class,
            NetworkModule::class,
            ActivityBuilder::class,
            ViewModelBuilder::class
        ]
)
interface AppComponent {
    fun inject(@NotNull application: MarvelHeroesApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}