package com.kenai.jbosh

internal interface BodyParser {
    @Throws(BOSHException::class)
    fun parse(str: String): BodyParserResults
}
