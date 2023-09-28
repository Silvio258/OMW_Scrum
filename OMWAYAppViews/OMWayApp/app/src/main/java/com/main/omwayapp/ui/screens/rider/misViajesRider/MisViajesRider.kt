package com.main.omwayapp.ui.screens.rider.misViajesRider


import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.main.omwayapp.R
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderViewModel
import com.main.omwayapp.apirest.viewmodel.trip.RideItemViewModel
import com.main.omwayapp.apirest.viewmodel.trip.RideViewModel
import com.main.omwayapp.ui.components.ExpandableCardTrips
import com.main.omwayapp.ui.components.ExpandableCardTripsDoneRider
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.model.Location
import kotlinx.coroutines.flow.first
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MisViajesRider(navController: NavController) {

    //Context
    val context = LocalContext.current
    //Values

    val distance = remember { mutableStateOf(5.54) }
    val notes = remember { mutableStateOf("") }
    val baseFare = remember { mutableStateOf(40) }
    val perKmFare = remember { mutableStateOf(10) }
    val fare = remember { mutableStateOf(baseFare.value + (distance.value * perKmFare.value)) }

    //ViewModel
    val riderModel: RiderViewModel = viewModel()
    val riderState by riderModel._riderState.collectAsState()
    val isRiderLoading = remember { mutableStateOf(true) }

    ///Ride Get ViewModel
    val rideModel: RideViewModel = viewModel()
    val discontinuedRideState by rideModel._discontinuedRideState.collectAsState()
    val inProgressRideState by rideModel._inProgressRideState.collectAsState()
    val isDiscontinuedRideLoading = remember { mutableStateOf(true) }
    val isInProgressRideLoading = remember { mutableStateOf(true) }
    //Values
    val cif = remember { mutableStateOf("") }


    //Storage

    val dataStore = DataStoreManager(context)

    //Launched Effect
    LaunchedEffect(Unit) {
        val value = dataStore.getValue.first()
        if (value != null) {
            cif.value = value
            riderModel.findRiderByCif(cif.value)

        }
    }

    LaunchedEffect(riderState) {
        isRiderLoading.value = riderState._loading
        Log.d("STATE", isRiderLoading.value.toString())
        if(!isRiderLoading.value){
            rideModel.findDiscontinuedRideByRiderCif(cif.value)
        }


    }
    LaunchedEffect(discontinuedRideState) {
        isDiscontinuedRideLoading.value = discontinuedRideState._loading
        Log.d("STATE", isDiscontinuedRideLoading.value.toString())
        if(!isDiscontinuedRideLoading.value){
            rideModel.findInProgressRidesByRiderCif(cif.value)
        }


    }
    LaunchedEffect(inProgressRideState) {
        isInProgressRideLoading.value = inProgressRideState._loading
        Log.d("STATE", isInProgressRideLoading.value.toString())

    }

    if (!isInProgressRideLoading.value) {
        val ridesR= remember { mutableStateOf(rideModel.discontinuedRideState.value.listDiscontinuedRides) }
        val ridesRIP= remember { mutableStateOf(rideModel.inProgressRideState.value.listInProgressRides) }

        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.fondo))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.regresar),
                contentDescription = "Back",
                tint = colorResource(id = R.color.texto_general),
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Start)
                //.clickable { navController.navigate(route= DriverScreens.MenuTabDriver.route)}
            )
            Text(
                text = "Mis Viajes",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 15.dp, horizontal = 25.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Planeados",
                color = colorResource(id = R.color.menta_importante),
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 40.dp)
            )
            Column() {

                for(rideRIP in ridesRIP.value) {
                    ExpandableCardTrips(
                        nomRider = rideRIP.driver.name,
                        tarifa = rideRIP.fare.toString(),
                        hora = rideRIP.pickUpTime.toString(),
                        puntoA = rideRIP.pickUpLocation,
                        puntoB = rideRIP.dropOffLocation,
                        distanciaEst = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Text(
                    text = "Completados",
                    color = colorResource(id = R.color.menta_importante),
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 40.dp)
                )
                for(rideR in ridesR.value) {
                    Log.d("FARE", rideR.fare.toString())
                    ExpandableCardTripsDoneRider(
                        tarifa = rideR.fare.toString(),
                        hora = rideR.dropOffTime.toString(),
                        puntoA = rideR.pickUpLocation,
                        puntoB = rideR.dropOffLocation,
                        distanciaRec = "Distancia: ",
                        distancia = rideR.distance.toString(),
                        tiempo = "Tiempo: ",
                        tiempotardado = "10 min.",
                        nomRider = rideR.driver.name,
                        detallesCarro =rideR.car.model.make.name+" "+rideR.car.model.name+" "+rideR.car.color,
                        placa =rideR.car.licensePlate
                    )
                }
            }

        }

    }
}