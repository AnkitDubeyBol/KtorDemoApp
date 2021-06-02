package com.ankitdubey.ktordemoapp

import android.util.Log
import com.ankitdubey.ktordemoapp.networking.baseURL
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import okhttp3.CertificatePinner

private const val TIME_OUT = 60_000

// Amazon Root CAs generated using openssl:
// openssl x509 -in my-certificate.crt -pubkey -noout | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
// Amazon Root CAs generated using openssl:
// openssl x509 -in my-certificate.crt -pubkey -noout | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64


/*var cert : CertificatePinner = CertificatePinner.Builder()
    .add(baseURL, "Lac=")
    .build()*/

val ktorHttpClient = HttpClient(OkHttp) {

    engine {
        /*this.config {
            certificatePinner(cert)
        }*/
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}