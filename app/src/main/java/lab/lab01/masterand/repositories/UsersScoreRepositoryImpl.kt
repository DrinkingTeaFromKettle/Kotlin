package lab.lab01.masterand.repositories

import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.dao.UserScoreDao
import lab.lab01.masterand.entities.Score
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.entities.UserWithScore

class UsersScoreRepositoryImpl(private val userScoreDao: UserScoreDao): UsersScoreRepository {
    override fun getScoresWithNames(): Flow<List<UserWithScore>> =
        userScoreDao.loadUsersWithScores()


    override suspend fun insertScore(score: Score): Long =
        userScoreDao.insert(score)

}