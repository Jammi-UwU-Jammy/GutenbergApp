package com.vivich.starlitapp

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.vivich.starlitapp.BookScreens.Details.withId
import com.vivich.starlitapp.ui.auth.LoginScreen
import com.vivich.starlitapp.ui.lobby.gutendex.BookContentScreen
import com.vivich.starlitapp.ui.lobby.gutendex.GBookScreen
import com.vivich.starlitapp.ui.lobby.gutendex.GutendexMainBody
import com.vivich.starlitapp.ui.lobby.gutendex.GutendexScreen
import com.vivich.starlitapp.viewModels.GBookViewModel


@Composable
fun RootGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ){
        authGraph(navController=navController)
        composable(route = Graph.LOBBY){
            val lobbyController = rememberNavController()
            GutendexScreen(navHostController = lobbyController)
        }
    }
}


private fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(
                // TODO: handle auth login
                onFacebookLogInClick = {
                    Log.d("ddd", "logged in!")
                    navController.navigate(Graph.LOBBY)
                },
                onGuestLogInClick = {
                    navController.navigate(Graph.LOBBY)
                },
                onGoogleLogInClick = {
                    navController.navigate(Graph.LOBBY)
                }
            )
        }
    }
}

@Composable
fun LobbyNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
){
    val bookViewModel = viewModel<GBookViewModel>()

    NavHost(
        navController = navController,
        startDestination = LobbySubScreens.Home.route
    ){
        composable(route = LobbySubScreens.Home.route){
            GutendexMainBody(
                paddingValues = paddingValues,
                bookViewModel=bookViewModel,
                navController = navController,
            )
        }
        composable(route = LobbySubScreens.Settings.route){
            ScreenContent(title = "Settings") {
                // /TODO: To implemented
            }
        }
        composable(route = LobbySubScreens.Profile.route){
            ScreenContent(title = "Profile") {
                // /TODO: To implemented
            }
        }


        composable(route=BookScreens.Details.route){ backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull() ?: 0
            GBookScreen(
                gBook = bookViewModel.state.gBooks[bookId],
                navHostController=navController
            )
        }
        composable(route=BookScreens.Content.route){ backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull() ?: 0
            BookContentScreen()
        }

    }
}
