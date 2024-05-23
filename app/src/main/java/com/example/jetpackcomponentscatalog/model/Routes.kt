package com.example.jetpackcomponentscatalog.model

sealed class Routes(val route: String) {
    object Screen1 : Routes("screen1")
    object Screen2 : Routes("screen2")
    object Screen3 : Routes("screen3")
    object Screen4 : Routes("screen4/{age}") {
        fun createRoute(age: Int) = "screen4/$age"
    }

    object Screen5 : Routes("screen5?name={name}") {
        fun createRoute(name: String) = "screen5?name=$name"
    }
}