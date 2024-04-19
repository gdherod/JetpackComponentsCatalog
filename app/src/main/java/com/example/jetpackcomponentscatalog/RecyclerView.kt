package com.example.jetpackcomponentscatalog

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Gary", "Andy", "Zeus")

    LazyColumn {
        item { Text(text = "Header") }
        items(myList) {
            Text(text = "Hi, my name is $it")
        }
        item { Text(text = "Footer") }
    }
}