package freemarker.template

import freemarker.ext.beans.BeansWrapper

interface ObjectWrapper {
    @Throws(TemplateModelException::class)
    fun wrap(obj: Any?): TemplateModel?

    companion object {
        @JvmField
        val BEANS_WRAPPER: ObjectWrapper = BeansWrapper.getDefaultInstance()

        @JvmField
        val DEFAULT_WRAPPER: ObjectWrapper = DefaultObjectWrapper.instance

        @JvmField
        val SIMPLE_WRAPPER: ObjectWrapper = SimpleObjectWrapper.instance
    }
}
