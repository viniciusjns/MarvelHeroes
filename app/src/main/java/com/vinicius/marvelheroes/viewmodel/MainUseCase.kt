package com.vinicius.marvelheroes.viewmodel

import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun getHeroes(): List<Hero> {
        return withContext(Dispatchers.IO) {
            mainRepository.getHeroes()
        }
    }

}