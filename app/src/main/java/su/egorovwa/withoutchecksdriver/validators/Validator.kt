package su.egorovwa.withoutchecksdriver.validators

import android.text.TextUtils

fun isPhoneNumberValid(phone:String):Boolean{
    return !TextUtils.isEmpty(phone) && android.util.Patterns.PHONE.matcher(phone).matches()
}
fun isNameValid(name:String):Boolean{
    return name.matches(Regex("[а-яА-я]{2,}"))
}
fun isEmailValid(email:String):Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isPasswordValid(password:String):Boolean{
    return password.matches(Regex("\\w{4,10}"))
}