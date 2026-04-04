package freemarker.log

internal interface LoggerFactory {
    fun getLogger(str: String): Logger
}
