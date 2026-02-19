package com.dvr.bluetooth

class StreamBuffer(i: Int) {
    @JvmField
    var buffer: ByteArray = ByteArray(i)

    @JvmField
    var nBufSize: Int = i

    @JvmField
    var nDataLen: Int = 0

    private fun resize(i: Int) {
        if (buffer.size < i) {
            val bArr2 = ByteArray(i)
            System.arraycopy(buffer, 0, bArr2, 0, buffer.size)
            buffer = bArr2
        }
    }

    fun AddData(bArr: ByteArray?, i: Int) {
        val safeInput = bArr ?: return
        val dataLen = nDataLen
        if (dataLen + i > buffer.size) {
            resize(dataLen + i)
        }
        System.arraycopy(safeInput, 0, buffer, nDataLen, i)
        nDataLen += i
    }

    fun GetFrame(bArr: ByteArray?, iArr: IntArray?): Boolean {
        val outBuffer = bArr ?: return false
        val outLen = iArr ?: return false

        for (i in 0 until nDataLen) {
            if (buffer[i].toInt() == 35) {
                System.arraycopy(buffer, 0, outBuffer, 0, i)
                val i2 = nDataLen - i - 1
                nDataLen = i2
                outLen[0] = i
                System.arraycopy(buffer, i + 1, buffer, 0, i2)
                return true
            }
        }
        return false
    }
}
