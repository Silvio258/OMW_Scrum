package com.main.omwayapp.ui.screens.rider.finalizadoRider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.omwayapp.R
import com.main.omwayapp.ui.components.CustomButton

@Preview(showSystemUi = true)
@Composable
fun FinalizadoRider(){

    Column(modifier =
    Modifier
        .background(color = colorResource(id = R.color.fondo))
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextFinalizado()
        TotalAPagar()

        Row(modifier = Modifier.align(Alignment.Start)
            .padding(horizontal = 40.dp, vertical = 20.dp))
        {
            DistanciaRecorrida()
            Spacer(modifier = Modifier.padding(10.dp))
            TarifaBase()
            Spacer(modifier = Modifier.padding(10.dp))
            TiempoRecorrido()

        }
        Rating()

        Spacer(modifier = Modifier.padding(18.dp))

        Comentarios()

        Spacer(modifier = Modifier.padding(40.dp))

        CustomButton(modifier = Modifier.size(width=222.dp,height=51.dp),text = "Calificar", fontSize = 20.sp){}

        Spacer(modifier = Modifier.padding(40.dp))

    }
}

@Composable
fun TextFinalizado(){
    Column() {

        Text(
            modifier = Modifier.padding(vertical = 50.dp),
            text= "¡Has llegado a tu destino!",
        color = colorResource(id = R.color.menta_importante),
            fontSize= 28.sp,
            fontStyle = FontStyle(R.font.ibmplexsans_bold)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalAPagar(){

    var totalPago by remember { mutableStateOf("50.23") }
    var textPago by remember { mutableStateOf("C$ ") }
    textPago = textPago + totalPago;

    Column( horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text= "Su total a pagar es:",
            color = colorResource(id = R.color.texto_general),
            fontSize= 18.sp,
            fontStyle = FontStyle(R.font.inter_regular),
            textAlign = TextAlign.Center
        )

        TextField(
            value = textPago, onValueChange = { textPago = it },
            readOnly = true,
            modifier = Modifier
                .size(width = 311.dp, height = 90.dp),
            colors = TextFieldDefaults.textFieldColors
                (
                containerColor = colorResource(id = R.color.fondo)
            ),
            textStyle = TextStyle(
                fontSize = 22.sp, color = colorResource(
                    id = R.color.texto_general
                ), fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                textAlign = TextAlign.Center
            )
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistanciaRecorrida(){

    var totalDistancia by remember { mutableStateOf("2.2") }
    var textDistancia by remember { mutableStateOf("Km") }

  //  totalDistancia = totalDistancia + textDistancia;

    Column( horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text= "Distancia",
            color = colorResource(id = R.color.menta_importante),
            fontSize= 18.sp,
            fontStyle = FontStyle(R.font.ibmplexsans_bold),
            textAlign = TextAlign.Center
        )
        Row(){
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= totalDistancia,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= textDistancia,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
        }

        /*
        TextField(
            value = textDistancia, onValueChange = { textDistancia = it },
            readOnly = true,
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp),
            colors = TextFieldDefaults.textFieldColors
                (
                containerColor = colorResource(id = R.color.fondo)
            ),
            textStyle = TextStyle(
                fontSize = 15.sp, color = colorResource(
                    id = R.color.texto_general
                ), fontFamily = FontFamily(Font(R.font.inter_regular)),
                textAlign = TextAlign.Center
            )
        )

         */

    }
}

@Composable
fun TarifaBase(){

    var TarifaBase by remember { mutableStateOf("20") }
    var textTarifa by remember { mutableStateOf(" C$") }


    Column( horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text= "Tarifa Base",
            color = colorResource(id = R.color.menta_importante),
            fontSize= 18.sp,
            fontStyle = FontStyle(R.font.ibmplexsans_bold),
            textAlign = TextAlign.Center
        )
        Row(){
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= textTarifa,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= TarifaBase,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
        }

    }
}


@Composable
fun TiempoRecorrido(){

    var Tiempo by remember { mutableStateOf("8") }
    var textTiempo by remember { mutableStateOf(" min") }


    Column( horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text= "Tiempo",
            color = colorResource(id = R.color.menta_importante),
            fontSize= 18.sp,
            fontStyle = FontStyle(R.font.ibmplexsans_bold),
            textAlign = TextAlign.Center
        )
        Row(){
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= Tiempo,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text= textTiempo,
                color = colorResource(id = R.color.texto_general),
                fontSize= 20.sp,
                fontStyle = FontStyle(R.font.ibmplexsans_bold),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun RatingBar(maxRating: Int, currentRating: Int, onRatingChanged: (Int) -> Unit) {
    Row {
        for (rating in 1..maxRating) {
            IconButton(
                onClick = { onRatingChanged(rating) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    modifier = Modifier.size(50.dp),
                    contentDescription = null,
                    tint = if (rating <= currentRating) Color.Yellow else Color.Gray
                )
            }
        }
    }
}

@Composable
fun Rating(){

    var currentRating by remember { mutableStateOf(0) }
    Column() {
        RatingBar(
            maxRating = 5,
            currentRating = currentRating,
            onRatingChanged = { rating ->
                currentRating = rating
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comentarios(){
    Column(){

        var TextComentario by remember { mutableStateOf(" ") }

        TextField(
            value = TextComentario, onValueChange = { TextComentario = it },
            modifier = Modifier
                .size(width = 300.dp, height = 96.dp),
            colors = TextFieldDefaults.textFieldColors
                (
                containerColor = colorResource(id = R.color.txt_fields)
            ),
            maxLines = 3,
            textStyle = TextStyle(
                fontSize = 15.sp, color = colorResource(
                    id = R.color.texto_general
                ), fontFamily = FontFamily(Font(R.font.inter_regular)),
                textAlign = TextAlign.Start
            ),
            label = {
                Text(modifier = Modifier.padding(6.dp),
                    text = "Comentarios",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                    color = colorResource(id = R.color.texto_general),
                    textAlign = TextAlign.Start)
            }

        )
    }
}