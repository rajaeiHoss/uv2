package com.google.firebase.remoteconfig

interface FirebaseRemoteConfigValue {
    @Throws(IllegalArgumentException::class)
    fun asBoolean(): Boolean

    fun asByteArray(): ByteArray

    @Throws(IllegalArgumentException::class)
    fun asDouble(): Double

    @Throws(IllegalArgumentException::class)
    fun asLong(): Long

    fun asString(): String

    val source: Int
}
