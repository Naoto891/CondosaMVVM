package com.example.condosamvvm.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.condosamvvm.data.DatabaseFactory
import com.example.condosamvvm.presentation.home.HomeScreen
import com.example.condosamvvm.presentation.login.LoginScreen
import com.example.condosamvvm.presentation.login.LoginViewModel
import com.example.condosamvvm.presentation.vistacontrato.personal.ContratoPersonalScreen
import com.example.condosamvvm.presentation.vistacontrato.personal.ContratoPersonalViewModel
import com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato.FirmarContratoPersonalScreen
import com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato.firmadigital.FirmaDigitalPersonalScreen
import com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato.firmadigital.FirmaDigitalPersonalViewModel
import com.example.condosamvvm.presentation.vistacontrato.solicitante.ContratoSolicitanteScreen
import com.example.condosamvvm.presentation.vistacontrato.solicitante.ContratoSolicitanteViewModel
import com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.FirmarContratoSolicitanteScreen
import com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.contratosfirmados.ContratosFirmadosSolicitantesScreen
import com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.contratosfirmados.ContratosFirmadosSolicitantesViewModel
import com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.firmadigital.FirmaDigitalSolicitanteScreen
import com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.firmadigital.FirmaDigitalSolicitanteViewModel

import com.example.condosamvvm.presentation.vistaempleado.EmpleadoScreen
import com.example.condosamvvm.presentation.vistaempleado.EmpleadoViewModel
import com.example.condosamvvm.presentation.vistasolicitante.SolicitanteScreen
import com.example.condosamvvm.presentation.vistasolicitante.SolicitanteViewModel
import com.example.condosamvvm.ui.theme.CondosaMVVMTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val empleadoViewModel: EmpleadoViewModel by viewModels()
    private val solicitanteViewModel: SolicitanteViewModel by viewModels()
    private val contratoPersonalViewModel: ContratoPersonalViewModel by viewModels()
    private val contratoSolicitanteViewModel: ContratoSolicitanteViewModel by viewModels()
    private val firmaDigitalSolicitanteViewModel: FirmaDigitalSolicitanteViewModel by viewModels()
    private val firmaDigitalPersonalViewModel: FirmaDigitalPersonalViewModel by viewModels()
    private val contratosFirmadosSolicitantesViewModel: ContratosFirmadosSolicitantesViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseFactory.init()
        WindowCompat.setDecorFitsSystemWindows(window, false)

            setContent{

                CondosaMVVMTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold {
                            val navController = rememberNavController()

                            NavHost(navController = navController, startDestination = Screen.Home.route){
                                composable(route = Screen.Home.route){
                                    HomeScreen(navController)
                                }
                                composable(route = Screen.Login.route ){
                                    LoginScreen(loginViewModel, navController)
                                }
                                composable(
                                    route = Screen.Empleado.route + "/{idPersona}",
                                    arguments = listOf(
                                        navArgument("idPersona"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    EmpleadoScreen(entry.arguments?.getInt("idPersona")!!,empleadoViewModel, navController)
                                }
                                composable(
                                    route = Screen.ContratoSolicitante.route + "/{idPersona}",
                                    arguments = listOf(
                                        navArgument("idPersona"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                        ContratoSolicitanteScreen(entry.arguments?.getInt("idPersona")!!,contratoSolicitanteViewModel, navController)
                                }
                                composable(
                                    route = Screen.ContratoEmpleado.route + "/{idPersona}",
                                    arguments = listOf(
                                        navArgument("idPersona"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    ContratoPersonalScreen(entry.arguments?.getInt("idPersona")!!,contratoPersonalViewModel, navController)
                                }

                                composable(
                                    route = Screen.FirmarSolicitante.route + "/{idContrato}",
                                    arguments = listOf(
                                    navArgument("idContrato"){
                                        type = NavType.IntType
                                        nullable = false
                                        }
                                    )
                                ){entry ->
                                    FirmarContratoSolicitanteScreen(idContrato = entry.arguments?.getInt("idContrato")!!, navController)
                                }

                                composable(
                                    route = Screen.FirmarPersonal.route + "/{idContrato}",
                                    arguments = listOf(
                                        navArgument("idContrato") {
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    FirmarContratoPersonalScreen(
                                        idContrato = entry.arguments?.getInt("idContrato")!!,
                                        navController = navController
                                    )
                                }

                               composable(
                                    route = Screen.FirmaDigitalPersonal.route + "/{idContrato}",
                                    arguments = listOf(
                                        navArgument("idContrato"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    FirmaDigitalPersonalScreen(idContrato = entry.arguments?.getInt("idContrato")!!, firmaDigitalPersonalViewModel, navController)
                                }


                                composable(
                                    route = Screen.FirmaDigitalSolicitante.route + "/{idContrato}",
                                    arguments = listOf(
                                        navArgument("idContrato"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    FirmaDigitalSolicitanteScreen(idContrato = entry.arguments?.getInt("idContrato")!!, firmaDigitalSolicitanteViewModel, navController)
                                }


                                composable(
                                    route = Screen.Solicitante.route + "/{idPersona}",
                                    arguments = listOf(
                                        navArgument("idPersona"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    SolicitanteScreen(
                                        idPersona = entry.arguments?.getInt("idPersona")!!,
                                        solicitanteViewModel = solicitanteViewModel,
                                        navController = navController
                                    )
                                }

                                composable(
                                    route = Screen.ImagenFirmaSolicitante.route + "/{idContrato}/{idSolicitante}",
                                    arguments = listOf(
                                        navArgument("idContrato"){
                                            type = NavType.IntType
                                            nullable = false
                                        },
                                        navArgument("idSolicitante"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    ContratosFirmadosSolicitantesScreen(
                                        idContrato= entry.arguments?.getInt("idContrato")!!,
                                        idSolicitante = entry.arguments?.getInt("idSolicitante")!!,
                                        contratosFirmadosSolicitantesViewModel = contratosFirmadosSolicitantesViewModel

                                    )
                                }
                            }
                        }

                    }
                }
            }

    }
}

