package freemarker.template

interface TemplateModel {
    companion object {
        @JvmField
        val NOTHING: TemplateModel = GeneralPurposeNothing.getInstance()
    }
}
