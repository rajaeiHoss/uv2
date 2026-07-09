package freemarker.template

interface TemplateNodeModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun getChildNodes(): TemplateSequenceModel?

    @Throws(TemplateModelException::class)
    fun getNodeName(): String

    @Throws(TemplateModelException::class)
    fun getNodeNamespace(): String?

    @Throws(TemplateModelException::class)
    fun getNodeType(): String

    @Throws(TemplateModelException::class)
    fun getParentNode(): TemplateNodeModel?
}
