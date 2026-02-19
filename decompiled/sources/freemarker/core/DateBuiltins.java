package freemarker.core;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.utility.DateUtil;
import java.util.Date;

class DateBuiltins {
    static /* synthetic */ Class class$java$util$TimeZone;

    private DateBuiltins() {
    }

    private static abstract class DateBuiltin extends BuiltIn {
        /* access modifiers changed from: protected */
        public abstract TemplateModel calculateResult(Date date, int i, Environment environment) throws TemplateException;

        private DateBuiltin() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateDateModel) {
                TemplateDateModel templateDateModel = (TemplateDateModel) eval;
                return calculateResult(EvalUtil.modelToDate(templateDateModel, this.target), templateDateModel.getDateType(), environment);
            } else if (eval == null) {
                throw InvalidReferenceException.getInstance(this.target, environment);
            } else {
                throw new NonDateException(this.target, eval, "date", environment);
            }
        }
    }

    static abstract class AbstractISOBI extends DateBuiltin {
        protected final int accuracy;
        protected final boolean showOffset;

        protected AbstractISOBI(boolean z, int i) {
            super();
            this.showOffset = z;
            this.accuracy = i;
        }

        /* access modifiers changed from: protected */
        public void checkDateTypeNotUnknown(int i) throws TemplateException {
            if (i == 0) {
                throw new _MiscTemplateException(new _ErrorDescriptionBuilder(new Object[]{"The value of the following has unknown date type, but ?", this.key, " needs a date value where it's known if it's a date-only, time-only, or date+time value:"}).blame(this.target).tips((Object[]) MessageUtil.UNKNOWN_DATE_TYPE_ERROR_TIPS));
            }
        }
    }

    static class iso_tz_BI extends AbstractISOBI {
        private final boolean useUTC;

        iso_tz_BI(boolean z, int i, boolean z2) {
            super(z, i);
            this.useUTC = z2;
        }

        /* access modifiers changed from: protected */
        public TemplateModel calculateResult(Date date, int i, Environment environment) throws TemplateException {
            checkDateTypeNotUnknown(i);
            return new SimpleScalar(DateUtil.dateToISO8601String(date, i != 1, i != 2, this.showOffset && i != 2, this.accuracy, this.useUTC ? DateUtil.UTC : environment.getTimeZone(), environment.getISOBuiltInCalendar()));
        }
    }

    static class iso_BI extends AbstractISOBI {
        iso_BI(boolean z, int i) {
            super(z, i);
        }

        /* access modifiers changed from: protected */
        public TemplateModel calculateResult(Date date, int i, Environment environment) throws TemplateException {
            checkDateTypeNotUnknown(i);
            return new Result(date, i, environment);
        }

        class Result implements TemplateMethodModelEx {
            private final Date date;
            private final int dateType;
            private final Environment env;

            Result(Date date2, int i, Environment environment) {
                this.date = date2;
                this.dateType = i;
                this.env = environment;
            }

            /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x004a  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x004d  */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object exec(java.util.List r12) throws freemarker.template.TemplateModelException {
                /*
                    r11 = this;
                    freemarker.core.DateBuiltins$iso_BI r0 = freemarker.core.DateBuiltins.iso_BI.this
                    r1 = 1
                    r0.checkMethodArgCount((java.util.List) r12, (int) r1)
                    r0 = 0
                    java.lang.Object r12 = r12.get(r0)
                    freemarker.template.TemplateModel r12 = (freemarker.template.TemplateModel) r12
                    boolean r2 = r12 instanceof freemarker.template.AdapterTemplateModel
                    r3 = 2
                    if (r2 == 0) goto L_0x0030
                    r2 = r12
                    freemarker.template.AdapterTemplateModel r2 = (freemarker.template.AdapterTemplateModel) r2
                    java.lang.Class r4 = freemarker.core.DateBuiltins.class$java$util$TimeZone
                    if (r4 != 0) goto L_0x0022
                    java.lang.String r4 = "java.util.TimeZone"
                    java.lang.Class r4 = freemarker.core.DateBuiltins.class$(r4)
                    freemarker.core.DateBuiltins.class$java$util$TimeZone = r4
                    goto L_0x0024
                L_0x0022:
                    java.lang.Class r4 = freemarker.core.DateBuiltins.class$java$util$TimeZone
                L_0x0024:
                    java.lang.Object r2 = r2.getAdaptedObject(r4)
                    boolean r4 = r2 instanceof java.util.TimeZone
                    if (r4 == 0) goto L_0x0030
                    java.util.TimeZone r2 = (java.util.TimeZone) r2
                L_0x002e:
                    r9 = r2
                    goto L_0x0040
                L_0x0030:
                    boolean r2 = r12 instanceof freemarker.template.TemplateScalarModel
                    if (r2 == 0) goto L_0x008e
                    freemarker.template.TemplateScalarModel r12 = (freemarker.template.TemplateScalarModel) r12
                    r2 = 0
                    java.lang.String r12 = freemarker.core.EvalUtil.modelToString(r12, r2, r2)
                    java.util.TimeZone r2 = freemarker.template.utility.DateUtil.getTimeZone(r12)     // Catch:{ UnrecognizedTimeZoneException -> 0x006f }
                    goto L_0x002e
                L_0x0040:
                    freemarker.template.SimpleScalar r12 = new freemarker.template.SimpleScalar
                    java.util.Date r4 = r11.date
                    int r2 = r11.dateType
                    if (r2 == r1) goto L_0x004a
                    r5 = 1
                    goto L_0x004b
                L_0x004a:
                    r5 = 0
                L_0x004b:
                    if (r2 == r3) goto L_0x004f
                    r6 = 1
                    goto L_0x0050
                L_0x004f:
                    r6 = 0
                L_0x0050:
                    freemarker.core.DateBuiltins$iso_BI r2 = freemarker.core.DateBuiltins.iso_BI.this
                    boolean r2 = r2.showOffset
                    if (r2 == 0) goto L_0x005c
                    int r2 = r11.dateType
                    if (r2 == r3) goto L_0x005c
                    r7 = 1
                    goto L_0x005d
                L_0x005c:
                    r7 = 0
                L_0x005d:
                    freemarker.core.DateBuiltins$iso_BI r0 = freemarker.core.DateBuiltins.iso_BI.this
                    int r8 = r0.accuracy
                    freemarker.core.Environment r0 = r11.env
                    freemarker.template.utility.DateUtil$DateToISO8601CalendarFactory r10 = r0.getISOBuiltInCalendar()
                    java.lang.String r0 = freemarker.template.utility.DateUtil.dateToISO8601String(r4, r5, r6, r7, r8, r9, r10)
                    r12.<init>(r0)
                    return r12
                L_0x006f:
                    freemarker.core._TemplateModelException r2 = new freemarker.core._TemplateModelException
                    r4 = 4
                    java.lang.Object[] r4 = new java.lang.Object[r4]
                    java.lang.String r5 = "The time zone string specified for ?"
                    r4[r0] = r5
                    freemarker.core.DateBuiltins$iso_BI r0 = freemarker.core.DateBuiltins.iso_BI.this
                    java.lang.String r0 = r0.key
                    r4[r1] = r0
                    java.lang.String r0 = "(...) is not recognized as a valid time zone name: "
                    r4[r3] = r0
                    r0 = 3
                    freemarker.core._DelayedJQuote r1 = new freemarker.core._DelayedJQuote
                    r1.<init>(r12)
                    r4[r0] = r1
                    r2.<init>((java.lang.Object[]) r4)
                    throw r2
                L_0x008e:
                    java.lang.StringBuffer r1 = new java.lang.StringBuffer
                    r1.<init>()
                    java.lang.String r2 = "?"
                    r1.append(r2)
                    freemarker.core.DateBuiltins$iso_BI r2 = freemarker.core.DateBuiltins.iso_BI.this
                    java.lang.String r2 = r2.key
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    java.lang.String r2 = "string or java.util.TimeZone"
                    freemarker.template.TemplateModelException r12 = freemarker.core.MessageUtil.newMethodArgUnexpectedTypeException(r1, r0, r2, r12)
                    throw r12
                */
                throw new UnsupportedOperationException("Method not decompiled: freemarker.core.DateBuiltins.iso_BI.Result.exec(java.util.List):java.lang.Object");
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
