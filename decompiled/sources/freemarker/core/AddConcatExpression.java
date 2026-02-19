package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.util.HashSet;
import java.util.Set;

final class AddConcatExpression extends Expression {
    private final Expression left;
    private final Expression right;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "+";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    AddConcatExpression(Expression expression, Expression expression2) {
        this.left = expression;
        this.right = expression2;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel eval = this.left.eval(environment);
        TemplateModel eval2 = this.right.eval(environment);
        if ((eval instanceof TemplateNumberModel) && (eval2 instanceof TemplateNumberModel)) {
            return new SimpleNumber((environment != null ? environment.getArithmeticEngine() : getTemplate().getArithmeticEngine()).add(EvalUtil.modelToNumber((TemplateNumberModel) eval, this.left), EvalUtil.modelToNumber((TemplateNumberModel) eval2, this.right)));
        } else if ((eval instanceof TemplateSequenceModel) && (eval2 instanceof TemplateSequenceModel)) {
            return new ConcatenatedSequence((TemplateSequenceModel) eval, (TemplateSequenceModel) eval2);
        } else {
            try {
                String coerceModelToString = Expression.coerceModelToString(eval, this.left, environment);
                String str = "null";
                if (coerceModelToString == null) {
                    coerceModelToString = str;
                }
                String coerceModelToString2 = Expression.coerceModelToString(eval2, this.right, environment);
                if (coerceModelToString2 != null) {
                    str = coerceModelToString2;
                }
                return new SimpleScalar(coerceModelToString.concat(str));
            } catch (NonStringException e) {
                if (!(eval instanceof TemplateHashModel) || !(eval2 instanceof TemplateHashModel)) {
                    throw e;
                } else if (!(eval instanceof TemplateHashModelEx) || !(eval2 instanceof TemplateHashModelEx)) {
                    return new ConcatenatedHash((TemplateHashModel) eval, (TemplateHashModel) eval2);
                } else {
                    TemplateHashModelEx templateHashModelEx = (TemplateHashModelEx) eval;
                    TemplateHashModelEx templateHashModelEx2 = (TemplateHashModelEx) eval2;
                    if (templateHashModelEx.size() == 0) {
                        return templateHashModelEx2;
                    }
                    if (templateHashModelEx2.size() == 0) {
                        return templateHashModelEx;
                    }
                    return new ConcatenatedHashEx(templateHashModelEx, templateHashModelEx2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.constantValue != null || (this.left.isLiteral() && this.right.isLiteral());
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new AddConcatExpression(this.left.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.right.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.left.getCanonicalForm());
        stringBuffer.append(" + ");
        stringBuffer.append(this.right.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        return i == 0 ? this.left : this.right;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.forBinaryOperatorOperand(i);
    }

    private static final class ConcatenatedSequence implements TemplateSequenceModel {
        private final TemplateSequenceModel left;
        private final TemplateSequenceModel right;

        ConcatenatedSequence(TemplateSequenceModel templateSequenceModel, TemplateSequenceModel templateSequenceModel2) {
            this.left = templateSequenceModel;
            this.right = templateSequenceModel2;
        }

        public int size() throws TemplateModelException {
            return this.left.size() + this.right.size();
        }

        public TemplateModel get(int i) throws TemplateModelException {
            int size = this.left.size();
            return i < size ? this.left.get(i) : this.right.get(i - size);
        }
    }

    private static class ConcatenatedHash implements TemplateHashModel {
        protected final TemplateHashModel left;
        protected final TemplateHashModel right;

        ConcatenatedHash(TemplateHashModel templateHashModel, TemplateHashModel templateHashModel2) {
            this.left = templateHashModel;
            this.right = templateHashModel2;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            TemplateModel templateModel = this.right.get(str);
            return templateModel != null ? templateModel : this.left.get(str);
        }

        public boolean isEmpty() throws TemplateModelException {
            return this.left.isEmpty() && this.right.isEmpty();
        }
    }

    private static final class ConcatenatedHashEx extends ConcatenatedHash implements TemplateHashModelEx {
        private CollectionAndSequence keys;
        private int size;
        private CollectionAndSequence values;

        ConcatenatedHashEx(TemplateHashModelEx templateHashModelEx, TemplateHashModelEx templateHashModelEx2) {
            super(templateHashModelEx, templateHashModelEx2);
        }

        public int size() throws TemplateModelException {
            initKeys();
            return this.size;
        }

        public TemplateCollectionModel keys() throws TemplateModelException {
            initKeys();
            return this.keys;
        }

        public TemplateCollectionModel values() throws TemplateModelException {
            initValues();
            return this.values;
        }

        private void initKeys() throws TemplateModelException {
            if (this.keys == null) {
                HashSet hashSet = new HashSet();
                SimpleSequence simpleSequence = new SimpleSequence(32);
                addKeys(hashSet, simpleSequence, (TemplateHashModelEx) this.left);
                addKeys(hashSet, simpleSequence, (TemplateHashModelEx) this.right);
                this.size = hashSet.size();
                this.keys = new CollectionAndSequence((TemplateSequenceModel) simpleSequence);
            }
        }

        private static void addKeys(Set set, SimpleSequence simpleSequence, TemplateHashModelEx templateHashModelEx) throws TemplateModelException {
            TemplateModelIterator it = templateHashModelEx.keys().iterator();
            while (it.hasNext()) {
                TemplateScalarModel templateScalarModel = (TemplateScalarModel) it.next();
                if (set.add(templateScalarModel.getAsString())) {
                    simpleSequence.add((Object) templateScalarModel);
                }
            }
        }

        private void initValues() throws TemplateModelException {
            if (this.values == null) {
                SimpleSequence simpleSequence = new SimpleSequence(size());
                int size2 = this.keys.size();
                for (int i = 0; i < size2; i++) {
                    simpleSequence.add((Object) get(((TemplateScalarModel) this.keys.get(i)).getAsString()));
                }
                this.values = new CollectionAndSequence((TemplateSequenceModel) simpleSequence);
            }
        }
    }
}
