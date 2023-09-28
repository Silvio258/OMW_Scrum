package com.main.omwayapp.ui.screens.driver.menuTabDriver


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.main.omwayapp.R
import com.main.omwayapp.apirest.dto.omwayuser.DriverDto
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverItemViewModel
import com.main.omwayapp.apirest.viewmodel.omwayuser.driver.DriverViewModel
import com.main.omwayapp.ui.configDS.DataStoreManager
import com.main.omwayapp.ui.navigationApp.AppScreens
import com.main.omwayapp.ui.theme.MentaImportante40
import com.main.omwayapp.ui.theme.TxtFields
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable()
//@Preview(showSystemUi=true)
fun MenuTabDriver(navController: NavController){

    val tabs = listOf(
        TabsItem.itemHome,
        TabsItem.itemSolicitudes,
        TabsItem.itemMisViajes,
        TabsItem.itemMisCarros
    )
    val pagerState = rememberPagerState()

    Column(
        modifier =
        Modifier
            .background(color = colorResource(id = R.color.fondo))
    ) {
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.fondo)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Activo",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                color = colorResource(id = R.color.texto_general),
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 16.dp)
            )

            val checkedState = remember {
                mutableStateOf(true)
            }
            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = TxtFields,
                    checkedTrackColor = MentaImportante40
                )
            )

            Tabs(tabs, pagerState)
            TabsContent(tabs, pagerState, navController)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs:List<TabsItem>, pagerState: PagerState){

    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage,
        indicator={
            tabPositions ->  TabRowDefaults.Indicator(modifier = Modifier.pagerTabIndicatorOffset(pagerState,tabPositions)) },
        containerColor = colorResource(id = R.color.fondo),
        contentColor = colorResource(id = R.color.texto_general)
         )
    {
        tabs.forEachIndexed { index, tabsItem ->
            LeadingIconTab(
            selected = pagerState.currentPage == index,
            onClick = { scope.launch { pagerState.animateScrollToPage(index)}
                      },
                icon = {},
            text = { Text(tabsItem.title)},
            )

        }
    }

}
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabsContent(tabs:List<TabsItem>,pagerState:PagerState,navController: NavController){

    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen(navController)
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun Modifier.pagerTabIndicatorOffset(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
    pageIndexMapping: (Int) -> Int = { it },
): Modifier = layout { measurable, constraints ->
    if (tabPositions.isEmpty()) {
        // If there are no pages, nothing to show
        layout(constraints.maxWidth, 0) {}
    } else {
        val currentPage = minOf(tabPositions.lastIndex, pageIndexMapping(pagerState.currentPage))
        val currentTab = tabPositions[currentPage]
        val previousTab = tabPositions.getOrNull(currentPage - 1)
        val nextTab = tabPositions.getOrNull(currentPage + 1)
        val fraction = pagerState.currentPageOffset
        val indicatorWidth = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.width, nextTab.width, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.width, previousTab.width, -fraction).roundToPx()
        } else {
            currentTab.width.roundToPx()
        }
        val indicatorOffset = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.left, nextTab.left, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.left, previousTab.left, -fraction).roundToPx()
        } else {
            currentTab.left.roundToPx()
        }
        val placeable = measurable.measure(
            Constraints(
                minWidth = indicatorWidth,
                maxWidth = indicatorWidth,
                minHeight = 0,
                maxHeight = constraints.maxHeight
            )
        )
        layout(constraints.maxWidth, maxOf(placeable.height, constraints.minHeight)) {
            placeable.placeRelative(
                indicatorOffset,
                maxOf(constraints.minHeight - placeable.height, 0)
            )
        }
    }

}


