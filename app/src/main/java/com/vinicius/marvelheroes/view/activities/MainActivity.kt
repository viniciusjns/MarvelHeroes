package com.vinicius.marvelheroes.view.activities

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.multistatelayout.MultiStateLayout
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.view.adapters.HeroesColumnAdapter
import com.vinicius.marvelheroes.view.adapters.HeroesListAdapter
import com.vinicius.marvelheroes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var optionsMenu: Menu
    private lateinit var heroesList: List<Hero>

    override fun getLayout(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun init() {
//        binding.viewModel = viewModel
        viewModel.getHeroes()

        viewModel.loadingMutableLiveData.observe(this, Observer {
            if (it)
                mslMain.setState(MultiStateLayout.State.LOADING)
            else
                mslMain.setState(MultiStateLayout.State.CONTENT)
        })

        viewModel.heroesMutableLiveData.observe(this, Observer {
            heroesList = it
            setupList(it)
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
        rvHeroes.adapter = if (isColumn) HeroesColumnAdapter(list) else HeroesListAdapter(list)
        rvHeroes.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        optionsMenu = menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.action_column -> {
            val menuItem: MenuItem = optionsMenu.findItem(R.id.action_list)
            menuItem.isVisible = true
            item.isVisible = false
            setupColumn(heroesList)

            true
        }

        R.id.action_list -> {
            val menuItem: MenuItem = optionsMenu.findItem(R.id.action_column)
            menuItem.isVisible = true
            item.isVisible = false
            setupList(heroesList)

            true
        }

        else -> {
            true
        }
    }
}
