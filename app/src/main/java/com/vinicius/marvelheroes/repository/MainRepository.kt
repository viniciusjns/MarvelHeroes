package com.vinicius.marvelheroes.repository

import com.vinicius.marvelheroes.model.DataHeroes
import kotlinx.coroutines.Deferred

interface MainRepository {

    suspend fun getHeroes(): DataHeroes
}