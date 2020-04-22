package com.vinicius.marvelheroes.model

class Hero(val id: Int,
           val name: String,
           val description: String,
           val thumbnail: Thumbnail) {

    data class Thumbnail(val path: String,
                         val extension: String)
}