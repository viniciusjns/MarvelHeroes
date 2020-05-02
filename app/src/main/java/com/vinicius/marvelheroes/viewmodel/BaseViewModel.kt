package com.vinicius.marvelheroes.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = Main

    private val viewModelJob = SupervisorJob()

    protected val viewModelScope = CoroutineScope(coroutineContext + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}