package freemarker.core;

import freemarker.cache.TemplateCache;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.StringUtil;
import java.io.IOException;
import kotlin.text.Typography;

final class Include extends TemplateElement {
    private String encoding;
    private Expression encodingExp;
    private boolean parse;
    private Expression parseExp;
    private Expression templateName;
    private final String templatePath;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#include";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 3;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:18|19|20|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x005b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Include(freemarker.template.Template r5, freemarker.core.Expression r6, freemarker.core.Expression r7, freemarker.core.Expression r8) throws freemarker.core.ParseException {
        /*
            r4 = this;
            r4.<init>()
            java.lang.String r5 = r5.getName()
            java.lang.String r0 = ""
            if (r5 != 0) goto L_0x000c
            r5 = r0
        L_0x000c:
            r1 = 47
            int r1 = r5.lastIndexOf(r1)
            r2 = -1
            r3 = 1
            if (r1 != r2) goto L_0x0017
            goto L_0x001d
        L_0x0017:
            r0 = 0
            int r1 = r1 + r3
            java.lang.String r0 = r5.substring(r0, r1)
        L_0x001d:
            r4.templatePath = r0
            r4.templateName = r6
            boolean r5 = r7 instanceof freemarker.core.StringLiteral
            if (r5 == 0) goto L_0x0037
            java.lang.String r5 = r7.toString()
            r4.encoding = r5
            int r6 = r5.length()
            int r6 = r6 - r3
            java.lang.String r5 = r5.substring(r3, r6)
            r4.encoding = r5
            goto L_0x0039
        L_0x0037:
            r4.encodingExp = r7
        L_0x0039:
            if (r8 != 0) goto L_0x003e
            r4.parse = r3
            goto L_0x006c
        L_0x003e:
            boolean r5 = r8.isLiteral()
            if (r5 == 0) goto L_0x006a
            boolean r5 = r8 instanceof freemarker.core.StringLiteral     // Catch:{ TemplateException -> 0x0063 }
            r6 = 0
            if (r5 == 0) goto L_0x0054
            java.lang.String r5 = r8.evalAndCoerceToString(r6)     // Catch:{ TemplateException -> 0x0063 }
            boolean r5 = freemarker.template.utility.StringUtil.getYesNo(r5)     // Catch:{ TemplateException -> 0x0063 }
            r4.parse = r5     // Catch:{ TemplateException -> 0x0063 }
            goto L_0x006c
        L_0x0054:
            boolean r5 = r8.evalToBoolean(r6)     // Catch:{ NonBooleanException -> 0x005b }
            r4.parse = r5     // Catch:{ NonBooleanException -> 0x005b }
            goto L_0x006c
        L_0x005b:
            freemarker.core.ParseException r5 = new freemarker.core.ParseException     // Catch:{ TemplateException -> 0x0063 }
            java.lang.String r6 = "Expected a boolean or string as the value of the parse attribute"
            r5.<init>(r6, r8)     // Catch:{ TemplateException -> 0x0063 }
            throw r5     // Catch:{ TemplateException -> 0x0063 }
        L_0x0063:
            r5 = move-exception
            freemarker.template.utility.UndeclaredThrowableException r6 = new freemarker.template.utility.UndeclaredThrowableException
            r6.<init>(r5)
            throw r6
        L_0x006a:
            r4.parseExp = r8
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.Include.<init>(freemarker.template.Template, freemarker.core.Expression, freemarker.core.Expression, freemarker.core.Expression):void");
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        Expression expression;
        String evalAndCoerceToString = this.templateName.evalAndCoerceToString(environment);
        String str = this.encoding;
        if (str == null && (expression = this.encodingExp) != null) {
            str = expression.evalAndCoerceToString(environment);
        }
        boolean z = this.parse;
        Expression expression2 = this.parseExp;
        if (expression2 != null) {
            TemplateModel eval = expression2.eval(environment);
            if (eval == null && !environment.isClassicCompatible()) {
                this.parseExp.assertNonNull(eval, environment);
            }
            if (eval instanceof TemplateScalarModel) {
                z = getYesNo(EvalUtil.modelToString((TemplateScalarModel) eval, this.parseExp, environment));
            } else {
                z = this.parseExp.evalToBoolean(environment);
            }
        }
        try {
            environment.include(environment.getTemplateForInclusion(TemplateCache.getFullTemplatePath(environment, this.templatePath, evalAndCoerceToString), str, z));
        } catch (ParseException e) {
            throw new _MiscTemplateException((Throwable) e, environment, new Object[]{"Error parsing included template ", new _DelayedJQuote(evalAndCoerceToString), ":\n", new _DelayedGetMessage(e)});
        } catch (IOException e2) {
            throw new _MiscTemplateException((Throwable) e2, environment, new Object[]{"Error reading included file ", new _DelayedJQuote(evalAndCoerceToString), ":\n", new _DelayedGetMessage(e2)});
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.templateName.getCanonicalForm());
        if (this.encoding != null) {
            stringBuffer.append(" encoding=\"");
            stringBuffer.append(this.encodingExp.getCanonicalForm());
            stringBuffer.append(Typography.quote);
        }
        if (this.parseExp != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(" parse=");
            stringBuffer2.append(this.parseExp.getCanonicalForm());
            stringBuffer.append(stringBuffer2.toString());
        } else if (!this.parse) {
            stringBuffer.append(" parse=false");
        }
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    private boolean getYesNo(String str) throws TemplateException {
        try {
            return StringUtil.getYesNo(str);
        } catch (IllegalArgumentException unused) {
            throw new _MiscTemplateException(this.parseExp, new Object[]{"Value of include parse parameter must be boolean (or one of these strings: \"n\", \"no\", \"f\", \"false\", \"y\", \"yes\", \"t\", \"true\"), but it was ", new _DelayedJQuote(str), "."});
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.templateName;
        }
        if (i == 1) {
            return new Boolean(this.parse);
        }
        if (i == 2) {
            return this.encoding;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.TEMPLATE_NAME;
        }
        if (i == 1) {
            return ParameterRole.PARSE_PARAMETER;
        }
        if (i == 2) {
            return ParameterRole.ENCODING_PARAMETER;
        }
        throw new IndexOutOfBoundsException();
    }
}
