package com.main.omwayapp.ui.components

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CustomAlertDialog(title : String,msg:String,onConfirm:()->Unit) {
    var openDialog by remember { mutableStateOf(true) }
    Log.d("OPENDIALOG",openDialog.toString())
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {openDialog = false},
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                    onConfirm()
                })
                { Text(text = "OK") }
            },
            title = { Text(text = title) },
            text = { Text(text = msg) }
        )
    }
}