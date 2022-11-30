package com.example.cdsample12.UIitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(){

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate by remember {
        derivedStateOf { DateTimeFormatter
            .ofPattern("yyyy mm dd")
            .format(pickedDate)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
        dateDialogState.show()
        }) {
            Text(text = "Pick date")
        }
        Text(text = formattedDate)

    }
//    dateDialogState.show()
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "ok"){
                }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it.dayOfMonth % 2 == 1
            }
        ){
            pickedDate = it
        }
    }
}

@Composable
fun TimePicker(){

    var pickedTime by remember {
        mutableStateOf(LocalTime.now())}

    val formattedTime by remember {
        derivedStateOf { DateTimeFormatter
            .ofPattern("hh:mm")
            .format(pickedTime)
        }
    }

    val timeDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick TIme")
        }
        Text(text = formattedTime)

    }


    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "ok"){
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a date",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ){
            pickedTime = it
        }
    }
}



//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Cdsample5Theme {
//        DatePicker()
//    }
//}