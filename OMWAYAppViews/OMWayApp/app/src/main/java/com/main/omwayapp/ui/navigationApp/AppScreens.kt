package com.main.omwayapp.ui.navigationApp


sealed class AppScreens(val route: String){

    object RegisterDriver: AppScreens("RegisterDriver")
    object TAC: AppScreens("TermsAndConditions")
    object MenuTabDriver: AppScreens("MenuTabDriver")
    object Ajustes: AppScreens("Ajustes")
    object EditarCarro: AppScreens("editarCarros")
    object AgregarCarro: AppScreens("agregarCarros")
    object HomeMenuRider: AppScreens("homeMenuRider")
    object ViajeDriver: AppScreens("viajeDriver")
    object ViajeRider: AppScreens("viajeRider")
    object Login: AppScreens("Login")
    object RegisterRider: AppScreens("RegisterRider")
    object SplashScreen: AppScreens("SplashScreen")
    object MisViajesRider: AppScreens("MisViajesRider")

}
