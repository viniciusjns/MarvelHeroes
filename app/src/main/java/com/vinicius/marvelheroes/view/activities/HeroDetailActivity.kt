package com.vinicius.marvelheroes.view.activities

import androidx.lifecycle.Observer
import br.com.zup.multistatelayout.MultiStateLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.view.activities.MainActivity.HeroConstant.HERO_ID
import com.vinicius.marvelheroes.viewmodel.HeroDetailViewModel
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class HeroDetailActivity : BaseActivity<HeroDetailViewModel>() {

    override fun getLayout(): Int = R.layout.activity_hero_detail

    override fun getViewModelClass(): Class<HeroDetailViewModel>? = HeroDetailViewModel::class.java

    override fun init() {
        val heroId: Int = intent.getIntExtra(HERO_ID, 0)

        viewModel.getHeroById(heroId)

        viewModel.heroLiveData.observe(this, Observer {
            when (it.status) {
//                Resource.Status.LOADING -> mslMain.setState(MultiStateLayout.State.LOADING)
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupHeroInfo(it1) }
                    mslMain.setState(MultiStateLayout.State.CONTENT)
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun setupHeroInfo(hero: Hero) {
        tvName.text = hero.name
        Picasso.get()
            .load(hero.thumbnail?.getPoster())
            .into(ivHero)
    }

}
