package login

data class LoginErrorHandler(
    var message: String? = "",
    var errorCode: Int? = 0,
    var success: Boolean? = false
    )
