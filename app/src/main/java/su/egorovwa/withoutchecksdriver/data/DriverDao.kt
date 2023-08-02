package su.egorovwa.withoutchecksdriver.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun save(driverRegistration: DriverRegistration)
@Query("SELECT * FROM DriverRegistration")
suspend fun findRegistred():List<DriverRegistration>
@Delete
suspend fun delete(registration: DriverRegistration)
}