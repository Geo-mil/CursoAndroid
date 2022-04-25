package global.login

import android.content.Context
import androidx.core.content.edit
import preferences.AppPreferences

data class LoginUserClass(
    var user: String = "",
    var password: String = "",
    var tenant: String = "",
    var  isRememberAccess: Boolean = false) {


    fun getCurrentUser(context: Context): LoginUserClass {
        var loginUserData: LoginUserClass = LoginUserClass("","", "", false)

        //cargar File preferences LOGIN
        val sharedPref = context.getSharedPreferences(AppPreferences.valueOf(AppPreferences.PREFERENCE_FILE_KEY.toString()).value, Context.MODE_PRIVATE)

        loginUserData.isRememberAccess  =
            sharedPref.getBoolean(AppPreferences.valueOf(AppPreferences.PREFERENCE_REMEMBER_LOGIN_KEY.toString()).value,false)
        loginUserData.password  =
            sharedPref.getString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_PASSWORD_KEY.toString()).value,"").toString()
        loginUserData.user  =
            sharedPref.getString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_NAME_KEY.toString()).value,"").toString()
        loginUserData.tenant  =
            sharedPref.getString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_TENANT_KEY.toString()).value,"").toString()

        return loginUserData
    }

    fun setCurrentUser(context: Context, user: LoginUserClass) {
        val sharedPref = context.getSharedPreferences(AppPreferences.valueOf(AppPreferences.PREFERENCE_FILE_KEY.toString()).value, Context.MODE_PRIVATE)
        sharedPref.edit { this
            putBoolean(AppPreferences.valueOf(AppPreferences.PREFERENCE_REMEMBER_LOGIN_KEY.toString()).value, user.isRememberAccess)
            putString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_NAME_KEY.toString()).value, user.user)
            putString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_PASSWORD_KEY.toString()).value, user.password)
            putString(AppPreferences.valueOf(AppPreferences.PREFERENCE_LOGIN_TENANT_KEY.toString()).value, user.tenant)
            this.apply()
        }
    }


}
