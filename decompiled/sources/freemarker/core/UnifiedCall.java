package freemarker.core;

import freemarker.template.EmptyMap;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

final class UnifiedCall extends TemplateElement {
    private List bodyParameterNames;
    boolean legacySyntax;
    private Expression nameExp;
    private Map namedArgs;
    private List positionalArgs;
    private volatile transient SoftReference sortedNamedArgsCache;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "@";
    }

    UnifiedCall(Expression expression, Map map, TemplateElement templateElement, List list) {
        this.nameExp = expression;
        this.namedArgs = map;
        this.nestedBlock = templateElement;
        this.bodyParameterNames = list;
    }

    UnifiedCall(Expression expression, List list, TemplateElement templateElement, List list2) {
        this.nameExp = expression;
        this.positionalArgs = list;
        this.nestedBlock = templateElement == TextBlock.EMPTY_BLOCK ? null : templateElement;
        this.bodyParameterNames = list2;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        Map map;
        TemplateModel eval = this.nameExp.eval(environment);
        if (eval != Macro.DO_NOTHING_MACRO) {
            if (eval instanceof Macro) {
                Macro macro = (Macro) eval;
                if (!macro.isFunction || this.legacySyntax) {
                    environment.visit(macro, this.namedArgs, this.positionalArgs, this.bodyParameterNames, this.nestedBlock);
                    return;
                }
                throw new _MiscTemplateException(environment, new Object[]{"Routine ", new _DelayedJQuote(macro.getName()), " is a function, not a directive. Functions can only be called from expressions, like in ${f()}, ${x + f()} or ", "<@someDirective someParam=f() />", "."});
            }
            boolean z = eval instanceof TemplateDirectiveModel;
            if (z || (eval instanceof TemplateTransformModel)) {
                Map map2 = this.namedArgs;
                if (map2 == null || map2.isEmpty()) {
                    map = EmptyMap.instance;
                } else {
                    map = new HashMap();
                    for (Map.Entry entry : this.namedArgs.entrySet()) {
                        map.put((String) entry.getKey(), ((Expression) entry.getValue()).eval(environment));
                    }
                }
                if (z) {
                    environment.visit(this.nestedBlock, (TemplateDirectiveModel) eval, map, this.bodyParameterNames);
                } else {
                    environment.visitAndTransform(this.nestedBlock, (TemplateTransformModel) eval, map);
                }
            } else if (eval == null) {
                throw InvalidReferenceException.getInstance(this.nameExp, environment);
            } else {
                throw new UnexpectedTypeException(this.nameExp, eval, "user-defined directive (macro, etc.)", environment);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append('@');
        MessageUtil.appendExpressionAsUntearable(stringBuffer, this.nameExp);
        boolean z2 = true;
        int i = 0;
        if (stringBuffer.charAt(stringBuffer.length() - 1) != ')') {
            z2 = false;
        }
        if (this.positionalArgs != null) {
            while (i < this.positionalArgs.size()) {
                Expression expression = (Expression) this.positionalArgs.get(i);
                if (i != 0) {
                    stringBuffer.append(',');
                }
                stringBuffer.append(' ');
                stringBuffer.append(expression.getCanonicalForm());
                i++;
            }
        } else {
            List sortedNamedArgs = getSortedNamedArgs();
            while (i < sortedNamedArgs.size()) {
                Map.Entry entry = (Map.Entry) sortedNamedArgs.get(i);
                stringBuffer.append(' ');
                stringBuffer.append(entry.getKey());
                stringBuffer.append('=');
                MessageUtil.appendExpressionAsUntearable(stringBuffer, (Expression) entry.getValue());
                i++;
            }
        }
        if (z) {
            if (this.nestedBlock == null) {
                stringBuffer.append("/>");
            } else {
                stringBuffer.append(Typography.greater);
                stringBuffer.append(this.nestedBlock.getCanonicalForm());
                stringBuffer.append("</@");
                if (!z2) {
                    Expression expression2 = this.nameExp;
                    if ((expression2 instanceof Identifier) || ((expression2 instanceof Dot) && ((Dot) expression2).onlyHasIdentifiers())) {
                        stringBuffer.append(this.nameExp.getCanonicalForm());
                    }
                }
                stringBuffer.append(Typography.greater);
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        List list = this.positionalArgs;
        int i = 0;
        int size = (list != null ? list.size() : 0) + 1;
        Map map = this.namedArgs;
        int size2 = size + (map != null ? map.size() * 2 : 0);
        List list2 = this.bodyParameterNames;
        if (list2 != null) {
            i = list2.size();
        }
        return size2 + i;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.nameExp;
        }
        List list = this.positionalArgs;
        int i2 = 0;
        int size = list != null ? list.size() : 0;
        int i3 = i - 1;
        if (i3 < size) {
            return this.positionalArgs.get(i3);
        }
        int i4 = size + 1;
        Map map = this.namedArgs;
        int i5 = i - i4;
        int size2 = (map != null ? map.size() : 0) * 2;
        if (i5 < size2) {
            Map.Entry entry = (Map.Entry) getSortedNamedArgs().get(i5 / 2);
            return i5 % 2 == 0 ? entry.getKey() : entry.getValue();
        }
        int i6 = i4 + size2;
        List list2 = this.bodyParameterNames;
        if (list2 != null) {
            i2 = list2.size();
        }
        int i7 = i - i6;
        if (i7 < i2) {
            return this.bodyParameterNames.get(i7);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CALLEE;
        }
        List list = this.positionalArgs;
        int i2 = 0;
        int size = list != null ? list.size() : 0;
        if (i - 1 < size) {
            return ParameterRole.ARGUMENT_VALUE;
        }
        int i3 = size + 1;
        Map map = this.namedArgs;
        int i4 = i - i3;
        int size2 = (map != null ? map.size() : 0) * 2;
        if (i4 < size2) {
            return i4 % 2 == 0 ? ParameterRole.ARGUMENT_NAME : ParameterRole.ARGUMENT_VALUE;
        }
        int i5 = i3 + size2;
        List list2 = this.bodyParameterNames;
        if (list2 != null) {
            i2 = list2.size();
        }
        if (i - i5 < i2) {
            return ParameterRole.TARGET_LOOP_VARIABLE;
        }
        throw new IndexOutOfBoundsException();
    }

    private List getSortedNamedArgs() {
        List list;
        SoftReference softReference = this.sortedNamedArgsCache;
        if (softReference != null && (list = (List) softReference.get()) != null) {
            return list;
        }
        List sortMapOfExpressions = MiscUtil.sortMapOfExpressions(this.namedArgs);
        this.sortedNamedArgsCache = new SoftReference(sortMapOfExpressions);
        return sortMapOfExpressions;
    }
}
