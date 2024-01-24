package lab.lab01.masterand.viewModels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import lab.lab01.masterand.entities.Score
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.repositories.UsersRepository
import lab.lab01.masterand.repositories.UsersScoreRepository

class GameViewModel (private val usersScoreRepository: UsersScoreRepository) : ViewModel()
{
    val score =  mutableIntStateOf(1)
    val gameEnd = mutableStateOf(false)
    var  colList= mutableListOf(Color.Cyan, Color.Red, Color.Blue, Color.Yellow, Color.Green,Color.Magenta, Color.Black, Color.Gray, Color(0xffffa500), Color(0xffbd3aff));
    var availableColors =  mutableListOf<Color>();
    var rowData = mutableStateOf(listOf(List(4) { Color.White }))
    var rightColors = mutableListOf<Color>()
    suspend fun setScores(score: Score) {
        usersScoreRepository.insertScore(score = score)
    }
}