package freemarker.core;

import freemarker.core.Environment;
import freemarker.core.Macro;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

final class BodyInstruction extends TemplateElement {
    /* access modifiers changed from: private */
    public List bodyParameters;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#nested";
    }

    BodyInstruction(List list) {
        this.bodyParameters = list;
    }

    /* access modifiers changed from: package-private */
    public List getBodyParameters() {
        return this.bodyParameters;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws IOException, TemplateException {
        environment.visit(new Context(environment));
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        if (this.bodyParameters != null) {
            for (int i = 0; i < this.bodyParameters.size(); i++) {
                stringBuffer.append(' ');
                stringBuffer.append(this.bodyParameters.get(i));
            }
        }
        if (z) {
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        List list = this.bodyParameters;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        checkIndex(i);
        return this.bodyParameters.get(i);
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        checkIndex(i);
        return ParameterRole.PASSED_VALUE;
    }

    private void checkIndex(int i) {
        List list = this.bodyParameters;
        if (list == null || i >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    class Context implements LocalContext {
        Environment.Namespace bodyVars;
        Macro.Context invokingMacroContext;

        Context(Environment environment) throws TemplateException {
            Macro.Context currentMacroContext = environment.getCurrentMacroContext();
            this.invokingMacroContext = currentMacroContext;
            List list = currentMacroContext.bodyParameterNames;
            if (BodyInstruction.this.bodyParameters != null) {
                for (int i = 0; i < BodyInstruction.this.bodyParameters.size(); i++) {
                    TemplateModel eval = ((Expression) BodyInstruction.this.bodyParameters.get(i)).eval(environment);
                    if (list != null && i < list.size()) {
                        String str = (String) list.get(i);
                        if (this.bodyVars == null) {
                            environment.getClass();
                            this.bodyVars = new Environment.Namespace();
                        }
                        this.bodyVars.put(str, (Object) eval);
                    }
                }
            }
        }

        public TemplateModel getLocalVariable(String str) throws TemplateModelException {
            Environment.Namespace namespace = this.bodyVars;
            if (namespace == null) {
                return null;
            }
            return namespace.get(str);
        }

        public Collection getLocalVariableNames() {
            List list = this.invokingMacroContext.bodyParameterNames;
            return list == null ? Collections.EMPTY_LIST : list;
        }
    }
}
