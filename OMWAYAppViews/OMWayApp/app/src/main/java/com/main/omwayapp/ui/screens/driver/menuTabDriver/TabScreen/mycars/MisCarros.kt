package com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.mycars

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderViewModel
import com.main.omwayapp.ui.components.CustomDivider
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.screens.driver.mycars.MyUI
import com.main.omwayapp.ui.navigationApp.AppScreens
import kotlinx.coroutines.flow.first

//@Preview(showSystemUi = true)
@Composable
fun MisCarros(navController: NavController){

    val context = LocalContext.current
    //ViewModel
    ///Driver Get ViewModel
    val driverModel: DriverViewModel = viewModel()
    val driverState by driverModel._driverState.collectAsState()
    val isDriverLoading = remember { mutableStateOf(false) }

    //Values
    val cif = remember { mutableStateOf("")}
    //Storage

    val dataStore = DataStoreManager(context)

    //Get Cif From DataStorage
    LaunchedEffect(Unit) {
        val value = dataStore.getValue.first()
        if (value != null) {
            cif.value = value
            driverModel.findDriverByCif(cif.value)
        }
    }
    //Get Driver With Cif
    LaunchedEffect(driverState) {
        isDriverLoading.value = driverState._loading
        Log.d("STATE", isDriverLoading.value.toString())
    }

    if (!isDriverLoading.value) {

        val cars= remember { mutableStateOf(driverModel.driverState.value.driverItem.driverCars) }
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.fondo))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomDivider(modifier = Modifier.height(21.dp),)
            Spacer(modifier = Modifier.height(15.dp))


            AgregarCarroText(navController)




          for(car in cars.value){
              Log.d("MARCA", car.model.make.name)
            Spacer(modifier = Modifier.height(15.dp))
                MisCarrosOptionMenuBox(
                    navController,
                    marca = car.model.make.name,
                    modelo = car.model.name,
                    anio = car.year,
                    tonocolor = car.color,
                    placa = car.licensePlate
                )

            }



        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCarrosOptionMenuBox(
    navController: NavController,
    marca: String,
    modelo: String,
    anio: String,
    tonocolor: String,
    placa: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .size(width = 330.dp, height = 75.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.txt_fields))
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.carro),
                        contentDescription = "Carro",
                        tint = colorResource(id = R.color.texto_general),
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = marca + " " + modelo + " " + anio,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 18.sp
                        )
                        Text(
                            text = tonocolor + " " + placa,
                            color = colorResource(id = R.color.texto_general),
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(45.dp))
                    MyUI(navController,coloricono = colorResource(id = R.color.texto_general))
                }

            }
        }
    }
    }

@Composable
fun AgregarCarroText(navController: NavController){
    Row(horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Agregar Carro",
            modifier = Modifier
                .clickable {
                    navController.navigate(route = AppScreens.AgregarCarro.route)
                           },
            color = colorResource(id = R.color.menta_importante),
        )
    }
}