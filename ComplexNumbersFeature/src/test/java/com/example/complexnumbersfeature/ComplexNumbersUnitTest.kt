package com.example.complexnumbersfeature

import com.example.complexnumbersfeature.ViewModels.getDivisionResult
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ComplexNumbersUnitTest {
    @Test
    fun zeroDivision() {
        val a1 = 5f
        val b1 = -7f

        val a2 = 0f
        val b2 = 0f

        //========================================================

        val result = getDivisionResult(a1, a2, b1, b2)

        //========================================================

        assertEquals("error: cannot calculate", result)
    }
    @Test
    fun goodCalculation() {
        val a1 = 3f
        val b1 = 7f

        val a2 = 4f
        val b2 = -1f

        //========================================================

        val result = getDivisionResult(a1, a2, b1, b2)

        //========================================================

        assertEquals("0.29 + 1.82", result)
    }
    @Test
    fun giantCalculation() {
        val a1 = 3123123412235423542345361234234f
        val b1 = 72354f

        val a2 = 42354671234f
        val b2 = -14598612351235125323151234234587f

        //========================================================

        val result = getDivisionResult(a1, a2, b1, b2)

        //========================================================

        assertEquals("error: cannot calculate", result)
    }
    @Test
    fun mathConstants() {
        val a1 = Math.E.toFloat()
        val b1 = -92f

        val a2 = 5f
        val b2 = -Math.PI.toFloat()

        //========================================================

        val result = getDivisionResult(a1, a2, b1, b2)

        //========================================================

        assertEquals("8.68 - 12.95", result)
    }
}