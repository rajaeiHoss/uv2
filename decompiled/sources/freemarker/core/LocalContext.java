package freemarker.core;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.Collection;

public interface LocalContext {
    TemplateModel getLocalVariable(String str) throws TemplateModelException;

    Collection getLocalVariableNames() throws TemplateModelException;
}
