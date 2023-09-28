package com.main.omwayapp.ui.screens.driver.termsAndConditions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.main.omwayapp.R
import com.main.omwayapp.ui.components.CenteredImage
import com.main.omwayapp.ui.components.CustomButton
import com.main.omwayapp.ui.components.CustomDivider


@Composable
//@Preview(showSystemUi=true)
fun TermsAndConditions(navController: NavController){

    Column(modifier =
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


        CustomDivider( modifier = Modifier.height(21.dp),)
        Spacer(modifier = Modifier.padding(5.dp))

        Column(modifier =
        Modifier
            .background(color = colorResource(id = R.color.txt_fields))
            .size(width = 352.dp, height = 629.dp)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TERMINOS Y CONDICIONES",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                color = colorResource(id = R.color.texto_general)
            )
            Spacer(modifier = Modifier.padding(3.dp))

            Text(modifier = Modifier.padding(3.dp),
                text = "\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vitae pharetra magna. Nulla pretium iaculis dui vel gravida. Integer vehicula vestibulum rhoncus. Proin in leo porta, viverra felis sed, pulvinar risus. Sed ultricies felis at ligula vehicula, vel scelerisque sapien placerat. In hac habitasse platea dictumst. Pellentesque scelerisque molestie lorem id convallis. Pellentesque lorem ex, convallis eu neque at, consectetur sodales tellus. Maecenas efficitur quam a purus cursus pulvinar. Aliquam erat volutpat. Cras vitae tortor lacus. Sed a vestibulum massa.\n" +
                        "\n" +
                        "Nullam urna libero, dapibus vel posuere id, convallis vel massa. Maecenas semper lectus et tincidunt sodales. Vestibulum egestas ante nunc, eget efficitur elit egestas nec. Morbi sed libero iaculis, bibendum est et, mollis leo. Sed suscipit dictum venenatis. Nam interdum rutrum auctor. Nullam cursus, odio id dictum placerat, nisi sem cursus mauris, efficitur molestie leo tellus ut sem. Nulla fringilla felis libero, et imperdiet mi faucibus sed. Phasellus tempor nibh eget nunc molestie eleifend. Integer sed arcu placerat, laoreet onec ultrices tempor laoreet. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse potenti. Duis ac mattis diam. " +
                        "Aliquam eget libero sed nulla mollis pellentesque. In erat metus, ultrices sed porta ac, sodales congue ex. Integer vel lorem mauris. Fusce quis scelerisque dui, ut ultricies arcu. Cras rutrum tortor vel erat ullamcorper faucibus. Vivamus sodales turpis ut dapibus blandit. Praesent malesuada urna leo, id viverra tortor tempus acpellentesque. In erat metus, ultrices sed porta ac, sodales congue ex. Integer vel lorem mauris." +
                        " Fusce quis scelerisque dui, ",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                color = colorResource(id = R.color.texto_general),
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.padding(20.dp))

        CustomButton(modifier = Modifier.size(width=222.dp,height=51.dp),text = "Acepto", fontSize = 20.sp, onClick = {})

        Spacer(modifier = Modifier.padding(20.dp))

    }


}


