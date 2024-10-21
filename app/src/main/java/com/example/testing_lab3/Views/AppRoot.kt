package com.example.testing_lab3.Views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.complexnumbersfeature.Views.ComplexNumbersRoot

@Composable
fun AppRoot(){
    var navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = "ComplexNumbers"
    )
    {
        composable(
            "ComplexNumbers"
        )
        {
            ComplexNumbersRoot()
        }
    }
}