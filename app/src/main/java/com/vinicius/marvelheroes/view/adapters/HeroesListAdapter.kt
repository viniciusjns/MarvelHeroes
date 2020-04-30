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


class HeroesListAdapter(heroes: List<Hero>) : BaseAdapter<ItemHeroListBinding>(heroes) {

    override fun getLayout(): Int = R.layout.item_hero_list

    override fun onBind(binding: ItemHeroListBinding, hero: Hero) {
        binding.tvName.text = hero.name
        Picasso.get()
            .load("${hero.thumbnail?.path}.${hero.thumbnail?.extension}")
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
        Palette.from(bitmap).generate {
            val defaultColor = 0x000000
            val color = it?.getVibrantColor(defaultColor)
            color?.let { it1 ->
                if (background is ShapeDrawable) {
                    background.paint.color = it1
                } else if (background is GradientDrawable) {
                    background.setColor(it1)
                } else if (background is ColorDrawable) {
                    background.color = it1
                }
            }
        }
    }
}