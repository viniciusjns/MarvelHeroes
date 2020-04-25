package com.vinicius.marvelheroes.view.adapters

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.databinding.ItemHeroBinding
import com.vinicius.marvelheroes.model.Hero


class HeroesAdapter(private val heroes: List<Hero>) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = HeroesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_hero,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bind(hero)
    }

    class HeroesViewHolder(val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.tvName.text = hero.name
            Picasso.get()
                .load("${hero.thumbnail?.path}.${hero.thumbnail?.extension}")
                .into(binding.ivHero, object : Callback {
                    override fun onSuccess() {
                        paintIndicatorByHeroImg()
                    }

                    override fun onError(e: Exception?) {

                    }

                })
            binding.executePendingBindings()
        }

        private fun paintIndicatorByHeroImg() {
            val bitmap = binding.ivHero.drawable.toBitmap()
            val background: Drawable = binding.indicator.background
            Palette.from(bitmap).generate {
                val defaultColor = 0x000000
                val color = it?.getVibrantColor(defaultColor)
                color?.let { it1 ->
                    //                                binding.view.setBackgroundColor(it1)
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
}