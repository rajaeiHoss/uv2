package freemarker.template.utility;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;

public class Constants {
    public static final TemplateCollectionModel EMPTY_COLLECTION = new TemplateCollectionModel() {
        public TemplateModelIterator iterator() throws TemplateModelException {
            return Constants.EMPTY_ITERATOR;
        }
    };
    public static final TemplateHashModelEx EMPTY_HASH = new TemplateHashModelEx() {
        public TemplateModel get(String str) throws TemplateModelException {
            return null;
        }

        public boolean isEmpty() throws TemplateModelException {
            return true;
        }

        public int size() throws TemplateModelException {
            return 0;
        }

        public TemplateCollectionModel keys() throws TemplateModelException {
            return Constants.EMPTY_COLLECTION;
        }

        public TemplateCollectionModel values() throws TemplateModelException {
            return Constants.EMPTY_COLLECTION;
        }
    };
    public static final TemplateModelIterator EMPTY_ITERATOR = new TemplateModelIterator() {
        public boolean hasNext() throws TemplateModelException {
            return false;
        }

        public TemplateModel next() throws TemplateModelException {
            throw new TemplateModelException("The collection has no more elements.");
        }
    };
    public static final TemplateSequenceModel EMPTY_SEQUENCE = new TemplateSequenceModel() {
        public TemplateModel get(int i) throws TemplateModelException {
            return null;
        }

        public int size() throws TemplateModelException {
            return 0;
        }
    };
    public static final TemplateScalarModel EMPTY_STRING = ((TemplateScalarModel) TemplateScalarModel.EMPTY_STRING);
    public static final TemplateBooleanModel FALSE = TemplateBooleanModel.FALSE;
    public static final TemplateNumberModel MINUS_ONE = new SimpleNumber(-1);
    public static final TemplateNumberModel ONE = new SimpleNumber(1);
    public static final TemplateBooleanModel TRUE = TemplateBooleanModel.TRUE;
    public static final TemplateNumberModel ZERO = new SimpleNumber(0);
}
