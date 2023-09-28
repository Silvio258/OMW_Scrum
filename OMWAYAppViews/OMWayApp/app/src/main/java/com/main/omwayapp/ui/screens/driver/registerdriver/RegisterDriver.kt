package com.main.omwayapp.ui.screens.driver.registerdriver

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.dto.vehicle.CarDto
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderViewModel
import com.main.omwayapp.apirest.viewmodel.vehicle.car.CarItemViewModel
import com.main.omwayapp.apirest.viewmodel.vehicle.make.MakeViewModel
import com.main.omwayapp.apirest.viewmodel.vehicle.model.ModelViewModel
import com.main.omwayapp.ui.components.CenteredImage
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.components.CustomDivider
import com.main.omwayapp.ui.components.InputField
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.navigationApp.AppScreens
import kotlinx.coroutines.flow.first
import java.util.Calendar


//Pantalla de registro driver
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterDriver(navController: NavController){
    //Context
    val context = LocalContext.current
    //Controller
    val keyBoardController = LocalSoftwareKeyboardController.current
    //Date Picker
    var dlExpDate: String by rememberSaveable{ mutableStateOf("") } //fecha expiracion licencia
    val yearDl: Int
    val monthDl: Int
    val dayDl: Int
    val mCalendar: Calendar = Calendar.getInstance()
    yearDl = mCalendar.get(Calendar.YEAR)
    monthDl = mCalendar.get(Calendar.MONTH)
    dayDl = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            val formattedMonth = (month + 1).toString().padStart(2, '0') // Add leading zero if necessary
            val formattedDay = day.toString().padStart(2, '0') // Add leading zero if necessary
            dlExpDate = "$year-$formattedMonth-$formattedDay"
        },
        yearDl,
        monthDl,
        dayDl
    )

    //Values
    val licensePlate = remember { mutableStateOf("")} //numero de placa carro
    val color = remember { mutableStateOf("") } //color de carro

    ///MakeExposedDropdownMenuBox
    var expandedMake by remember { mutableStateOf(false) }
    var expandedModel by remember { mutableStateOf(false) }

    val selectedMake = remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }

    val selectedModelId = remember { mutableStateOf(0) }
    val selectedMakeId = remember{ mutableStateOf(0)}
    val yearCar = remember { mutableStateOf("") }  // año del carro


    //ViewModel
    ///Rider Get ViewModel
    val riderModel: RiderViewModel = viewModel()
    val riderState by riderModel._riderState.collectAsState()
    val isRiderLoading = remember { mutableStateOf(true) }

    ///Driver Get ViewModel
    val driverModel: DriverViewModel = viewModel()
    val driverState by driverModel._driverState.collectAsState()
    val isDriverLoading = remember { mutableStateOf(true) }

    ///Make Get ViewModel
    val makeModel: MakeViewModel = viewModel()
    val makeState by makeModel._makeState.collectAsState()
    val isMakeLoading = remember { mutableStateOf(true) }

    ///Model Get ViewModel
    val modelModel: ModelViewModel = viewModel()
    val modelState by modelModel._modelState.collectAsState()
    val isModelLoading = remember { mutableStateOf(true) }

    ///Post Driver Item ViewModel
    val driverItemModel: DriverItemViewModel = viewModel()
    val driverItemState by driverItemModel._driverState.collectAsState()

    ///Post Car Item ViewModel
    val carItemModel: CarItemViewModel = viewModel()
    val carItemState by carItemModel._carState.collectAsState()

    //Values
    val cif = remember { mutableStateOf("")}
    val driverCif = remember{ mutableStateOf("")}
    //Storage
    val dataStore = DataStoreManager(context)


    //Launched Efect

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
        Log.d("STATEDRIVER", isDriverLoading.value.toString())
        if(!isDriverLoading.value){
            driverCif.value = driverModel.driverState.value.driverItem.cif
            riderModel.findRiderByCif(cif.value)
        }
    }
    if(!isDriverLoading.value) {
        if (driverCif.value == cif.value) {
            LaunchedEffect(Unit) {
                navController.navigate(route = AppScreens.MenuTabDriver.route)
            }
        }
    }
    //Get Rider With Cif
    LaunchedEffect(riderState) {
        isRiderLoading.value = riderState._loading
        Log.d("STATERIDER", isRiderLoading.value.toString())
        if(!isRiderLoading.value){
            makeModel.getAllMake()
        }
    }

    //Get All Make
    LaunchedEffect(makeState) {
        Log.d("STATERIDER", isRiderLoading.value.toString())
        isMakeLoading.value = makeState._loading
        Log.d("STATEMAKE", isMakeLoading.value.toString())
    }

    //Get Models From Make Id
    LaunchedEffect(modelState) {
        Log.d("STATEMAKE", isMakeLoading.value.toString())
        isModelLoading.value = modelState._loading
        Log.d("STATEMODEL", isModelLoading.value.toString())
    }
    //After Saving Driver Data, Save Car
    if(driverItemState) {
        carItemModel.saveCar(CarDto(licensePlate.value,color.value,yearCar.value,true,cif.value,selectedModelId.value))
    }
    //After Saving Car Data, Navigate
    if(carItemState){
        LaunchedEffect(Unit){
            navController.navigate(route = AppScreens.MenuTabDriver.route)
        }
    }
    //States
    val modelTextfldState = remember { mutableStateOf(false) }
    if(!isModelLoading.value) {
        modelTextfldState.value=true
    }

    if (!isRiderLoading.value) {
        val passwordD = remember { mutableStateOf(riderModel.riderState.value.riderItem.password) }
        val nameD = remember { mutableStateOf(riderModel.riderState.value.riderItem.name) }
        val phoneD = remember { mutableStateOf(riderModel.riderState.value.riderItem.phone) }
        val emailD = remember { mutableStateOf(riderModel.riderState.value.riderItem.email) }
        if (!isMakeLoading.value) {
            val makes = remember { mutableStateOf(makeModel.makeState.value.listMake)}
            Column(
                modifier =
                Modifier
                    .background(color = colorResource(id = R.color.fondo))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.padding(8.dp))

                CenteredImage(
                    image = painterResource(id = R.drawable.logo_omway),
                    size = DpSize(width = 504.dp, height = 100.dp)
                )


                CustomDivider(modifier = Modifier.height(21.dp),)

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Regístrate como conductor",
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    color = colorResource(id = R.color.texto_general),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                )
                Text(
                    text = "Por favor llenar todos los campos.",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.ibmplexsans_regular)),
                    color = colorResource(id = R.color.texto_opacidad),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Datos de tu Licencia",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    color = colorResource(id = R.color.texto_general),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                )

                //Fecha expiracion Licencia
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {

                    TextField(value = dlExpDate,
                        onValueChange = { dlExpDate = it },
                        singleLine = true,
                        readOnly = true,
                        modifier = Modifier
                            .size(width = 330.dp, height = 70.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { mDatePickerDialog.show() },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.txt_fields),
                            focusedLabelColor = colorResource(id = R.color.texto_general),
                            unfocusedLabelColor = colorResource(id = R.color.texto_general)
                        ),
                        label = {
                            Text(
                                text = "Fecha de vencimiento"
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp, color = colorResource(
                                id = R.color.texto_general
                            )
                        ),

                        leadingIcon = {
                            IconButton(onClick = {/*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.calendario),
                                    contentDescription = "Calendario",
                                    modifier = Modifier
                                        .clickable { mDatePickerDialog.show() }
                                        .size(24.dp))
                            }
                        }


                    )
                }

                //Informacion de carro
                Text(
                    text = "Agregá tú carro",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    color = colorResource(id = R.color.texto_general),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                )

                //TextField Numero de Placa
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputField(
                        modifier = Modifier
                            .size(width = 330.dp, height = 70.dp),
                        valueState = licensePlate,
                        labelId = "No.de Placa",
                        icon = painterResource(id = R.drawable.placa),
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text,
                        onAction = KeyboardActions {
                            keyBoardController?.hide()
                        }
                    )
                }

                // MakeExposedDropdownMenuBox
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expandedMake,
                        onExpandedChange = {
                            expandedMake = !expandedMake
                        }
                    ) {
                        TextField(
                            value = selectedMake.value,
                            onValueChange = { selectedMake.value = it },
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMake) },
                            modifier = Modifier
                                .menuAnchor()
                                .size(width = 330.dp, height = 70.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            colors = TextFieldDefaults.textFieldColors
                                (
                                containerColor = colorResource(id = R.color.txt_fields)
                            ),
                            textStyle = TextStyle(
                                fontSize = 16.sp, color = colorResource(
                                    id = R.color.texto_general
                                )
                            ),
                            label = {
                                Text(
                                    text = "Marca",
                                    color = colorResource(id = R.color.texto_general)
                                )
                            },
                            leadingIcon = {
                                IconButton(onClick = {/*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.carro),
                                        contentDescription = "Marca del carro",
                                        modifier = Modifier
                                            .size(24.dp)
                                    )
                                }
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expandedMake,
                            onDismissRequest = { expandedMake = false }
                        ) {

                            makes.value.forEach { item ->
                                Log.d("INSIDEFOREACH","Id ${item.id} , Nombre ${item.name}")
                                DropdownMenuItem(
                                    text = { Text(text = item.name) },
                                    onClick = {
                                        selectedMake.value = item.name
                                        selectedMakeId.value = item.id
                                        expandedMake = false
                                        selectedModel = ""
                                        Log.d("MODELID","Id ${selectedMakeId.value}")
                                        modelModel.findModelsByMake(selectedMakeId.value)
                                    }
                                )
                            }
                        }
                    }
                }
                if(!isModelLoading.value) {
                    Log.d("MODELSTATE","${!isModelLoading.value}")
                    val models = remember { mutableStateOf(modelModel.modelState.value.listModel) }
                    //Log.d("MODELVALUE","Nombre de modelo${models.value.get(0).name} , marca ${models.value.get(0).make.name}")
                    // ModelExposedDropdownMenuBox
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = expandedModel,
                            onExpandedChange = {
                                expandedModel = !expandedModel
                            }
                        ) {
                            TextField(
                                value = selectedModel,
                                onValueChange = { selectedModel = it },
                                readOnly = true,
                                enabled = modelTextfldState.value,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedModel) },
                                modifier = Modifier
                                    .menuAnchor()
                                    .size(width = 330.dp, height = 70.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                colors = TextFieldDefaults.textFieldColors
                                    (
                                    containerColor = colorResource(id = R.color.txt_fields)
                                ),
                                textStyle = TextStyle(
                                    fontSize = 16.sp, color = colorResource(
                                        id = R.color.texto_general
                                    )
                                ),
                                label = {
                                    Text(
                                        text = "Modelo",
                                        color = colorResource(id = R.color.texto_general)
                                    )
                                },
                                leadingIcon = {
                                    IconButton(onClick = {/*TODO*/ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.carro),
                                            contentDescription = "Modelo del carro",
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                    }
                                }
                            )
                            if(modelTextfldState.value) {
                                ExposedDropdownMenu(
                                    expanded = expandedModel,
                                    onDismissRequest = { expandedModel = false }
                                ) {

                                    models.value.forEach { item ->
                                        DropdownMenuItem(
                                            text = { Text(text = item.name) },
                                            onClick = {
                                                selectedModel = item.name
                                                selectedModelId.value = item.id
                                                selectedMake.value = item.make.name
                                                expandedModel = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                else{
                    TextField(
                        value = selectedModel,
                        onValueChange = { selectedModel = it },
                        readOnly = true,
                        enabled = modelTextfldState.value,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedModel) },
                        modifier = Modifier
                            .size(width = 330.dp, height = 70.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = TextFieldDefaults.textFieldColors
                            (
                            containerColor = colorResource(id = R.color.txt_fields)
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp, color = colorResource(
                                id = R.color.texto_general
                            )
                        ),
                        label = {
                            Text(
                                text = "Modelo",
                                color = colorResource(id = R.color.texto_general)
                            )
                        },
                        leadingIcon = {
                            IconButton(onClick = {/*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.carro),
                                    contentDescription = "Modelo del carro",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }
                        }
                    )
                }

                //Texfield color de carro
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputField(
                        modifier = Modifier
                            .size(width = 330.dp, height = 70.dp),
                        valueState = color,
                        labelId = "Color",
                        icon = painterResource(id = R.drawable.color),
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text,
                        onAction = KeyboardActions {
                            keyBoardController?.hide()
                        }
                    )
                }
                // Text field año modelo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputField(
                        modifier = Modifier
                            .size(width = 330.dp, height = 70.dp),
                        valueState = yearCar,
                        labelId = "Año modelo",
                        icon = painterResource(id = R.drawable.carro),
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,
                        onAction = KeyboardActions {
                            keyBoardController?.hide()
                        }
                    )
                }

                Spacer(modifier = Modifier.padding(25.dp))
                CustomButton(
                    modifier = Modifier.size(width = 222.dp, height = 51.dp),
                    text = "Regístrate",
                    fontSize = 20.sp){
                    driverItemModel.saveDriver(DriverDto(cif.value,passwordD.value,nameD.value,
                            phoneD.value,emailD.value,true,dlExpDate
                        ))
                }
                Spacer(modifier = Modifier.padding(25.dp))


            }
        }
    }
}








