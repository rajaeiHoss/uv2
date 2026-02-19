package freemarker.core;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateSequenceModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.text.Typography;

final class IteratorBlock extends TemplateElement {
    private boolean isForEach;
    /* access modifiers changed from: private */
    public Expression listSource;
    /* access modifiers changed from: private */
    public String loopVariableName;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    IteratorBlock(Expression expression, String str, TemplateElement templateElement, boolean z) {
        this.listSource = expression;
        this.loopVariableName = str;
        this.isForEach = z;
        this.nestedBlock = templateElement;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        TemplateModel eval = this.listSource.eval(environment);
        if (eval == null) {
            if (!environment.isClassicCompatible()) {
                this.listSource.assertNonNull(eval, environment);
            } else {
                return;
            }
        }
        environment.visitIteratorBlock(new Context(eval));
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        if (this.isForEach) {
            stringBuffer.append(this.loopVariableName);
            stringBuffer.append(" in ");
            stringBuffer.append(this.listSource.getCanonicalForm());
        } else {
            stringBuffer.append(this.listSource.getCanonicalForm());
            stringBuffer.append(" as ");
            stringBuffer.append(this.loopVariableName);
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
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.listSource;
        }
        if (i == 1) {
            return this.loopVariableName;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.LIST_SOURCE;
        }
        if (i == 1) {
            return ParameterRole.TARGET_LOOP_VARIABLE;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.isForEach ? "#foreach" : "#list";
    }

    class Context implements LocalContext {
        private boolean hasNext;
        private int index;
        private TemplateModel list;
        private TemplateModel loopVar;
        private Collection variableNames = null;

        Context(TemplateModel templateModel) {
            this.list = templateModel;
        }

        /* access modifiers changed from: package-private */
        public void runLoop(Environment environment) throws TemplateException, IOException {
            TemplateModel templateModel = this.list;
            if (templateModel instanceof TemplateCollectionModel) {
                TemplateModelIterator it = ((TemplateCollectionModel) templateModel).iterator();
                this.hasNext = it.hasNext();
                while (this.hasNext) {
                    this.loopVar = it.next();
                    this.hasNext = it.hasNext();
                    if (IteratorBlock.this.nestedBlock != null) {
                        environment.visit(IteratorBlock.this.nestedBlock);
                    }
                    this.index++;
                }
            } else if (templateModel instanceof TemplateSequenceModel) {
                TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) templateModel;
                int size = templateSequenceModel.size();
                this.index = 0;
                while (true) {
                    int i = this.index;
                    if (i < size) {
                        this.loopVar = templateSequenceModel.get(i);
                        this.hasNext = size > this.index + 1;
                        if (IteratorBlock.this.nestedBlock != null) {
                            environment.visitByHiddingParent(IteratorBlock.this.nestedBlock);
                        }
                        this.index++;
                    } else {
                        return;
                    }
                }
            } else if (environment.isClassicCompatible()) {
                this.loopVar = this.list;
                if (IteratorBlock.this.nestedBlock != null) {
                    environment.visitByHiddingParent(IteratorBlock.this.nestedBlock);
                }
            } else {
                throw new UnexpectedTypeException(IteratorBlock.this.listSource, this.list, "collection or sequence", environment);
            }
        }

        public TemplateModel getLocalVariable(String str) {
            if (!str.startsWith(IteratorBlock.this.loopVariableName)) {
                return null;
            }
            int length = str.length() - IteratorBlock.this.loopVariableName.length();
            if (length == 0) {
                return this.loopVar;
            }
            if (length != 6) {
                if (length == 9 && str.endsWith("_has_next")) {
                    return this.hasNext ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
                }
                return null;
            } else if (str.endsWith("_index")) {
                return new SimpleNumber(this.index);
            } else {
                return null;
            }
        }

        public Collection getLocalVariableNames() {
            if (this.variableNames == null) {
                ArrayList arrayList = new ArrayList(3);
                this.variableNames = arrayList;
                arrayList.add(IteratorBlock.this.loopVariableName);
                Collection collection = this.variableNames;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(IteratorBlock.this.loopVariableName);
                stringBuffer.append("_index");
                collection.add(stringBuffer.toString());
                Collection collection2 = this.variableNames;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(IteratorBlock.this.loopVariableName);
                stringBuffer2.append("_has_next");
                collection2.add(stringBuffer2.toString());
            }
            return this.variableNames;
        }
    }
}
