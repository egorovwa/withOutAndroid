package su.egorovwa.withoutchecksdriver.network.dto

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
sealed class NetworkData {
    @Serializable
    data class NewDriverDto(
        val _type: String = "NewDriverDto",
        val phone: String,
        val fistName: String,
        val lastName: String,
        val password: String,
        val email: String,
        val id: Long? = null
    ) : NetworkData()

    @Serializable
    data class NetworkError(
        val _type: String = "NetworkError",
        val casus: String,
        val message: String
    ) : NetworkData()

    @Serializable
    data class AuthRequest(
        val _type: String = "AuthRequest",
        val userName: String,
        val password: String
    ):NetworkData()

    @Serializable
    data class AuthResponce(
        val _type: String = "AuthResponce",
        val driverId:Long,
        val token:String,
        val issuedAt:Long,
        val expiresAt:Long
    ):NetworkData()
}