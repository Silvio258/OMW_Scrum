package com.main.omwayapp.ui.screens.driver.viajeDriver

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.main.omwayapp.R
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.model.Location
import com.main.omwayapp.ui.screens.rider.viajeRider.tbtr
import com.main.omwayapp.ui.screens.rider.viajeRider.tllr
import com.main.omwayapp.ui.views.map.OurGoogleMaps



lateinit var tbtd:String
lateinit var bttnd:String
lateinit var tlld:String



@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun viajeDriver(navController: NavHostController) {
    var estado: String by rememberSaveable { mutableStateOf("") }
    //Estado barra superior
    if(estado=="ACCEPTED") tbtr ="Usted va en camino"
    else if(estado=="ONMYWAY") tbtr ="Esta en el punto inicial"
    else if(estado=="STARTED") tbtr ="El viaje ha iniciado"
    //Estado Boton
    if(estado=="ACCEPTED") tbtr ="Estoy aqui"
    else if(estado=="ONMYWAY") tbtr ="Iniciar"
    else if(estado=="STARTED") tbtr ="Cobrar"
    //Estado tiempo
    if(estado=="ACCEPTED") tllr ="Tiempo estimado de llegada: 8mins"
    else if(estado=="ONMYWAY") tllr =""
    else if(estado=="STARTED") tllr ="Tiempo estimado de llegada: 8mins"
    TopBarDriver()

}


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarDriver(){
    Scaffold(topBar = { TopAppBar(
        elevation = 2.dp, backgroundColor = colorResource(id = R.color.fondo)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = tbtd,color= colorResource(id = R.color.menta_importante), fontSize = 20.sp, fontWeight = FontWeight.Bold )

        }

    }
    }) {
        BottmSheetDriver()
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottmSheetDriver(){
    val uam= Location(LatLng(12.10877952,-86.2564972),"UAM","Universidad Americana")

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(color = colorResource(id = R.color.fondo))
            ) {
                val context = LocalContext.current

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top

                ){
                    Image(
                        painter = painterResource(id = R.drawable.usuario_perfil),
                        contentDescription = "Driver",
                        modifier = Modifier
                            .padding(15.dp)
                            .width(80.dp)
                            .height(80.dp)
                    )
                    Column(
                        //modifier = Modifier.fillMaxSize(),
                        //verticalArrangement = Arrangement.Top,
                        //horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TxtFieldDname()

                    }

                }




            }

        }, sheetBackgroundColor = colorResource(id = R.color.fondo), sheetPeekHeight = 30.dp
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            //TestMap()
            OurGoogleMaps(uam) {}
        }

    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtFieldDname(){
    var DriverName: String by rememberSaveable{ mutableStateOf("") }
    var PointA: String by rememberSaveable{ mutableStateOf("") }
    var PointB: String by rememberSaveable{ mutableStateOf("") }

    DriverName="Pedro Pascal"
    PointA="Villa Fontana Norte"
    PointB="Universidad Americana (UAM) "
    Column() {


        Row() {
            Text(
                text = DriverName, fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier.padding(10.dp)
            )
        }
        Row() {
            Image(
                painter = painterResource(id = R.drawable.punto_a),
                contentDescription = "Point A",
                modifier = Modifier
                    .padding(7.dp)
                    .width(10.dp)
                    .height(10.dp)
            )
            Text(
                text = PointA, fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier.padding(5.dp)
            )
        }
        Row() {
            Image(
                painter = painterResource(id = R.drawable.punto_b),
                contentDescription = "Point B",
                modifier = Modifier
                    .padding(7.dp)
                    .width(10.dp)
                    .height(10.dp)
            )
            Text(
                text = PointB, fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier.padding(5.dp)
            )
        }
        Text(
            text = tlld, fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(id = R.color.menta_importante),
            modifier = Modifier.padding(5.dp)
        )
    }
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomButton(text = bttnd, modifier = Modifier
            .width(100.dp)
            .height(35.dp)
            .padding(5.dp), fontSize = 10.sp) {

        }
    }




}