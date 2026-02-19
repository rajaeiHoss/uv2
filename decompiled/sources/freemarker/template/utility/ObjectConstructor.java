package freemarker.template.utility;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import java.util.List;

public class ObjectConstructor implements TemplateMethodModelEx {
    public Object exec(List list) throws TemplateModelException {
        if (!list.isEmpty()) {
            try {
                Class forName = ClassUtil.forName(list.get(0).toString());
                BeansWrapper defaultInstance = BeansWrapper.getDefaultInstance();
                return defaultInstance.wrap(defaultInstance.newInstance(forName, list.subList(1, list.size())));
            } catch (Exception e) {
                throw new TemplateModelException(e.getMessage());
            }
        } else {
            throw new TemplateModelException("This method must have at least one argument, the name of the class to instantiate.");
        }
    }
}
