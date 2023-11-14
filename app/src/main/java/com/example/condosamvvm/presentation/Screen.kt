package com.example.condosamvvm.presentation

sealed class Screen(val route : String){
    object Home : Screen(route = "home")
    object Login : Screen(route = "login_screen")
    object Empleado : Screen(route = "empleado_screen")
    object Contrato : Screen(route = "contrato_screen")
    object Firmar : Screen(route = "firmar")


    fun whitArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }

}
