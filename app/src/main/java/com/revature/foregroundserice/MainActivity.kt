package com.revature.foregroundserice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.foregroundserice.services.foregroundStartService
import com.revature.foregroundserice.ui.theme.ForegroundServiceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForegroundServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ForegroundService()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ForegroundService() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Foreground Service")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Button(onClick = { context.foregroundStartService("Start") }) {
                        Text(text = "Start Service")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { context.foregroundStartService("Stop") }) {
                        Text(text = "Stop Service")
                    }
                }
                
            }
        }


    }
}