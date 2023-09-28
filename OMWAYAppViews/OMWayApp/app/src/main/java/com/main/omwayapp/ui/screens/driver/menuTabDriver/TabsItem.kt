package com.main.omwayapp.ui.screens.driver.menuTabDriver

import SolicitudesDriver
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.Home.HomeDriver
import com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.mycars.MisCarros
import com.main.omwayapp.ui.screens.driver.menuTabDriver.TabScreen.mytrips.cardMytrips


typealias ScreenFun = @Composable () ->Unit
sealed class TabsItem(
    var title: String,
    var screen: @Composable (NavController) -> Unit

)
{
    object itemHome: TabsItem("Home",{ navController -> HomeDriver(navController) })
    object itemMisViajes: TabsItem("Mis Viajes",{ cardMytrips()})
    object itemMisCarros: TabsItem("Mis Carros",{navController -> MisCarros(navController)})

    object itemSolicitudes: TabsItem("Solicitudes",{ navController -> SolicitudesDriver(navController) })
}