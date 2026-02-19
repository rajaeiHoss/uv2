package freemarker.core;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.List;

class ExistenceBuiltins {
    private ExistenceBuiltins() {
    }

    private static abstract class ExistenceBuiltIn extends BuiltIn {
        private ExistenceBuiltIn() {
        }

        /* access modifiers changed from: protected */
        public TemplateModel evalMaybeNonexistentTarget(Environment environment) throws TemplateException {
            TemplateModel templateModel;
            if (!(this.target instanceof ParentheticalExpression)) {
                return this.target.eval(environment);
            }
            boolean fastInvalidReferenceExceptions = environment.setFastInvalidReferenceExceptions(true);
            try {
                templateModel = this.target.eval(environment);
            } catch (InvalidReferenceException unused) {
                templateModel = null;
            } catch (Throwable th) {
                environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
                throw th;
            }
            environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
            return templateModel;
        }
    }

    static class defaultBI extends ExistenceBuiltIn {
        private static final TemplateMethodModelEx FIRST_NON_NULL_METHOD = new TemplateMethodModelEx() {
            public Object exec(List list) throws TemplateModelException {
                int size = list.size();
                if (size != 0) {
                    for (int i = 0; i < size; i++) {
                        TemplateModel templateModel = (TemplateModel) list.get(i);
                        if (templateModel != null) {
                            return templateModel;
                        }
                    }
                    return null;
                }
                throw MessageUtil.newArgCntError("?default", size, 1, Integer.MAX_VALUE);
            }
        };

        defaultBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel evalMaybeNonexistentTarget = evalMaybeNonexistentTarget(environment);
            return evalMaybeNonexistentTarget == null ? FIRST_NON_NULL_METHOD : new ConstantMethod(evalMaybeNonexistentTarget);
        }

        private static class ConstantMethod implements TemplateMethodModelEx {
            private final TemplateModel constant;

            ConstantMethod(TemplateModel templateModel) {
                this.constant = templateModel;
            }

            public Object exec(List list) {
                return this.constant;
            }
        }
    }

    static class existsBI extends ExistenceBuiltIn {
        existsBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return evalMaybeNonexistentTarget(environment) == null ? TemplateBooleanModel.FALSE : TemplateBooleanModel.TRUE;
        }

        /* access modifiers changed from: package-private */
        public boolean evalToBoolean(Environment environment) throws TemplateException {
            return _eval(environment) == TemplateBooleanModel.TRUE;
        }
    }

    static class has_contentBI extends ExistenceBuiltIn {
        has_contentBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return Expression.isEmpty(evalMaybeNonexistentTarget(environment)) ? TemplateBooleanModel.FALSE : TemplateBooleanModel.TRUE;
        }

        /* access modifiers changed from: package-private */
        public boolean evalToBoolean(Environment environment) throws TemplateException {
            return _eval(environment) == TemplateBooleanModel.TRUE;
        }
    }

    static class if_existsBI extends ExistenceBuiltIn {
        if_existsBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel evalMaybeNonexistentTarget = evalMaybeNonexistentTarget(environment);
            return evalMaybeNonexistentTarget == null ? TemplateModel.NOTHING : evalMaybeNonexistentTarget;
        }
    }
}
