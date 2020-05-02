package com.vinicius.marvelheroes.repository

import com.nhaarman.mockitokotlin2.whenever
import com.vinicius.marvelheroes.model.DataHeroes
import com.vinicius.marvelheroes.model.Hero
import com.vinicius.marvelheroes.model.Heroes
import com.vinicius.marvelheroes.rules.TestCoroutineRule
import com.vinicius.marvelheroes.service.HeroesService
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    companion object {
        const val ERROR_MSG_MOCK = "Erro inesperado"
    }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mainRepository: MainRepository
    @Mock
    private lateinit var heroesService: HeroesService

    @Before
    fun setup() {
        mainRepository = MainRepositoryImpl(heroesService)
    }

    @Test
    fun `test get heroes when success`() = testCoroutineRule.runBlockingTest {
        // cenario
        whenever(heroesService.getHeroes()).thenReturn(mockDataHero())

        // acao
        val heroes = mainRepository.getHeroes()

        // verificacao
        assertThat(heroes, `is`(mockHeroes()))
    }

    @Test
    fun `test get heroes when error`() = testCoroutineRule.runBlockingTest {
        // cenario
        whenever(heroesService.getHeroes()).thenReturn(mockDataHero())

        // acao
        val heroes = mainRepository.getHeroes()

        // verificacao
        try {
            heroes[0]
            fail(ERROR_MSG_MOCK)
        } catch (e: Throwable) {
            assertEquals(e.message, ERROR_MSG_MOCK)
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

    private fun mockDataHero(): DataHeroes {
        val results = mockHeroes();
        val heroes = Heroes(results)

        return DataHeroes(heroes)
    }
}