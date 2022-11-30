package com.example.cdsample12.Admin


sealed class AdminPage(val route: String) {

    object AdminRefuseAppro : AdminPage("Admin_RefuseAppro")

    object LectureRoomManage : AdminPage("LectureRoom_Manage")
}