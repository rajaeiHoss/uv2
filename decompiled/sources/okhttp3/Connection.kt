package okhttp3

import java.net.Socket
import javax.annotation.Nullable

interface Connection {
    @Nullable
    fun handshake(): Handshake?

    fun protocol(): Protocol

    fun route(): Route

    fun socket(): Socket
}
