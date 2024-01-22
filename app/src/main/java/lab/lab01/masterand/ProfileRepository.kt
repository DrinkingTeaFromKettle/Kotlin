package lab.lab01.masterand

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getAllProfileStream(): Flow<List<Profile>>
}