package su.egorovwa.withoutchecksdriver.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import su.egorovwa.withoutchecksdriver.di.dataStore

class TokenManager(val context:Context) {
    companion object{
        private val TOKEN_KEY= stringPreferencesKey("jwt_token")
        private val PHONE_KEY= stringPreferencesKey("phone")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val ID_KEY = stringPreferencesKey("id_key")
    }
    fun getToken(): Flow<String?>{
        return context.dataStore.data.map { pref-> pref[TOKEN_KEY] }
    }
    suspend fun saveToken(token:String){
        context.dataStore.edit { pref->
            pref[TOKEN_KEY] = token
        }
    }
    suspend fun deleteToken(){
        context.dataStore.edit {pref->
            pref.remove(TOKEN_KEY)
        }
    }
    fun getId(): Flow<String?>{
        return context.dataStore.data.map { pref-> pref[ID_KEY] }
    }
    suspend fun saveId(id:String){
        context.dataStore.edit { pref->
            pref[ID_KEY] = id
        }
    }
    suspend fun deleteId(){
        context.dataStore.edit {pref->
            pref.remove(ID_KEY)
        }
    }
    fun getPhone(): Flow<String?>{
        return context.dataStore.data.map { pref-> pref[PHONE_KEY] }
    }
    suspend fun savePhone(phone:String){
        context.dataStore.edit { pref->
            pref[PHONE_KEY] = phone
        }
    }
    suspend fun deletePhone(){
        context.dataStore.edit {pref->
            pref.remove(PHONE_KEY)
        }
    }
    fun getPassword(): Flow<String?>{
        return context.dataStore.data.map { pref-> pref[PASSWORD_KEY] }
    }
    suspend fun savePassword(password:String){
        context.dataStore.edit { pref->
            pref[PASSWORD_KEY] = password
        }
    }
    suspend fun deletePassword(){
        context.dataStore.edit {pref->
            pref.remove(PASSWORD_KEY)
        }
    }
}