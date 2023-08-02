package su.egorovwa.withoutchecksdriver.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import su.egorovwa.withoutchecksdriver.network.dto.NetworkData

interface TaxiServiceApi {
    @POST("/auth/registre")
   suspend fun register(@Body networkData: NetworkData): Response<NetworkData>
   @POST("/auth/login")
   suspend fun login(@Body networkData: NetworkData):Response<NetworkData>
}