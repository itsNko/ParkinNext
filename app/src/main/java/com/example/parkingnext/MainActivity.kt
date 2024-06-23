package com.example.parkingnext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkingnext.ui.ParkingSlots
import com.example.parkingnext.ui.theme.ParkingNextTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkingNextTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ParkingNextApp()
                }
            }
        }
    }
}

@Composable
fun ParkingNextApp() {
    ParkingSlots({})
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        ParkingNextApp()
    }
}