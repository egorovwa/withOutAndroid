package su.egorovwa.withoutchecksdriver.network

import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import su.egorovwa.withoutchecksdriver.network.dto.NetworkData

import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkRepository {
    suspend fun registre(registrationDto: NetworkData.NewDriverDto): NetworkData
    suspend fun login(phone: String, password: String): NetworkData
}

const val TAG = "NetworkRepositoryDefault"

private val json = Json { encodeDefaults = true }

@Singleton
class NetworkRepositoryDefault @Inject constructor(private val taxiServiceApi: TaxiServiceApi) :
    NetworkRepository {
    override suspend fun registre(registrationDto: NetworkData.NewDriverDto): NetworkData {

        val response = taxiServiceApi.register(registrationDto)
        val result = response.body()
        if (response.isSuccessful && response.code() == 200 && result != null && result is NetworkData.NewDriverDto) {
            Log.i(TAG, "Success answer serwer is ${result}")
            return result
        } else {
            Log.e(TAG, "error answer server is ${result.toString()}")
            return NetworkData.NetworkError(
                casus = "Server responce code is ${response.code()}",
                message = "Registration error"
            )
        }

    }

    override suspend fun login(phone: String, password: String): NetworkData {
        val authRequest = NetworkData.AuthRequest(userName = phone, password = password)
        val responce = taxiServiceApi.login(authRequest)
        if (responce.isSuccessful && responce.code() == 200 && responce.body() != null) {
            val body = responce.body()
            if (body != null && responce.body() is NetworkData.AuthResponce) {
                Log.i(TAG, "Auth Success: ${body.toString()}")
                return body
            } else {
                Log.e(TAG, "Server answer is ${body}")
                return NetworkData.NetworkError(
                    casus = "Server answer is ${body}",
                    message = "Server error"
                )
            }
        }
        return NetworkData.NetworkError(
            casus = "Uncnow error server responce code is ${responce.code()}",
            message = "UncnowError"
        )
    }
}