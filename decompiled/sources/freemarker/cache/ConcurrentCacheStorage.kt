package freemarker.cache

interface ConcurrentCacheStorage : CacheStorage {
    fun isConcurrent(): Boolean
}
