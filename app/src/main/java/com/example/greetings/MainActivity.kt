package com.example.greetings

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetings.ui.theme.GreetingsTheme
import java.time.LocalTime
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.ui.graphics.Color.Magenta
                ){
                    Greeting()
                }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting() {

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var greeting by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
      TextField(
        value = name,
          onValueChange = {name = it},
          label = { Text("NAME") },
          modifier = Modifier.fillMaxWidth()
      )

        Button(
            onClick = {greeting = greetingByDay(name.text) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Click for a new greeting!")
        }

        if (greeting.isNotBlank()) {
            Text(
                text = greeting,
                fontSize = 24.sp,
                color = Color.Red
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun greetingByDay(name: String): String{
    val currentTime = LocalTime.now().hour
    val timeDay = when {
        currentTime in 5..11 -> "Rise and shine to a new day"
        currentTime in 12..18 -> "Time to be productive in this afternoon"
        currentTime in 19..24 -> "Get ready to relax and sleep"
        else -> "You shouldn't be up"
    }

    return "$timeDay $name!"
}
