package lab.lab01.masterand.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val scoreId: Long = 0,
    val userId: Long,
    val score: Int = 0,
)