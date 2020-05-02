package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.Hero

interface MainRepository {

    suspend fun getHeroes(): List<Hero>

    suspend fun getHeroById(heroId: Int): Hero
}