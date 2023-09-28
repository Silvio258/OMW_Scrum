package com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.Home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.dto.vehicle.CarDto
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.ui.components.CustomDivider
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.navigationApp.AppScreens
import kotlinx.coroutines.flow.first
import java.math.BigDecimal
import java.math.RoundingMode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview(showSystemUi = true)
fun HomeDriver(navController: NavController) {

    //Context
    val context = LocalContext.current
    //ViewModel
    ///Rider Get ViewModel
    val driverModel: DriverViewModel = viewModel()
    val driverState by driverModel._driverState.collectAsState()
    val isDriverLoading = remember { mutableStateOf(true) }

    //Values
    val cif = remember { mutableStateOf("") }

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

    //Post Driver Item ViewModel
    val driverItemModel: DriverItemViewModel = viewModel()
    val driverItemState by driverItemModel._driverState.collectAsState()

    if(!isDriverLoading.value) {
        val name = remember { mutableStateOf(driverModel.driverState.value.driverItem.name)}
        val sumRating = remember { mutableStateOf(driverModel.driverState.value.driverItem.sumRating)}
        val numberRides = remember { mutableStateOf(driverModel.driverState.value.driverItem.numberRides)}
        val earnings = remember { mutableStateOf(0.0)}
        val state = remember { mutableStateOf(driverModel.driverState.value.driverItem.state)}
        val password = remember { mutableStateOf(driverModel.driverState.value.driverItem.password)}
        val phone = remember { mutableStateOf(driverModel.driverState.value.driverItem.phone)}
        val email = remember { mutableStateOf(driverModel.driverState.value.driverItem.email)}
        val dlExpirationDate = remember { mutableStateOf(driverModel.driverState.value.driverItem.dlExpirationDate)}

        if(driverItemState) {
            LaunchedEffect(Unit){
                navController.navigate(AppScreens.HomeMenuRider.route)
            }

        }

        Column(
            modifier =
            Modifier
                .background(color = colorResource(id = R.color.fondo))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomDivider(modifier = Modifier.height(21.dp),)

            Spacer(modifier = Modifier.padding(2.dp))
            Column(modifier = Modifier.align(Alignment.Start))
            {
                Spacer(modifier = Modifier.padding(4.dp))
                //Welcome Text

                Row() {

                    Column() {
                        Text(
                            text = "Bienvenido, ${name.value}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.ibmplexsans_semibold)),
                            color = colorResource(id = R.color.texto_general),
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                        )
                    }
                    IconButton(onClick = {/*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario_perfil),
                            contentDescription = "Usuario perfil",
                            tint = Color.White,
                            modifier = Modifier
                                .size(190.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Tú Resumen:",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.ibmplexsans_semibold)),
                    color = colorResource(id = R.color.texto_general),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                )

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Row(
                    modifier = Modifier.padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Column(
                        modifier = Modifier
                            .padding(10.dp)//,

                    ) {

                        TextField(
                            value = numberRides.value.toString(),
                            onValueChange = { newValue ->
                                numberRides.value = newValue.toIntOrNull() ?: 0
                            },
                            readOnly = true,
                            modifier = Modifier
                                .size(width = 155.dp, height = 90.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            colors = TextFieldDefaults.textFieldColors
                                (
                                containerColor = colorResource(id = R.color.menta_importante)
                            ),
                            textStyle = TextStyle(
                                fontSize = 24.sp, color = colorResource(
                                    id = R.color.fondo
                                ), fontFamily = FontFamily(Font(R.font.imbplexsans_medium)),
                                textAlign = TextAlign.Center
                            ),
                            label = {
                                androidx.compose.material3.Text(
                                    text = "Viajes",
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                                    color = colorResource(id = R.color.fondo),
                                    textAlign = TextAlign.Start
                                )
                            },
                        )
                    }
                    //Ganancias Summary
                    TextField(
                        value = earnings.value.toString(),
                        onValueChange = { newValue ->
                            earnings.value = newValue.toDoubleOrNull() ?: 0.0
                        },
                        readOnly = true,
                        modifier = Modifier
                            .size(width = 155.dp, height = 95.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.textFieldColors
                            (
                            containerColor = colorResource(id = R.color.menta_importante)
                        ),
                        textStyle = TextStyle(
                            fontSize = 24.sp, color = colorResource(
                                id = R.color.fondo
                            ), fontFamily = FontFamily(Font(R.font.imbplexsans_medium)),
                            textAlign = TextAlign.Center
                        ),
                        label = {
                            androidx.compose.material3.Text(
                                text = "Total C$",
                                fontSize = 11.sp,
                                fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                                color = colorResource(id = R.color.fondo),
                                textAlign = TextAlign.Start
                            )
                        },
                    )

                }
                //Rating
                TextField(value = sumRating.value.toString(),
                    onValueChange = { newValue ->
                        sumRating.value = newValue.toIntOrNull() ?: 0
                    },
                    readOnly = true,
                    modifier = Modifier
                        .size(width = 155.dp, height = 95.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = TextFieldDefaults.textFieldColors
                        (
                        containerColor = colorResource(id = R.color.menta_importante)
                    ),
                    textStyle = TextStyle(
                        fontSize = 24.sp, color = colorResource(
                            id = R.color.fondo
                        ), fontFamily = FontFamily(Font(R.font.imbplexsans_medium)),
                        textAlign = TextAlign.Center
                    ),
                    label = {
                        androidx.compose.material3.Text(
                            text = "Rating",
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                            color = colorResource(id = R.color.fondo),
                            textAlign = TextAlign.Center
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {/*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorito),
                                contentDescription = "Rating",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    }
                )


            }

            Spacer(modifier = Modifier.padding(12.dp))
            val decimal = BigDecimal(earnings.value*0.2)
                .setScale(2, RoundingMode.HALF_EVEN)
            DepositoText(decimal.toString())

            Spacer(modifier = Modifier.padding(12.dp))

            Column() {
                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(10.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(85.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        AjustesBox(navController){
                            navController.navigate(AppScreens.Ajustes.route)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .height(85.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        ModoPasajeroBox(navController){
                            driverItemModel.updateDriver(DriverDto(cif.value,password.value,name.value,phone.value,email.value,!state.value,dlExpirationDate.value.toString()))
                        }
                    }
                }
            }


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun DepositoText(quantityToDeposit:String) {

    Column(){

        TextField(
            value = "Debes depositar: C$${quantityToDeposit}", onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .size(width = 311.dp, height = 90.dp),
            colors = TextFieldDefaults.textFieldColors
                (
                containerColor = colorResource(id = R.color.fondo)
            ),
            textStyle = TextStyle(
                fontSize = 20.sp, color = colorResource(
                    id = R.color.texto_general
                ), fontFamily = FontFamily(Font(R.font.imbplexsans_medium)),
                textAlign = TextAlign.Center
            )
        )
    }



    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesBox(navController: NavController,onClick:()->Unit){


    Column(
        modifier = Modifier
            .size(width = 150.dp, height = 85.dp)
            .background(colorResource(id = R.color.menta_importante))
            .clip(RoundedCornerShape(12.dp))
            .padding(10.dp)
            .clickable { onClick },
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(modifier =  Modifier.align(Alignment.Start)){
            Text("Ajustes",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                color = colorResource(id = R.color.fondo)
            )
        }
        Icon(painter = painterResource(id = R.drawable.ajustes),
            contentDescription = "Ajustes",
            modifier = Modifier
                .size(35.dp))

    }

}

@Composable
fun ModoPasajeroBox(navController: NavController,onClick:()->Unit){

    Column(
        modifier = Modifier
            .size(width = 150.dp, height = 85.dp)
            .background(colorResource(id = R.color.menta_importante))
            .clip(RoundedCornerShape(12.dp))
            .padding(10.dp)
            .clickable {
                onClick.invoke()
            },

        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(modifier =  Modifier.align(Alignment.Start)){
            Text("Cambiar de Modo",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.ibmplexsans_bold)),
                color = colorResource(id = R.color.fondo)
            )
        }
        Icon(painter = painterResource(id = R.drawable.carro),
            contentDescription = "Cambio de Modo",
            modifier = Modifier
                .size(35.dp))
    }

}





