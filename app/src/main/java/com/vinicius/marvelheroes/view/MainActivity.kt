package com.vinicius.marvelheroes.view

import androidx.lifecycle.Observer
import com.vinicius.marvelheroes.R
import com.vinicius.marvelheroes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun init() {
        viewModel.getHeroes()

        viewModel.heroesMutableLiveData.observe(this, Observer {
            text.text = it[0].name
        })
    }


}
