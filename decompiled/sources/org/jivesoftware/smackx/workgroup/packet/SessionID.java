package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class SessionID implements PacketExtension {
    public static final String ELEMENT_NAME = "session";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String sessionID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jivesoftware.com/protocol/workgroup";
    }

    public SessionID(String str) {
        this.sessionID = str;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String toXML() {
        return "<" + ELEMENT_NAME + " xmlns=\"" + "http://jivesoftware.com/protocol/workgroup" + "\" " + "id=\"" + getSessionID() + "\"/>";
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            String attributeValue = xmlPullParser.getAttributeValue("", "id");
            xmlPullParser.next();
            return new SessionID(attributeValue);
        }
    }
}
