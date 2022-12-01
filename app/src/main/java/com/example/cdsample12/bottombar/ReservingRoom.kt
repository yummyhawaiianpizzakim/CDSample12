package com.example.cdsample12.bottombar

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cdsample12.LectureRoom.Place
import com.example.cdsample12.LectureRoom.places
import com.example.cdsample12.UIitem.PlaceContent
import com.example.cdsample12.UIitem.SelectableSettingGroupItem
import java.util.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.example.cdsample12.UIitem.DatePicker
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogScope
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ReservingRoom(
    navController: NavController,
//    onScheduleButtonClick: (
//        selectedDate: LocalDate,
//        selectedTime: LocalTime
//    ) -> Unit
) {

    val datePickerDialogState = rememberMaterialDialogState()
    val timePickerDialogState = rememberMaterialDialogState()

    var currentDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var currentTime by rememberSaveable { mutableStateOf(LocalTime.now()) }

    Scaffold(

        content = {

            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(100.dp))

//                Text(text = "Account",)
                val dateTimePickerSection: @Composable (Modifier) -> Unit =
                    remember(currentDate, currentTime) {
                        movableContentOf { modifier ->
                            Column(
                                modifier = modifier
                                    .padding(horizontal = 32.dp)
                                    .padding(top = 32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SchedulePickupText(text = currentDate.uberFormattedDate()) {
                                    datePickerDialogState.show()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Divider()
                                SchedulePickupText(text = currentTime.uberFormattedTime()) {
                                    timePickerDialogState.show()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Divider()
                                SchedulePickupText(text = "People") {
//                                    timePickerDialogState.show()
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                dateTimePickerSection(Modifier)
                Spacer(modifier = Modifier.height(16.dp))
//                SelectableSettingGroupItem(
//                    title = "aaa",
//                    desc = "",
//                    icon = Icons.Outlined.Person
//                ) {
//
//                }
                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "Admin")
//                SelectableSettingGroupItem(
//                    title = "aaa",
//                    desc = "",
//                    icon = Icons.Outlined.AccountCircle
//                ) {
//
//                }
                Spacer(modifier = Modifier.height(16.dp))
//                SelectableSettingGroupItem(
//                    title = "aaa",
//                    desc = "",
//                    icon = Icons.Outlined.AccountCircle
//                ) {
//
//                }
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(places) { place ->
                            PlacesToBookVerticalComponent(place = place)
                        }
                    }
                )


                UberDateTimePicker(dialogState = datePickerDialogState) {
                    datepicker(
                        initialDate = currentDate,
                        colors = DatePickerDefaults.colors(
                            headerBackgroundColor = MaterialTheme.colorScheme.primary,
                            headerTextColor = MaterialTheme.colorScheme.onPrimary,
                            calendarHeaderTextColor = MaterialTheme.colorScheme.primary,
                            dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                            dateInactiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                            dateActiveTextColor = MaterialTheme.colorScheme.onPrimary,
                            dateInactiveTextColor = MaterialTheme.colorScheme.primary
                        )
                    ) { currentDate = it }
                }

                UberDateTimePicker(dialogState = timePickerDialogState) {
                    timepicker(
                        initialTime = currentTime,
                        colors = TimePickerDefaults.colors(
                            activeBackgroundColor = MaterialTheme.colorScheme.primary,
                            inactiveBackgroundColor = MaterialTheme.colorScheme.onPrimary,
                            activeTextColor = MaterialTheme.colorScheme.onPrimary,
                            inactiveTextColor = MaterialTheme.colorScheme.primary,
                            inactivePeriodBackground = MaterialTheme.colorScheme.onPrimary,
                            selectorColor = MaterialTheme.colorScheme.primary,
                            selectorTextColor = MaterialTheme.colorScheme.onPrimary,
                            headerTextColor = MaterialTheme.colorScheme.primary,
                            borderColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) { currentTime = it }
                }

            }
        }
    )
}


@Composable
fun SchedulePickupText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickableWithRipple(onClick = onClick)
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            ,
        text = text,
        textAlign = TextAlign.Left,


        )
}


fun LocalDate.uberFormattedDate() =
    "${dayOfWeek.name.substring(0, 3)}, $dayOfMonth ${month.name.substring(0, 3)}".toCamelCase()

fun LocalTime.uberFormattedTime() = "$hour:${if (minute < 10) "0$minute" else "$minute"}"
private fun String.toCamelCase(delimiter: String = " "): String {
    val wordList = this.split(delimiter).map { word ->
        word
            .lowercase()
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
    }
    return wordList.joinToString(separator = delimiter)
}


@Composable
fun UberDateTimePicker(
    dialogState: MaterialDialogState,
    content: @Composable MaterialDialogScope.() -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            negativeButton(
                text = "Cancel",
                textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        onCloseRequest = { it.hide() },
        backgroundColor = MaterialTheme.colorScheme.surface,
        content = content
    )
}


@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.clickableWithRipple(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication = rememberRipple(),
    onClick: () -> Unit
) = clickable(
    interactionSource = interactionSource,
    indication = indication,
    onClick = onClick
)


@Composable
fun PlacesToBookVerticalComponent(place: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.clickable { }) {
            PlaceContent(place, Modifier.weight(1f))
//            ImageContent(place)
        }
    }
}
