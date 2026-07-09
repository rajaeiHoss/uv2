package freemarker.template

interface TemplateHashModelEx : TemplateHashModel {
    @Throws(TemplateModelException::class)
    fun keys(): TemplateCollectionModel

    @Throws(TemplateModelException::class)
    fun size(): Int

    @Throws(TemplateModelException::class)
    fun values(): TemplateCollectionModel
}
