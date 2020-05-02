package com.vinicius.marvelheroes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val resourceLiveData = MutableLiveData<Resource<List<Hero>>>()

    fun getHeroes() {
        viewModelScope.launch {
            resourceLiveData.value = Resource.loading()
            try {
                val heroes = mainRepository.getHeroes()
                resourceLiveData.value = Resource.success(heroes)
            } catch (t: Throwable) {
                resourceLiveData.value = Resource.error(t.message)
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