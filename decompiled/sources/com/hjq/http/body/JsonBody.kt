package com.hjq.http.body

import com.hjq.http.model.ContentType
import java.io.IOException
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import org.json.JSONArray
import org.json.JSONObject

open class JsonBody private constructor(
    private val mJson: String,
    ignored: Boolean
) : RequestBody() {
    private val mBytes: ByteArray = mJson.toByteArray()

    constructor(map: Map<*, *>) : this(JSONObject(map).toString(), true)

    constructor(list: List<*>) : this(JSONArray(list).toString(), true)

    constructor(jsonObject: JSONObject) : this(jsonObject.toString(), true)

    constructor(jsonArray: JSONArray) : this(jsonArray.toString(), true)

    constructor(str: String) : this(str, true)

    override fun contentType(): MediaType {
        return ContentType.JSON
    }

    override fun contentLength(): Long {
        return mBytes.size.toLong()
    }

    @Throws(IOException::class)
    override fun writeTo(bufferedSink: BufferedSink) {
        bufferedSink.write(mBytes, 0, mBytes.size)
    }

    fun getJson(): String {
        return mJson
    }

    override fun toString(): String {
        return mJson
    }
}
