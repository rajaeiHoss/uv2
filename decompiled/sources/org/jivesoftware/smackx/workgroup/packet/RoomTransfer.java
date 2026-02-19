package org.jivesoftware.smackx.workgroup.packet;

import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.measurement.AppMeasurement;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class RoomTransfer implements PacketExtension {
    public static final String ELEMENT_NAME = "transfer";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    /* access modifiers changed from: private */
    public String invitee;
    /* access modifiers changed from: private */
    public String inviter;
    /* access modifiers changed from: private */
    public String reason;
    /* access modifiers changed from: private */
    public String room;
    /* access modifiers changed from: private */
    public String sessionID;
    /* access modifiers changed from: private */
    public Type type;

    public enum Type {
        user,
        queue,
        workgroup
    }

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    public RoomTransfer(Type type2, String str, String str2, String str3) {
        this.type = type2;
        this.invitee = str;
        this.sessionID = str2;
        this.reason = str3;
    }

    private RoomTransfer() {
    }

    public String getInviter() {
        return this.inviter;
    }

    public String getRoom() {
        return this.room;
    }

    public String getReason() {
        return this.reason;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jabber.org/protocol/workgroup");
        sb.append("\" type=\"");
        sb.append(this.type);
        sb.append("\">");
        sb.append("<session xmlns=\"http://jivesoftware.com/protocol/workgroup\" id=\"");
        sb.append(this.sessionID);
        sb.append("\"></session>");
        if (this.invitee != null) {
            sb.append("<invitee>");
            sb.append(this.invitee);
            sb.append("</invitee>");
        }
        if (this.inviter != null) {
            sb.append("<inviter>");
            sb.append(this.inviter);
            sb.append("</inviter>");
        }
        if (this.reason != null) {
            sb.append("<reason>");
            sb.append(this.reason);
            sb.append("</reason>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            RoomTransfer roomTransfer = new RoomTransfer();
            Type unused = roomTransfer.type = Type.valueOf(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE));
            boolean z = false;
            while (!z) {
                xmlPullParser.next();
                String name = xmlPullParser.getName();
                if (xmlPullParser.getEventType() == 2) {
                    if (SessionID.ELEMENT_NAME.equals(name)) {
                        String unused2 = roomTransfer.sessionID = xmlPullParser.getAttributeValue("", "id");
                    } else if ("invitee".equals(name)) {
                        String unused3 = roomTransfer.invitee = xmlPullParser.nextText();
                    } else if ("inviter".equals(name)) {
                        String unused4 = roomTransfer.inviter = xmlPullParser.nextText();
                    } else if ("reason".equals(name)) {
                        String unused5 = roomTransfer.reason = xmlPullParser.nextText();
                    } else if (Multiplayer.EXTRA_ROOM.equals(name)) {
                        String unused6 = roomTransfer.room = xmlPullParser.nextText();
                    }
                } else if (xmlPullParser.getEventType() == 3 && RoomTransfer.ELEMENT_NAME.equals(name)) {
                    z = true;
                }
            }
            return roomTransfer;
        }
    }
}
