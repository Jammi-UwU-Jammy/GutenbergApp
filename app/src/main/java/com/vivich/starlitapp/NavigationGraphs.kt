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
import com.vivich.starlitapp.ui.auth.LoginScreen
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


fun NavGraphBuilder.authGraph(
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
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route
    ){

        composable(route = BottomBarScreens.Home.route){
            val bookViewModel = viewModel<GBookViewModel>()
            GutendexMainBody(paddingValues = paddingValues, bookViewModel=bookViewModel)
        }
        composable(route = BottomBarScreens.Settings.route){
            ScreenContent(title = "Settings") {
                // /TODO: To implemented
            }
        }
        composable(route = BottomBarScreens.Profile.route){
            ScreenContent(title = "Profile") {
                // /TODO: To implemented
            }
        }
    }
}

