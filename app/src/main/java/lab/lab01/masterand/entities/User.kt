package lab.lab01.masterand.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long? = null ,
    val login: String ,
    val email: String,
    //val description: String
)