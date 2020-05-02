package com.vinicius.marvelheroes.service

import retrofit2.Retrofit

interface APIClient {
    fun configure(baseUrl: String): Retrofit
}