package com.example.complexnumbersfeature.ViewModels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.math.RoundingMode

internal fun Float.withSigns(signs: Int) : Float{
    return BigDecimal(this.toDouble()).setScale(signs, RoundingMode.HALF_EVEN).toFloat()
}

internal fun getDivisionResult(a1: Float, a2: Float, b1: Float, b2: Float) : String{
    return try {
        val real = (a1 * a2 + b1 * b2) / (a2 * a2 + b2 * b2)
        val image = (a2 * b1 - a1 * b2) / (a2 * a2 + b2 * b2)
        if (image < 0)
            "${real.withSigns(2)} - ${-image.withSigns(2)}"
        else
            "${real.withSigns(2)} + ${image.withSigns(2)}"
    }
    catch (e: NumberFormatException){
        "error: cannot calculate"
    }
    catch (e: Exception)
    {
        "error: unknown(${e.message})"
    }
}

internal class ComplexNumbersViewModel : ViewModel() {
    var aRealText by mutableStateOf("")
        private set
    val aRealValue by derivedStateOf {
        aRealText.toFloatOrNull() ?: when(aRealText){
            "e" -> Math.E.toFloat()
            "pi" -> Math.PI.toFloat()
            else -> null
        }
    }

    var aImageText by mutableStateOf("")
        private set
    val aImageValue by derivedStateOf {
        aImageText.toFloatOrNull() ?: when(aImageText){
            "e" -> Math.E.toFloat()
            "pi" -> Math.PI.toFloat()
            else -> null
        }
    }

    var bRealText by mutableStateOf("")
        private set
    val bRealValue by derivedStateOf {
        bRealText.toFloatOrNull() ?: when(bRealText){
            "e" -> Math.E.toFloat()
            "pi" -> Math.PI.toFloat()
            else -> null
        }
    }

    var bImageText by mutableStateOf("")
        private set
    val bImageValue by derivedStateOf {
        bImageText.toFloatOrNull() ?: when(bImageText){
            "e" -> Math.E.toFloat()
            "pi" -> Math.PI.toFloat()
            else -> null
        }
    }

    var divisionMode by mutableStateOf("A / B")
    var plusminusAMode by mutableStateOf("+")
    var plusminusBMode by mutableStateOf("+")


    val resultValue by derivedStateOf {
        var (a1, a2, b1, b2) = listOf(0f,0f,0f,0f)
        try {
            a1 = aRealValue!!
            a2 = bRealValue!!
            b1 = aImageValue!! * if (plusminusAMode == "+") 1 else -1
            b2 = bImageValue!! * if (plusminusBMode == "+") 1 else -1
            if (divisionMode == "B / A") {
                a1 = a2.also { a2 = a1 }
                b1 = b2.also { b2 = b1 }
            }
            getDivisionResult(a1, a2, b1, b2)
        }
        catch (e: NullPointerException){
            "error: incorrect data"
        }
    }


    fun updateARealText(str: String){
        //some checks(in this scenario not required)
        aRealText = str
    }
    fun updateAImageText(str: String){
        aImageText = str
    }

    fun updateBRealText(str: String){
        //some checks(in this scenario not required)
        bRealText = str
    }
    fun updateBImageText(str: String){
        bImageText = str
    }

    fun togglePlusMinusAMode(){
        plusminusAMode = when(plusminusAMode){
            "+" -> "-"
            else -> "+"
        }
    }
    fun togglePlusMinusBMode(){
        plusminusBMode = when(plusminusBMode){
            "+" -> "-"
            else -> "+"
        }
    }
    fun toggleDivisionMode(){
        divisionMode = when(divisionMode){
            "A / B" -> "B / A"
            else -> "A / B"
        }
    }
}