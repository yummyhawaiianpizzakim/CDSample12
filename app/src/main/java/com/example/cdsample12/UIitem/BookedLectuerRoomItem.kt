package com.example.cdsample12.UIitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cdsample12.LectureRoom.Place


@Composable
fun BookedLectureRoomItem(place: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.clickable { }) {
            PlaceContent(place, Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButtonExample(modifier = Modifier.padding(8.dp))
            }
        }
    }
}
