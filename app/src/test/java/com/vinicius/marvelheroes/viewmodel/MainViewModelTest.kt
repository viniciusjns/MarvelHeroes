package com.vinicius.marvelheroes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Resource
import com.vinicius.marvelheroes.repository.MainRepository
import com.vinicius.marvelheroes.rules.TestCoroutineRule
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
    private lateinit var observerResource: Observer<Resource<List<Hero>>>

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.heroesLiveData.observeForever(observerResource)
    }

    @Test
    fun `teste get heroes when success`() = testCoroutineRule.runBlockingTest {
        // cenario
        val heroList = mockHeroes()
        whenever(mainRepository.getHeroes()).thenReturn(heroList)

        // acao
        mainViewModel.getHeroes()
        val value = mainViewModel.heroesLiveData.value?.data

        // verifacao
        assertThat(value, `is`(heroList))
        verify(observerResource).onChanged(Resource.success(heroList))
    }

    @Test
    fun `test get heroes when throw exception`() = testCoroutineRule.runBlockingTest {
        // cenario
        val exception = RuntimeException("Erro inesperado")
        whenever(mainRepository.getHeroes()).thenThrow(exception)

        // acao
        mainViewModel.getHeroes()

        // verificacao
        assertNotNull(mainViewModel.heroesLiveData.value?.message)
        assertNull(mainViewModel.heroesLiveData.value?.data)
        verify(observerResource).onChanged(Resource.error(exception.message))
    }

    @Test
    fun `test loading should change`() = testCoroutineRule.runBlockingTest {
        // cenario
//        val bool = false

        // acao
        mainViewModel.getHeroes()

        // verificacao
//        val captor = ArgumentCaptor.forClass(Boolean::class.java)
//        captor.run {
//            verify(observerLoading, times(2)).onChanged(capture())
//            assertEquals(bool, value)
//        }
        verify(observerResource).onChanged(Resource.loading())
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