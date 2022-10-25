package com.simulacratech.base.utility

import com.simulacratech.base.utility.Constants.Companion.API_SUFFIX


private const val isAlphaServer = false
fun getAuth0ClientId(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_AUTH0_CLIENTID,
        "Ivvg6biRsTw37Ai3KKZf6jeW8XMz5XUP"
    )
}

fun getAuth0Domain(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_AUTH0_DOMAIN,
        "dev-9dcdtmyu.us.auth0.com"
    )
}

fun getAuth0Audience(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_AUTH0_AUDIENCE,
        "ivms-auth-api"
    )
}

fun getAuth0AuthScheme(): String {
    return PreferenceHelper.instance.getStringData(Constants.PREF_IVMS_AUTH0_AUTH_SCHEME, "demo")
}

fun getImageAccessUrl(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_IMAGE_ACCESS_ENDPOINT_URL,
        "https://ivms-test-1.s3.amazonaws.com/"
    )
}

fun getAuthBaseUrl(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_AUTH_ENDPOINT_URL,
        (if (isAlphaServer) "https://ivms-auth-alpha.aidash.io" else "https://ivms-auth-service-test.avista.aidash.io") + API_SUFFIX
    )
}

fun getBaseUrl(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_API_ENDPOINT_URL,
        (if (isAlphaServer) "https://ivms-service-alpha.aidash.io" else "https://ivms-service-test.avista.aidash.io") + API_SUFFIX
    )
}

fun getBackFillUrl(): String {
    return PreferenceHelper.instance.getStringData(
        Constants.PREF_IVMS_BACKFIL_ENDOINT_URL,
        (if (isAlphaServer) "https://ivms-backfill-alpha.aidash.io" else "https://ivms-backfill-test.avista.aidash.io") + API_SUFFIX
    )
}