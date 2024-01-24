package lab.lab01.masterand.containers

import android.content.Context
import lab.lab01.masterand.database.ScoreDatabase
import lab.lab01.masterand.repositories.UsersRepository
import lab.lab01.masterand.repositories.UsersRepositoryImpl
import lab.lab01.masterand.repositories.UsersScoreRepository
import lab.lab01.masterand.repositories.UsersScoreRepositoryImpl

class AppDataContainer(private val context: Context) : AppContainer {
    override val usersRepository: UsersRepository by lazy {
        UsersRepositoryImpl(ScoreDatabase.getDatabase(context).userDao())
    }
    override val userScoreRepository: UsersScoreRepository by lazy {
        UsersScoreRepositoryImpl(ScoreDatabase.getDatabase(context).userScoreDao())
    }
}