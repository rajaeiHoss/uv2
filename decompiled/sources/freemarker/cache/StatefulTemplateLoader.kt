package freemarker.cache

interface StatefulTemplateLoader : TemplateLoader {
    fun resetState()
}
