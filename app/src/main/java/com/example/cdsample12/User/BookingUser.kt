package com.example.cdsample12.User

import androidx.compose.runtime.Immutable
import kotlin.random.Random

class BookingUser(
    val id: Long,
    val name :String,
    var bookingdate : String,
    var bookingtime : String,
    var bookedroom : String,
) {
}

var bookingusers = listOf(
    BookingUser(1,"김요한","2022-12-03","16:00-17:00","A13-425"),

//    BookingUser(2,"양보원","2022-12-07","12:00-13:00"),
//
//    BookingUser(3,"이다현","2022-12-13","17:00-18:00"),
)
//
//@Immutable
//data class Place(
//    val id: Long,
//    val description: String,
//    val istherecomputer: Boolean,
//    val istherebeamproject : Boolean,
//    val istherelabequipment : Boolean,
//    val maxcapacity : Int,
//    val rating: Double = Random.nextDouble(0.0, 10.0),
//
//
//    )
//
//val places = listOf(
//    Place(1, "A13-425", istherecomputer = false,true,false,40),
//    Place(2, "A13-126", istherecomputer = true,true,false,60),
//    Place(3, "A13-428", istherecomputer = true,true,true,40),
//    Place(4, "A13-326", istherecomputer = true,true,false,30),
//
//    )