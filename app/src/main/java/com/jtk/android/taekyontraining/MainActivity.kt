package com.jtk.android.taekyontraining

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jtk.android.taekyontraining.ui.theme.AndroidTaekyonTrainingTheme
import com.jtk.android.taekyontraining.unity.UnityHostActivity

import android.content.Context
import android.util.Log
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTaekyonTrainingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, UnityHostActivity::class.java))
                        },
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Start Training")
                    }
                }
            }
        }


    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTaekyonTrainingTheme {
        Greeting("Android")
    }
}