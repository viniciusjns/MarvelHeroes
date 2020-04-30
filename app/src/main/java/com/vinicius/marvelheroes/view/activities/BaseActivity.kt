package com.vinicius.marvelheroes.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vinicius.marvelheroes.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {
//abstract class BaseActivity<V : ViewDataBinding, T : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewModel: T
//    lateinit var binding: V

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    protected abstract fun getViewModelClass(): Class<T>?

    protected abstract fun init()

    abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

//        binding = DataBindingUtil.setContentView(this, getLayout())
//        binding.executePendingBindings()

        getViewModelClass()?.apply {
            viewModel = ViewModelProviders.of(this@BaseActivity, mViewModelFactory).get(this)
        }

        init()
    }


}