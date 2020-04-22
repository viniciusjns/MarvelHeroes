package com.vinicius.marvelheroes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(), CoroutineScope {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val heroesMutableLiveData: MutableLiveData<List<Hero>> = MutableLiveData()
    val errorMutableLiveData: MutableLiveData<Throwable> = MutableLiveData()

    fun getHeroes() {
        viewModelScope.launch {
            loading.value = true
            try {
                if (heroesMutableLiveData.value == null) {
                    val teste = mainRepository.getHeroes().await()
                    heroesMutableLiveData.value = teste.data.results
                }
                loading.value = false
            } catch (t: Throwable) {
                errorMutableLiveData.value = t
            } finally {
                loading.value = false
            }
        }
    }
}