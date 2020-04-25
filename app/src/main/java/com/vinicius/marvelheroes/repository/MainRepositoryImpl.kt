package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.DataHeroes
import com.vinicius.marvelheroes.service.APIClient
import com.vinicius.marvelheroes.service.HeroesService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: HeroesService
) : MainRepository {

    override suspend fun getHeroes(): DataHeroes {
        return service.getHeroes()
    }

}