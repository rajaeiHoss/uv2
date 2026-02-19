package freemarker.core;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;

class HashBuiltins {
    private HashBuiltins() {
    }

    private static abstract class HashExBuiltin extends BuiltIn {
        /* access modifiers changed from: package-private */
        public abstract TemplateModel calculateResult(TemplateHashModelEx templateHashModelEx, Environment environment) throws TemplateModelException, InvalidReferenceException;

        private HashExBuiltin() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateHashModelEx) {
                return calculateResult((TemplateHashModelEx) eval, environment);
            }
            throw new UnexpectedTypeException(this.target, eval, "extended hash", environment);
        }

        /* access modifiers changed from: protected */
        public InvalidReferenceException newNullPropertyException(String str, TemplateModel templateModel, Environment environment) {
            if (environment.getFastInvalidReferenceExceptions()) {
                return InvalidReferenceException.FAST_INSTANCE;
            }
            return new InvalidReferenceException(new _ErrorDescriptionBuilder(new Object[]{"The exteneded hash (of class ", templateModel.getClass().getName(), ") has returned null for its \"", str, "\" property. This is maybe a bug. The extended hash was returned by this expression:"}).blame(this.target), environment);
        }
    }

    static class keysBI extends HashExBuiltin {
        keysBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateHashModelEx templateHashModelEx, Environment environment) throws TemplateModelException, InvalidReferenceException {
            TemplateCollectionModel keys = templateHashModelEx.keys();
            if (keys != null) {
                return keys instanceof TemplateSequenceModel ? keys : new CollectionAndSequence(keys);
            }
            throw newNullPropertyException("keys", templateHashModelEx, environment);
        }
    }

    static class valuesBI extends HashExBuiltin {
        valuesBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateHashModelEx templateHashModelEx, Environment environment) throws TemplateModelException, InvalidReferenceException {
            TemplateCollectionModel values = templateHashModelEx.values();
            if (values != null) {
                return values instanceof TemplateSequenceModel ? values : new CollectionAndSequence(values);
            }
            throw newNullPropertyException("values", templateHashModelEx, environment);
        }
    }
}
