package vn.namnp.userinfoapplication.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import vn.namnp.userinfoapplication.util.NetworkUtils.isNetworkAvailable
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkAvailable(context)) {
            throw NoConnectionException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(request = builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message = super.message ?: "No Internet Connection"
    }
}