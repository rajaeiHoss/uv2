package com.dvr.bluetooth

class StreamBuffer(capacity: Int) {
    @JvmField
    var buffer: ByteArray = ByteArray(capacity)

    @JvmField
    var nBufSize: Int = capacity

    @JvmField
    var nDataLen: Int = 0

    private fun resize(newSize: Int) {
        if (buffer.size < newSize) {
            val newBuffer = ByteArray(newSize)
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.size)
            buffer = newBuffer
        }
    }

    fun AddData(data: ByteArray?, length: Int) {
        val safeInput = data ?: return
        val dataLen = nDataLen
        if (dataLen + length > buffer.size) {
            resize(dataLen + length)
        }
        System.arraycopy(safeInput, 0, buffer, nDataLen, length)
        nDataLen += length
    }

    fun GetFrame(frameBuffer: ByteArray?, frameLength: IntArray?): Boolean {
        val outBuffer = frameBuffer ?: return false
        val outLen = frameLength ?: return false

        for (frameEndIndex in 0 until nDataLen) {
            if (buffer[frameEndIndex].toInt() == 35) {
                System.arraycopy(buffer, 0, outBuffer, 0, frameEndIndex)
                val remainingBytes = nDataLen - frameEndIndex - 1
                nDataLen = remainingBytes
                outLen[0] = frameEndIndex
                System.arraycopy(buffer, frameEndIndex + 1, buffer, 0, remainingBytes)
                return true
            }
        }
        return false
    }
}
