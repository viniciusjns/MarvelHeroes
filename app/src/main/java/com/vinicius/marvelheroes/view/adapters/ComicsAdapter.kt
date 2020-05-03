package com.vinicius.marvelheroes.view.adapters

import com.squareup.picasso.Picasso
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.databinding.ItemComicBinding
import com.vinicius.marvelheroes.model.Comic

class ComicsAdapter(comics: List<Comic>) : BaseAdapter<Comic, ItemComicBinding>(comics) {

    override fun getLayout(): Int = R.layout.item_comic

    override fun onBind(binding: ItemComicBinding, comic: Comic) {
        binding.comic = comic
        Picasso.get()
            .load(comic.thumbnail.getPoster())
            .into(binding.ivComic)
        binding.executePendingBindings()
    }
}