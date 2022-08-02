package com.example.weather

import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.core.Is.`is` as Is

class RomanNumeralToIntTest
{

    val sut = RomanNumeralToInt()

    @Test
    fun getIntArray_III_return111()
    {
        // given:
        val s = "III"
        // when:
        val intArray = sut.getIntArray(s)
        printIntArray(intArray)
        // then:

    }

    @Test
    fun getNumber_III_return3()
    {
        // given:
        val s = "III"
        // when:
        val result = sut.romanToInt(s)
        // then:
        assertThat(result, Is(3))

    }

    @Test
    fun getNumber_input_returnResult()
    {
        // given:
        val s = "XXIV"
        // when:
        val result = sut.romanToInt(s)
        // then:
        assertThat(result, Is(24))

    }

    @Test
    fun getNumber_input_returnResult2()
    {
        // given:
        val s = "MCMXCIV"
        // when:
        val result = sut.romanToInt(s)
        // then:
        assertThat(result, Is(1994))

    }

    @Test
    fun getIntArray_input_returnResult2()
    {
        // given:
        val s = "MCMXCIV"
        // when:
        val result = sut.getIntArray(s)
        printIntArray(result)
        // then:


    }

    private fun printIntArray(intArray: IntArray)
    {
        for (i in intArray.indices)
        {
            val s = intArray[i].toString() + ", "
            print(s)
        }
    }
}