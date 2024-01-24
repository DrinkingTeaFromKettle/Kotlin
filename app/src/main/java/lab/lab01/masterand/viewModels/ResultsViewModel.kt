package lab.lab01.masterand.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.repositories.UsersRepository
import lab.lab01.masterand.repositories.UsersScoreRepository

class ResultsViewModel (private val usersScoreRepository: UsersScoreRepository) : ViewModel()
{

    suspend fun getScores() {
        usersScoreRepository.getScoresWithNames()
    }
}