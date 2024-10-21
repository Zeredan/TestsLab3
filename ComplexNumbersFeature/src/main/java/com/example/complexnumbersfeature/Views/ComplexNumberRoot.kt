package com.example.complexnumbersfeature.Views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.complexnumbersfeature.ViewModels.ComplexNumbersViewModel

@Composable
fun ComplexNumbersRoot(){
    val vm = viewModel<ComplexNumbersViewModel>()
    val snackbarHostState = remember{SnackbarHostState()}

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    )
    { pv ->
        val focusManager = LocalFocusManager.current
        pv
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(object : ShaderBrush() {
                    override fun createShader(size: Size): Shader {
                        return LinearGradientShader(
                            Offset.Zero,
                            Offset(size.width, size.height / 2),
                            listOf(
                                Color.Gray,
                                Color.DarkGray
                            )
                        )
                    }
                })
                .pointerInput(1) {
                    detectTapGestures {
                        focusManager.clearFocus()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                Text("A: ", fontSize = 24.sp, color = Color.Green)
                OutlinedTextField(
                    modifier = Modifier.width(100.dp),
                    value = vm.aRealText,
                    onValueChange = {
                                    vm.updateARealText(it)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(200, 200, 200),
                        focusedContainerColor = Color(240, 240, 240),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = if (vm.aRealValue != null) Color(0, 128, 0) else Color.Red
                    )
                )
                AnimatedContent(
                    vm.plusminusAMode,
                    label = "plusMinus",
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = fadeIn(tween(300)),
                            initialContentExit = fadeOut(tween(300))
                        )
                    }
                )
                { k ->
                    val animatedColor by rememberInfiniteTransition().animateColor(
                        initialValue = if (k == "+") Color.Yellow else Color.Green,
                        targetValue = if (k == "+") Color.Red else Color.Blue,
                        animationSpec = infiniteRepeatable(
                            tween(6000),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "color"
                    )
                    animatedColor
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(object : ShaderBrush() {
                                override fun createShader(size: Size): Shader {
                                    return LinearGradientShader(
                                        Offset.Zero,
                                        Offset(size.width, size.height / 2),
                                        listOf(
                                            animatedColor,
                                            animatedColor.copy(
                                                red = 1f - animatedColor.green,
                                                green = 1f - animatedColor.blue,
                                                blue = 1f - animatedColor.red
                                            )
                                        )
                                    )
                                }
                            })
                            .clickable {
                                vm.togglePlusMinusAMode()
                            }
                            .padding(20.dp, 10.dp, 20.dp, 10.dp),
                        text = k,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.width(100.dp),
                    value = vm.aImageText,
                    onValueChange = {
                        vm.updateAImageText(it)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(200, 200, 200),
                        focusedContainerColor = Color(240, 240, 240),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = if (vm.aImageValue != null) Color(0, 128, 0) else Color.Red
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                Text("B: ", fontSize = 24.sp, color = Color.Green)
                OutlinedTextField(
                    modifier = Modifier.width(100.dp),
                    value = vm.bRealText,
                    onValueChange = {
                        vm.updateBRealText(it)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(200, 200, 200),
                        focusedContainerColor = Color(240, 240, 240),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = if (vm.bRealValue != null) Color(0, 128, 0) else Color.Red
                    )
                )
                AnimatedContent(
                    vm.plusminusBMode,
                    label = "plusMinus",
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = fadeIn(tween(300)),
                            initialContentExit = fadeOut(tween(300))
                        )
                    }
                )
                { k ->
                    val animatedColor by rememberInfiniteTransition().animateColor(
                        initialValue = if (k == "+") Color.Yellow else Color.Green,
                        targetValue = if (k == "+") Color.Red else Color.Blue,
                        animationSpec = infiniteRepeatable(
                            tween(6000),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "color"
                    )
                    animatedColor
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(object : ShaderBrush() {
                                override fun createShader(size: Size): Shader {
                                    return LinearGradientShader(
                                        Offset.Zero,
                                        Offset(size.width, size.height / 2),
                                        listOf(
                                            animatedColor,
                                            animatedColor.copy(
                                                red = 1f - animatedColor.green,
                                                green = 1f - animatedColor.blue,
                                                blue = 1f - animatedColor.red
                                            )
                                        )
                                    )
                                }
                            })
                            .clickable {
                                vm.togglePlusMinusBMode()
                            }
                            .padding(20.dp, 10.dp, 20.dp, 10.dp),
                        text = k,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.width(100.dp),
                    value = vm.bImageText,
                    onValueChange = {
                        vm.updateBImageText(it)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(200, 200, 200),
                        focusedContainerColor = Color(240, 240, 240),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = if (vm.bImageValue != null) Color(0, 128, 0) else Color.Red
                    )
                )
            }
            Text(
                modifier = Modifier
                    .clip(CutCornerShape(10.dp))
                    .clickable {
                        vm.toggleDivisionMode()
                    }
                    .background(Color.Black)
                    .padding(20.dp)
                    .size(150.dp, 100.dp),
                text = vm.divisionMode,
                fontSize = 40.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Divider(thickness = 3.dp, color = Color.White, modifier = Modifier.padding(5.dp, 20.dp, 5.dp, 0.dp))
            Text(
                text = vm.resultValue,
                fontSize = 32.sp,
                color = if (vm.resultValue.startsWith("error")) Color.Red else Color.Green
            )
        }
    }
}