package okhttp3

import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.annotation.Nullable

interface Interceptor {
    interface Chain {
        fun call(): Call

        fun connectTimeoutMillis(): Int

        @Nullable
        fun connection(): Connection?

        @Throws(IOException::class)
        fun proceed(request: Request): Response

        fun readTimeoutMillis(): Int

        fun request(): Request

        fun withConnectTimeout(timeout: Int, unit: TimeUnit): Chain

        fun withReadTimeout(timeout: Int, unit: TimeUnit): Chain

        fun withWriteTimeout(timeout: Int, unit: TimeUnit): Chain

        fun writeTimeoutMillis(): Int
    }

    @Throws(IOException::class)
    fun intercept(chain: Chain): Response
}
