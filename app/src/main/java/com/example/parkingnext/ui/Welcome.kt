package com.example.parkingnext.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkingnext.ui.theme.ParkingNextTheme
import com.example.parkingnext.R
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Welcome(
    registerButtonOnClick: () -> Unit,
    loginButtonOnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 75.dp,
                bottom = 75.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_figure),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.size(40.dp))
            Image(
                painter = painterResource(id = R.drawable.parking_next_logo_text),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth().size(100.dp)
            )
            Spacer(Modifier.size(20.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.app_description)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = loginButtonOnClick,
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.MainOrange)),
                modifier = Modifier.size(width = 120.dp, height = 45.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Button(
                onClick = registerButtonOnClick,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(20),
                modifier = Modifier.size(width = 120.dp, height = 45.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
@Composable
fun WelcomePreview() {
    ParkingNextTheme {
        Welcome({}, {})
    }
}