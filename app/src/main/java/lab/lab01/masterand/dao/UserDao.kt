package lab.lab01.masterand.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User) : Long
    @Query("SELECT * from users WHERE userId = :userId")
    fun getUserStream(userId: Int): Flow<User>
    @Query("SELECT * from users WHERE email = :email")
    suspend fun getUsersByEmail(email: String): List<User>

    @Query("SELECT * from users")
     fun getAllUsersStream(): Flow<List<User>>
}