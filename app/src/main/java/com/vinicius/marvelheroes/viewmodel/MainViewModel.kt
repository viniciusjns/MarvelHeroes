package com.vinicius.marvelheroes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val loadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val heroesMutableLiveData: MutableLiveData<List<Hero>> = MutableLiveData()
    val errorMutableLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun getHeroes() {
        viewModelScope.launch {
            loadingMutableLiveData.value = true
            try {
                val heroes = mainRepository.getHeroes()
                heroesMutableLiveData.value = heroes
            } catch (t: Throwable) {
                errorMutableLiveData.value = t
            } finally {
                loadingMutableLiveData.value = false
            }
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