package su.egorovwa.withoutchecksdriver.data

import su.egorovwa.withoutchecksdriver.exceptions.TokenNotFoundException
import java.util.Date
import javax.inject.Inject

interface DataBaseRepository {

    suspend fun getCurrentToken():Token
    suspend fun registration(password: String, phone:String, driverId: Long)
    suspend fun addToken(token:String, isusetAt:Long, expiriesAt:Long)
}
class DataRepositoryImpl @Inject constructor(
    private val driverDao: DriverDao,
    private val tokenDao: TokenDao
):DataBaseRepository{

    override suspend fun getCurrentToken():Token {
        val dateNovMilis = Date().time
        try {
         return   tokenDao.getCurrentToken(dateNovMilis).first()
        }catch (e:NoSuchElementException){
            throw TokenNotFoundException()
        }
    }

    override suspend fun registration(password: String, phone: String, driverId: Long) {
        val driverRegistration = DriverRegistration(password = password, phone = phone, id = driverId)
        driverDao.save(driverRegistration)
    }

    override suspend fun addToken(token: String, isusetAt: Long, expiriesAt: Long) {
        val newToken = Token(token = token, isusetAt = isusetAt, expiredAt = expiriesAt, id = null)
        tokenDao.save(newToken)
    }
}