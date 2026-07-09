package freemarker.template

interface TemplateMethodModelEx : TemplateMethodModel {
    @Throws(TemplateModelException::class)
    override fun exec(arguments: List<*>): Any?
}
