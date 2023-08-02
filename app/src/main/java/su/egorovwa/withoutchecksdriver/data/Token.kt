package su.egorovwa.withoutchecksdriver.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class Token(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val token: String,
    val isusetAt: Long,
    val expiredAt: Long
)