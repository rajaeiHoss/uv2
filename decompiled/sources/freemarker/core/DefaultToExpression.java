package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.util.ArrayList;
import java.util.Collection;

class DefaultToExpression extends Expression {
    /* access modifiers changed from: private */
    public static final TemplateCollectionModel EMPTY_COLLECTION = new SimpleCollection((Collection) new ArrayList(0));
    static final TemplateModel EMPTY_STRING_AND_SEQUENCE = new EmptyStringAndSequence();
    private final Expression lho;
    private final Expression rho;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "...!...";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    private static class EmptyStringAndSequence implements TemplateScalarModel, TemplateSequenceModel, TemplateHashModelEx {
        public TemplateModel get(int i) {
            return null;
        }

        public TemplateModel get(String str) {
            return null;
        }

        public String getAsString() {
            return "";
        }

        public boolean isEmpty() {
            return true;
        }

        public int size() {
            return 0;
        }

        private EmptyStringAndSequence() {
        }

        public TemplateCollectionModel keys() {
            return DefaultToExpression.EMPTY_COLLECTION;
        }

        public TemplateCollectionModel values() {
            return DefaultToExpression.EMPTY_COLLECTION;
        }
    }

    DefaultToExpression(Expression expression, Expression expression2) {
        this.lho = expression;
        this.rho = expression2;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel templateModel;
        Expression expression = this.lho;
        if (expression instanceof ParentheticalExpression) {
            boolean fastInvalidReferenceExceptions = environment.setFastInvalidReferenceExceptions(true);
            try {
                templateModel = this.lho.eval(environment);
            } catch (InvalidReferenceException unused) {
                templateModel = null;
            } catch (Throwable th) {
                environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
                throw th;
            }
            environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
        } else {
            templateModel = expression.eval(environment);
        }
        if (templateModel != null) {
            return templateModel;
        }
        Expression expression2 = this.rho;
        if (expression2 == null) {
            return EMPTY_STRING_AND_SEQUENCE;
        }
        return expression2.eval(environment);
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        Expression deepCloneWithIdentifierReplaced = this.lho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState);
        Expression expression2 = this.rho;
        return new DefaultToExpression(deepCloneWithIdentifierReplaced, expression2 != null ? expression2.deepCloneWithIdentifierReplaced(str, expression, replacemenetState) : null);
    }

    public String getCanonicalForm() {
        if (this.rho == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.lho.getCanonicalForm());
            stringBuffer.append('!');
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.lho.getCanonicalForm());
        stringBuffer2.append('!');
        stringBuffer2.append(this.rho.getCanonicalForm());
        return stringBuffer2.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.lho;
        }
        if (i == 1) {
            return this.rho;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.forBinaryOperatorOperand(i);
    }
}
