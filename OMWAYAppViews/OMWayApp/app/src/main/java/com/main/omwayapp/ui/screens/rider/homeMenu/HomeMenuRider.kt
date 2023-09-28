package com.main.omwayapp.ui.screens.rider.homeMenu

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.trip.RideDto
import com.main.omwayapp.apirest.model.omwayuser.RiderItem
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderViewModel
import com.main.omwayapp.apirest.viewmodel.trip.RideItemViewModel
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.components.InputField
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.model.Location
import com.main.omwayapp.ui.navigationApp.AppScreens

import com.main.omwayapp.ui.views.map.OurGoogleMaps
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter",
    "StateFlowValueCalledInComposition"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homemenuRider(navController: NavHostController) {
    //Context
    val context = LocalContext.current

    //ViewModel
    ///Rider Get ViewModel
    val riderModel: RiderViewModel = viewModel()
    val riderState by riderModel._riderState.collectAsState()
    val isRiderLoading = remember { mutableStateOf(false) }

    //Values
    val cif = remember { mutableStateOf("") }


    //Storage

    val dataStore = DataStoreManager(context)


    //Get Cif From DataStorage
    LaunchedEffect(Unit) {
        val value = dataStore.getValue.first()
        if (value != null) {
            cif.value = value
            riderModel.findRiderByCif(cif.value)
        }
    }
    //Get Rider With Cif
    LaunchedEffect(riderState) {
        isRiderLoading.value = riderState._loading
        Log.d("STATE", isRiderLoading.value.toString())
    }
    //States
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    //Print Composables until state is not loading (Rider information is ready)
    if (!isRiderLoading.value) {
        val riderName = remember { mutableStateOf(riderModel.riderState.value.riderItem.name)}
        Scaffold(backgroundColor = colorResource(id = R.color.fondo),
            scaffoldState = scaffoldState, topBar = {
                AppBarMapView(onNavigationIconClick = { scope.launch { scaffoldState.drawerState.open() } })
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen, drawerContent = {
                DrawerHeader(riderName)
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "misviajes",
                            title = "Mis Viajes",
                            contentDescrip = "Go to",
                            R.drawable.misviajes
                        ),
                        MenuItem(
                            id = "ajustes",
                            title = "Ajustes",
                            contentDescrip = "Go to",
                            R.drawable.ajustes
                        ),
                        MenuItem(
                            id = "driver",
                            title = "Driver",
                            contentDescrip = "Go to",
                            R.drawable.carro
                        ),
                    ),
                    onItemClick = {
                        when (it.id) {
                            "misviajes" -> navController.navigate(route = AppScreens.MisViajesRider.route)
                            "ajustes" -> navController.navigate(route = AppScreens.Ajustes.route)
                            "driver" -> navController.navigate(route = AppScreens.RegisterDriver.route)
                        }
                    },
                )
            }
        ) {
            Mapa(navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarMapView(onNavigationIconClick:()->Unit){
    TopAppBar(backgroundColor = colorResource(id = R.color.fondo), contentColor = Color.White,
        title = {
            Text(text = "")
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toogle Drawer", tint = Color.White)
            }
        }
    )
}

@Composable
fun DrawerHeader(riderName:MutableState<String>){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(colorResource(id = R.color.menta_importante))){
        Column(modifier=Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally ) {
            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                // Image(painter = , contentDescription = )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.usuario_perfil),
                    contentDescription = "Tiempo",
                    modifier = Modifier
                        .padding(12.dp)
                        .width(60.dp)
                        .height(60.dp))
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = riderName.value, fontSize = 20.sp,color=Color.Black, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold )
            }
        }
    }
}

