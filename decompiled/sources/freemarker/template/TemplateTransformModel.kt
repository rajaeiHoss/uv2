package freemarker.template

import java.io.IOException
import java.io.Writer

interface TemplateTransformModel : TemplateModel {
    @Throws(TemplateModelException::class, IOException::class)
    fun getWriter(writer: Writer, args: Map<*, *>): Writer
}
