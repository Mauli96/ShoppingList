package com.example.shoppinglist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: ShoppingListViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            ShoppingList(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {entry ->
            val id = if(entry.arguments != null) {
                entry.arguments!!.getLong("id")
            }
            else {
                0L
            }
            EditorScreen(id = id, viewModel = viewModel, navController = navController)
        }
    }
}