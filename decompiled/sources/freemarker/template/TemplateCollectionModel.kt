package freemarker.template

interface TemplateCollectionModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun iterator(): TemplateModelIterator
}
