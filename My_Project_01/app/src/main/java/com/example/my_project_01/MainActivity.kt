package com.example.my_project_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_project_01.ui.theme.My_Project_01Theme
import kotlin.random.Random

var comp = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_Project_01Theme {
                DefaultPreview()
            }
        }
    }
}

@Composable
fun Tittle(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(androidx.compose.ui.graphics.Color.Cyan),
    ){
        Text(
            fontSize = 24.sp,
            text = "Number Guessing Game")
    }
}

@Composable
fun PlayApp(modifier: Modifier = Modifier){
    var myRandomInt by remember {
        mutableStateOf(Random.nextInt(1,101))
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Tittle()
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Try to guess the number in my head")
        Text(text = "Between 1-100!")
        Spacer(modifier = Modifier.height(160.dp))
        val num = EditNumberField()
        Spacer(modifier = Modifier.height(160.dp))
        Compare(num = num, rand = myRandomInt)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { myRandomInt = Random.nextInt(1,101) }) {
            Text(text = stringResource(R.string.play))
        }
    }
}

@Composable
fun EditNumberField(): Int{
    var Input by remember { mutableStateOf("") }
    TextField(
        value = Input,
        onValueChange = {Input = it},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        label = {
            Text(
                text = stringResource(id = R.string.enter),
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
    return Input.toIntOrNull() ?: 0
}

@Composable
fun Compare(num: Int, rand: Int){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (num > rand){
            comp = "Hint: It's lower!"
        } else if (num < rand){
            comp = "Hint: It's higher!"
        } else {
            comp = "Good job!"
        }
        Text(text = comp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My_Project_01Theme {
            PlayApp(modifier = Modifier.fillMaxSize())
    }
}
