package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class OfferRevokeProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "jid");
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = attributeValue;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("reason")) {
                str = xmlPullParser.nextText();
            } else if (next == 2 && xmlPullParser.getName().equals(SessionID.ELEMENT_NAME)) {
                str2 = xmlPullParser.getAttributeValue("", "id");
            } else if (next == 2 && xmlPullParser.getName().equals(UserID.ELEMENT_NAME)) {
                str3 = xmlPullParser.getAttributeValue("", "id");
            } else if (next == 3 && xmlPullParser.getName().equals("offer-revoke")) {
                z = true;
            }
        }
        return new OfferRevokePacket(attributeValue, str3, str, str2);
    }

    public class OfferRevokePacket extends IQ {
        private String reason;
        private String sessionID;
        private String userID;
        private String userJID;

        public OfferRevokePacket(String str, String str2, String str3, String str4) {
            this.userJID = str;
            this.userID = str2;
            this.reason = str3;
            this.sessionID = str4;
        }

        public String getUserJID() {
            return this.userJID;
        }

        public String getUserID() {
            return this.userID;
        }

        public String getReason() {
            return this.reason;
        }

        public String getSessionID() {
            return this.sessionID;
        }

        public String getChildElementXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<offer-revoke xmlns=\"http://jabber.org/protocol/workgroup\" jid=\"");
            sb.append(this.userID);
            sb.append("\">");
            if (this.reason != null) {
                sb.append("<reason>");
                sb.append(this.reason);
                sb.append("</reason>");
            }
            String str = this.sessionID;
            if (str != null) {
                sb.append(new SessionID(str).toXML());
            }
            String str2 = this.userID;
            if (str2 != null) {
                sb.append(new UserID(str2).toXML());
            }
            sb.append("</offer-revoke>");
            return sb.toString();
        }
    }
}
