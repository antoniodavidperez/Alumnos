package com.example.alumnos

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream

open class NetworkClient {
    fun get(url: String): InputStream {
        val request = Request.Builder().url(url).build()
        val response = OkHttpClient().newCall(request).execute()
        val body = response.body()

        return body!!.byteStream()
    }
}