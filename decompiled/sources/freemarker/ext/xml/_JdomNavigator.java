package freemarker.ext.xml;

import freemarker.ext.xml.Navigator;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.NamespaceContext;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.output.XMLOutputter;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class _JdomNavigator extends Navigator {
    private static final XMLOutputter OUTPUT = new XMLOutputter();

    /* access modifiers changed from: package-private */
    public void getAsString(Object obj, StringWriter stringWriter) throws TemplateModelException {
        try {
            if (obj instanceof Element) {
                OUTPUT.output((Element) obj, stringWriter);
            } else if (obj instanceof Attribute) {
                Attribute attribute = (Attribute) obj;
                stringWriter.write(" ");
                stringWriter.write(attribute.getQualifiedName());
                stringWriter.write("=\"");
                stringWriter.write(OUTPUT.escapeAttributeEntities(attribute.getValue()));
                stringWriter.write("\"");
            } else if (obj instanceof Text) {
                OUTPUT.output((Text) obj, stringWriter);
            } else if (obj instanceof Document) {
                OUTPUT.output((Document) obj, stringWriter);
            } else if (obj instanceof ProcessingInstruction) {
                OUTPUT.output((ProcessingInstruction) obj, stringWriter);
            } else if (obj instanceof Comment) {
                OUTPUT.output((Comment) obj, stringWriter);
            } else if (obj instanceof CDATA) {
                OUTPUT.output((CDATA) obj, stringWriter);
            } else if (obj instanceof DocType) {
                OUTPUT.output((DocType) obj, stringWriter);
            } else if (obj instanceof EntityRef) {
                OUTPUT.output((EntityRef) obj, stringWriter);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(obj.getClass().getName());
                stringBuffer.append(" is not a core JDOM class");
                throw new TemplateModelException(stringBuffer.toString());
            }
        } catch (IOException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    /* access modifiers changed from: package-private */
    public void getChildren(Object obj, String str, String str2, List list) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str == null) {
                list.addAll(element.getChildren());
            } else {
                list.addAll(element.getChildren(str, Namespace.getNamespace("", str2)));
            }
        } else if (obj instanceof Document) {
            Element rootElement = ((Document) obj).getRootElement();
            if (str == null || (equal(rootElement.getName(), str) && equal(rootElement.getNamespaceURI(), str2))) {
                list.add(rootElement);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getAttributes(Object obj, String str, String str2, List list) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str == null) {
                list.addAll(element.getAttributes());
                return;
            }
            Attribute attribute = element.getAttribute(str, Namespace.getNamespace("", str2));
            if (attribute != null) {
                list.add(attribute);
            }
        } else if (obj instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
            if ("target".equals(str)) {
                list.add(new Attribute("target", processingInstruction.getTarget()));
            } else if (DataPacketExtension.ELEMENT_NAME.equals(str)) {
                list.add(new Attribute(DataPacketExtension.ELEMENT_NAME, processingInstruction.getData()));
            } else {
                list.add(new Attribute(str, processingInstruction.getValue(str)));
            }
        } else if (obj instanceof DocType) {
            DocType docType = (DocType) obj;
            if ("publicId".equals(str)) {
                list.add(new Attribute("publicId", docType.getPublicID()));
            } else if ("systemId".equals(str)) {
                list.add(new Attribute("systemId", docType.getSystemID()));
            } else if ("elementName".equals(str)) {
                list.add(new Attribute("elementName", docType.getElementName()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getDescendants(Object obj, List list) {
        if (obj instanceof Document) {
            Element rootElement = ((Document) obj).getRootElement();
            list.add(rootElement);
            getDescendants(rootElement, list);
        } else if (obj instanceof Element) {
            getDescendants((Element) obj, list);
        }
    }

    private void getDescendants(Element element, List list) {
        for (Element element2 : element.getChildren()) {
            list.add(element2);
            getDescendants(element2, list);
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParent(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getParent();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getParent();
        }
        if (obj instanceof Text) {
            return ((Text) obj).getParent();
        }
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getParent();
        }
        if (obj instanceof Comment) {
            return ((Comment) obj).getParent();
        }
        if (obj instanceof EntityRef) {
            return ((EntityRef) obj).getParent();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Object getDocument(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getDocument();
        }
        if (obj instanceof Attribute) {
            Element parent = ((Attribute) obj).getParent();
            if (parent == null) {
                return null;
            }
            return parent.getDocument();
        } else if (obj instanceof Text) {
            Element parent2 = ((Text) obj).getParent();
            if (parent2 == null) {
                return null;
            }
            return parent2.getDocument();
        } else if (obj instanceof Document) {
            return obj;
        } else {
            if (obj instanceof ProcessingInstruction) {
                return ((ProcessingInstruction) obj).getDocument();
            }
            if (obj instanceof EntityRef) {
                return ((EntityRef) obj).getDocument();
            }
            if (obj instanceof Comment) {
                return ((Comment) obj).getDocument();
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Object getDocumentType(Object obj) {
        if (obj instanceof Document) {
            return ((Document) obj).getDocType();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void getContent(Object obj, List list) {
        if (obj instanceof Element) {
            list.addAll(((Element) obj).getContent());
        } else if (obj instanceof Document) {
            list.addAll(((Document) obj).getContent());
        }
    }

    /* access modifiers changed from: package-private */
    public String getText(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getTextTrim();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getValue();
        }
        if (obj instanceof CDATA) {
            return ((CDATA) obj).getText();
        }
        if (obj instanceof Comment) {
            return ((Comment) obj).getText();
        }
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getData();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getLocalName(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getName();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getName();
        }
        if (obj instanceof EntityRef) {
            return ((EntityRef) obj).getName();
        }
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getTarget();
        }
        if (obj instanceof DocType) {
            return ((DocType) obj).getElementName();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getNamespacePrefix(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getNamespacePrefix();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getNamespacePrefix();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getNamespaceUri(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getNamespaceURI();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getNamespaceURI();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getType(Object obj) {
        if (obj instanceof Attribute) {
            return "attribute";
        }
        if (obj instanceof CDATA) {
            return "cdata";
        }
        if (obj instanceof Comment) {
            return "comment";
        }
        if (obj instanceof Document) {
            return "document";
        }
        if (obj instanceof DocType) {
            return "documentType";
        }
        if (obj instanceof Element) {
            return "element";
        }
        if (obj instanceof EntityRef) {
            return "entityReference";
        }
        if (obj instanceof Namespace) {
            return "namespace";
        }
        if (obj instanceof ProcessingInstruction) {
            return "processingInstruction";
        }
        return obj instanceof Text ? "text" : "unknown";
    }

    /* access modifiers changed from: package-private */
    public Navigator.XPathEx createXPathEx(String str) throws TemplateModelException {
        try {
            return new JDOMXPathEx(str);
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }

    private static final class JDOMXPathEx extends JDOMXPath implements Navigator.XPathEx {
        JDOMXPathEx(String str) throws Exception {
            super(str);
        }

        public List selectNodes(Object obj, NamespaceContext namespaceContext) throws TemplateModelException {
            Context context = getContext(obj);
            context.getContextSupport().setNamespaceContext(namespaceContext);
            try {
                return selectNodesForContext(context);
            } catch (Exception e) {
                throw new TemplateModelException(e);
            }
        }
    }
}
