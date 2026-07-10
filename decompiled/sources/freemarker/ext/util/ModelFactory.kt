package freemarker.ext.util

import freemarker.template.ObjectWrapper
import freemarker.template.TemplateModel

interface ModelFactory {
    fun create(obj: Any?, wrapper: ObjectWrapper): TemplateModel
}
