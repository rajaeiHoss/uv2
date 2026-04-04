package freemarker.template

interface AdapterTemplateModel : TemplateModel {
    fun getAdaptedObject(cls: Class<*>): Any?
}
