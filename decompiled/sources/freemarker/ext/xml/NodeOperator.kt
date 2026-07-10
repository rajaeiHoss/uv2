package freemarker.ext.xml

internal interface NodeOperator {
    fun process(
        node: Any,
        localName: String?,
        namespaceUri: String?,
        result: MutableList<Any?>,
    )
}
