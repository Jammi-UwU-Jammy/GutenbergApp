package com.vivich.starlitapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val LOBBY = "lobby_graph"
    const val BOOK = "book_graph"
    const val STARLIT = "starlit_graph"
    const val GUTENDEX = "gutendex_graph"
}

sealed class AuthScreen(val route: String){
    data object  Login : AuthScreen(route = "LOGIN")
    data object  SignUp : AuthScreen(route = "SIGN_UP")
}

sealed class LobbySubScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Home : LobbySubScreens(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )
    data object Settings : LobbySubScreens(
        route = "Settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    data object Profile : LobbySubScreens(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.AccountCircle
    )
}

sealed class BookScreens(
    val route: String,
    val title: String,
){
    data object Details : BookScreens(
        route = "book_details/{bookId}",
        title = ""
    )
    data object Content : BookScreens(
        route = "book_content/{bookId}",
        title = ""
    )
    fun BookScreens.withId(id: String): String {
        return when (this) {
            is BookScreens.Details -> "book_details/$id"
            is BookScreens.Content -> "book_content/$id"
        }
    }
}

@Composable
fun ScreenContent(
    title: String,
    onClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ){
            Text(text = title)
        }
    }
}