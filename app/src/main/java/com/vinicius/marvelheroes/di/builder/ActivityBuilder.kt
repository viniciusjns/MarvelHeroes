package com.vinicius.marvelheroes.di.builder

import com.vinicius.marvelheroes.di.Activity
import com.vinicius.marvelheroes.view.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Activity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}