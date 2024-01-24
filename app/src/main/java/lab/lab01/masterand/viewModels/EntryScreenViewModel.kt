package lab.lab01.masterand.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.repositories.UsersRepository

class EntryScreenViewModel (private val usersRepository: UsersRepository) : ViewModel()
{
    var userId = mutableStateOf(0L)
    val login = mutableStateOf("")
    val email = mutableStateOf("")
    val colors = mutableStateOf("")
    suspend fun saveUser() {
        usersRepository.insertUser( User(userId.value, login.value, email.value))
    }
}