package su.egorovwa.withoutchecksdriver.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import su.egorovwa.withoutchecksdriver.network.dto.NewDriverDto

interface TaxiServiceApi {
    @POST("/auth/registre")
   suspend fun register(@Body registrationDto: NewDriverDto): Call<NewDriverDto>
}