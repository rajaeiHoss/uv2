package freemarker.template;

import java.util.List;

public interface TemplateMethodModel extends TemplateModel {
    Object exec(List list) throws TemplateModelException;
}
