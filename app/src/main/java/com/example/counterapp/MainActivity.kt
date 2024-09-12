package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.ui.CounterViewModel
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
              Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
                  CounterApp(Modifier.padding(innerPadding))
              }
            }
        }
    }
}

@Composable
fun CounterApp( modifier: Modifier = Modifier, viewModel: CounterViewModel= viewModel()) {
//  val counter by rememberSaveable { mutableIntStateOf(0) }
//    viewModel.uiState
    val counter by viewModel.uiState.collectAsState()
  Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier=Modifier.fillMaxSize()) {
      Text(
          text = counter.toString(),
          fontSize = 60.sp,
          style = TextStyle(
              shadow = Shadow(
                  Color.Blue,
                  offset = Offset(5f,10f),
                  blurRadius = 5f
              )
          )
          )
      Button(
          onClick = {
              viewModel.increment()
              //++counter
              },
          Modifier.padding(top = 32.dp),
          colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
      )

      {
          Text(text = "Increment",
      color = Color.White
              )
      }
  }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    CounterAppTheme {
        CounterApp()
    }
}