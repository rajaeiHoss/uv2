package freemarker.template

interface TemplateModelIterator {
    @Throws(TemplateModelException::class)
    fun hasNext(): Boolean

    @Throws(TemplateModelException::class)
    fun next(): TemplateModel
}
