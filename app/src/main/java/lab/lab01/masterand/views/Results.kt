package lab.lab01.masterand.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import lab.lab01.masterand.navigation.Screen
import lab.lab01.masterand.viewModels.AppViewModelProvider
import lab.lab01.masterand.viewModels.ResultsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Results(navController: NavController, score: String, number:String, viewModel: ResultsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Results",
            style = MaterialTheme.typography.displayLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Recent score: " + score,
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(route = Screen.Game.route + "/" + number)
            }

            ) {
            Text(
                "Restart Game"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(route = Screen.Entry.route)
            }

        ) {
            Text(
                "Logout"
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewResults(){
//    Results("3");
//}