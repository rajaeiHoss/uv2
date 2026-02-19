package freemarker.core;

import freemarker.debug.impl.DebuggerService;
import freemarker.template.TemplateException;
import java.io.IOException;

public class DebugBreak extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#debug_break";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    public DebugBreak(TemplateElement templateElement) {
        this.nestedBlock = templateElement;
        templateElement.parent = this;
        copyLocationFrom(templateElement);
    }

    /* access modifiers changed from: protected */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (!DebuggerService.suspendEnvironment(environment, getTemplate().getName(), this.nestedBlock.getBeginLine())) {
            this.nestedBlock.accept(environment);
            return;
        }
        throw new StopException(environment, "Stopped by debugger");
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return "debug break";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<#-- ");
        stringBuffer.append("debug break");
        if (this.nestedBlock == null) {
            stringBuffer.append(" /-->");
        } else {
            stringBuffer.append(" -->");
            stringBuffer.append(this.nestedBlock.getCanonicalForm());
            stringBuffer.append("<#--/ debug break -->");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        throw new IndexOutOfBoundsException();
    }
}
