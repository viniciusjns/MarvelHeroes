package com.vinicius.marvelheroes

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.vinicius.marvelheroes.di.app.AppComponent
import com.vinicius.marvelheroes.di.app.AppModule
import com.vinicius.marvelheroes.di.app.DaggerAppComponent
import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.BuildConfig
import com.vinicius.marvelheroes.di.app.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class MarvelHeroesApplication : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    companion object {
        var mInstance: MarvelHeroesApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initInjector()
    }

    private fun initInjector() {
        appComponent = getDaggerAppComponent()
        appComponent.inject(this)
    }

    protected open fun getDaggerAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}