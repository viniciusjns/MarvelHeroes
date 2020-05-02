package com.vinicius.marvelheroes.service

import com.vinicius.marvelheroes.model.Response
import com.vinicius.marvelheroes.model.Hero
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesService {

    @GET("v1/public/characters")
    suspend fun getHeroes(): Response<List<Hero>>

    @GET("v1/public/characters/{heroId}")
    suspend fun getHeroById(@Path("heroId") heroId: Int): Response<List<Hero>>

    @GET("v1/public/characters/{heroId}/comics")
    suspend fun getComicsByHeroId(heroId: Int)
}