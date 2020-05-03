package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.Comic
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.service.HeroesService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: HeroesService
) : MainRepository {

    override suspend fun getHeroes(): List<Hero> =
        service.getHeroes().data.results

    override suspend fun getHeroById(heroId: Int): Hero =
        service.getHeroById(heroId).data.results[0]

    override suspend fun getComicsByHeroId(heroId: Int): List<Comic> =
        service.getComicsByHeroId(heroId).data.results

}