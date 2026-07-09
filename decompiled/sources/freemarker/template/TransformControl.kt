package freemarker.template

import java.io.IOException

interface TransformControl {
    @Throws(TemplateModelException::class, IOException::class)
    fun afterBody(): Int

    @Throws(Throwable::class)
    fun onError(throwable: Throwable)

    @Throws(TemplateModelException::class, IOException::class)
    fun onStart(): Int

    companion object {
        const val REPEAT_EVALUATION: Int = 0
        const val SKIP_BODY: Int = 0
        const val END_EVALUATION: Int = 1
        const val EVALUATE_BODY: Int = 1
    }
}
