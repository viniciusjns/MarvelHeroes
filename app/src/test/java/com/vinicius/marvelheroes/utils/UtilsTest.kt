package com.vinicius.marvelheroes.utils

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class UtilsTest {

    @Test
    fun testGenerateMd5WithValidInput() {
        // cenario
        val input = "12345"
        val result = "827ccb0eea8a706c4c34a16891f84e7b";

        // acao
        val s: String? = Utils.md5(input)

        // verificacao
        assertThat(s, `is`(result))
    }

    @Test
    fun testGenerateMd5WithInvalidInput() {
        // cenario
        val input = null
        val result = ""

        // acao
        val s: String? = Utils.md5(input)

        // verificacao
        assertThat(s, `is`(result))
    }
}