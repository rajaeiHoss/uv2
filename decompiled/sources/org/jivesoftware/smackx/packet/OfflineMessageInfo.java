package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class OfflineMessageInfo implements PacketExtension {
    private String node = null;

    public String getElementName() {
        return MessageEvent.OFFLINE;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/offline";
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (getNode() != null) {
            sb.append("<item node=\"");
            sb.append(getNode());
            sb.append("\"/>");
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            OfflineMessageInfo offlineMessageInfo = new OfflineMessageInfo();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        offlineMessageInfo.setNode(xmlPullParser.getAttributeValue("", "node"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(MessageEvent.OFFLINE)) {
                    z = true;
                }
            }
            return offlineMessageInfo;
        }
    }
}
