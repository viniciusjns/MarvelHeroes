package com.vinicius.marvelheroes.view.activities

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.view.adapters.HeroesAdapter
import com.vinicius.marvelheroes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun init() {
        viewModel.getHeroes()

        viewModel.heroesMutableLiveData.observe(this, Observer {
            setupList(it)
        })
    }

    private fun setupList(list: List<Hero>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvHeroes.adapter = HeroesAdapter(list)
        rvHeroes.layoutManager = layoutManager
    }


}
