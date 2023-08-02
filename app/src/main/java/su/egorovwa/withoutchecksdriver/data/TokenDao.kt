package su.egorovwa.withoutchecksdriver.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(token: Token)

    @Query("SELECT * FROM token WHERE :currentTimeMilis < expiredAt AND :currentTimeMilis > isusetAt ORDER BY expiredAt DESC ")
    suspend fun getCurrentToken(currentTimeMilis:Long):List<Token>

}