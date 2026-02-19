package freemarker.template;

import freemarker.core.Environment;
import java.io.IOException;
import java.util.Map;

public interface TemplateDirectiveModel extends TemplateModel {
    void execute(Environment environment, Map map, TemplateModel[] templateModelArr, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException;
}
