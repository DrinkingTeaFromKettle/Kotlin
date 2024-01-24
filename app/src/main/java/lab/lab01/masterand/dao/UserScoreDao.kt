package lab.lab01.masterand.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.entities.Score
import lab.lab01.masterand.entities.User
import lab.lab01.masterand.entities.UserWithScore

@Dao
interface UserScoreDao {
    @Query(
        "SELECT users.userId AS userId, scores.scoreId AS scoreId " +
                "FROM users, scores WHERE users.userId = scores.userId"
    )
    fun loadUsersWithScores(): Flow<List<UserWithScore>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(score: Score): Long

}