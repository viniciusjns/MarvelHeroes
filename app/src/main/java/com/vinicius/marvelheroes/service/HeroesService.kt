package com.vinicius.marvelheroes.service

import com.vinicius.marvelheroes.model.DataHeroes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HeroesService {

    @GET("v1/public/characters")
    suspend fun getHeroes(): DataHeroes
}