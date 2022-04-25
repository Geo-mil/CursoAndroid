package preferences

enum class AppPreferences(val value: String) {

    PREFERENCE_FILE_KEY("SharedPreferencesFileKey"),
    PREFERENCE_LOGIN_PASSWORD_KEY("LoginPasswordKey"),
    PREFERENCE_LOGIN_NAME_KEY("loginNameKey"),
    PREFERENCE_LOGIN_TENANT_KEY("loginTenantKey"),
    PREFERENCE_REMEMBER_LOGIN_KEY("loginRememberUserKey")

}