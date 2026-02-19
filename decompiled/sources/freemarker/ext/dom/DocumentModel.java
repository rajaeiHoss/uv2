package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.StringUtil;
import org.w3c.dom.Document;

class DocumentModel extends NodeModel implements TemplateHashModel {
    private ElementModel rootElement;

    public String getNodeName() {
        return "@document";
    }

    public boolean isEmpty() {
        return false;
    }

    DocumentModel(Document document) {
        super(document);
    }

    public TemplateModel get(String str) throws TemplateModelException {
        if (str.equals("*")) {
            return getRootElement();
        }
        if (str.equals("**")) {
            return new NodeListModel(((Document) this.node).getElementsByTagName("*"), (NodeModel) this);
        }
        if (!StringUtil.isXMLID(str)) {
            return super.get(str);
        }
        ElementModel elementModel = (ElementModel) NodeModel.wrap(((Document) this.node).getDocumentElement());
        if (elementModel.matchesName(str, Environment.getCurrentEnvironment())) {
            return elementModel;
        }
        return new NodeListModel((NodeModel) this);
    }

    /* access modifiers changed from: package-private */
    public ElementModel getRootElement() {
        if (this.rootElement == null) {
            this.rootElement = (ElementModel) wrap(((Document) this.node).getDocumentElement());
        }
        return this.rootElement;
    }
}
