package com.example.cdsample12.Admin

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cdsample12.UIitem.SelectableSettingGroupItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color.Companion.LightGray
import com.example.cdsample12.LectureRoom.places
import com.example.cdsample12.UIitem.BookedLectureRoomItem
import com.example.cdsample12.bottombar.PlacesToBookVerticalComponent

@Composable
fun AdminRefuseAppro(
//    navController: NavController,
    onBackClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.onSurface,
                contentColor = LightGray,
                elevation = 10.dp
            )
        },
        content = {
        Column(modifier = Modifier
            .padding(it)
            ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(text = "Account")
            SelectableSettingGroupItem(
                title = "aaa",
                desc = "",
                icon = Icons.Outlined.Person
            ) {
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content ={
                    items(places) { place ->
                        BookedLectureRoomItem(place = place)
                    }
                }
            )
        }
    })

}