package freemarker.ext.dom;

import freemarker.template.TemplateScalarModel;
import org.w3c.dom.ProcessingInstruction;

class PINodeModel extends NodeModel implements TemplateScalarModel {
    public boolean isEmpty() {
        return true;
    }

    public PINodeModel(ProcessingInstruction processingInstruction) {
        super(processingInstruction);
    }

    public String getAsString() {
        return ((ProcessingInstruction) this.node).getData();
    }

    public String getNodeName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@pi$");
        stringBuffer.append(((ProcessingInstruction) this.node).getTarget());
        return stringBuffer.toString();
    }
}
