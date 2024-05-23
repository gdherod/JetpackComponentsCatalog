package com.example.jetpackcomponentscatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomponentscatalog.model.Routes
import com.example.jetpackcomponentscatalog.ui.CheckInfo
import com.example.jetpackcomponentscatalog.ui.theme.JetpackComponentsCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComponentsCatalogTheme {
                // A surface container using the 'background' color from the theme
                /*var selected by remember {
                    mutableStateOf("Gary")
                }*/

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Screen1.route
                    ) {
                        composable(Routes.Screen1.route) { Screen1(navigationController) }
                        composable(Routes.Screen2.route) { Screen2(navigationController) }
                        composable(Routes.Screen3.route) { Screen3(navigationController) }
                        composable(
                            Routes.Screen4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                    }


                    /*ScaffoldExample()*/

                    /*val myOptions = getOptions(listOf("Gary", "Ejemplo", "Pikachu"))*/

                    /*Column {*/
                    /*var show by remember {
                        mutableStateOf(false)
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Mostrar diálogo")
                        }
                        MyConfirmationDialog(show = show, onDismiss = { show = false })*/
                    /*MyAlertDialog(
                        show = show,
                        onDismiss = { show = false },
                        onConfirm = { Log.i("Gary", "Click") })*/
                }
                /*MyRadioButtonList(selected) { selected = it }*//*MyTriStatusCheckBox()
                        myOptions.forEach {
                            MyCheckBoxWithTextCompleted(it)
                        }
                    }
                }*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComponentsCatalogTheme {
        MyDivider()
    }
}

@Composable
fun MyDropDownMenu() {

    var selectedText by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    val desserts = listOf("Helado", "Chocolate", "Café", "Fruta", "Batillas", "Chilaquiles")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth())
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem({ Text(dessert) }, onClick = {
                    expanded = false
                    selectedText = dessert
                })
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    BadgedBox(
        modifier = Modifier.padding(16.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp), text = "100")
                }, containerColor = Color.Blue, contentColor = Color.Green
            )
        },
    ) {
        Icon(
            imageVector = Icons.Default.Star, contentDescription = ""
        )
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(Color.Red, contentColor = Color.Green),
        border = BorderStroke(5.dp, Color.Green)

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = true,
            onClick = { /*TODO*/ },
            enabled = false,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledUnselectedColor = Color.Green
            )
        )
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row() {
            RadioButton(selected = name == "Gary", onClick = { onItemSelected("Gary") })
            Text(text = "Gary")
        }
        Row() {
            RadioButton(selected = name == "David", onClick = { onItemSelected("David") })
            Text(text = "David")
        }
        Row() {
            RadioButton(selected = name == "Andy", onClick = { onItemSelected("Andy") })
            Text(text = "Andy")
        }
        Row() {
            RadioButton(selected = name == "Zeus", onClick = { onItemSelected("Zeus") })
            Text(text = "Zeus")
        }
    }
}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable {
        mutableStateOf(ToggleableState.Off)
    }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off
            }

            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus })
    }
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red, uncheckedColor = Color.Yellow, checkmarkColor = Color.Blue
        )
    )
}

@Composable
fun MySwitch() {
    var state by rememberSaveable {
        mutableStateOf(false)
    }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = false,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
            disabledCheckedTrackColor = Color.Yellow.copy(alpha = 0.3f),
            disabledCheckedThumbColor = Color.Blue.copy(alpha = 0.3f),
            disabledUncheckedTrackColor = Color.Blue.copy(alpha = 0.3f),
            disabledUncheckedThumbColor = Color.Yellow.copy(alpha = 0.3f)
        )
    )
}

@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable {
        mutableStateOf(0f)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressStatus)

        Row(Modifier.fillMaxWidth()) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }

        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar perfil")
        }
    }
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star, contentDescription = "Icono", tint = Color.Red
    )
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        alpha = 0.5f
    )
}

@Composable
fun MyButtonExample() {

    var enabled by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta, contentColor = Color.Blue
            ), border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Blue,
                disabledContentColor = Color.Red
            )
        ) {
            Text(text = "Hola")
        }

        TextButton(onClick = { }) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyTextFieldOutlined() {
    var myText by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Holita") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue,
        )
    )
}

@Composable
fun MyTextFieldAdvance() {
    var myText by remember {
        mutableStateOf("")
    }

    TextField(value = myText, onValueChange = {
        myText = if (it.contains("a")) {
            it.replace("a", "")
        } else {
            it
        }
    }, label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Esto es un ejemplo"
        )
        Text(
            text = "Esto es un ejemplo", color = Color.Blue
        )
        Text(
            text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Esto es un ejemplo", fontWeight = FontWeight.Light
        )
        Text(
            text = "Esto es un ejemplo", fontFamily = FontFamily.Cursive
        )
        Text(
            text = "Esto es un ejemplo", textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Esto es un ejemplo", textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Esto es un ejemplo", textDecoration = TextDecoration.combine(
                listOf(TextDecoration.Underline, TextDecoration.LineThrough)
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}