package su.egorovwa.withoutchecksdriver.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import su.egorovwa.withoutchecksdriver.datastore.TokenManager
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule{
@Singleton
@Provides
fun provideTokenManager(@ApplicationContext context: Context)=TokenManager(context)
}