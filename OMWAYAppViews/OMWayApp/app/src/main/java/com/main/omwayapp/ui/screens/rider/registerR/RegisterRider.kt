package com.main.omwayapp.ui.screens.rider.register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Surface
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.RiderDto
import com.main.omwayapp.apirest.viewmodel.omwayuser.rider.RiderItemViewModel
import com.main.omwayapp.ui.components.CustomButtonG
import com.main.omwayapp.ui.components.InputField

import com.main.omwayapp.ui.navigationApp.AppScreens
import com.main.omwayapp.ui.theme.Fondo
import com.main.omwayapp.ui.theme.IBMplexSans
import com.main.omwayapp.ui.theme.MentaImportante40
import com.main.omwayapp.ui.theme.TextOpacidad
import com.main.omwayapp.ui.theme.TextoGeneral


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterRider(navController: NavController ) {

    var cif = remember {
        mutableStateOf("")
    }
    var name = remember {
        mutableStateOf("")
    }
    var email = remember {
        mutableStateOf("")
    }
    var phone = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    var confirmpass = remember {
        mutableStateOf("")
    }


    val riderItemModel: RiderItemViewModel = viewModel()
    val riderItemState by riderItemModel._riderState.collectAsState()

    var keyBoardController = LocalSoftwareKeyboardController.current


    if(riderItemState){
        LaunchedEffect(Unit){
            navController.navigate(route = AppScreens.Login.route)
        }

    }

    Surface(modifier = Modifier.fillMaxSize(), color = Fondo) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 10.dp, horizontal = 30.dp),
                text = "Crear Cuenta",
                color = TextoGeneral,
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = IBMplexSans


            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 30.dp),
                text = "Por favor llenar todos los campos.",
                color = TextOpacidad,
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = IBMplexSans


            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    valueState = cif,
                    labelId = "CIF",
                    icon = painterResource(id = R.drawable.id),
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {
                        keyBoardController?.hide()
                    }
                )
                Spacer(modifier = Modifier.height(7.dp))

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
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
                Spacer(modifier = Modifier.height(7.dp))

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    valueState = email,
                    labelId = "Email",
                    icon = painterResource(id = R.drawable.email),
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {

                    }
                )
                Spacer(modifier = Modifier.height(7.dp))

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    valueState = phone,
                    labelId = "Telefono",
                    icon = painterResource(id = R.drawable.telefono),
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {
                        keyBoardController?.hide()
                    }
                )

                Spacer(modifier = Modifier.height(7.dp))

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    valueState = password,
                    labelId = "Contraseña",
                    icon = painterResource(id = R.drawable.password),
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {
                        keyBoardController?.hide()
                    }
                )
                Spacer(modifier = Modifier.height(7.dp))

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 4.dp)
                        .align(Alignment.CenterHorizontally),
                    valueState = confirmpass,
                    labelId = "Confirmar Contraseña",
                    icon = painterResource(id = R.drawable.password),
                    enabled = true,
                    isSingleLine = true,
                    keyboardType = KeyboardType.Text,
                    onAction = KeyboardActions {
                        keyBoardController?.hide()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

            }

            CustomButtonG(
                modifier = Modifier
                    .width(222.dp)
                    .height(51.dp),
                text = "Regístrate",
                fontSize = 20.sp
            ) {
                if(password.value == confirmpass.value){
                    riderItemModel.saveRider(RiderDto(cif.value,password.value,name.value, phone.value,email.value,state = true))
                }
                else{
                    Log.d("CUSTOMERROR","Mala contraseña")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
            ) {

                Text(
                    modifier = Modifier
                        .padding(horizontal = 3.dp),
                    text = "Tienes una cuenta?",
                    color = TextoGeneral,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = IBMplexSans


                )

                Text(
                    modifier = Modifier
                        .clickable(onClick = { navController.navigate(AppScreens.Login.route) })
                        .padding(horizontal = 9.dp),
                    text = "Entra Aquí",
                    color = MentaImportante40,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = IBMplexSans,


                    )

            }

        }
    }
}