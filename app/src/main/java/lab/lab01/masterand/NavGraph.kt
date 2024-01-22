package lab.lab01.masterand

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "entry_screen" ){
        composable(route = Screen.Entry.route){
            ProfileScreenInitial(navController = navController)
        }
        composable(Screen.Game.route+"/{number}", arguments = listOf(navArgument("number") { type = NavType.StringType}))
        { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number")!!
            GameScreen(navController = navController, number = number)
        }
        composable(route = Screen.Results.route+"/{score}", arguments = listOf(navArgument("score") { type = NavType.StringType}))
        { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")!!
            Results(navController = navController, score = score)
        }

    }
}