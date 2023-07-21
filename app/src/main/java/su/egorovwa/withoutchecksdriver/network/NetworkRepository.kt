package su.egorovwa.withoutchecksdriver.network

import android.util.Log
import retrofit2.Response
import su.egorovwa.withoutchecksdriver.network.dto.NewDriverDto
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkRepository {
    suspend fun registre(registrationDto: NewDriverDto): Response<NewDriverDto>
}
const val TAG = "NetworkRepositoryDefault"
@Singleton
class NetworkRepositoryDefault @Inject constructor(private val taxiServiceApi: TaxiServiceApi) :
    NetworkRepository {
    override suspend fun registre(registrationDto: NewDriverDto): Response<NewDriverDto> {
val result = taxiServiceApi.register(registrationDto).execute()
        Log.i(TAG, "result = ${result.body()}, ${result.code()}, ${result.message()}")
        return result
    }
}