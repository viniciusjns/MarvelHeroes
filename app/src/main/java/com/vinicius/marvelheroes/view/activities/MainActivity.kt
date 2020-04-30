package com.vinicius.marvelheroes.view.activities

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.multistatelayout.MultiStateLayout
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.view.adapters.HeroesColumnAdapter
import com.vinicius.marvelheroes.view.adapters.HeroesListAdapter
import com.vinicius.marvelheroes.view.adapters.OnClickHero
import com.vinicius.marvelheroes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(), OnClickHero {

    private lateinit var optionsMenu: Menu

    override fun getLayout(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun init() {
//        binding.viewModel = viewModel
        viewModel.getHeroes()

        viewModel.resourceLiveData.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> mslMain.setState(MultiStateLayout.State.LOADING)
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> setupList(it1) }
                    mslMain.setState(MultiStateLayout.State.CONTENT)
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun setupList(list: List<Hero>) {
        setupRecyclerView(list, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupColumn(list: List<Hero>) {
        setupRecyclerView(list, LinearLayoutManager.HORIZONTAL, true)
    }

    private fun setupRecyclerView(list: List<Hero>, orientation: Int, isColumn: Boolean) {
        val layoutManager = LinearLayoutManager(this, orientation, false)
        rvHeroes.adapter = if (isColumn) HeroesColumnAdapter(list, this)
        else HeroesListAdapter(list, this)
        rvHeroes.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        optionsMenu = menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_column -> {
            optionsMenu.findItem(R.id.action_list).apply {
                this.isVisible = true
            }
            item.isVisible = false
            viewModel.resourceLiveData.value?.data?.let { setupColumn(it) }

            true
        }

        R.id.action_list -> {
            optionsMenu.findItem(R.id.action_column).apply {
                this.isVisible = true
            }
            item.isVisible = false
            viewModel.resourceLiveData.value?.data?.let { setupList(it) }

            true
        }

        else -> true

    }

    override fun onClick(hero: Hero) {
        val intent = Intent(this, HeroDetailActivity::class.java)
        intent.putExtra("hero", "${hero.thumbnail?.path}.${hero.thumbnail?.extension}")

        startActivity(intent)
    }
}
