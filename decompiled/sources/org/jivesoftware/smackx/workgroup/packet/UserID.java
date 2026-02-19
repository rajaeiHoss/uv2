package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class UserID implements PacketExtension {
    public static final String ELEMENT_NAME = "user";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String userID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jivesoftware.com/protocol/workgroup";
    }

    public UserID(String str) {
        this.userID = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public String toXML() {
        return "<" + ELEMENT_NAME + " xmlns=\"" + "http://jivesoftware.com/protocol/workgroup" + "\" " + "id=\"" + getUserID() + "\"/>";
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            String attributeValue = xmlPullParser.getAttributeValue("", "id");
            xmlPullParser.next();
            return new UserID(attributeValue);
        }
    }
}
