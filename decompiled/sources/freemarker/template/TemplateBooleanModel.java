package freemarker.template;

public interface TemplateBooleanModel extends TemplateModel {
    public static final TemplateBooleanModel FALSE = new TemplateBooleanModel() {
        public boolean getAsBoolean() {
            return false;
        }

        private Object readResolve() {
            return TemplateBooleanModel.FALSE;
        }
    };
    public static final TemplateBooleanModel TRUE = new TemplateBooleanModel() {
        public boolean getAsBoolean() {
            return true;
        }

        private Object readResolve() {
            return TemplateBooleanModel.TRUE;
        }
    };

    boolean getAsBoolean() throws TemplateModelException;
}
