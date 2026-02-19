package com.hjq.http.body

import com.hjq.http.EasyUtils
import com.hjq.http.model.ContentType
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.Okio
import okio.Source

open class UpdateBody(
    private val mSource: Source,
    private val mMediaType: MediaType,
    private val mKeyName: String,
    private val mLength: Long
) : RequestBody() {

    @Throws(FileNotFoundException::class)
    constructor(file: File) : this(
        Okio.source(file),
        ContentType.guessMimeType(file.name),
        file.name,
        file.length()
    )

    @Throws(IOException::class)
    constructor(inputStream: InputStream, str: String) : this(
        Okio.source(inputStream),
        ContentType.STREAM,
        str,
        inputStream.available().toLong()
    )

    override fun contentType(): MediaType {
        return mMediaType
    }

    override fun contentLength(): Long {
        if (mLength == 0L) {
            return -1
        }
        return mLength
    }

    @Throws(IOException::class)
    override fun writeTo(bufferedSink: BufferedSink) {
        try {
            bufferedSink.writeAll(mSource)
        } finally {
            EasyUtils.closeStream(mSource)
        }
    }

    fun getKeyName(): String {
        return mKeyName
    }
}
