package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;

public class NumberModel extends BeanModel implements TemplateNumberModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new NumberModel((Number) obj, (BeansWrapper) objectWrapper);
        }
    };

    public NumberModel(Number number, BeansWrapper beansWrapper) {
        super(number, beansWrapper);
    }

    public Number getAsNumber() {
        return (Number) this.object;
    }
}
