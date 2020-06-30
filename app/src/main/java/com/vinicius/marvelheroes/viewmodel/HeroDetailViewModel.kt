package com.vinicius.marvelheroes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.vinicius.marvelheroes.model.Comic
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import javax.inject.Inject

class HeroDetailViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val heroLiveData = MutableLiveData<Resource<Hero>>()
    val comicsLiveData = MutableLiveData<Resource<List<Comic>>>()

    fun getHeroById(heroId: Int) {
        viewModelScope.launch {
            heroLiveData.value = Resource.loading()
            try {
                delay(2000)
                val heroes = mainRepository.getHeroById(heroId)
                heroLiveData.value = Resource.success(heroes)
            } catch (t: Throwable) {
                heroLiveData.value = Resource.error(t.message)
            } finally { }
        }
    }

    fun getComicsByHeroId(heroId: Int) {
        viewModelScope.launch {
            try {
                val comics = mainRepository.getComicsByHeroId(heroId)
                comicsLiveData.value = Resource.success(comics)
            } catch (t: Throwable) {
                heroLiveData.value = Resource.error(t.message)
            } finally { }
        }
    }
}