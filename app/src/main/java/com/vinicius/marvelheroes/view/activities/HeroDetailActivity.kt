package com.vinicius.marvelheroes.view.activities

import com.squareup.picasso.Picasso
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.viewmodel.HeroDetailViewModel
import kotlinx.android.synthetic.main.activity_hero_detail.*

class HeroDetailActivity : BaseActivity<HeroDetailViewModel>() {

    override fun getLayout(): Int = R.layout.activity_hero_detail

    override fun getViewModelClass(): Class<HeroDetailViewModel>? = HeroDetailViewModel::class.java

    override fun init() {
        val hero = intent.getStringExtra("hero")
        Picasso.get()
            .load(hero)
            .into(ivHero)
    }

}
