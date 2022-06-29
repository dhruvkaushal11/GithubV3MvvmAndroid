package com.navigithubapp.data.api

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.navigithubapp.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DEFAULT_REQUEST_TIMEOUT = 30L // In seconds

class ApiClient {
    companion object {
        @Volatile
        private var apiInterface: ApiInterface? = null

        @Volatile
        private var retrofit: Retrofit? = null
        fun getApiInterface(application: Application): ApiInterface {
            var localRef = apiInterface

            return localRef ?: synchronized(ApiClient::class.java) {
                localRef = apiInterface

                if (localRef != null) {
                    localRef!!
                } else {
                    val retrofit = getRetrofit(application, "https://api.github.com")
                    retrofit.create(ApiInterface::class.java).also {
                        this@Companion.apiInterface = it
                    }
                }
            }
        }
        private fun getRetrofit(application: Application, baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getDefaultOkHttpClient(application))
                .build()
        }
        @SuppressLint("HardwareIds")
        private fun getDefaultOkHttpClient(application: Application): OkHttpClient {
            // Create a new Interceptor
            val httpClient = OkHttpClient.Builder()

            httpClient.readTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build()

            // Adding http interceptors
            // Chuck's interceptor (for debugging)
            httpClient.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer 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")

                chain.proceed(requestBuilder.apply {

                }.build())
            }

            return httpClient.build()
        }
        fun getClient(baseUrl: String, context: Context): Retrofit? {

            // Create a new Interceptor
            val httpClient = OkHttpClient.Builder()

            httpClient.readTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build()
            httpClient.addInterceptor { chain ->
                val request = chain.request().newBuilder().build()
                chain.proceed(request)
            }

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }

            return retrofit
        }

    }
}