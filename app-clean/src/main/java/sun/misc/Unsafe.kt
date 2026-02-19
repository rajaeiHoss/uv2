package sun.misc

import java.lang.reflect.Field

class Unsafe private constructor() {
    fun objectFieldOffset(field: Field?): Long = 0L

    fun arrayBaseOffset(cls: Class<*>?): Int = 0

    fun arrayIndexScale(cls: Class<*>?): Int = 1

    fun getInt(obj: Any?, offset: Long): Int = 0

    fun putInt(obj: Any?, offset: Long, value: Int) {
    }

    fun getLong(obj: Any?, offset: Long): Long = 0L

    fun putLong(obj: Any?, offset: Long, value: Long) {
    }

    fun getObject(obj: Any?, offset: Long): Any? = null

    fun putObject(obj: Any?, offset: Long, value: Any?) {
    }

    fun getByte(obj: Any?, offset: Long): Byte = 0

    fun putByte(obj: Any?, offset: Long, value: Byte) {
    }

    fun getBoolean(obj: Any?, offset: Long): Boolean = false

    fun putBoolean(obj: Any?, offset: Long, value: Boolean) {
    }

    fun getFloat(obj: Any?, offset: Long): Float = 0f

    fun putFloat(obj: Any?, offset: Long, value: Float) {
    }

    fun getDouble(obj: Any?, offset: Long): Double = 0.0

    fun putDouble(obj: Any?, offset: Long, value: Double) {
    }

    fun getByte(address: Long): Byte = 0

    fun putByte(address: Long, value: Byte) {
    }

    fun getInt(address: Long): Int = 0

    fun putInt(address: Long, value: Int) {
    }

    fun getLong(address: Long): Long = 0L

    fun putLong(address: Long, value: Long) {
    }

    fun copyMemory(srcAddress: Long, destAddress: Long, bytes: Long) {
    }

    fun copyMemory(srcBase: Any?, srcOffset: Long, destBase: Any?, destOffset: Long, bytes: Long) {
    }

    companion object {
        @JvmField
        val theUnsafe: Unsafe? = null

        @JvmStatic
        fun getUnsafe(): Unsafe? = theUnsafe
    }
}
