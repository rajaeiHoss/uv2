package org.jivesoftware.smackx.workgroup.agent;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class OfferConfirmation extends IQ {
    private long sessionID;
    private String userJID;

    public String getUserJID() {
        return this.userJID;
    }

    public void setUserJID(String str) {
        this.userJID = str;
    }

    public long getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(long j) {
        this.sessionID = j;
    }

    public void notifyService(Connection connection, String str, String str2) {
        connection.sendPacket(new NotifyServicePacket(str, str2));
    }

    public String getChildElementXML() {
        return "<offer-confirmation xmlns=\"http://jabber.org/protocol/workgroup\">" + "</offer-confirmation>";
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            OfferConfirmation offerConfirmation = new OfferConfirmation();
            boolean z = false;
            while (!z) {
                xmlPullParser.next();
                String name = xmlPullParser.getName();
                if (xmlPullParser.getEventType() == 2 && "user-jid".equals(name)) {
                    try {
                        offerConfirmation.setUserJID(xmlPullParser.nextText());
                    } catch (NumberFormatException unused) {
                    }
                } else if (xmlPullParser.getEventType() == 2 && "session-id".equals(name)) {
                    offerConfirmation.setSessionID(Long.valueOf(xmlPullParser.nextText()).longValue());
                } else if (xmlPullParser.getEventType() == 3 && "offer-confirmation".equals(name)) {
                    z = true;
                }
            }
            return offerConfirmation;
        }
    }

    private class NotifyServicePacket extends IQ {
        String roomName;

        NotifyServicePacket(String str, String str2) {
            setTo(str);
            setType(IQ.Type.RESULT);
            this.roomName = str2;
        }

        public String getChildElementXML() {
            return "<offer-confirmation  roomname=\"" + this.roomName + "\" xmlns=\"http://jabber.org/protocol/workgroup" + "\"/>";
        }
    }
}
