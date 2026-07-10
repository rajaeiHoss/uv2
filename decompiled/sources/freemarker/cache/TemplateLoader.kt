package freemarker.cache

import java.io.IOException
import java.io.Reader

interface TemplateLoader {
    @Throws(IOException::class)
    fun closeTemplateSource(templateSource: Any)

    @Throws(IOException::class)
    fun findTemplateSource(name: String): Any?

    fun getLastModified(templateSource: Any): Long

    @Throws(IOException::class)
    fun getReader(templateSource: Any, encoding: String): Reader
}
