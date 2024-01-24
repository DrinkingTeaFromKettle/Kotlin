package lab.lab01.masterand.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lab.lab01.masterand.dao.UserDao
import lab.lab01.masterand.dao.UserScoreDao
import lab.lab01.masterand.entities.Score
import lab.lab01.masterand.entities.User

@Database(entities = [User::class, Score::class], version = 1, exportSchema = false)
abstract class ScoreDatabase: RoomDatabase() {
   abstract fun userDao(): UserDao
    abstract fun userScoreDao(): UserScoreDao

   companion object{
       @Volatile
       private var Instance: ScoreDatabase? = null
       fun getDatabase(context: Context): ScoreDatabase {
           return Room.databaseBuilder(
               context,
               ScoreDatabase::class.java,
               "highscore_database"
           )
               .build().also { Instance = it }
       }
   }
}