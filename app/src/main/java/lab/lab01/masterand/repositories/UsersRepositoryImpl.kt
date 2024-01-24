package lab.lab01.masterand.repositories

import kotlinx.coroutines.flow.Flow
import lab.lab01.masterand.dao.UserDao
import lab.lab01.masterand.entities.User

class UsersRepositoryImpl(private val userDao: UserDao) : UsersRepository {
    override fun getAllUsersStream(): Flow<List<User>> =
        userDao.getAllUsersStream();

    override fun getUserStream(userId: Int): Flow<User?> =
        userDao.getUserStream(userId)
    override suspend fun getUsersByEmail(email: String): List<User> =
        userDao.getUsersByEmail(email)
    override suspend fun insertUser(user: User): Long = userDao.insert(user)

}