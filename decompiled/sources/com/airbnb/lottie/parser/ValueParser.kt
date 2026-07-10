package com.airbnb.lottie.parser

import com.airbnb.lottie.parser.moshi.JsonReader
import java.io.IOException

internal interface ValueParser<V> {
    @Throws(IOException::class)
    fun parse(reader: JsonReader, scale: Float): V
}
