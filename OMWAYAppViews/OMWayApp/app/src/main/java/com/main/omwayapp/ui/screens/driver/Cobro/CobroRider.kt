package com.main.omwayapp.ui.screens.driver.Cobro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.main.omwayapp.R
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.screens.rider.finalizadoRider.DistanciaRecorrida
import com.main.omwayapp.ui.screens.rider.finalizadoRider.TarifaBase
import com.main.omwayapp.ui.screens.rider.finalizadoRider.TextFinalizado
import com.main.omwayapp.ui.screens.rider.finalizadoRider.TiempoRecorrido
import com.main.omwayapp.ui.screens.rider.finalizadoRider.TotalAPagar

@Composable
fun CobroRider(){
    Column(modifier =
    Modifier
        .background(color = colorResource(id = R.color.fondo))
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(12.dp))
        TextFinalizado()
        TotalAPagar()

        Row(
            modifier = Modifier.align(Alignment.Start)
                .padding(horizontal = 40.dp, vertical = 20.dp)
        )
        {
            DistanciaRecorrida()
            Spacer(modifier = Modifier.padding(10.dp))
            TarifaBase()
            Spacer(modifier = Modifier.padding(10.dp))
            TiempoRecorrido()

        }

        Spacer(modifier = Modifier.padding(20.dp))

        CustomButton(modifier = Modifier.size(width=222.dp,height=51.dp),text = "Listo", fontSize = 20.sp){}

    }

}