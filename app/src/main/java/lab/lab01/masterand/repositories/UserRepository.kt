package lab.lab01.masterand.repositories

import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.entities.User

interface UsersRepository {
    fun getAllUsersStream(): Flow<List<User>>
    fun getUserStream(id: Int): Flow<User?>
    suspend fun getUsersByEmail(email: String): List<User>
    suspend fun insertUser(player: User) : Long
}