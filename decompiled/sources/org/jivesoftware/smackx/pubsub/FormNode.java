package org.jivesoftware.smackx.pubsub;

import kotlin.text.Typography;
import org.jivesoftware.smackx.Form;

public class FormNode extends NodeExtension {
    private Form configForm;

    public FormNode(FormNodeType formNodeType, Form form) {
        super(formNodeType.getNodeElement());
        if (form != null) {
            this.configForm = form;
            return;
        }
        throw new IllegalArgumentException("Submit form cannot be null");
    }

    public FormNode(FormNodeType formNodeType, String str, Form form) {
        super(formNodeType.getNodeElement(), str);
        if (form != null) {
            this.configForm = form;
            return;
        }
        throw new IllegalArgumentException("Submit form cannot be null");
    }

    public Form getForm() {
        return this.configForm;
    }

    public String toXML() {
        if (this.configForm == null) {
            return super.toXML();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        if (getNode() != null) {
            sb.append(" node='");
            sb.append(getNode());
            sb.append("'>");
        } else {
            sb.append(Typography.greater);
        }
        sb.append(this.configForm.getDataFormToSend().toXML());
        sb.append("</");
        sb.append(getElementName() + Typography.greater);
        return sb.toString();
    }
}
