package freemarker.template

interface TemplateScalarModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun getAsString(): String

    companion object {
        @JvmField
        val EMPTY_STRING: TemplateModel = SimpleScalar("")
    }
}
