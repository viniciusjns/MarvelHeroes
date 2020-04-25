package com.vinicius.marvelheroes.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vinicius.marvelheroes.BuildConfig
import com.vinicius.marvelheroes.utils.Utils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClientImpl : APIClient {

    private lateinit var mRetrofit: Retrofit

    companion object {
        private const val TIMEOUT = 360

        lateinit var instance: APIClient
    }

    private val requestIntercept = { chain: Interceptor.Chain ->

        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", BuildConfig.TS)
//            .addQueryParameter("hash", "7c4c652b6b8ca320fdb12bde65294920") //md5(ts+privateKey+publicKey)
            .addQueryParameter("hash", getHash()) //md5(ts+privateKey+publicKey)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("limit", "100")
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()

        chain.proceed(request)
    }

    private fun getHash(): String? =
        Utils.md5(BuildConfig.TS + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

    private val client: OkHttpClient
        get() {
            val okHttp = OkHttpClient().newBuilder()
                .addInterceptor(requestIntercept)
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            okHttp.addInterceptor(loggingCapableHttpClient)

            return okHttp.build()
        }

    override fun getRetrofit(): Retrofit = mRetrofit

    private val loggingCapableHttpClient: HttpLoggingInterceptor
        get() {
            val mLogging = HttpLoggingInterceptor()
            mLogging.level = HttpLoggingInterceptor.Level.BODY

            return mLogging
        }

    init {
        instance = this
    }

    override fun configure(baseUrl: String) {
        val mClient = client

        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}