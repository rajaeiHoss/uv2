package freemarker.core;

import freemarker.template.EmptyMap;
import freemarker.template.TemplateException;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

final class TransformBlock extends TemplateElement {
    Map namedArgs;
    private volatile transient SoftReference sortedNamedArgsCache;
    private Expression transformExpression;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#transform";
    }

    TransformBlock(Expression expression, Map map, TemplateElement templateElement) {
        this.transformExpression = expression;
        this.namedArgs = map;
        this.nestedBlock = templateElement;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        Map map;
        TemplateTransformModel transform = environment.getTransform(this.transformExpression);
        if (transform != null) {
            Map map2 = this.namedArgs;
            if (map2 == null || map2.isEmpty()) {
                map = EmptyMap.instance;
            } else {
                map = new HashMap();
                for (Map.Entry entry : this.namedArgs.entrySet()) {
                    map.put((String) entry.getKey(), ((Expression) entry.getValue()).eval(environment));
                }
            }
            environment.visitAndTransform(this.nestedBlock, transform, map);
            return;
        }
        throw new UnexpectedTypeException(this.transformExpression, this.transformExpression.eval(environment), "transform", environment);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.transformExpression);
        if (this.namedArgs != null) {
            for (Map.Entry entry : getSortedNamedArgs()) {
                stringBuffer.append(' ');
                stringBuffer.append(entry.getKey());
                stringBuffer.append('=');
                MessageUtil.appendExpressionAsUntearable(stringBuffer, (Expression) entry.getValue());
            }
        }
        if (z) {
            stringBuffer.append(">");
            if (this.nestedBlock != null) {
                stringBuffer.append(this.nestedBlock.getCanonicalForm());
            }
            stringBuffer.append("</");
            stringBuffer.append(getNodeTypeSymbol());
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        Map map = this.namedArgs;
        return (map != null ? map.size() * 2 : 0) + 1;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        int i2;
        if (i == 0) {
            return this.transformExpression;
        }
        Map map = this.namedArgs;
        if (map == null || i - 1 >= map.size() * 2) {
            throw new IndexOutOfBoundsException();
        }
        Map.Entry entry = (Map.Entry) getSortedNamedArgs().get(i2 / 2);
        return i2 % 2 == 0 ? entry.getKey() : entry.getValue();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CALLEE;
        }
        int i2 = i - 1;
        if (i2 < this.namedArgs.size() * 2) {
            return i2 % 2 == 0 ? ParameterRole.ARGUMENT_NAME : ParameterRole.ARGUMENT_VALUE;
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
