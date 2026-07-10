package freemarker.cache

interface CacheStorage {
    fun clear()

    fun get(key: Any?): Any?

    fun put(key: Any?, value: Any?)

    fun remove(key: Any?)
}
