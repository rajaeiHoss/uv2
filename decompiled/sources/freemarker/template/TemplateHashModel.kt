package freemarker.template

interface TemplateHashModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun get(key: String): TemplateModel?

    @Throws(TemplateModelException::class)
    fun isEmpty(): Boolean
}
