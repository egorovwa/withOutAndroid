package su.egorovwa.withoutchecksdriver.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import su.egorovwa.withoutchecksdriver.network.NetworkRepository
import su.egorovwa.withoutchecksdriver.network.NetworkRepositoryDefault
import su.egorovwa.withoutchecksdriver.network.TaxiServiceApi
import javax.inject.Singleton

const val BASE_URL = "http://192.168.1.226:8080" // TODO: base url

@InstallIn(SingletonComponent::class)
@Module
object NetworkService {
    @Provides
    fun provideTaxiService(retrofit: Retrofit): TaxiServiceApi {
        return retrofit.create(TaxiServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        Json {
            encodeDefaults = true
            classDiscriminator = "_type"
        }
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class NetworkModule {
    @Binds
    abstract fun bindNetworkRepository(impl: NetworkRepositoryDefault): NetworkRepository
}