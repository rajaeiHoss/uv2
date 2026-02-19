package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.Collections12;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

final class ListLiteral extends Expression {
    final ArrayList items;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "[...]";
    }

    ListLiteral(ArrayList arrayList) {
        this.items = arrayList;
        arrayList.trimToSize();
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        SimpleSequence simpleSequence = new SimpleSequence(this.items.size());
        Iterator it = this.items.iterator();
        while (it.hasNext()) {
            Expression expression = (Expression) it.next();
            TemplateModel eval = expression.eval(environment);
            if (environment == null || !environment.isClassicCompatible()) {
                expression.assertNonNull(eval, environment);
            }
            simpleSequence.add((Object) eval);
        }
        return simpleSequence;
    }

    /* access modifiers changed from: package-private */
    public List getValueList(Environment environment) throws TemplateException {
        int size = this.items.size();
        if (size == 0) {
            return Collections.EMPTY_LIST;
        }
        if (size == 1) {
            return Collections12.singletonList(((Expression) this.items.get(0)).evalAndCoerceToString(environment));
        }
        ArrayList arrayList = new ArrayList(this.items.size());
        ListIterator listIterator = this.items.listIterator();
        while (listIterator.hasNext()) {
            arrayList.add(((Expression) listIterator.next()).evalAndCoerceToString(environment));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List getModelList(Environment environment) throws TemplateException {
        int size = this.items.size();
        if (size == 0) {
            return Collections.EMPTY_LIST;
        }
        if (size == 1) {
            return Collections12.singletonList(((Expression) this.items.get(0)).eval(environment));
        }
        ArrayList arrayList = new ArrayList(this.items.size());
        ListIterator listIterator = this.items.listIterator();
        while (listIterator.hasNext()) {
            arrayList.add(((Expression) listIterator.next()).eval(environment));
        }
        return arrayList;
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer("[");
        int size = this.items.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(((Expression) this.items.get(i)).getCanonicalForm());
            if (i != size - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        if (this.constantValue != null) {
            return true;
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (!((Expression) this.items.get(i)).isLiteral()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public TemplateSequenceModel evaluateStringsToNamespaces(Environment environment) throws TemplateException {
        TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) eval(environment);
        SimpleSequence simpleSequence = new SimpleSequence(templateSequenceModel.size());
        for (int i = 0; i < this.items.size(); i++) {
            Object obj = this.items.get(i);
            if (obj instanceof StringLiteral) {
                StringLiteral stringLiteral = (StringLiteral) obj;
                String asString = stringLiteral.getAsString();
                try {
                    simpleSequence.add((Object) environment.importLib(asString, (String) null));
                } catch (IOException e) {
                    throw new _MiscTemplateException((Expression) stringLiteral, new Object[]{"Couldn't import library ", new _DelayedJQuote(asString), ": ", new _DelayedGetMessage(e)});
                }
            } else {
                simpleSequence.add((Object) templateSequenceModel.get(i));
            }
        }
        return simpleSequence;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        ArrayList arrayList = (ArrayList) this.items.clone();
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            listIterator.set(((Expression) listIterator.next()).deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
        }
        return new ListLiteral(arrayList);
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        ArrayList arrayList = this.items;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        checkIndex(i);
        return this.items.get(i);
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        checkIndex(i);
        return ParameterRole.ITEM_VALUE;
    }

    private void checkIndex(int i) {
        ArrayList arrayList = this.items;
        if (arrayList == null || i >= arrayList.size()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
