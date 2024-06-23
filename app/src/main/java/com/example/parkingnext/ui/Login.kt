package com.example.parkingnext.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parkingnext.R
import com.example.parkingnext.ui.theme.ParkingNextTheme

@Composable
fun Login(
    modifier: Modifier = Modifier.padding(top = 50.dp, bottom = 50.dp, start = 30.dp, end = 30.dp)
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = colorResource(id = R.color.MainOrange)
        )

        Spacer(
            modifier = Modifier.size(10.dp)
        )

        Text(
            text = stringResource(id = R.string.login_msg),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )

        Spacer(Modifier.size(50.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(20),
            label = { Text(stringResource(id = R.string.email)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.MainOrange),
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor =  colorResource(id = R.color.LightBlue)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password)) },
            shape = RoundedCornerShape(20),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.MainOrange),
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor =  colorResource(id = R.color.LightBlue)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.size(10.dp))

        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                color = colorResource(id = R.color.MainOrange),
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                modifier = Modifier.padding(top = 2.dp)
                )
        }

        Spacer(Modifier.size(10.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.MainOrange)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Spacer(Modifier.size(20.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(
                text = stringResource(id = R.string.new_account),
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Spacer(Modifier.size(50.dp))

        Text(
            text = stringResource(id = R.string.oauth),
            fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.MainOrange)
        )

        Spacer(Modifier.size(10.dp))

        Row() {
            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.LightBlue))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = stringResource(id = R.string.login_google),
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.LightBlue))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook_logo),
                    contentDescription = stringResource(id = R.string.login_facebook),
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Button(
                onClick = {},
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.LightBlue))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.apple_logo),
                    contentDescription = stringResource(id = R.string.login_apple),
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
//@Preview(widthDp = 1000, heightDp = 1000, showBackground = true)
//@Preview(widthDp = 1920, heightDp = 1080, showBackground = true)
@Composable
fun LoginPreview() {
    ParkingNextTheme {
        Login()
    }
}