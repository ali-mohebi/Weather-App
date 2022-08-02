package com.example.weather

class RomanNumeralToInt
{
    fun romanToInt(s: String): Int
    {
        if(s.isEmpty()) return 0
        val intArray = getIntArray(s)
        var result = 0
        for (i in 0 until intArray.size - 1)
        {
            if (intArray[i] < intArray[i + 1])
                result -= intArray[i]
            else
                result += intArray[i]
        }
        result += intArray[intArray.size - 1]
        return result
    }

    fun getIntArray(s: String): IntArray
    {
        val intArray = IntArray(s.length)
        for (i in s.indices)
        {
            val number =
                when (s[i])
                {
                    'I' -> 1
                    'V' -> 5
                    'X' -> 10
                    'L' -> 50
                    'C' -> 100
                    'D' -> 500
                    'M' -> 1000
                    else -> 0
                }
            intArray[i] = number
        }
        return intArray
    }
}