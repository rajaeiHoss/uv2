package freemarker.template

import java.io.IOException
import java.io.Writer

interface TemplateDirectiveBody {
    @Throws(TemplateException::class, IOException::class)
    fun render(writer: Writer)
}
