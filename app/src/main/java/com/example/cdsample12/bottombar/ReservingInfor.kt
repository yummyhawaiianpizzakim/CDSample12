package com.example.cdsample12.bottombar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cdsample12.LectureRoom.places
import com.example.cdsample12.UIitem.BookedLectureRoomItem
import com.example.cdsample12.UIitem.BookedRoomContent
import com.example.cdsample12.UIitem.BookedRoomInforCard
import com.example.cdsample12.UIitem.SelectableSettingGroupItem
import com.example.cdsample12.User.BookingUser
import com.example.cdsample12.User.bookingusers

@Composable
fun ReservingInfor() {

    Scaffold(content = {

        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Booked Infor")

            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(bookingusers) { bookinguser ->
                        BookedRoomInforCard(bookingUser = bookinguser)
                    }
                }

            )

        }
    })
}