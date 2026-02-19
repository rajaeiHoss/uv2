package freemarker.ext.dom;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import org.w3c.dom.DocumentType;
import org.w3c.dom.ProcessingInstruction;

class DocumentTypeModel extends NodeModel {
    public boolean isEmpty() {
        return true;
    }

    public DocumentTypeModel(DocumentType documentType) {
        super(documentType);
    }

    public String getAsString() {
        return ((ProcessingInstruction) this.node).getData();
    }

    public TemplateSequenceModel getChildren() throws TemplateModelException {
        throw new TemplateModelException("entering the child nodes of a DTD node is not currently supported");
    }

    public TemplateModel get(String str) throws TemplateModelException {
        throw new TemplateModelException("accessing properties of a DTD is not currently supported");
    }

    public String getNodeName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@document_type$");
        stringBuffer.append(((DocumentType) this.node).getNodeName());
        return stringBuffer.toString();
    }
}
