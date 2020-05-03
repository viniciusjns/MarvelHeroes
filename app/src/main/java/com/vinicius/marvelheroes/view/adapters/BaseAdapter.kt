package com.vinicius.marvelheroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.marvelheroes.model.Hero


abstract class BaseAdapter<T, B : ViewDataBinding>(private val list: List<T>) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<B>>() {

    abstract fun getLayout(): Int

    abstract fun onBind(binding: B, any: T)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = ViewHolder(
        DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        ) as B
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        val any = list[position]
        onBind(holder.getBinding(), any)
    }

    class ViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {

        fun getBinding(): B = binding
    }
}