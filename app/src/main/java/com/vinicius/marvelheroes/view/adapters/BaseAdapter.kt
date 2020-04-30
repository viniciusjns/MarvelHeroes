package com.vinicius.marvelheroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.marvelheroes.model.Hero


abstract class BaseAdapter<B : ViewDataBinding>(private val heroes: List<Hero>) :
    RecyclerView.Adapter<BaseAdapter.HeroesViewHolder<B>>() {

    abstract fun getLayout(): Int

    abstract fun onBind(binding: B, hero: Hero)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = HeroesViewHolder(
        DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        ) as B
    )

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: HeroesViewHolder<B>, position: Int) {
        val hero = heroes[position]
        onBind(holder.getBinding(), hero)
    }

    class HeroesViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {

        fun getBinding(): B = binding
    }
}