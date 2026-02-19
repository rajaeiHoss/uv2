package com.dvr.common

import java.text.SimpleDateFormat
import java.util.Date

object CommonFunction {
    @JvmStatic
    fun getFileLineMethod(): String {
        val stackTraceElement = Exception().stackTrace[1]
        val stringBuffer = StringBuffer("[")
        stringBuffer.append(stackTraceElement.fileName)
        stringBuffer.append(" | ")
        stringBuffer.append(stackTraceElement.lineNumber)
        stringBuffer.append(" | ")
        stringBuffer.append(stackTraceElement.methodName)
        stringBuffer.append("]")
        return stringBuffer.toString()
    }

    @JvmStatic
    fun _FILE_(): String? = Exception().stackTrace[1].fileName

    @JvmStatic
    fun _FUNC_(): String = Exception().stackTrace[1].methodName

    @JvmStatic
    fun _LINE_(): Int = Exception().stackTrace[1].lineNumber

    @JvmStatic
    fun _TIME_(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
}
