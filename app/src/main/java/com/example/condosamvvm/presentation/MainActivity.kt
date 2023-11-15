package com.example.condosamvvm.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.example.condosamvvm.presentation.vistacontrato.ContratoViewModel
import com.example.condosamvvm.presentation.vistacontrato.ContratosScreen
import com.example.condosamvvm.presentation.vistacontrato.firmarContrato.FirmarContratoScreen
import com.example.condosamvvm.presentation.vistacontrato.firmarContrato.firmaDigital.SignaturePad
import com.example.condosamvvm.presentation.vistaempleado.EmpleadoScreen
import com.example.condosamvvm.presentation.vistaempleado.EmpleadoViewModel
import com.example.condosamvvm.ui.theme.CondosaMVVMTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val empleadoViewModel: EmpleadoViewModel by viewModels()
    private val contratoViewModel: ContratoViewModel by viewModels()

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
                                composable(route = Screen.Login.route){
                                    LoginScreen(loginViewModel, navController)
                                }
                                composable(route = Screen.Empleado.route){
                                    EmpleadoScreen(empleadoViewModel, navController)
                                }
                                composable(route = Screen.Contrato.route){
                                        ContratosScreen(contratoViewModel, navController)
                                }
                                composable(
                                    route = Screen.Firmar.route + "/{idContrato}",
                                    arguments = listOf(
                                    navArgument("idContrato"){
                                        type = NavType.IntType
                                        nullable = false
                                        }
                                    )
                                ){entry ->
                                    FirmarContratoScreen(idContrato = entry.arguments?.getInt("idContrato")!!, navController)
                                }
                                composable(
                                    route = Screen.FirmaDigital.route + "/{idContrato}",
                                    arguments = listOf(
                                        navArgument("idContrato"){
                                            type = NavType.IntType
                                            nullable = false
                                        }
                                    )
                                ){entry ->
                                    SignaturePad(idContrato = entry.arguments?.getInt("idContrato")!!)
                                }
                            }
                        }

                    }
                }
            }

    }
}

