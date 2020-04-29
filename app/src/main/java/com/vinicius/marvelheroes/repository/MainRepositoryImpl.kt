package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.service.HeroesService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: HeroesService
) : MainRepository {

    override suspend fun getHeroes(): List<Hero> {
        return service.getHeroes().data.results
    }

}