package freemarker.template

import java.util.Arrays
import java.util.Collections
import java.util.Date

interface TemplateDateModel : TemplateModel {
    @Throws(TemplateModelException::class)
    fun getAsDate(): Date

    fun getDateType(): Int

    companion object {
        @JvmField
        val UNKNOWN: Int = 0

        @JvmField
        val TIME: Int = 1

        @JvmField
        val DATE: Int = 2

        @JvmField
        val DATETIME: Int = 3

        @JvmField
        val TYPE_NAMES: List<String> = Collections.unmodifiableList(
            Arrays.asList("UNKNOWN", "TIME", "DATE", "DATETIME"),
        )
    }
}
