package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateModel;
import java.util.ArrayList;

final class MethodCall extends Expression {
    private final ListLiteral arguments;
    private final Expression target;

    /* access modifiers changed from: package-private */
    public TemplateModel getConstantValue() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "...(...)";
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    MethodCall(Expression expression, ArrayList arrayList) {
        this(expression, new ListLiteral(arrayList));
    }

    private MethodCall(Expression expression, ListLiteral listLiteral) {
        this.target = expression;
        this.arguments = listLiteral;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        throw new java.lang.InternalError("This should be impossible.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        r9.setOut(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0057 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel _eval(freemarker.core.Environment r9) throws freemarker.template.TemplateException {
        /*
            r8 = this;
            freemarker.core.Expression r0 = r8.target
            freemarker.template.TemplateModel r0 = r0.eval(r9)
            boolean r1 = r0 instanceof freemarker.template.TemplateMethodModel
            if (r1 == 0) goto L_0x002a
            freemarker.template.TemplateMethodModel r0 = (freemarker.template.TemplateMethodModel) r0
            boolean r1 = r0 instanceof freemarker.template.TemplateMethodModelEx
            if (r1 == 0) goto L_0x0017
            freemarker.core.ListLiteral r1 = r8.arguments
            java.util.List r1 = r1.getModelList(r9)
            goto L_0x001d
        L_0x0017:
            freemarker.core.ListLiteral r1 = r8.arguments
            java.util.List r1 = r1.getValueList(r9)
        L_0x001d:
            java.lang.Object r0 = r0.exec(r1)
            freemarker.template.ObjectWrapper r9 = r9.getObjectWrapper()
            freemarker.template.TemplateModel r9 = r9.wrap(r0)
            return r9
        L_0x002a:
            boolean r1 = r0 instanceof freemarker.core.Macro
            if (r1 == 0) goto L_0x006b
            r3 = r0
            freemarker.core.Macro r3 = (freemarker.core.Macro) r3
            r0 = 0
            r9.setLastReturnValue(r0)
            boolean r0 = r3.isFunction
            if (r0 == 0) goto L_0x0063
            java.io.Writer r0 = r9.getOut()
            freemarker.template.utility.NullWriter r1 = freemarker.template.utility.NullWriter.INSTANCE     // Catch:{ IOException -> 0x0057 }
            r9.setOut(r1)     // Catch:{ IOException -> 0x0057 }
            r4 = 0
            freemarker.core.ListLiteral r1 = r8.arguments     // Catch:{ IOException -> 0x0057 }
            java.util.ArrayList r5 = r1.items     // Catch:{ IOException -> 0x0057 }
            r6 = 0
            r7 = 0
            r2 = r9
            r2.visit(r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x0057 }
            r9.setOut(r0)
            freemarker.template.TemplateModel r9 = r9.getLastReturnValue()
            return r9
        L_0x0055:
            r1 = move-exception
            goto L_0x005f
        L_0x0057:
            java.lang.InternalError r1 = new java.lang.InternalError     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "This should be impossible."
            r1.<init>(r2)     // Catch:{ all -> 0x0055 }
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x005f:
            r9.setOut(r0)
            throw r1
        L_0x0063:
            freemarker.core._MiscTemplateException r0 = new freemarker.core._MiscTemplateException
            java.lang.String r1 = "A macro cannot be called in an expression."
            r0.<init>((freemarker.core.Environment) r9, (java.lang.String) r1)
            throw r0
        L_0x006b:
            freemarker.core.UnexpectedTypeException r1 = new freemarker.core.UnexpectedTypeException
            freemarker.core.Expression r2 = r8.target
            java.lang.String r3 = "method"
            r1.<init>(r2, r0, r3, r9)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.MethodCall._eval(freemarker.core.Environment):freemarker.template.TemplateModel");
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.target.getCanonicalForm());
        stringBuffer.append("(");
        String canonicalForm = this.arguments.getCanonicalForm();
        stringBuffer.append(canonicalForm.substring(1, canonicalForm.length() - 1));
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new MethodCall(this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), (ListLiteral) this.arguments.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return this.arguments.items.size() + 1;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.target;
        }
        if (i < getParameterCount()) {
            return this.arguments.items.get(i - 1);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CALLEE;
        }
        if (i < getParameterCount()) {
            return ParameterRole.ARGUMENT_VALUE;
        }
        throw new IndexOutOfBoundsException();
    }
}
