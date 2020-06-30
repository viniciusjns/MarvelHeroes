package com.vinicius.marvelheroes

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MarvelHeroesApplication : MultiDexApplication() {

    companion object {
        var mInstance: MarvelHeroesApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}