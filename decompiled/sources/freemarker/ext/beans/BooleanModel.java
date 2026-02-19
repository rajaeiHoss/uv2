package freemarker.ext.beans;

import freemarker.template.TemplateBooleanModel;

public class BooleanModel extends BeanModel implements TemplateBooleanModel {
    private final boolean value;

    public BooleanModel(Boolean bool, BeansWrapper beansWrapper) {
        super(bool, beansWrapper);
        this.value = bool.booleanValue();
    }

    public boolean getAsBoolean() {
        return this.value;
    }
}
