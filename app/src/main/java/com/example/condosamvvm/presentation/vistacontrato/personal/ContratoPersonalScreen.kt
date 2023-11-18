package com.example.condosamvvm.presentation.vistacontrato.personal

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.presentation.Screen
import com.example.condosamvvm.presentation.login.HeaderImage
import kotlinx.coroutines.launch

@Composable
fun ContratoPersonalScreen(idPersona: Int, contratoPersonalViewModel: ContratoPersonalViewModel, navController: NavHostController) {

    Box (
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1c4c96)),
        contentAlignment = Alignment.TopCenter
    ) {
        ContratoDataListPersonal(Modifier, contratoPersonalViewModel, navController,idPersona)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContratoDataListPersonal(modifier: Modifier, contratoPersonalViewModel: ContratoPersonalViewModel, navController: NavHostController, idPersona: Int) {

    val isSelectedIndex: Int by contratoPersonalViewModel.isSelectedIndex.observeAsState(initial = 0)
    val list = listOf("Crear Contratos","Contratos Pendientes", "Contratos Completados")
    val pagerState = rememberPagerState(0)

    Column(modifier){
        HeaderImage(
            Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(height = 200.dp, width = 200.dp)
                .padding(top = 50.dp)
        )

        Text(
            text = "CONDOSA",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 50.dp),
            fontSize = 32.sp,
            color = Color.White
        )

        LaunchedEffect(isSelectedIndex){
            pagerState.animateScrollToPage(isSelectedIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
            if(!pagerState.isScrollInProgress){
                contratoPersonalViewModel.selectedIndexChanged(pagerState.currentPage)
            }
        }
        Column (
            modifier = Modifier.fillMaxSize()
        ){
            TabRow(
                selectedTabIndex = isSelectedIndex,
                containerColor = Color.Transparent,
            ) {
                list.forEachIndexed { index, text ->
                    val selected = isSelectedIndex == index
                    Tab(
                        selected = selected,
                        onClick = { contratoPersonalViewModel.selectedIndexChanged(index) },
                        modifier = if (selected) Modifier
                            .clip(RoundedCornerShape(15))
                            .background(Color(0xff062863))
                        else Modifier
                            .clip(RoundedCornerShape(15))
                            .background(Color(0xff607EC9)),
                        text = { Text(text = text, color = Color.White)
                        }
                    )
                }

            }

            HorizontalPager(
                state = pagerState,
                pageCount = list.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){ //Text(text = list[index])
                    ContratoListPersonal(contratoPersonalViewModel, index, navController, idPersona)
                    print(message = index)
                }

            }
        }



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContratoListPersonal(contratoPersonalViewModel: ContratoPersonalViewModel, index: Int, navController: NavHostController, idPersona: Int) {

    val contratoList: List<Contrato> by contratoPersonalViewModel.contratos.observeAsState(emptyList())
    contratoPersonalViewModel.getContratosPersonal(idPersona)

    Scaffold(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 50.dp),
        containerColor  = Color(0xff9AB4FF)
    ) {
        LazyColumn (
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            items(
                items =

                when (index) {
                    0 -> {
                        contratoList.filter {
                            it.fechaRegistro == null
                        }
                    }
                    1 -> {
                        contratoList.filter{
                            it.fechaFirmaPersonal == null
                        }
                    }
                    else -> {
                        contratoList.filter {
                            it.fechaFirmaPersonal != null && it.fechaFirmaSolicitante != null
                        }
                    }
                },

                itemContent = {
                    ContratosCardPersonal(contrato = it,index, navController)
                }
            )


        }
    }
}

@Composable
fun ContratosCardPersonal(contrato: Contrato,index: Int,  navController: NavHostController) {


    val coroutineScope = rememberCoroutineScope()


    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xff062863))
    ){

        Row {
            Column(
                modifier = Modifier
                    .weight(.70f)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ){

                TextVisibleLabel(text = "ID Contrato: ", isVisible =  index != 0)
                TextVisible(text = contrato.idContrato.toString(), isVisible =  index != 0)
                TextVisibleLabel(text = "ID Cotizacion: ", isVisible =  index != 2)
                TextVisible(text = contrato.idSolicitudCotizacion.toString(),isVisible = index != 2)
                TextVisibleLabel(text = "Fecha de contrato: ", isVisible =  index == 1)
                TextVisible(text = contrato.fechaContrato.toString(), isVisible = index == 1 )
                TextVisibleLabel(text = "ID Personal: ", isVisible =  index == 2)
                TextVisible(text = contrato.idPersonal.toString(), isVisible = index == 2)
                TextVisibleLabel(text = "ID Solicitante: ", isVisible =  index == 2)
                TextVisible(text = contrato.idSolicitante.toString(), isVisible = index == 2)
                TextVisibleLabel(text = "Fecha de registro: ", isVisible =  index == 2)
                TextVisible(text = contrato.fechaRegistro.toString(), isVisible = index == 2)
            }
            when(index){
                0->
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(.30f)
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)
                    ) {
                        Text(text = "Crear", color = Color.White)
                    }
                1->
                    Button(
                        onClick = {
                            coroutineScope.launch { navController.navigate(Screen.FirmarPersonal.whitArgs(contrato.idContrato)) }
                        },
                        modifier = Modifier
                            .weight(.30f)
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)
                    ) {
                        Text(text = "Firmar", color = Color.White)
                    }
                2->
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(.30f)
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)
                    ) {
                        Text(text = "Ver", color = Color.White)
                    }
            }


        }

    }


}


@Composable
fun TextVisible(text: String, isVisible : Boolean){
    if(isVisible){
        Text(text = text, color = Color.White)
    }
}

@Composable
fun TextVisibleLabel(text: String, isVisible : Boolean){
    if(isVisible){
        Text(text = text, color = Color.White, style = typography.titleSmall)
    }
}