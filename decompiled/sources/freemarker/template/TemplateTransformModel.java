package freemarker.template;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public interface TemplateTransformModel extends TemplateModel {
    Writer getWriter(Writer writer, Map map) throws TemplateModelException, IOException;
}
