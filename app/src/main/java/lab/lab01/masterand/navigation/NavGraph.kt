package lab.lab01.masterand.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import lab.lab01.masterand.views.GameScreen
import lab.lab01.masterand.views.Profile
import lab.lab01.masterand.views.ProfileScreenInitial
import lab.lab01.masterand.views.Results
import java.net.URI

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
        composable(route = Screen.Results.route+"/{score}/{number}", arguments = listOf(navArgument("score") { type = NavType.StringType}, navArgument("number") { type = NavType.StringType}))
        { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")!!
            val number = backStackEntry.arguments?.getString("number")!!
            Results(navController = navController, score = score, number = number)
        }
//        composable(route = Screen.Profile.route+"/{colors}/{id}", arguments = listOf( navArgument("colors") { type = NavType.IntType},navArgument("id") { type = NavType.LongType}))
//        { backStackEntry ->
//            val id = backStackEntry.arguments?.getLong("id")!!
//            val colors = backStackEntry.arguments?.getInt("colors")!!
//            Profile(navController = navController, colors = colors, id = id)
//        }
        composable(route = Screen.Profile.route+"/{colors}/{name}/{email}", arguments = listOf( navArgument("colors") { type = NavType.IntType},navArgument("name") { type = NavType.StringType}, navArgument("email") { type = NavType.StringType}))
        { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")!!
            val colors = backStackEntry.arguments?.getInt("colors")!!
            val email = backStackEntry.arguments?.getString("email")!!
            Profile(navController = navController, colors = colors, name = name, email = email)
        }
    }
}