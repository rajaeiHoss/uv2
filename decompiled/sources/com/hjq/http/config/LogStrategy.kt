package com.hjq.http.config

import android.text.TextUtils
import android.util.Log
import com.hjq.http.EasyConfig
import com.hjq.http.EasyHttp

class LogStrategy : ILogStrategy {
    override fun print(str: String?) {
        val logTag = EasyConfig.getInstance().logTag
        Log.i(logTag, str ?: "null")
    }

    override fun json(str: String?) {
        val formatJson = ILogStrategy.formatJson(str)
        if (!TextUtils.isEmpty(formatJson)) {
            var message = " \n$formatJson"
            if (message.length.toLong() <= 3072L) {
                print(message)
                return
            }
            while (message.length > 3072) {
                val substring = message.substring(0, 3072)
                message = message.replace(substring, "")
                print(substring)
            }
            print(message)
        }
    }

    override fun print(str: String?, str2: String?) {
        print(str + " = " + str2)
    }

    override fun print(th: Throwable) {
        Log.e(EasyConfig.getInstance().logTag, th.message, th)
    }

    override fun print(stackTraceElementArr: Array<StackTraceElement>) {
        for (stackTraceElement in stackTraceElementArr) {
            val lineNumber = stackTraceElement.lineNumber
            val className = stackTraceElement.className
            if (lineNumber > 0 && !className.startsWith(EasyHttp::class.java.`package`.name)) {
                print("RequestCode = (" + stackTraceElement.fileName + ":" + lineNumber + ") ")
                return
            }
        }
    }
}
