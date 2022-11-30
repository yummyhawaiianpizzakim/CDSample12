package com.example.cdsample12.LectureRoom

//package com.smarttoolfactory.tutorial1_1basics.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
//import com.smarttoolfactory.tutorial1_1basics.R
import kotlin.random.Random

@Immutable
data class Place(
    val id: Long,
    val description: String,
    val rating: Double = Random.nextDouble(0.0, 10.0),
    val price: Int = Random.nextInt(500)
)

val places = listOf(
    Place(1, "Place1"),
    Place(2, "Place2"),
    Place(3, "Place3"),

    )