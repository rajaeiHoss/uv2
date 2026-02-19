package freemarker.core;

import com.streamax.config.constant.Constants;
import freemarker.core.Expression;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateSequenceModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ListIterator;

final class HashLiteral extends Expression {
    /* access modifiers changed from: private */
    public final ArrayList keys;
    /* access modifiers changed from: private */
    public final int size;
    /* access modifiers changed from: private */
    public final ArrayList values;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "{...}";
    }

    HashLiteral(ArrayList arrayList, ArrayList arrayList2) {
        this.keys = arrayList;
        this.values = arrayList2;
        this.size = arrayList.size();
        arrayList.trimToSize();
        arrayList2.trimToSize();
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        return new SequenceHash(environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer("{");
        for (int i = 0; i < this.size; i++) {
            stringBuffer.append(((Expression) this.keys.get(i)).getCanonicalForm());
            stringBuffer.append(" : ");
            stringBuffer.append(((Expression) this.values.get(i)).getCanonicalForm());
            if (i != this.size - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(Constants.JsonSstringSuffix);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        if (this.constantValue != null) {
            return true;
        }
        for (int i = 0; i < this.size; i++) {
            Expression expression = (Expression) this.values.get(i);
            if (!((Expression) this.keys.get(i)).isLiteral() || !expression.isLiteral()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        ArrayList arrayList = (ArrayList) this.keys.clone();
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            listIterator.set(((Expression) listIterator.next()).deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
        }
        ArrayList arrayList2 = (ArrayList) this.values.clone();
        ListIterator listIterator2 = arrayList2.listIterator();
        while (listIterator2.hasNext()) {
            listIterator2.set(((Expression) listIterator2.next()).deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
        }
        return new HashLiteral(arrayList, arrayList2);
    }

    private class SequenceHash implements TemplateHashModelEx {
        private TemplateCollectionModel keyCollection;
        private HashMap keyMap = new HashMap();
        private TemplateCollectionModel valueCollection;

        SequenceHash(Environment environment) throws TemplateException {
            ArrayList arrayList = new ArrayList(HashLiteral.this.size);
            ArrayList arrayList2 = new ArrayList(HashLiteral.this.size);
            for (int i = 0; i < HashLiteral.this.size; i++) {
                Expression expression = (Expression) HashLiteral.this.values.get(i);
                String evalAndCoerceToString = ((Expression) HashLiteral.this.keys.get(i)).evalAndCoerceToString(environment);
                TemplateModel eval = expression.eval(environment);
                if (environment == null || !environment.isClassicCompatible()) {
                    expression.assertNonNull(eval, environment);
                }
                this.keyMap.put(evalAndCoerceToString, eval);
                arrayList.add(evalAndCoerceToString);
                arrayList2.add(eval);
            }
            this.keyCollection = new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence((Collection) arrayList));
            this.valueCollection = new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence((Collection) arrayList2));
        }

        public int size() {
            return HashLiteral.this.size;
        }

        public TemplateCollectionModel keys() {
            return this.keyCollection;
        }

        public TemplateCollectionModel values() {
            return this.valueCollection;
        }

        public TemplateModel get(String str) {
            return (TemplateModel) this.keyMap.get(str);
        }

        public boolean isEmpty() {
            return HashLiteral.this.size == 0;
        }
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return this.size * 2;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        checkIndex(i);
        return (i % 2 == 0 ? this.keys : this.values).get(i / 2);
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        checkIndex(i);
        return i % 2 == 0 ? ParameterRole.ITEM_KEY : ParameterRole.ITEM_VALUE;
    }

    private void checkIndex(int i) {
        if (i >= this.size * 2) {
            throw new IndexOutOfBoundsException();
        }
    }
}
