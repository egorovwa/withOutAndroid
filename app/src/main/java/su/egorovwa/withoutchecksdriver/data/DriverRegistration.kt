package su.egorovwa.withoutchecksdriver.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DriverRegistration(
    @PrimaryKey(autoGenerate = false)
    val phone: String,
    val password: String,
    val id:Long
)
