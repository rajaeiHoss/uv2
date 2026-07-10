package okio

import java.io.Closeable
import java.io.IOException

interface Source : Closeable {
    @Throws(IOException::class)
    override fun close()

    @Throws(IOException::class)
    fun read(sink: Buffer, byteCount: Long): Long

    fun timeout(): Timeout
}
