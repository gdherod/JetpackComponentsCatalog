package com.example.jetpackcomponentscatalog.model

sealed class Routes(val route: String) {
    object Screen1 : Routes("screen1")
    object Screen2 : Routes("screen2")
    object Screen3 : Routes("screen3")

}