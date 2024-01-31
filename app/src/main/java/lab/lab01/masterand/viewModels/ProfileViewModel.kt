package lab.lab01.masterand.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.repositories.UsersRepository

class ProfileViewModel(private val usersRepository: UsersRepository) : ViewModel()
{
    var userId = mutableStateOf(0L)
    var login = mutableStateOf("")
    var email = mutableStateOf("")
    suspend fun getUser(): Flow<User?> {
        return usersRepository.getUserStream(userId.value.toInt())
    }
}