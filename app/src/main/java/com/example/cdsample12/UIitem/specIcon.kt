package com.example.cdsample12.UIitem

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CheckIcon(
    modifier: Modifier = Modifier,
    isFavourite: Boolean = false,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {
    Icon(
        imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.Favorite,
        contentDescription = null,
        tint = if (isFavourite) Color.Blue else Color.Blue.copy(ContentAlpha.disabled),
        modifier = modifier
            .width(25.dp)
            .height(25.dp)
            .clickable() { onClick.invoke() }
            .pointerInteropFilter { false }
    )
}


@Composable
fun IconButtonExample(modifier: Modifier) {

    Row{
//        IconButton(onClick = {}, modifier = modifier) {
//            Icon(
//                Icons.Filled.Favorite,
//                contentDescription = null
//            )
//        }

        var checked by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(false) }

        IconToggleButton(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = modifier
        ) {

            val tint by animateColorAsState(
                targetValue = if (checked) Color.Green else Color(0xffB0BEC5),
                animationSpec = tween(durationMillis = 400)
            )

            Icon(
                Icons.Filled.Check, tint = tint,
                contentDescription = null
            )
        }
        IconToggleButton(
            checked = checked2,
            onCheckedChange = { checked2 = it },
            modifier = modifier
        ) {

            val tint by animateColorAsState(
                targetValue = if (checked2) Color(0xffE91E63) else Color(0xffB0BEC5),
                animationSpec = tween(durationMillis = 400)
            )

            Icon(
                Icons.Filled.Delete, tint = tint,
                contentDescription = null
            )
        }
    }
}
