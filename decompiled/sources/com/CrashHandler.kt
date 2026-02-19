package com

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {
    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    private val infos: MutableMap<String, String> = HashMap()
    private var mContext: Context? = null
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    fun init(context: Context) {
        mContext = context
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        val uncaughtExceptionHandler = mDefaultHandler
        if (handleException(throwable) || uncaughtExceptionHandler == null) {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                Log.e(TAG, "error : ", e)
            }
            AppManager.getAppManager().finishAllActivity()
            return
        }
        uncaughtExceptionHandler.uncaughtException(thread, throwable)
    }

    private fun handleException(throwable: Throwable?): Boolean {
        if (throwable == null) {
            return false
        }
        Thread {
            Looper.prepare()
            mContext?.let {
                Toast.makeText(it, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show()
            }
            Looper.loop()
        }.start()
        mContext?.let { collectDeviceInfo(it) }
        saveCrashInfo2File(throwable)
        return true
    }

    fun collectDeviceInfo(context: Context) {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 1)
            infos["versionName"] = packageInfo.versionName ?: "null"
            infos["versionCode"] = packageInfo.versionCode.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "an error occured when collect package info", e)
        }
        for (field in Build::class.java.declaredFields) {
            try {
                field.isAccessible = true
                infos[field.name] = field.get(null).toString()
                Log.d(TAG, field.name + " : " + field.get(null))
            } catch (e: Exception) {
                Log.e(TAG, "an error occured when collect crash info", e)
            }
        }
    }

    private fun saveCrashInfo2File(throwable: Throwable): String? {
        val stringBuffer = StringBuffer()
        for ((key, value) in infos.entries) {
            stringBuffer.append(key).append("=").append(value).append("\n")
        }
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        throwable.printStackTrace(printWriter)
        var cause = throwable.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        stringBuffer.append(stringWriter.toString())
        return try {
            val currentTimeMillis = System.currentTimeMillis()
            val fileName = "crash-${formatter.format(Date())}-$currentTimeMillis.log"
            if (Environment.getExternalStorageState() == "mounted") {
                val dir = File("/sdcard/crash/")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                FileOutputStream("/sdcard/crash/$fileName").use {
                    it.write(stringBuffer.toString().toByteArray())
                }
            }
            fileName
        } catch (e: Exception) {
            Log.e(TAG, "an error occured while writing file...", e)
            null
        }
    }

    companion object {
        const val TAG: String = "CrashHandler"

        @Volatile
        private var instance: CrashHandler? = null

        @JvmStatic
        fun getInstance(): CrashHandler {
            if (instance == null) {
                synchronized(CrashHandler::class.java) {
                    if (instance == null) {
                        instance = CrashHandler()
                    }
                }
            }
            return instance!!
        }
    }
}
