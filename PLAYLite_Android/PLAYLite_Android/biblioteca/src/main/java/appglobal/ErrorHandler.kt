package appglobal

data class ErrorHandler(
    var message: String? = "",
    var errorCode: Int? = 0,
    var success: Boolean? = false,
    var continueProcess: Boolean = false // Uso esta variable para si hay que realizar un subProceso
    )
