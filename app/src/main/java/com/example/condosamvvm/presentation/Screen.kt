package com.example.condosamvvm.presentation

sealed class Screen(val route : String){
    object Home : Screen(route = "home")
    object Login : Screen(route = "login_screen")
    object Empleado : Screen(route = "empleado_screen")
    object Solicitante : Screen(route = "solicitante_screen")
    object ContratoEmpleado : Screen(route = "contrato_screen_empleado")
    object ContratoSolicitante : Screen(route = "contrato_screen_solicitante")
    object FirmarSolicitante : Screen(route = "firma_solicitante")
    object FirmarPersonal: Screen(route = "firma_personal")
    object FirmaDigitalPersonal: Screen(route = "firma_digital_personal")
    object FirmaDigitalSolicitante: Screen(route = "firma_digital_solicitante")


    fun whitArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }

}
