package lab.lab01.masterand.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import lab.lab01.masterand.R
import lab.lab01.masterand.navigation.Screen
import lab.lab01.masterand.repositories.UsersRepository
import lab.lab01.masterand.viewModels.AppViewModelProvider
import lab.lab01.masterand.viewModels.EntryScreenViewModel
import lab.lab01.masterand.viewModels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenInitial(navController: NavController, viewModel: EntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val coroutineScope = rememberCoroutineScope()
//    val name: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val email = rememberSaveable { mutableStateOf("") }
//    val colors = rememberSaveable { mutableStateOf("") }
    var id = 0L;
    val numerIsValid  = rememberSaveable { mutableStateOf(false) }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val emailIsValid = rememberSaveable { mutableStateOf(false) }
    val nameIsValid = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Box  {
            val imagePicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { selectedUri ->
                    if (selectedUri != null) {
                        profileImageUri.value = selectedUri
                    }
                })
            //Ikona startująca imagePicker
//            IconButton(
//                onClick = { imagePicker.launch(
//                    PickVisualMediaRequest(
//                        ActivityResultContracts.PickVisualMedia.ImageOnly)
//                )
//                },
//                modifier = Modifier.size(64.dp).align(Alignment.TopEnd)
//            ) {
//                Icon(Icons.Filled.Edit, "Edit", tint = MaterialTheme.colorScheme.secondary)
//
//            }
            // Wyświetlanie obrazu z zasobów
//

            ProfileImageWithPicker(profileImageUri = profileImageUri.value, selectImageOnClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            })

        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextFieldWithError(value = viewModel.login, errorText = "Name can't be empty", isValid = nameIsValid, name = "name",
            valiadationFunc = { text:String -> text.matches(Regex("[a-zA-Z0-9._%+-]+")) });
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = name.value,
//            onValueChange = { input ->
//                name.value = input
//                nameIsValid.value = name.value.isNotEmpty()},
//            label = { Text("Enter name") },
//            singleLine = true,
//            isError = !nameIsValid.value,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            supportingText = {
//                if(!name.value.isNotEmpty()){
//                    Text("Name can't be empty")
//                }
//            }
//        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextFieldWithError(value = viewModel.email, errorText = "That's not a valid email", isValid = emailIsValid, name = "email",
            valiadationFunc = { text:String -> text.matches(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) });
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = email.value,
//            onValueChange = {  input ->
//                email.value = input
//                emailIsValid.value = (email.value.isNotEmpty() && isValidEmail(email.value))},
//            label = { Text("Enter email") },
//            singleLine = true,
//            isError = !emailIsValid.value,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            supportingText = {
//                if(!email.value.isNotEmpty() && !isValidEmail(email.value)){
//                    Text("Email can't be empty")
//                }
//                if(email.value.isNotEmpty() && !isValidEmail(email.value)){
//                    Text("That's not a valid email")
//                }
//
//            }
//        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextFieldWithError(value = viewModel.colors, errorText = "Number has to be between 5-10", isValid = numerIsValid, name = "number of colors",
            valiadationFunc = { text: String -> text.matches(Regex("[5-9]|10"))});
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = colors.value,
//            onValueChange = { input ->
//                colors.value = input
//                numerIsValid.value = (colors.value.isNotEmpty() && isValidNumber(colors.value))},
//            label = { Text("Enter number of colors") },
//            singleLine = true,
//            isError = !numerIsValid.value,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            supportingText = {
//                if(!(colors.value.isNotEmpty() && isValidNumber(colors.value))){
//                    Text("Value has to be between 5-10")
//                }
//            }
//        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                   id = viewModel.saveUser()
                }
                    //navController.navigate(route = Screen.Profile.route+"/"+viewModel.colors.value )
                navController.navigate(route = Screen.Profile.route+"/"+viewModel.colors.value+"/"+viewModel.login.value+"/"+viewModel.email.value )

                      },
                enabled = (nameIsValid.value && emailIsValid.value && numerIsValid.value) ,
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                "Profile"
            )
        }
    }
}

@Composable
private fun ProfileImageWithPicker(profileImageUri: Uri?,
                                   selectImageOnClick: () -> Unit) {

    //FIXME:
    // To się da zrobić lepiej

    Box {
        if( profileImageUri == null){
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_question_mark_24),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
           )
        } else {
            AsyncImage(
                model = profileImageUri,
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop,

                )
        }
        IconButton(
            onClick = {
                selectImageOnClick()
            },
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(Icons.Filled.Edit, "Edit", tint = MaterialTheme.colorScheme.secondary)

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithError(errorText: String, value: MutableState<String>, isValid: MutableState<Boolean>, name:String, valiadationFunc: (text: String) -> Boolean){
    Row {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.value,
            onValueChange = { input ->
                value.value = input
                isValid.value = (value.value.isNotEmpty() && valiadationFunc(value.value))},
            label = { Text("Enter "+ name) },
            singleLine = true,
            isError = !isValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                if(!value.value.isNotEmpty()){
                    Text(errorText)
                }
            }
        )
    }

}



//@Preview
//@Composable
//fun ProfileScreenInitialPreview() {
//    MasterAndTheme {
//        ProfileScreenInitial()
//    }
//}
