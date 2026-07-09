package freemarker.template

interface TemplateMethodModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun exec(arguments: List<*>): Any?
}
