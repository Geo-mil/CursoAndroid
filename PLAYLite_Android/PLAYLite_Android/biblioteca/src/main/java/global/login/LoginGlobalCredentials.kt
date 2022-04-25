package global.login

object LoginGlobalCredentials {
    val basePathURLDativeProduccion = "https://priluxwebuiapi.jx-staging.prilux.cloud"
    val basePathURLDativeDesarrollo = "https://priluxwebuiapi.dev-staging.dativepartners.eu"

    var user =  LoginUserClass("","", "", false)
    var tenantID = "-1"
    var tokenAuth = ""
    var basePathUrl = basePathURLDativeProduccion
    var flagModoDesarrolladorIsEnabled = false


}