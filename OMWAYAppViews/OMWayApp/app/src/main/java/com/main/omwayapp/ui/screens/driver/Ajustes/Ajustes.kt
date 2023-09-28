package com.main.omwayapp.ui.screens.driver.Ajustes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderViewModel
import com.main.omwayapp.apirest.viewmodel.trip.RideItemViewModel
import com.main.omwayapp.ui.components.CenteredImage
import com.main.omwayapp.ui.components.CustomDivider
import com.main.omwayapp.ui.components.InputField
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.navigationApp.AppScreens
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalComposeUiApi::class)
@Composable
//@Preview(showSystemUi=true)
fun Ajustes(navController: NavController) {
    //Context
    val context = LocalContext.current

    //Controller
    var keyBoardController = LocalSoftwareKeyboardController.current

    //ViewModel
        ///Rider Get ViewModel
    val riderModel: RiderViewModel = viewModel()
    val riderState by riderModel._riderState.collectAsState()
    val isRiderLoading = remember { mutableStateOf(false) }

        ///Rider Item ViewModel
    val riderItemModel: RiderItemViewModel = viewModel()
    val riderItemState by riderItemModel._riderState.collectAsState()

    //Values
    val cif = remember { mutableStateOf("")}
    val logout = remember { mutableStateOf(false)}


    //Storage

    val dataStore = DataStoreManager(context)

    if(riderItemState){
        LaunchedEffect(Unit){
            navController.navigate(route = AppScreens.HomeMenuRider.route)
        }
    }
    if(logout.value){
        LaunchedEffect(Unit){
            navController.navigate(route = AppScreens.Login.route)
        }
    }


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
    //Print Composables until state is not loading (Rider information is ready)
    if (!isRiderLoading.value) {
        val password = remember { mutableStateOf(riderModel.riderState.value.riderItem.password) }
        val name = remember { mutableStateOf(riderModel.riderState.value.riderItem.name) }
        val email = remember { mutableStateOf(riderModel.riderState.value.riderItem.email) }
        val phone = remember { mutableStateOf(riderModel.riderState.value.riderItem.phone) }
        val state = remember { mutableStateOf(riderModel.riderState.value.riderItem.state) }
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

            Spacer(modifier = Modifier.padding(8.dp))

            CustomDivider(modifier = Modifier.height(21.dp),)

            Text(
                text = "Ajustes",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.ibmplexsans_semibold)),
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 30.dp, horizontal = 16.dp)
            )
            Log.d("RIDERVALUES",name.value)


            Spacer(modifier = Modifier.padding(10.dp))
            //AjustesNombreUser()
            InputField(
                modifier = Modifier
                    .size(width = 340.dp, height = 70.dp),
                valueState = name,
                labelId = "Nombre",
                icon = painterResource(id = R.drawable.usuario),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions {
                    keyBoardController?.hide()
                }
            )
            //AjustesEmailUser()
            InputField(
                modifier = Modifier
                    .size(width=340.dp,height=70.dp),
                valueState = email,
                labelId = "Email",
                icon = painterResource(id = R.drawable.email),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions{
                    keyBoardController?.hide()
                }
            )
            //AjustesPhoneUser()
            InputField(
                modifier = Modifier
                    .size(width=340.dp,height=70.dp),
                valueState = phone,
                labelId = "Telefono",
                icon = painterResource(id = R.drawable.telefono),
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Text,
                onAction = KeyboardActions{
                    keyBoardController?.hide()
                }
            )

            Spacer(modifier = Modifier.padding(15.dp))


            //GuardarCambios()
            Text(
                text = "Guardar",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ibmplexsans_semibold)),
                color = colorResource(id = R.color.menta_importante),
                modifier = Modifier
                    .clickable {
                        riderItemModel.updateRider(
                            RiderDto(
                                cif.value,
                                password.value,
                                name.value,
                                phone.value,
                                email.value,
                                state.value
                            )
                        )
                    }
                    .align(Alignment.Start)
                    .padding(vertical = 30.dp, horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            //CerrarSesion()
            Text(
                text = "Cerrar Sesion",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.ibmplexsans_semibold)),
                color = colorResource(id = R.color.menta_importante),
                modifier = Modifier
                    .clickable {logout.value = true}
                    .align(Alignment.Start)
                    .padding(vertical = 10.dp, horizontal = 16.dp)
            )


        }

    }
}



