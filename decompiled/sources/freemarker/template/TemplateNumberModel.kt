package freemarker.template

interface TemplateNumberModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun getAsNumber(): Number
}
