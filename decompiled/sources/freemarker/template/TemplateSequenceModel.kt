package freemarker.template

interface TemplateSequenceModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun get(index: Int): TemplateModel?

    @Throws(TemplateModelException::class)
    fun size(): Int
}
