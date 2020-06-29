package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.Comic
import com.vinicius.marvelheroes.model.Hero

interface MainRepository {

    suspend fun getHeroes(): List<Hero>

    suspend fun getHeroById(heroId: Int): Hero

    suspend fun getComicsByHeroId(heroId: Int): List<Comic>
}