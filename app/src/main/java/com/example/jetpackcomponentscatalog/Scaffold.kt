package com.example.jetpackcomponentscatalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ScaffoldExample() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(content = {
            MyDrawer {
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        })
    }, gesturesEnabled = false, content = {
        Scaffold(topBar = {
            MyTopAppBar(onClickIcon = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        "Pressed $it"
                    )
                }
            }, onClickDrawer = { coroutineScope.launch { drawerState.open() } })
        },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            floatingActionButton = { MyFab() },
            floatingActionButtonPosition = FabPosition.Center,

            bottomBar = { MyBottomNavigation() }) {
            // Not used in this example
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Red,
        titleContentColor = Color.White,
        navigationIconContentColor = Color.White,
        actionIconContentColor = Color.White,   //Color for all icons
    ), modifier = Modifier.shadow(
        elevation = 4.dp, spotColor = Color.Red
    ), title = { Text(text = "My first toolbar") }, navigationIcon = {
        IconButton(onClick = { onClickDrawer() }) {
            Icon(
                imageVector = Icons.Filled.Menu, contentDescription = "Menú"
            )
        }
    }, actions = {
        IconButton(onClick = { onClickIcon("Search") }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = Color.Yellow     //Change color just fot this icon
            )
        }
        IconButton(onClick = { onClickIcon("Close") }) {
            Icon(
                imageVector = Icons.Filled.Close, contentDescription = "Close"
            )
        }
    })
}

@Composable
fun MyBottomNavigation() {
    var index by remember {
        mutableStateOf(0)
    }
    NavigationBar(containerColor = Color.Red) {
        NavigationBarItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }, label = { Text(text = "Home") })
        NavigationBarItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites")
        }, label = { Text(text = "Favorites") })
        NavigationBarItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
        }, label = { Text(text = "Profile") })
    }
}

@Composable
fun MyFab() {
    FloatingActionButton(onClick = { }, containerColor = Color.Yellow, contentColor = Color.Black) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "First option",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Second option",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Third option",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
        Text(text = "Fourth option",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() })
    }
}