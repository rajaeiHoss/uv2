package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;

public class LastActivity extends IQ {
    public long lastActivity = -1;
    public String message;

    public LastActivity() {
        setType(IQ.Type.GET);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:last\"");
        if (this.lastActivity != -1) {
            sb.append(" seconds=\"");
            sb.append(this.lastActivity);
            sb.append("\"");
        }
        sb.append("></query>");
        return sb.toString();
    }

    public void setLastActivity(long j) {
        this.lastActivity = j;
    }

    /* access modifiers changed from: private */
    public void setMessage(String str) {
        this.message = str;
    }

    public long getIdleTime() {
        return this.lastActivity;
    }

    public String getStatusMessage() {
        return this.message;
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                LastActivity lastActivity = new LastActivity();
                try {
                    String attributeValue = xmlPullParser.getAttributeValue("", "seconds");
                    String nextText = xmlPullParser.nextText();
                    if (attributeValue != null) {
                        lastActivity.setLastActivity((long) ((int) new Double(attributeValue).longValue()));
                    }
                    if (nextText != null) {
                        lastActivity.setMessage(nextText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return lastActivity;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }

    public static LastActivity getLastActivity(Connection connection, String str) throws XMPPException {
        LastActivity lastActivity2 = new LastActivity();
        lastActivity2.setTo(StringUtils.parseBareAddress(str));
        PacketCollector createPacketCollector = connection.createPacketCollector(new PacketIDFilter(lastActivity2.getPacketID()));
        connection.sendPacket(lastActivity2);
        LastActivity lastActivity3 = (LastActivity) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (lastActivity3 == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (lastActivity3.getError() == null) {
            return lastActivity3;
        } else {
            throw new XMPPException(lastActivity3.getError());
        }
    }
}
