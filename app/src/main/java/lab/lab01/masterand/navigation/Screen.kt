package lab.lab01.masterand.navigation

sealed class
Screen(val route:String) {
    object Entry: Screen(route = "entry_screen")
    object Game: Screen(route = "game_screen")
    object Results: Screen(route = "results_screen")

    object Profile: Screen(route = "profile_screen")
}