package freemarker.ext.util

import freemarker.template.TemplateModel

interface WrapperTemplateModel : TemplateModel {
    fun getWrappedObject(): Any?
}
