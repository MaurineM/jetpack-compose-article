package com.intro.mycompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intro.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val modifier: Modifier = Modifier.padding(4.dp)
    var textState = remember { mutableStateOf("") }
    MyComposeTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f)
        ) {
            /* TextField(
             value = textState.value,
             onValueChange = {it -> textState = it },
             label = Text(text = "Name")
         )*/
            val context = LocalContext.current
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .clickable {
                        Toast
                            .makeText(context,"Clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
            )

            Row(modifier = modifier) {
                Text(text = "Name:")
                Text(text = "Android")
            }
            Row(modifier = modifier) {
                Text(text = "Born:")
                Text(text = "1998")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomPreview() {
    Greeting(name = "Name")
}