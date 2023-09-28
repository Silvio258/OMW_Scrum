package com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.mycars

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.dto.vehicle.CarDto
import com.main.omwayapp.apirest.model.vehicle.CarItem
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.apirest.viewmodel.vehicle.car.CarItemViewModel
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.components.InputField
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.screens.driver.registerdriver.CarYearTextField
import com.main.omwayapp.ui.screens.driver.registerdriver.ColorTextField
import com.main.omwayapp.ui.screens.driver.registerdriver.MakeExposedDropdownMenuBox
import com.main.omwayapp.ui.screens.driver.registerdriver.ModelTextField
import com.main.omwayapp.ui.screens.driver.registerdriver.PlacaTextField
import kotlinx.coroutines.flow.first

//@Preview(showSystemUi = true)
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun agregarCarros(navController: NavController,carItem:CarItem,oper:String?){
    val item = remember{ mutableStateOf(carItem)}
    var detail: CarItemViewModel = viewModel()

    val keyBoardController = LocalSoftwareKeyboardController.current

    val licensePlate = remember { mutableStateOf("") } //numero de placa carro
    val color = remember { mutableStateOf("") } //color de carro

    //MakeExposedDropdownMenuBox
    val context = LocalContext.current
    val MakeList = listOf("Toyota", "Nissan", "Kia")
    var expanded by remember { mutableStateOf(false) }
    var selectedMake by remember { mutableStateOf("") }

    val model = remember { mutableStateOf("") } // modelo del carro

    val yearCar = remember { mutableStateOf("") }  // año del carro

    val carItemModel: CarItemViewModel = viewModel()
    val carItemState by carItemModel._carState.collectAsState()

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

    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.fondo))
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.regresar),
            contentDescription = "Carro",
            tint = colorResource(id = R.color.texto_general),
            modifier = Modifier
                .size(35.dp)
            //.clickable { navController.navigate(route= AppScreens.MenuTabApp.route)}
        )
        Text(
            text = "Agregá tu carro",
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            color = colorResource(id = R.color.texto_general),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 30.dp, horizontal = 25.dp)
        )

        //Textfield numero de placa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputField(
                modifier = Modifier
                    .size(width=330.dp,height=70.dp),
                valueState = licensePlate,
                labelId = "No.de Placa",
                icon = painterResource(id = R.drawable.placa),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions{
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
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedMake,
                    onValueChange = {selectedMake = it},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .size(width = 330.dp, height = 70.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.textFieldColors
                        (containerColor = colorResource(id = R.color.txt_fields)
                    ),
                    textStyle= TextStyle(fontSize=16.sp,color= colorResource(
                        id = R.color.texto_general)
                    ),
                    label = {
                        Text(text = "Marca",
                            color = colorResource(id = R.color.texto_general)
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = {/*TODO*/}){
                            Icon(painter = painterResource(id = R.drawable.carro),
                                contentDescription = "Marca del carro",
                                modifier = Modifier
                                    .size(24.dp))
                        }
                    }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    MakeList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedMake = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        //Text field modelo del carro
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputField(
                modifier = Modifier
                    .size(width=330.dp,height=70.dp),
                valueState = model,
                labelId = "Modelo",
                icon = painterResource(id = R.drawable.carro),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions{
                    keyBoardController?.hide()
                }
            )
        }

        //Texfield color de carro
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputField(
                modifier = Modifier
                    .size(width=330.dp,height=70.dp),
                valueState = color,
                labelId = "Color",
                icon = painterResource(id = R.drawable.color),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions{
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
        ){
            InputField(
                modifier = Modifier
                    .size(width=330.dp,height=70.dp),
                valueState = yearCar,
                labelId = "Año modelo",
                icon = painterResource(id = R.drawable.carro),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Number,
                onAction = KeyboardActions{
                    keyBoardController?.hide()
                }
            )
        }
        Spacer(modifier = Modifier.padding(25.dp))
        CustomButton(modifier = Modifier
            .align(CenterHorizontally)
            .size(width = 222.dp, height = 51.dp),
            text = "Agregar",
            fontSize = 20.sp) {
            //carItemModel.saveCar(CarDto(licensePlate.value,color.value,yearCar.value, state = true,cif.value,model.value))

        }
        Spacer(modifier = Modifier.padding(25.dp))


    }

}