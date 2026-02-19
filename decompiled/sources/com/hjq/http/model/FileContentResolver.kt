package com.hjq.http.model

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import java.io.File
import java.io.FileFilter
import java.io.FileNotFoundException
import java.io.FilenameFilter
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import okhttp3.MediaType

class FileContentResolver : FileWrapper {
    private val mContentResolver: ContentResolver
    private var mContentType: MediaType?
    private val mContentUri: Uri
    private var mFileName: String

    constructor(context: Context, uri: Uri) : this(context.contentResolver, uri)

    constructor(contentResolver: ContentResolver, uri: Uri) : this(contentResolver, uri, null)

    constructor(context: Context, uri: Uri, str: String?) : this(context.contentResolver, uri, str)

    constructor(contentResolver: ContentResolver, uri: Uri, str: String?) : super(File(uri.toString())) {
        mContentResolver = contentResolver
        mContentUri = uri
        if (!TextUtils.isEmpty(str)) {
            mFileName = str!!
            mContentType = ContentType.guessMimeType(str)
        } else {
            mFileName = name
            mContentType = ContentType.STREAM
        }
    }

    override fun exists(): Boolean {
        return true
    }

    override fun getParentFile(): File? {
        return null
    }

    override fun isDirectory(): Boolean {
        return false
    }

    override fun isFile(): Boolean {
        return true
    }

    override fun isHidden(): Boolean {
        return false
    }

    override fun lastModified(): Long {
        return 0
    }

    override fun list(): Array<String>? {
        return null
    }

    override fun list(filter: FilenameFilter?): Array<String>? {
        return null
    }

    override fun listFiles(): Array<File>? {
        return null
    }

    override fun listFiles(filter: FileFilter?): Array<File>? {
        return null
    }

    override fun listFiles(filter: FilenameFilter?): Array<File>? {
        return null
    }

    override fun mkdir(): Boolean {
        return true
    }

    override fun mkdirs(): Boolean {
        return true
    }

    override fun renameTo(dest: File): Boolean {
        return false
    }

    override fun setLastModified(time: Long): Boolean {
        return false
    }

    fun setFileName(str: String) {
        mFileName = str
    }

    fun getFileName(): String {
        return mFileName
    }

    fun setContentType(mediaType: MediaType?) {
        mContentType = mediaType
    }

    fun getContentType(): MediaType? {
        return mContentType
    }

    fun getContentUri(): Uri {
        return mContentUri
    }

    @Throws(FileNotFoundException::class)
    override fun openInputStream(): InputStream? {
        return mContentResolver.openInputStream(mContentUri)
    }

    @Throws(FileNotFoundException::class)
    override fun openOutputStream(): OutputStream? {
        return mContentResolver.openOutputStream(mContentUri)
    }

    override fun delete(): Boolean {
        return mContentResolver.delete(mContentUri, null, null) > 0
    }

    override fun length(): Long {
        var inputStream: InputStream? = null
        return try {
            inputStream = openInputStream()
            if (inputStream != null) {
                inputStream.available().toLong()
            } else {
                0
            }
        } catch (e: FileNotFoundException) {
            EasyLog.print(e)
            0
        } catch (e: IOException) {
            EasyLog.print(e)
            0
        } finally {
            EasyUtils.closeStream(inputStream)
        }
    }
}
