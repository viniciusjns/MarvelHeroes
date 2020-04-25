package com.vinicius.marvelheroes.viewmodel

import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainUseCase(
    private val mainRepository: MainRepository
) {

    suspend fun getHeroes(): List<Hero> {
        return withContext(Dispatchers.IO) {
            mainRepository.getHeroes().data.results
        }
    }

}