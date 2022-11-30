package com.example.cdsample12.Admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cdsample12.UIitem.SelectableSettingGroupItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun LectureRoomManage(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.White)
                    .alpha(0f),
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                contentColor = LightGray,
                elevation = 10.dp
            )
        },
        content = {
            Column(
                modifier = Modifier
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

            }
        })

}