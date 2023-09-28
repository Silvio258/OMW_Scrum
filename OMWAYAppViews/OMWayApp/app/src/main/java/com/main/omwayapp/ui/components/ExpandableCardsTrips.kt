package com.main.omwayapp.ui.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.omwayapp.R

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ExpandableCardTrips(
    nomRider: String,
    tarifa: String,
    hora: String,
    puntoA: String,
    puntoB: String,
    distanciaEst: String
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .width(330.dp)
            .padding(5.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        ,
        shape = RoundedCornerShape(10.dp),
        enabled = true,
        onClick = {
            Log.d("Estado","Estado:${expandedState}")
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bg_terminosCondiciones)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {/*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = "Usuario",
                        tint= colorResource(id = R.color.texto_opacidad),
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(4f)
                ) {
                    Text(
                        text = nomRider,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp
                    )
                    Text(
                        text = tarifa,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp

                    )
                    Text(
                        text = hora,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
                Column() {
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)

                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop Down Arrow",
                            tint = colorResource(id = R.color.texto_opacidad)
                        )
                    }
                }
            }

            if (expandedState) {
                Log.d("Estado", "DentroIF")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_a),
                            contentDescription = "PuntoA",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto A",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoA,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_b),
                            contentDescription = "PuntoB",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto B",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoB,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier .align(Alignment.CenterHorizontally)) {
                    Text(text = distanciaEst,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp,
                    )
                }
                Spacer(modifier = Modifier
                    .height(20.dp)
                )

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ExpandableCardTripsDone(
    nomRider: String,
    tarifa: String,
    hora: String,
    puntoA: String,
    puntoB: String,
    distanciaRec: String,
    distancia: String,
    tiempo: String,
    tiempotardado: String
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .width(330.dp)
            .padding(5.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        ,
        shape = RoundedCornerShape(10.dp),
        enabled = true,
        onClick = {
            Log.d("Estado","Estado:${expandedState}")
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bg_terminosCondiciones)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {/*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = "Usuario",
                        tint= colorResource(id = R.color.texto_opacidad),
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(4f)
                ) {
                    Text(
                        text = nomRider,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp
                    )
                    Row() {


                        Text(
                            text = "Tarifa: C$",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp

                        )
                        Text(
                            text = tarifa,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp

                        )
                    }
                    Row() {
                        Text(
                            text = "Hora Finalizado: ",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = hora,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }
                Column() {
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)

                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop Down Arrow",
                            tint = colorResource(id = R.color.texto_opacidad)
                        )
                    }
                }
            }

            if (expandedState) {
                Log.d("Estado", "DentroIF")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_a),
                            contentDescription = "PuntoA",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto A",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoA,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_b),
                            contentDescription = "PuntoB",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto B",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoB,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier .align(Alignment.CenterHorizontally)) {

                    Row {
                        Text(text = distanciaRec,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp,
                        )
                        Text(text = distancia,
                            color = colorResource(id = R.color.menta_importante),
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier
                            .width(30.dp)
                        )
                        Text(text = tiempo,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp,
                        )
                        Text(text = tiempotardado,
                            color = colorResource(id = R.color.menta_importante),
                            fontSize = 16.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .height(20.dp)
                )

            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun ExpandableCardTripsDoneRider(
    nomRider: String,
    tarifa: String,
    hora: String,
    puntoA: String,
    puntoB: String,
    distanciaRec: String,
    distancia: String,
    tiempo: String,
    tiempotardado: String,
    detallesCarro: String,
    placa: String
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .width(330.dp)
            .padding(5.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        ,
        shape = RoundedCornerShape(10.dp),
        enabled = true,
        onClick = {
            Log.d("Estado","Estado:${expandedState}")
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bg_terminosCondiciones)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {/*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = "Usuario",
                        tint= colorResource(id = R.color.texto_opacidad),
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(4f)
                ) {
                    Text(
                        text = tarifa,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp

                    )
                    Text(
                        text = hora,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                }
                Column() {
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)

                            .rotate(rotationState),
                        onClick = {
                            expandedState = !expandedState
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop Down Arrow",
                            tint = colorResource(id = R.color.texto_opacidad)
                        )
                    }
                }
            }

            if (expandedState) {
                Log.d("Estado", "DentroIF")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_a),
                            contentDescription = "PuntoA",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto A",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoA,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.punto_b),
                            contentDescription = "PuntoB",
                            modifier = Modifier
                                .size(24.dp),
                            tint = colorResource(id = R.color.texto_general)
                        )
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = "Punto B",
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )
                        Text(
                            text = puntoB,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier .align(Alignment.CenterHorizontally)) {

                    Row {
                        Text(text = distanciaRec,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp,
                        )
                        Text(text = distancia,
                            color = colorResource(id = R.color.menta_importante),
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier
                            .width(30.dp)
                        )
                        Text(text = tiempo,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 16.sp,
                        )
                        Text(text = tiempotardado,
                            color = colorResource(id = R.color.menta_importante),
                            fontSize = 16.sp,
                        )
                    }
                    Text(text = "Detalles del Conductor",
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.inter_bold))
                    )
                    Spacer(modifier = Modifier
                        .height(10.dp)
                    )
                    Text(text = nomRider,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp,
                    )
                    Text(text = detallesCarro,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp,
                    )
                    Text(text = placa,
                        color = colorResource(id = R.color.texto_general),
                        fontSize = 16.sp,
                    )

                }
                Spacer(modifier = Modifier
                    .height(20.dp)
                )

            }
        }
    }
}