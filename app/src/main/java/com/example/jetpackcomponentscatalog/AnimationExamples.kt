package com.example.jetpackcomponentscatalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var showBox by rememberSaveable {
        mutableStateOf(true)
    }
    val realColor by animateColorAsState(
        targetValue = if (firstColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 1000),
        finishedListener = { showBox = false }
    )
    if (showBox) {
        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor)
            .clickable { firstColor = !firstColor })
    }
}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 1000),
        finishedListener = { }
    )
    Column {
        Box(modifier = Modifier
            .size(size)
            .background(Color.Cyan)
            .clickable { smallSize = !smallSize })
    }
}

@Composable
fun VisibilityAnimation() {
    var isVisible by remember {
        mutableStateOf(true)
    }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Show/Hidde")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossfadeExampleAnimation() {
    var myComponentType: ComponentType by remember {
        mutableStateOf(ComponentType.Text)
    }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text(text = "Change component")
        }

        Crossfade(targetState = myComponentType) { myComponentType ->
            when (myComponentType) {
                ComponentType.Image -> Icon(Icons.Default.Done, contentDescription = "")
                ComponentType.Text -> Text(text = "I'm a component")
                ComponentType.Box -> Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Red)
                )

                ComponentType.Error -> Text(text = "Error!")
            }
        }
    }
}

fun getComponentTypeRandom(): ComponentType {
    return when (nextInt(from = 0, until = 3)) {
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}

enum class ComponentType() {
    Image, Text, Box, Error
}
