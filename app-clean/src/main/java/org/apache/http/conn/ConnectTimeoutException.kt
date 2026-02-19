package org.apache.http.conn

import java.io.InterruptedIOException

open class ConnectTimeoutException : InterruptedIOException {
    constructor() : super()

    constructor(message: String?) : super(message)
}
