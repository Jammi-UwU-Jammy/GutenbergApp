package com.vivich.starlitapp

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val LOBBY = "lobby_graph"
    const val STARLIT = "starlit_graph"
    const val GUTENDEX = "gutendex_graph"
}

sealed class AuthScreen(val route: String){
    data object  Login : AuthScreen(route = "LOGIN")
    data object  SignUp : AuthScreen(route = "SIGN_UP")
}
