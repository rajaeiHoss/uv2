package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class QueueUpdate implements PacketExtension {
    public static final String ELEMENT_NAME = "queue-status";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    private int position;
    private int remainingTime;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    public QueueUpdate(int i, int i2) {
        this.position = i;
        this.remainingTime = i2;
    }

    public int getPosition() {
        return this.position;
    }

    public int getRemaingTime() {
        return this.remainingTime;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<queue-status xmlns=\"http://jabber.org/protocol/workgroup\">");
        if (this.position != -1) {
            sb.append("<position>");
            sb.append(this.position);
            sb.append("</position>");
        }
        if (this.remainingTime != -1) {
            sb.append("<time>");
            sb.append(this.remainingTime);
            sb.append("</time>");
        }
        sb.append("</queue-status>");
        return sb.toString();
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            int i = -1;
            int i2 = -1;
            boolean z = false;
            while (!z) {
                xmlPullParser.next();
                String name = xmlPullParser.getName();
                if (xmlPullParser.getEventType() == 2 && "position".equals(name)) {
                    try {
                        i = Integer.parseInt(xmlPullParser.nextText());
                    } catch (NumberFormatException unused) {
                    }
                } else if (xmlPullParser.getEventType() == 2 && "time".equals(name)) {
                    i2 = Integer.parseInt(xmlPullParser.nextText());
                } else if (xmlPullParser.getEventType() == 3 && QueueUpdate.ELEMENT_NAME.equals(name)) {
                    z = true;
                }
            }
            return new QueueUpdate(i, i2);
        }
    }
}
