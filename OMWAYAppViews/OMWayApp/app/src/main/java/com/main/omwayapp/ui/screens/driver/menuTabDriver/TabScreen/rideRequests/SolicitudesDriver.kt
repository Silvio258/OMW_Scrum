import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.apirest.model.trip.RideItem
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.apirest.viewmodel.trip.RideViewModel
import com.main.omwayapp.ui.components.CustomDivider
import com.main.omwayapp.ui.components.ExpandableCardDriver
import com.main.omwayapp.ui.configDS.DataStoreManager
import kotlinx.coroutines.flow.first


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SolicitudesDriver(navController: NavController){

    val context = LocalContext.current
    //ViewModel
    ///Driver Get ViewModel
    val driverModel: DriverViewModel = viewModel()
    val driverState by driverModel._driverState.collectAsState()
    val isDriverLoading = remember { mutableStateOf(true) }

    ///Driver Get ViewModel
    val rideModel: RideViewModel = viewModel()
    val requestedRideState by rideModel._requestedRideState.collectAsState()
    val isRequestedRideLoading = remember { mutableStateOf(true) }

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
        if(!isDriverLoading.value){
            rideModel.getRequestedRides()
        }
    }

    LaunchedEffect(requestedRideState) {
        isRequestedRideLoading.value = requestedRideState._loading
        Log.d("STATE", isRequestedRideLoading.value.toString())

    }
    if(!isRequestedRideLoading.value) {
        val reqRides= remember { mutableStateOf(rideModel.requestedsRideState.value.listRequestedRides) }
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.fondo))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for(reqRide in reqRides.value) {
                CustomDivider(modifier = Modifier.height(21.dp),)
                Spacer(modifier = Modifier.height(15.dp))
                ExpandableCardDriver(
                    nomRider = reqRide.rider.name,
                    tarifa = reqRide.fare.toString(),
                    hora = reqRide.pickUpTime.toString(),
                    puntoA = reqRide.pickUpLocation,
                    puntoB = reqRide.dropOffLocation,
                    distanciaEst = "Distancia Estimada: 2 Km"
                )
                Spacer(modifier = Modifier.height(10.dp))
            }


        }
    }

}