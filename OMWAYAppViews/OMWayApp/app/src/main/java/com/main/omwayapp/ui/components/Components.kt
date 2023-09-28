package com.main.omwayapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.DpSize

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.omwayapp.ui.theme.Fondo
import com.main.omwayapp.ui.theme.IBMplexSans
import com.main.omwayapp.ui.theme.MentaImportante40
import com.main.omwayapp.ui.theme.TextoGeneral
import com.main.omwayapp.ui.theme.TxtFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    icon: Painter,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Go,
    onAction: KeyboardActions,
    backgroundColor:Color = TxtFields,
    textColor:Color = TextoGeneral,
    iconDp:Dp = 20.dp,
    maxLines:Int = 4
){
    TextField(value = valueState.value , onValueChange = {valueState.value = it},
        modifier = modifier.padding(5.dp),
        label ={ Text(text = labelId)},
        leadingIcon = {Icon(painter = icon, contentDescription = "Text Field Icon",modifier = iconModifier.size(iconDp))},
        singleLine = isSingleLine,
        maxLines = maxLines,
        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.textFieldColors(containerColor = backgroundColor,focusedLabelColor = textColor, unfocusedLabelColor = textColor,
            disabledTextColor = textColor, disabledLeadingIconColor = Color.Gray, disabledTrailingIconColor = Color.Gray, disabledLabelColor = textColor),
        shape = RoundedCornerShape(10.dp)
    )




}
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colorFondo:Color = MentaImportante40,
    text: String,

    textColor : Color = Fondo,
    fontSize:TextUnit,
    onClick:()->Unit
){
    Button(onClick = onClick,
    modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorFondo),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        contentPadding = PaddingValues(4.dp)
        ) {
            Text(text = text,
                color = textColor,
                fontSize = fontSize
                )
    }

}

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    height:Dp = 30.dp,
    color: Color = MentaImportante40
){
    Box(
        modifier = modifier.fillMaxWidth().height(height).background(color)
    )
}

@Composable
fun CenteredImage(image: Painter, size: DpSize) {
    Box(

        contentAlignment = Alignment.TopCenter,
        //modifier = Modifier.padding(20.dp).height(10.dp).width(10.dp),


    ) {
        Image(
            painter = image,
            contentDescription = "OMWayLogo",
            modifier = Modifier
                .size(size.width, size.height),
            alignment = Alignment.Center
        )

    }
}

@Composable
fun CustomButtonG(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colorFondo:Color = MentaImportante40,
    text: String,
    textColor: Color = Fondo,
    fontSize:TextUnit,
    fontFamily: FontFamily = IBMplexSans,
    fontWeight: FontWeight = FontWeight.Bold,
    onClick:()->Unit
){
    Button(onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(corner = CornerSize(25.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorFondo),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        Text(text = text,
            color = textColor,
            fontSize = fontSize
        )
    }

}