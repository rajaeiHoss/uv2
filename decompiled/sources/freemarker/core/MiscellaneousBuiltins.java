package freemarker.core;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans._BeansAPI;
import freemarker.template.SimpleDate;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.TemplateTransformModel;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class MiscellaneousBuiltins {
    private MiscellaneousBuiltins() {
    }

    static class sizeBI extends BuiltIn {
        sizeBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateSequenceModel) {
                return new SimpleNumber(((TemplateSequenceModel) eval).size());
            }
            if (eval instanceof TemplateHashModelEx) {
                return new SimpleNumber(((TemplateHashModelEx) eval).size());
            }
            throw new UnexpectedTypeException(this.target, eval, "extended-hash or sequence", environment);
        }
    }

    static class dateBI extends BuiltIn {
        /* access modifiers changed from: private */
        public final int dateType;

        dateBI(int i) {
            this.dateType = i;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (!(eval instanceof TemplateDateModel)) {
                return new DateParser(this.target.evalAndCoerceToString(environment), environment);
            }
            TemplateDateModel templateDateModel = (TemplateDateModel) eval;
            int dateType2 = templateDateModel.getDateType();
            if (this.dateType == dateType2) {
                return eval;
            }
            if (dateType2 == 0 || dateType2 == 3) {
                return new SimpleDate(templateDateModel.getAsDate(), this.dateType);
            }
            throw new _MiscTemplateException((Expression) this, new Object[]{"Cannot convert ", TemplateDateModel.TYPE_NAMES.get(dateType2), " into ", TemplateDateModel.TYPE_NAMES.get(this.dateType)});
        }

        private class DateParser implements TemplateDateModel, TemplateMethodModel, TemplateHashModel {
            private Date cachedValue;
            private final DateFormat defaultFormat;
            private final Environment env;
            private final String text;

            public boolean isEmpty() {
                return false;
            }

            DateParser(String str, Environment environment) throws TemplateModelException {
                this.text = str;
                this.env = environment;
                this.defaultFormat = environment.getDateFormatObject(dateBI.this.dateType);
            }

            public Date getAsDate() throws TemplateModelException {
                if (this.cachedValue == null) {
                    this.cachedValue = parse(this.defaultFormat);
                }
                return this.cachedValue;
            }

            public int getDateType() {
                return dateBI.this.dateType;
            }

            public TemplateModel get(String str) throws TemplateModelException {
                return new SimpleDate(parse(this.env.getDateFormatObject(dateBI.this.dateType, str)), dateBI.this.dateType);
            }

            public Object exec(List list) throws TemplateModelException {
                dateBI.this.checkMethodArgCount(list, 1);
                return get((String) list.get(0));
            }

            private Date parse(DateFormat dateFormat) throws TemplateModelException {
                try {
                    return dateFormat.parse(this.text);
                } catch (ParseException unused) {
                    String str = null;
                    if (dateFormat instanceof SimpleDateFormat) {
                        str = ((SimpleDateFormat) dateFormat).toPattern();
                    }
                    Object[] objArr = new Object[6];
                    objArr[0] = "The string doesn't match the expected date/time format. The string to parse was: ";
                    objArr[1] = new _DelayedJQuote(this.text);
                    String str2 = ". ";
                    objArr[2] = str2;
                    objArr[3] = str != null ? "The expected format was: " : "";
                    objArr[4] = str != null ? new _DelayedJQuote(str) : "";
                    if (str == null) {
                        str2 = "";
                    }
                    objArr[5] = str2;
                    throw new _TemplateModelException(objArr);
                }
            }
        }
    }

    static class stringBI extends BuiltIn {
        stringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateNumberModel) {
                return new NumberFormatter(EvalUtil.modelToNumber((TemplateNumberModel) eval, this.target), environment);
            }
            if (eval instanceof TemplateDateModel) {
                TemplateDateModel templateDateModel = (TemplateDateModel) eval;
                return new DateFormatter(EvalUtil.modelToDate(templateDateModel, this.target), templateDateModel.getDateType(), environment);
            } else if (eval instanceof SimpleScalar) {
                return eval;
            } else {
                if (eval instanceof TemplateBooleanModel) {
                    return new BooleanFormatter((TemplateBooleanModel) eval, environment);
                }
                if (eval instanceof TemplateScalarModel) {
                    return new SimpleScalar(((TemplateScalarModel) eval).getAsString());
                }
                if (environment.isClassicCompatible() && (eval instanceof BeanModel)) {
                    return new SimpleScalar(_BeansAPI.getAsClassicCompatibleString((BeanModel) eval));
                }
                throw new UnexpectedTypeException(this.target, eval, "number, date, or string", environment);
            }
        }

        private class NumberFormatter implements TemplateScalarModel, TemplateHashModel, TemplateMethodModel {
            private String cachedValue;
            private final NumberFormat defaultFormat;
            private final Environment env;
            private final Number number;

            public boolean isEmpty() {
                return false;
            }

            NumberFormatter(Number number2, Environment environment) {
                this.number = number2;
                this.env = environment;
                this.defaultFormat = environment.getNumberFormatObject(environment.getNumberFormat());
            }

            public String getAsString() {
                if (this.cachedValue == null) {
                    this.cachedValue = this.defaultFormat.format(this.number);
                }
                return this.cachedValue;
            }

            public TemplateModel get(String str) {
                return new SimpleScalar(this.env.getNumberFormatObject(str).format(this.number));
            }

            public Object exec(List list) throws TemplateModelException {
                stringBI.this.checkMethodArgCount(list, 1);
                return get((String) list.get(0));
            }
        }

        private class DateFormatter implements TemplateScalarModel, TemplateHashModel, TemplateMethodModel {
            private String cachedValue;
            private final Date date;
            private final int dateType;
            private final DateFormat defaultFormat;
            private final Environment env;

            public boolean isEmpty() {
                return false;
            }

            DateFormatter(Date date2, int i, Environment environment) throws TemplateModelException {
                this.date = date2;
                this.dateType = i;
                this.env = environment;
                this.defaultFormat = environment.getDateFormatObject(i);
            }

            public String getAsString() throws TemplateModelException {
                if (this.dateType != 0) {
                    if (this.cachedValue == null) {
                        this.cachedValue = this.defaultFormat.format(this.date);
                    }
                    return this.cachedValue;
                }
                throw new _TemplateModelException(new _ErrorDescriptionBuilder("Can't convert the date to string, because it isn't known if it's a date-only, time-only, or date-time value.").tip((Object[]) MessageUtil.UNKNOWN_DATE_TO_STRING_TIPS));
            }

            public TemplateModel get(String str) throws TemplateModelException {
                return new SimpleScalar(this.env.getDateFormatObject(this.dateType, str).format(this.date));
            }

            public Object exec(List list) throws TemplateModelException {
                stringBI.this.checkMethodArgCount(list, 1);
                return get((String) list.get(0));
            }
        }

        private class BooleanFormatter implements TemplateScalarModel, TemplateMethodModel {
            private final TemplateBooleanModel bool;
            private final Environment env;

            BooleanFormatter(TemplateBooleanModel templateBooleanModel, Environment environment) {
                this.bool = templateBooleanModel;
                this.env = environment;
            }

            public String getAsString() throws TemplateModelException {
                TemplateBooleanModel templateBooleanModel = this.bool;
                if (templateBooleanModel instanceof TemplateScalarModel) {
                    return ((TemplateScalarModel) templateBooleanModel).getAsString();
                }
                try {
                    return this.env.formatBoolean(templateBooleanModel.getAsBoolean(), true);
                } catch (TemplateException e) {
                    throw new TemplateModelException((Exception) e);
                }
            }

            public Object exec(List list) throws TemplateModelException {
                stringBI.this.checkMethodArgCount(list, 2);
                return new SimpleScalar((String) list.get(this.bool.getAsBoolean() ^ true ? 1 : 0));
            }
        }
    }

    static class is_stringBI extends BuiltIn {
        is_stringBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateScalarModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_numberBI extends BuiltIn {
        is_numberBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateNumberModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_nodeBI extends BuiltIn {
        is_nodeBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateNodeModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_booleanBI extends BuiltIn {
        is_booleanBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateBooleanModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_dateBI extends BuiltIn {
        is_dateBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateDateModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_methodBI extends BuiltIn {
        is_methodBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateMethodModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_macroBI extends BuiltIn {
        is_macroBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof Macro ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_transformBI extends BuiltIn {
        is_transformBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateTransformModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_hashBI extends BuiltIn {
        is_hashBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateHashModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_hash_exBI extends BuiltIn {
        is_hash_exBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateHashModelEx ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_sequenceBI extends BuiltIn {
        is_sequenceBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateSequenceModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_collectionBI extends BuiltIn {
        is_collectionBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateCollectionModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_indexableBI extends BuiltIn {
        is_indexableBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return eval instanceof TemplateSequenceModel ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_enumerableBI extends BuiltIn {
        is_enumerableBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return ((eval instanceof TemplateSequenceModel) || (eval instanceof TemplateCollectionModel)) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_directiveBI extends BuiltIn {
        is_directiveBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            this.target.assertNonNull(eval, environment);
            return ((eval instanceof TemplateTransformModel) || (eval instanceof Macro) || (eval instanceof TemplateDirectiveModel)) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class namespaceBI extends BuiltIn {
        namespaceBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof Macro) {
                return environment.getMacroNamespace((Macro) eval);
            }
            throw new UnexpectedTypeException(this.target, eval, "macro or function", environment);
        }
    }

    static class cBI extends BuiltIn {
        cBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateNumberModel) {
                Number modelToNumber = EvalUtil.modelToNumber((TemplateNumberModel) eval, this.target);
                if ((modelToNumber instanceof Integer) || (modelToNumber instanceof Long)) {
                    return new SimpleScalar(modelToNumber.toString());
                }
                return new SimpleScalar(environment.getCNumberFormat().format(modelToNumber));
            } else if (eval instanceof TemplateBooleanModel) {
                return new SimpleScalar(((TemplateBooleanModel) eval).getAsBoolean() ? "true" : "false");
            } else {
                throw new UnexpectedTypeException(this.target, eval, "number or boolean", environment);
            }
        }
    }
}
