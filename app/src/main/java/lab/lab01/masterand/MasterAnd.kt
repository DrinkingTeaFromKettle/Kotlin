package lab.lab01.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import lab.lab01.masterand.navigation.SetupNavGraph
import lab.lab01.masterand.ui.theme.MasterAndTheme

class MasterAnd : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAndTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}
