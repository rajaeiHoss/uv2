package okhttp3

import java.io.IOException

interface Callback {
    fun onFailure(call: Call, exception: IOException)

    @Throws(IOException::class)
    fun onResponse(call: Call, response: Response)
}
