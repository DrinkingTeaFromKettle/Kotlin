package lab.lab01.masterand

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    fun dao(): ProfileDao{
        val profileDao: ProfileDao;
        return profileDao;
    }
}