package com.example.cdsample12.LectureRoom

//package com.smarttoolfactory.tutorial1_1basics.model

import androidx.compose.runtime.Immutable
//import com.smarttoolfactory.tutorial1_1basics.R
import kotlin.random.Random

@Immutable
data class Place(
    val id: Long,
    val description: String,
    val istherecomputer: Boolean,
    val istherebeamproject : Boolean,
    val istherelabequipment : Boolean,
    val maxcapacity : Int,
    val rating: Double = Random.nextDouble(0.0, 10.0),


    )

val places = listOf(
    Place(1, "A13-425", istherecomputer = false,true,false,40),
    Place(2, "A13-126", istherecomputer = true,true,false,60),
    Place(3, "A13-428", istherecomputer = true,true,true,40),
    Place(4, "A13-326", istherecomputer = true,true,false,30),

    )