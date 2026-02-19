package org.jivesoftware.smackx.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.packet.VCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VCardProvider implements IQProvider {
    private static final String PREFERRED_ENCODING = "UTF-8";

    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            int eventType = xmlPullParser.getEventType();
            while (true) {
                if (eventType == 2) {
                    sb.append(Typography.less);
                    sb.append(xmlPullParser.getName());
                    sb.append(Typography.greater);
                } else if (eventType == 3) {
                    sb.append("</");
                    sb.append(xmlPullParser.getName());
                    sb.append(Typography.greater);
                } else if (eventType == 4) {
                    sb.append(StringUtils.escapeForXML(xmlPullParser.getText()));
                }
                if (eventType == 3 && "vCard".equals(xmlPullParser.getName())) {
                    break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return createVCardFromXML(sb.toString());
    }

    public static VCard createVCardFromXML(String str) throws Exception {
        VCard vCard = new VCard();
        new VCardReader(vCard, DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes(PREFERRED_ENCODING)))).initializeFields();
        return vCard;
    }

    private static class VCardReader {
        private final Document document;
        private final VCard vCard;

        VCardReader(VCard vCard2, Document document2) {
            this.vCard = vCard2;
            this.document = document2;
        }

        public void initializeFields() {
            this.vCard.setFirstName(getTagContents("GIVEN"));
            this.vCard.setLastName(getTagContents("FAMILY"));
            this.vCard.setMiddleName(getTagContents("MIDDLE"));
            this.vCard.setEncodedImage(getTagContents("BINVAL"));
            setupEmails();
            this.vCard.setOrganization(getTagContents("ORGNAME"));
            this.vCard.setOrganizationUnit(getTagContents("ORGUNIT"));
            setupSimpleFields();
            setupPhones();
            setupAddresses();
        }

        private void setupEmails() {
            NodeList elementsByTagName = this.document.getElementsByTagName("USERID");
            if (elementsByTagName != null) {
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    Element element = (Element) elementsByTagName.item(i);
                    if ("WORK".equals(element.getParentNode().getFirstChild().getNodeName())) {
                        this.vCard.setEmailWork(getTextContent(element));
                    } else {
                        this.vCard.setEmailHome(getTextContent(element));
                    }
                }
            }
        }

        private void setupPhones() {
            NodeList elementsByTagName = this.document.getElementsByTagName("TEL");
            if (elementsByTagName != null) {
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    NodeList childNodes = elementsByTagName.item(i).getChildNodes();
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item = childNodes.item(i2);
                        if (item.getNodeType() == 1) {
                            String nodeName = item.getNodeName();
                            if ("NUMBER".equals(nodeName)) {
                                str2 = getTextContent(item);
                            } else if (isWorkHome(nodeName)) {
                                str3 = nodeName;
                            } else {
                                str = nodeName;
                            }
                        }
                    }
                    if (!(str == null || str2 == null)) {
                        if ("HOME".equals(str3)) {
                            this.vCard.setPhoneHome(str, str2);
                        } else {
                            this.vCard.setPhoneWork(str, str2);
                        }
                    }
                }
            }
        }

        private boolean isWorkHome(String str) {
            return "HOME".equals(str) || "WORK".equals(str);
        }

        private void setupAddresses() {
            NodeList elementsByTagName = this.document.getElementsByTagName("ADR");
            if (elementsByTagName != null) {
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    String str = null;
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    NodeList childNodes = ((Element) elementsByTagName.item(i)).getChildNodes();
                    for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                        Node item = childNodes.item(i2);
                        if (item.getNodeType() == 1) {
                            String nodeName = item.getNodeName();
                            if (isWorkHome(nodeName)) {
                                str = nodeName;
                            } else {
                                arrayList.add(nodeName);
                                arrayList2.add(getTextContent(item));
                            }
                        }
                    }
                    for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                        if ("HOME".equals(str)) {
                            this.vCard.setAddressFieldHome((String) arrayList.get(i3), (String) arrayList2.get(i3));
                        } else {
                            this.vCard.setAddressFieldWork((String) arrayList.get(i3), (String) arrayList2.get(i3));
                        }
                    }
                }
            }
        }

        private String getTagContents(String str) {
            NodeList elementsByTagName = this.document.getElementsByTagName(str);
            if (elementsByTagName == null || elementsByTagName.getLength() != 1) {
                return null;
            }
            return getTextContent(elementsByTagName.item(0));
        }

        private void setupSimpleFields() {
            NodeList childNodes = this.document.getDocumentElement().getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    Element element = (Element) item;
                    String nodeName = element.getNodeName();
                    if (element.getChildNodes().getLength() == 0) {
                        this.vCard.setField(nodeName, "");
                    } else if (element.getChildNodes().getLength() == 1 && (element.getChildNodes().item(0) instanceof Text)) {
                        this.vCard.setField(nodeName, getTextContent(element));
                    }
                }
            }
        }

        private String getTextContent(Node node) {
            StringBuilder sb = new StringBuilder();
            appendText(sb, node);
            return sb.toString();
        }

        private void appendText(StringBuilder sb, Node node) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                String nodeValue = item.getNodeValue();
                if (nodeValue != null) {
                    sb.append(nodeValue);
                }
                appendText(sb, item);
            }
        }
    }
}
