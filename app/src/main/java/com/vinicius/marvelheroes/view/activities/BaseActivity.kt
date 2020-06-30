package com.vinicius.marvelheroes.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vinicius.marvelheroes.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity<V : ViewDataBinding, T : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: T
    lateinit var binding: V

    protected abstract fun getViewModelClass(): Class<T>

    protected abstract fun init()

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.executePendingBindings()

        init()
    }


}