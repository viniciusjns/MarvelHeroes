package com.vinicius.marvelheroes.view.activities

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.multistatelayout.MultiStateLayout
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.databinding.ActivityHeroDetailBinding
import com.vinicius.marvelheroes.model.Comic
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.view.activities.MainActivity.HeroConstant.HERO_ID
import com.vinicius.marvelheroes.view.adapters.ComicsAdapter
import com.vinicius.marvelheroes.viewmodel.HeroDetailViewModel
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.activity_main.*


class HeroDetailActivity : BaseActivity<ActivityHeroDetailBinding, HeroDetailViewModel>() {

    override fun getLayout(): Int = R.layout.activity_hero_detail

    override fun getViewModelClass(): Class<HeroDetailViewModel>? = HeroDetailViewModel::class.java

    override fun init() {
        setupToolbar()
        setupAppbarLayout()
        val heroId: Int = intent.getIntExtra(HERO_ID, 0)

        viewModel.getHeroById(heroId)
        viewModel.getComicsByHeroId(heroId)

        viewModel.heroLiveData.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> mslDetail.setState(MultiStateLayout.State.LOADING)
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupHeroInfo(it1) }
                    mslDetail.setState(MultiStateLayout.State.CONTENT)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        viewModel.comicsLiveData.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupComic(it1) }
                }
                Resource.Status.ERROR -> {}
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun setupAppbarLayout() {
        collapseLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite))
        collapseLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
    }

    private fun setupHeroInfo(hero: Hero) {
        binding.hero = hero
    }

    private fun setupComic(comics: List<Comic>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvComic.adapter = ComicsAdapter(comics)
        rvComic.layoutManager = layoutManager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> true
    }

}
