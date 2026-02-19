package org.jivesoftware.smackx.workgroup.packet;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class WorkgroupInformation implements PacketExtension {
    public static final String ELEMENT_NAME = "workgroup";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    private String workgroupJID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    public WorkgroupInformation(String str) {
        this.workgroupJID = str;
    }

    public String getWorkgroupJID() {
        return this.workgroupJID;
    }

    public String toXML() {
        return Typography.less + ELEMENT_NAME + " jid=\"" + getWorkgroupJID() + "\"" + " xmlns=\"" + "http://jabber.org/protocol/workgroup" + "\" />";
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            String attributeValue = xmlPullParser.getAttributeValue("", "jid");
            xmlPullParser.next();
            return new WorkgroupInformation(attributeValue);
        }
    }
}
