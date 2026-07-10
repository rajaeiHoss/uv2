package freemarker.ext.dom

import freemarker.template.TemplateModel
import freemarker.template.TemplateModelException

interface XPathSupport {
    @Throws(TemplateModelException::class)
    fun executeQuery(context: Any?, xpathQuery: String): TemplateModel
}
