package freemarker.template

import freemarker.core.Environment
import java.io.IOException

interface TemplateDirectiveModel : TemplateModel {
    @Throws(TemplateException::class, IOException::class)
    fun execute(
        environment: Environment,
        params: Map<*, *>,
        loopVars: Array<TemplateModel?>,
        body: TemplateDirectiveBody?,
    )
}
