package com.simulacratech.base.network

import com.simulacratech.base.BuildConfig
import com.google.gson.GsonBuilder
import com.simulacratech.base.utility.*
import com.simulacratech.base.utility.Constants.Companion.PREF_IVMS_ACCESS_TOKEN
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager

class RetrofitClient {

    private val readWriteTimeout = 25L
    private val connectionTimeout = 25L

    private var instance: Retrofit? = null

    private fun providesOkHttpsClientBuilder(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readWriteTimeout, TimeUnit.SECONDS)
            .writeTimeout(readWriteTimeout, TimeUnit.SECONDS)
        try {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(interceptor)
            }
            httpClient.addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "Content-Type: application/json")
                    .header("User-Agent", "IVMS-Android")

                val userToken =
                    PreferenceHelper.instance.getStringData(PREF_IVMS_ACCESS_TOKEN, "")
                if (userToken.isNotEmpty()) {
                    //showLog("\nAccess token: \n $userToken")
                    requestBuilder.header("X-IVMS-ACCESS-TOKEN", userToken)
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            })
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                )
                .build()

            val context = SSLContext.getInstance("TLS")
            val tmList = arrayOf<TrustManager>(MyTrustCertificate())
            context.init(null, tmList, null)
            httpClient.sslSocketFactory(context.socketFactory, MyTrustCertificate())
            httpClient.connectionSpecs(listOf(spec))
        } catch (exception: Exception) {
            exception.showLog()
        }

        return httpClient.build()
    }

    private fun providesOkHttpClientBuilder(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readWriteTimeout, TimeUnit.SECONDS)
            .writeTimeout(readWriteTimeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(interceptor)
        }
        httpClient.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "Content-Type: application/json")
                .header("User-Agent", "IVMS-Android")

            val userToken = PreferenceHelper.instance.getStringData(PREF_IVMS_ACCESS_TOKEN, "")
            if (userToken.isNotEmpty()) {
                showLog("\nAccess token: \n $userToken")
                requestBuilder.header("X-IVMS-ACCESS-TOKEN", userToken)
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        })
        return httpClient.build()
    }

    fun getRolesClient(httpsConnectionOnly: Boolean): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) { // HTTPS
                Retrofit.Builder()
                    .baseUrl(getAuthBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpsClientBuilder())
                    .build()
            } else { // HTTP
                Retrofit.Builder()
                    .baseUrl(getAuthBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpClientBuilder())
                    .build()
            }
        }
        return instance
    }

    fun getRetrofitClient(
        httpsConnectionOnly: Boolean,
        baseUrl: String,
        shouldSendNull: Boolean = false
    ): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) { // HTTPS
                val builder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpsClientBuilder())

                if (shouldSendNull)
                    builder.addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                else
                    builder.addConverterFactory(GsonConverterFactory.create())

                builder.build()
            } else { // HTTP
                val builder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(providesOkHttpClientBuilder())

                if (shouldSendNull)
                    builder.addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                else
                    builder.addConverterFactory(GsonConverterFactory.create())

                builder.build()
            }
        }
        return instance
    }

    fun getClient(httpsConnectionOnly: Boolean): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) { // HTTPS
                Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpsClientBuilder())
                    .build()
            } else { // HTTP
                Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpClientBuilder())
                    .build()
            }
        }
        return instance
    }

    fun getTenantClient(httpsConnectionOnly: Boolean): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) { // HTTPS
                Retrofit.Builder()
                    .baseUrl(BASE_URL_TENANT_CONFIG)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpsClientBuilder())
                    .build()
            } else { // HTTP
                Retrofit.Builder()
                    .baseUrl(BASE_URL_TENANT_CONFIG)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().serializeNulls().create()
                        )
                    )
                    .client(providesOkHttpClientBuilder())
                    .build()
            }
        }
        return instance
    }

    fun getTreeClient(httpsConnectionOnly: Boolean): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) {
                Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpsClientBuilder())
                    .build()
            } else {
                Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build()
            }
        }

        return instance
    }

    fun getImageClient(httpsConnectionOnly: Boolean): Retrofit? {
        if (instance == null) {
            instance = if (httpsConnectionOnly) {
                Retrofit.Builder()
                    .baseUrl(getImageAccessUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpsClientBuilder())
                    .build()
            } else {
                Retrofit.Builder()
                    .baseUrl(getImageAccessUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build()
            }
        }

        return instance
    }
}