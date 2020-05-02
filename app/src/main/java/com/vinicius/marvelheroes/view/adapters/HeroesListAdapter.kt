package com.vinicius.marvelheroes.view.adapters

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.databinding.ItemHeroListBinding
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.utils.extensions.paint


class HeroesListAdapter(heroes: List<Hero>, private val onClickHero: OnClickHero) : BaseAdapter<ItemHeroListBinding>(heroes) {

    override fun getLayout(): Int = R.layout.item_hero_list

    override fun onBind(binding: ItemHeroListBinding, hero: Hero) {
        binding.root.setOnClickListener {
            onClickHero.onClick(hero)
        }
        binding.hero = hero
        Picasso.get()
            .load(hero.thumbnail?.getPoster())
            .into(binding.ivHero, object : Callback {
                override fun onSuccess() {
                    paintIndicatorByHeroImg(binding)
                }

                override fun onError(e: Exception?) { }

            })
        binding.executePendingBindings()
    }

    private fun paintIndicatorByHeroImg(binding: ItemHeroListBinding) {
        val bitmap = binding.ivHero.drawable.toBitmap()
        val background: Drawable = binding.indicator.background.mutate()
        background.paint(bitmap)
    }
}