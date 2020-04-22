package com.vinicius.marvelheroes.service

import retrofit2.Retrofit

interface APIClient {
    fun getRetrofit(): Retrofit
    fun configure(baseUrl: String)
}