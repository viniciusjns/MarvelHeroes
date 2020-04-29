package com.vinicius.marvelheroes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.rules.TestCoroutineRule
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    @Mock
    private lateinit var mainRepository: MainRepository
    @Mock
    private lateinit var observerLoading: Observer<Boolean>
    @Mock
    private lateinit var observerError: Observer<Throwable>

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.loadingMutableLiveData.observeForever(observerLoading)
        mainViewModel.errorMutableLiveData.observeForever(observerError)
    }

    @Test
    fun `teste get heroes when success`() = testCoroutineRule.runBlockingTest {
        // cenario
        val heroList = mockHeroes()
        whenever(mainRepository.getHeroes()).thenReturn(heroList)

        // acao
        mainViewModel.getHeroes()
        val value = mainViewModel.heroesMutableLiveData.value

        // verifacao
        assertThat(value, `is`(heroList))
    }

    @Test
    fun `test get heroes when throw exception`() = testCoroutineRule.runBlockingTest {
        // cenario
        val exception = RuntimeException()
        whenever(mainRepository.getHeroes()).thenThrow(exception)

        // acao
        mainViewModel.getHeroes()

        // verificacao
        assertNotNull(mainViewModel.errorMutableLiveData.value)
        assertNull(mainViewModel.heroesMutableLiveData.value)
    }

    @Test
    fun `test loading should change`() = testCoroutineRule.runBlockingTest {
        // cenario
        val bool = false

        // acao
        mainViewModel.getHeroes()

        // verificacao
        val captor = ArgumentCaptor.forClass(Boolean::class.java)
        captor.run {
            verify(observerLoading, times(2)).onChanged(capture())
            assertEquals(bool, value)
        }
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