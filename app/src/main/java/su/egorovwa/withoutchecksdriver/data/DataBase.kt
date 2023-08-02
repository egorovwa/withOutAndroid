package su.egorovwa.withoutchecksdriver.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DriverRegistration::class, Token::class), version = 1, exportSchema = false)
abstract class DataBase :RoomDatabase(){
    abstract fun driverDao():DriverDao
    abstract fun tokenDao():TokenDao
}
