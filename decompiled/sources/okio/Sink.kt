package okio

import java.io.Closeable
import java.io.Flushable
import java.io.IOException

interface Sink : Closeable, Flushable {
    @Throws(IOException::class)
    override fun close()

    @Throws(IOException::class)
    override fun flush()

    fun timeout(): Timeout

    @Throws(IOException::class)
    fun write(source: Buffer, byteCount: Long)
}
