package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

public class StringModel extends BeanModel implements TemplateScalarModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new StringModel(obj, (BeansWrapper) objectWrapper);
        }
    };

    public StringModel(Object obj, BeansWrapper beansWrapper) {
        super(obj, beansWrapper);
    }

    public String getAsString() {
        return this.object.toString();
    }
}