@Composable
fun DrawerBody(items:List<MenuItem>, modifier: Modifier= Modifier
    .background(color = Color.Black)
    .fillMaxSize(), itemTextStyle:TextStyle= TextStyle(fontSize = 16.sp), onItemClick:(MenuItem)->Unit){
    LazyColumn(modifier){
        items(items){item->
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
            ){
                Image(painter=painterResource(id = item.icon), contentDescription = item.contentDescrip,modifier= Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(5.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun Mapa(navController: NavHostController) {

    //Context
    val context = LocalContext.current
    //Values

    val uam = Location(LatLng(12.10877952, -86.2564972), "UAM", "Universidad Americana")
    val cif = remember {mutableStateOf("")}
    val pickUpLocation = remember {mutableStateOf("My Location")}
    val dropOffLocation = remember {mutableStateOf("")}
    val distance = remember {mutableStateOf(5.54)}
    val notes = remember {mutableStateOf("")}
    val baseFare = remember {mutableStateOf(40)}
    val perKmFare = remember {mutableStateOf(10)}
    val fare = remember {mutableStateOf(baseFare.value + (distance.value * perKmFare.value))}
        ///Values TimePicker
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
        //// Value for storing time as a string in am pm format
    var mTime = remember { mutableStateOf("") }
        ////TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            val amPm: String = if (mHour < 12) "AM" else "PM"
            val hour12: Int = if (mHour == 0) 12 else if (mHour > 12) mHour - 12 else mHour
            val minuteFormatted: String = String.format("%02d", mMinute)
            mTime.value = "$hour12:$minuteFormatted $amPm"
        },
        mHour,
        mMinute,
        false // Enable 12-hour format
    )
    //ViewModel
    val rideItemViewModel:RideItemViewModel = viewModel()
    val rideState by rideItemViewModel ._rideState.collectAsState()

    //States

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    //Controller

    var keyBoardController = LocalSoftwareKeyboardController.current

    //Storage

    val dataStore = DataStoreManager(context)

    //Launched Effect

    LaunchedEffect(Unit) {
        val value = dataStore.getValue.first()
        if (value != null) {
            cif.value = value
        }
    }

    //Navigation

    if(rideState){
        LaunchedEffect(Unit){
            navController.navigate(route= AppScreens.MisViajesRider.route)
        }

    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
            ) {
                val context = LocalContext.current
                //IF BOTTOM SHEET CLOSED
                if (sheetState.isCollapsed) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))
                        // DropOffLocation
                        InputField(
                            valueState = dropOffLocation,
                            labelId = "¿A dónde vas?",
                            icon = painterResource(id = R.drawable.punto_a),
                            enabled = true,
                            isSingleLine = true,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        //Time

                        InputField(
                            modifier = Modifier.clickable { mTimePickerDialog.show() },
                            valueState = mTime,
                            labelId = "Tiempo",
                            icon = painterResource(id = R.drawable.calendario),
                            enabled = false,
                            isSingleLine = true,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )


                        Spacer(modifier = Modifier.height(20.dp))
                        //Notes
                        InputField(
                            valueState = notes,
                            labelId = "Notas",
                            icon = painterResource(id = R.drawable.notas),
                            enabled = true,
                            isSingleLine = false,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        //Fare
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "            Precio C$:",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp),
                                color = Color.White,
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        //Request Button
                        CustomButton(
                            text = "Pedir Ahora",
                            modifier = Modifier.width(150.dp),
                            fontSize = 20.sp,
                            onClick = {
                                val pickUpTime= convertTimeToFormatted(mTime.value)
                                Log.d("PICKUPTIME",pickUpTime)
                                rideItemViewModel.saveRide(RideDto(
                                    null,
                                    pickUpTime,
                                    null,
                                    pickUpLocation.value,
                                    dropOffLocation.value,
                                    distance.value,
                                    LocalDate.now().toString(),
                                    notes.value,
                                    "REQUESTED",
                                    fare.value,
                                    null,
                                    null,
                                    cif.value,
                                    null,
                                    null))
                            }
                        )


                    }
                    //SI SE ABRE
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))
                        // DropOffLocation
                        InputField(
                            valueState = dropOffLocation,
                            labelId = "¿A dónde vas?",
                            icon = painterResource(id = R.drawable.punto_a),
                            enabled = true,
                            isSingleLine = true,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        //Time
                        InputField(
                            modifier = Modifier.clickable { mTimePickerDialog.show() },
                            valueState = mTime,
                            labelId = "Tiempo",
                            icon = painterResource(id = R.drawable.calendario),
                            enabled = false,
                            isSingleLine = true,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        //Notes
                        InputField(
                            valueState = notes,
                            labelId = "Notas",
                            icon = painterResource(id = R.drawable.notas),
                            enabled = true,
                            isSingleLine = false,
                            keyboardType = KeyboardType.Text,
                            onAction = KeyboardActions{
                                keyBoardController?.hide()
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        //Fare
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "            Precio C$:",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp),
                                color = Color.White,
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        //Request Button
                        CustomButton(
                            text = "Pedir Ahora",
                            modifier = Modifier.width(150.dp),
                            fontSize = 20.sp,
                            onClick = {
                                val pickUpTime= convertTimeToFormatted(mTime.value)
                                Log.d("PICKUPTIME",pickUpTime)
                                rideItemViewModel.saveRide(RideDto(
                                    0,
                                    pickUpTime,
                                    null,
                                    pickUpLocation.value,
                                    dropOffLocation.value,
                                    distance.value,
                                    LocalDate.now().toString(),
                                    notes.value,
                                    "REQUESTED",
                                    fare.value,
                                    null,
                                    null,
                                    cif.value,
                                    null,
                                    null))
                            }
                        )


                    }
                }

            }

        }, sheetBackgroundColor = colorResource(id = R.color.fondo), sheetPeekHeight = 110.dp
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            //TestMap()
            OurGoogleMaps(uam) {}
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertTimeToFormatted(time: String): String {
    val parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:mm a"))
    return parsedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
}