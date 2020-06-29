package com.vinicius.marvelheroes.model

data class Thumbnail(
    private val path: String,
    private val extension: String
) {
    fun getPoster(): String? =
        "${path}.${extension}"
}