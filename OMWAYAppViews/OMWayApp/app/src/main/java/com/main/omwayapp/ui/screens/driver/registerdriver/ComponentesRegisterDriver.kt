package com.main.omwayapp.ui.screens.driver.registerdriver

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.omwayapp.R
import com.main.omwayapp.ui.components.InputField
import java.util.Calendar

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlacaTextField(){
    var text = remember {
        mutableStateOf("")
    }
    var keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        InputField(
            modifier = Modifier
                .size(width=330.dp,height=70.dp),
            valueState = text,
            labelId = "No.de Placa",
            icon = painterResource(id = R.drawable.placa),
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            onAction = KeyboardActions{
                keyBoardController?.hide()
            }
        )
    }

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ColorTextField(){
    var text = remember {
        mutableStateOf("") }
    var keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        InputField(
            modifier = Modifier
                .size(width=330.dp,height=70.dp),
            valueState = text,
            labelId = "Color",
            icon = painterResource(id = R.drawable.color),
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            onAction = KeyboardActions{
                keyBoardController?.hide()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeExposedDropdownMenuBox() {
    val context = LocalContext.current
    var MakeList = listOf("Toyota", "Nissan", "Kia")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {selectedText = it},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .size(width = 330.dp, height = 70.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors
                    (containerColor = colorResource(id = R.color.txt_fields)
                ),
                textStyle= TextStyle(fontSize=16.sp,color= colorResource(
                    id = R.color.texto_general)
                ),
                label = {
                    Text(text = "Marca",
                        /*
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),*/
                        color = colorResource(id = R.color.texto_general)
                    )
                },
                leadingIcon = {
                    IconButton(onClick = {/*TODO*/}){
                        Icon(painter = painterResource(id = R.drawable.carro),
                            contentDescription = "Marca del carro",
                            modifier = Modifier
                                .size(24.dp))
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                MakeList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ModelTextField() {

    var text = remember {
        mutableStateOf("")
    }

    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        InputField(
            modifier = Modifier
                .size(width=330.dp,height=70.dp),
            valueState = text,
            labelId = "Modelo",
            icon = painterResource(id = R.drawable.carro),
            enabled = true,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            onAction = KeyboardActions{
                keyBoardController?.hide()
            }
        )
    }


}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CarYearTextField(){
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text = remember {
            mutableStateOf("")
        }
        var keyBoardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputField(
                modifier = Modifier
                    .size(width=330.dp,height=70.dp),
                valueState = text,
                labelId = "Año modelo",
                icon = painterResource(id = R.drawable.carro),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Number,
                onAction = KeyboardActions{
                    keyBoardController?.hide()
                }
            )
        }

    }

}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DlExpiraationDatePicker(){

    var date: String by rememberSaveable{ mutableStateOf("") }
    val yearDl: Int
    val monthDl: Int
    val dayDl: Int
    val mCalendar: Calendar = Calendar.getInstance()
    yearDl = mCalendar.get(Calendar.YEAR)
    monthDl = mCalendar.get(Calendar.MONTH)
    dayDl = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        {
                _: DatePicker, yearDl:Int, monthDl:Int, dayDl:Int ->
            date = "$yearDl/${monthDl+1}/$dayDl"
        },yearDl,monthDl,dayDl
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        TextField(value = date,
            onValueChange = {date=it},
            singleLine = true,
            readOnly = true,
            modifier = Modifier
                .size(width = 330.dp, height = 70.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { mDatePickerDialog.show() },
            colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.txt_fields),
                focusedLabelColor = colorResource(id =R.color.texto_general), unfocusedLabelColor = colorResource(id =R.color.texto_general)
            ),
            label = {
                Text(text = "Fecha de vencimiento"
                )
            },
            textStyle=TextStyle(fontSize=16.sp,color=colorResource(
                id = R.color.texto_general)),

            leadingIcon = {
                IconButton(onClick = {/*TODO*/}){
                    Icon(painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "Calendario",
                        modifier = Modifier
                            .clickable { mDatePickerDialog.show()}
                            .size(24.dp))
                }
            }


        )
    }
}
