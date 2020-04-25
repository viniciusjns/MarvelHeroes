package com.vinicius.marvelheroes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import com.vinicius.marvelheroes.model.DataHeroes
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Heroes
import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.rules.TestCoroutineRule
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var mainRepository: MainRepository
    @Mock
    private lateinit var heroesLiveData: Observer<List<Hero>>
    @Mock
    private lateinit var loadingLiveData: Observer<Boolean>
    @Mock
    private lateinit var errorLiveData: Observer<Throwable>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.heroesMutableLiveData.observeForever(heroesLiveData)
        mainViewModel.loadingMutableLiveData.observeForever(loadingLiveData)
        mainViewModel.errorMutableLiveData.observeForever(errorLiveData)
    }

    @Test
    fun `heroes livedata should return some value test`() = testCoroutineRule.runBlockingTest {
        // cenario
        val heroList = mockHeroes()
        val heroes = Heroes(heroList)
        val dataHeroes = DataHeroes(heroes)

        // acao
        whenever(mainRepository.getHeroes()).thenReturn(dataHeroes)
        mainViewModel.getHeroes()
        val value = mainViewModel.heroesMutableLiveData.value

        // verifacao
        assertThat(value, `is`(heroList))
    }

    private fun mockHeroes() = (1..10).map {
        Hero(
            id = it,
            name = "Hero $it",
            description = "Desc $it",
            thumbnail = null
        )
    }
}