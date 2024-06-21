package com.example.parkingnext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parkingnext.data.DAO
import com.example.parkingnext.data.DummyDAO
import com.example.parkingnext.model.Slot
import com.example.parkingnext.ui.theme.ParkingNextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkingNextTheme {
                ParkingNextApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingNextApp(modifier: Modifier = Modifier
    .safeDrawingPadding()
    .padding(30.dp)) {
    val dao: DAO = DummyDAO()
    var slots = dao.getSlots(dao.getSectors(dao.getFloors()[0])[0])
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Button(
                        onClick = {}
                    ) {
                        Text(text = "<")
                    }
                }

                Text("Parking Slots",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f))

                Text("8/17",
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f))
            }
        },
        bottomBar = {
            Row() {
                Button(onClick = {}) {
                    Text("< Go back")
                }
                Button(onClick = {}) {
                    Text("Next >")
                }
            }
        },
        modifier = modifier
    ) {
        SlotSearcher(slots, Modifier.padding(it))
    }
}

@Composable
fun SlotSearcher(slots: List<Slot>,
                 modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row() {
            Text("ToDelete")
        }
        SlotList(slots,
            modifier = Modifier.weight(1.0f))
        Row() {
            Button(onClick = {}) {
                Text("<")
            }
            Text("Move Sector")
            Button(onClick = {}) {
                Text(">")
            }
        }
    }
}

@Composable
fun SlotList(
    slots: List<Slot>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        GradientDivider(
            colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
            thickness = 1.dp
        )
        repeat(slots.size / 2) {rowIndex ->
            Row (modifier = Modifier.weight(1f)){
                Button(
                    onClick = {}
                    , modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = slots[rowIndex * 2].number.toString()
                    )
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxHeight() //fill the max height
                        .width(1.dp)
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = slots[rowIndex * 2 + 1].number.toString()
                    )
                }
            }
            GradientDivider(
                colors = listOf(Color.Transparent, Color.Gray, Color.Transparent),
                thickness = 1.dp
            )
        }
    }
}


@Composable
fun GradientDivider(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
) {
    Canvas(
        modifier = modifier
            .height(thickness)
            .fillMaxWidth()
    ) {
        val gradient = Brush.horizontalGradient(
            colors = colors,
            startX = 0f,
            endX = size.width
        )

        drawRect(
            brush = gradient,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParkingNextTheme {
        ParkingNextApp()
    }
}