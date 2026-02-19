package com.hjq.http.model

import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

open class FileWrapper(file: File) : File(file.path) {

    @Throws(FileNotFoundException::class)
    open fun openInputStream(): InputStream? {
        return FileInputStream(this)
    }

    @Throws(FileNotFoundException::class)
    open fun openOutputStream(): OutputStream? {
        return FileOutputStream(this)
    }

    companion object {
        @JvmStatic
        fun createFolder(file: File): Boolean {
            if (file.exists()) {
                if (file.isDirectory) {
                    return true
                }
                file.delete()
            }
            return file.mkdirs()
        }

        @JvmStatic
        fun getFileMd5(inputStream: InputStream?): String? {
            var digestInputStream: DigestInputStream? = null
            if (inputStream == null) {
                return ""
            }
            return try {
                digestInputStream = DigestInputStream(inputStream, MessageDigest.getInstance("MD5"))
                while (digestInputStream.read(ByteArray(262144)) > 0) {
                }
                val digest = digestInputStream.messageDigest.digest()
                val sb = StringBuilder()
                for (value in digest) {
                    sb.append(String.format("%02X", value))
                }
                sb.toString().lowercase()
            } catch (e: IOException) {
                EasyLog.print(e)
                null
            } catch (e: NoSuchAlgorithmException) {
                EasyLog.print(e)
                null
            } finally {
                EasyUtils.closeStream(inputStream)
                EasyUtils.closeStream(digestInputStream)
            }
        }
    }
}
