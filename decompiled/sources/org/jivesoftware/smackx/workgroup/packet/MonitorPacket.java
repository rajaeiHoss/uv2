package org.jivesoftware.smackx.workgroup.packet;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class MonitorPacket extends IQ {
    public static final String ELEMENT_NAME = "monitor";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private boolean isMonitor;
    private String sessionID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jivesoftware.com/protocol/workgroup";
    }

    public boolean isMonitor() {
        return this.isMonitor;
    }

    public void setMonitor(boolean z) {
        this.isMonitor = z;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=");
        sb.append(Typography.quote);
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append(Typography.quote);
        sb.append(">");
        if (this.sessionID != null) {
            sb.append("<makeOwner sessionID=\"" + this.sessionID + "\"></makeOwner>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                MonitorPacket monitorPacket = new MonitorPacket();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next != 2 || !"isMonitor".equals(xmlPullParser.getName())) {
                        if (next == 3 && MonitorPacket.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                            z = true;
                        }
                    } else if ("false".equalsIgnoreCase(xmlPullParser.nextText())) {
                        monitorPacket.setMonitor(false);
                    } else {
                        monitorPacket.setMonitor(true);
                    }
                }
                return monitorPacket;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }
}
