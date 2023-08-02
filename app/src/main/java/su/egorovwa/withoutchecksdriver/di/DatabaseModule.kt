package su.egorovwa.withoutchecksdriver.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import su.egorovwa.withoutchecksdriver.data.DataBase
import su.egorovwa.withoutchecksdriver.data.DataBaseRepository
import su.egorovwa.withoutchecksdriver.data.DataRepositoryImpl
import su.egorovwa.withoutchecksdriver.data.DriverDao
import su.egorovwa.withoutchecksdriver.data.TokenDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDataBaseRepository(tokenDao: TokenDao, driverDao: DriverDao):DataBaseRepository{
        return DataRepositoryImpl(tokenDao = tokenDao, driverDao = driverDao)
    }
    @Provides
    fun provideDriverDao(database: DataBase): DriverDao {
        return database.driverDao()
    }
    @Provides
    fun provideTokenDao(database: DataBase):TokenDao{
        return database.tokenDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DataBase {
        return Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            "driver_data"
        ).build()
    }
}