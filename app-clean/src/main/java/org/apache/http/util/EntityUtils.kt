package org.apache.http.util

import java.io.ByteArrayOutputStream
import java.io.IOException
import org.apache.http.HttpEntity

class EntityUtils private constructor() {
    companion object {
        @JvmStatic
        @Throws(IOException::class)
        fun toByteArray(entity: HttpEntity?): ByteArray {
            if (entity == null) {
                return ByteArray(0)
            }
            val input = entity.getContent() ?: return ByteArray(0)
            val buffer = ByteArrayOutputStream()
            val data = ByteArray(4096)
            while (true) {
                val read = input.read(data)
                if (read == -1) {
                    break
                }
                buffer.write(data, 0, read)
            }
            return buffer.toByteArray()
        }
    }
}
