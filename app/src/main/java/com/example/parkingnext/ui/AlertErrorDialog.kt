package com.example.parkingnext.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaxiAlert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkingnext.ui.theme.ParkingNextTheme
import com.example.parkingnext.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertErrorDialog(
    icon: ImageVector,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    acceptButtonOnClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()){
        AlertDialog(
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = title,
                    fontFamily = MaterialTheme.typography.displayLarge.fontFamily
                )
            },
            text = {
                Text(content)
            },
            onDismissRequest = onDismissRequest,
            confirmButton = {
                Button(
                    onClick = acceptButtonOnClick,
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.MainOrange))
                ) {
                    Text(
                        text = stringResource(id = R.string.accept)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertErrorDialogPreview() {
    ParkingNextTheme {
        AlertErrorDialog(
            Icons.Filled.TaxiAlert,
            "Test",
            "This is a test.",
            {},
            {}
        )
    }
}