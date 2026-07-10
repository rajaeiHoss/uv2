package okhttp3.internal.cache

import java.io.IOException
import okhttp3.Request
import okhttp3.Response

interface InternalCache {
    @Throws(IOException::class)
    fun get(request: Request): Response?

    @Throws(IOException::class)
    fun put(response: Response): CacheRequest?

    @Throws(IOException::class)
    fun remove(request: Request)

    fun trackConditionalCacheHit()

    fun trackResponse(cacheStrategy: CacheStrategy)

    fun update(cached: Response, network: Response)
}
