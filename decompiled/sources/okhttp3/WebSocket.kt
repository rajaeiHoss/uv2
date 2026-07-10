package okhttp3

import javax.annotation.Nullable
import okio.ByteString

interface WebSocket {
    interface Factory {
        fun newWebSocket(request: Request, listener: WebSocketListener): WebSocket
    }

    fun cancel()

    fun close(code: Int, @Nullable reason: String?): Boolean

    fun queueSize(): Long

    fun request(): Request

    fun send(text: String): Boolean

    fun send(bytes: ByteString): Boolean
}
