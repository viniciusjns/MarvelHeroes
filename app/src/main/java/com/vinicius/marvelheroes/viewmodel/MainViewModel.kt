package com.vinicius.marvelheroes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val heroesLiveData = MutableLiveData<Resource<List<Hero>>>()

    fun getHeroes() {
        viewModelScope.launch {
            heroesLiveData.value = Resource.loading()
            try {
                val heroes = mainRepository.getHeroes()
                heroesLiveData.value = Resource.success(heroes)
            } catch (t: Throwable) {
                heroesLiveData.value = Resource.error(t.message)
            } finally { }
        }
    }

//    fun getHeroes() {
//        viewModelScope.launch {
//            loadingMutableLiveData.value = true
//            runCatching {
//                heroesMutableLiveData.value = mainUseCase.getHeroes()
//                loadingMutableLiveData.value = false
//            }.onFailure {
//                errorMutableLiveData.value = it
//            }
//        }
//    }
}