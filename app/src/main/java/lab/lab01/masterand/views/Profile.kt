package lab.lab01.masterand.views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Entity
import androidx.room.PrimaryKey
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import lab.lab01.masterand.R
import lab.lab01.masterand.navigation.Screen
import lab.lab01.masterand.viewModels.AppViewModelProvider
import lab.lab01.masterand.viewModels.GameViewModel
import lab.lab01.masterand.viewModels.ProfileViewModel

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val email: String
)

@Composable
//fun Profile(navController: NavController,colors: Int, id: Long,  viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)){
fun Profile(navController: NavController,colors: Int, name: String, email: String,  viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)){
    var uri = "empty"
    viewModel.login.value = name
    viewModel.email.value = email
//    val coroutineScope = rememberCoroutineScope()
//    viewModel.userId.value = id
//    LaunchedEffect(key1 = viewModel.userId.value) {
//        coroutineScope.launch {
//            viewModel.getUser().collect { user ->
//                viewModel.login.value = user?.login ?: ""
//                viewModel.email.value = user?.email ?: ""
//
//            }
//        }
//    }


    val profile = Profile(login = viewModel.login.value, email = viewModel.email.value)
    Column {
        Row {
            if (uri == "empty") {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_question_mark_24
                    ),
                    contentDescription = "Profile photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = uri,
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,

                    )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = profile.login,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Text(
                    text = profile.email
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(route = Screen.Game.route+"/"+colors)
            },
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                "Play"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(route = Screen.Entry.route)
        },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text("Logout")
        }
    }



}




//@Preview
//@Composable
//fun PreviewProfileCard(){
//    val profile: Profile = Profile("Login",  "Fusce id malesuada ante, sit amet dapibus dolor. Aenean a felis ex. Praesent sagittis interdum enim.")
//    ProfileCard(profile);
//}