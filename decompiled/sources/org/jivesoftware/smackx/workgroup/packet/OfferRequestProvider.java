package org.jivesoftware.smackx.workgroup.packet;

import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.jivesoftware.smackx.workgroup.agent.InvitationRequest;
import org.jivesoftware.smackx.workgroup.agent.OfferContent;
import org.jivesoftware.smackx.workgroup.agent.TransferRequest;
import org.jivesoftware.smackx.workgroup.agent.UserRequest;
import org.jivesoftware.smackx.workgroup.util.MetaDataUtils;
import org.xmlpull.v1.XmlPullParser;

public class OfferRequestProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        xmlPullParser.getEventType();
        Map hashMap = new HashMap();
        String attributeValue = xmlPullParser.getAttributeValue("", "jid");
        boolean z = false;
        Map map = hashMap;
        String str = null;
        OfferContent offerContent = null;
        String str2 = attributeValue;
        int i = -1;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                if ("timeout".equals(name)) {
                    i = Integer.parseInt(xmlPullParser.nextText());
                } else if (MetaData.ELEMENT_NAME.equals(name)) {
                    map = MetaDataUtils.parseMetaData(xmlPullParser);
                } else if (SessionID.ELEMENT_NAME.equals(name)) {
                    str = xmlPullParser.getAttributeValue("", "id");
                } else if (UserID.ELEMENT_NAME.equals(name)) {
                    str2 = xmlPullParser.getAttributeValue("", "id");
                } else if ("user-request".equals(name)) {
                    offerContent = UserRequest.getInstance();
                } else if (RoomInvitation.ELEMENT_NAME.equals(name)) {
                    RoomInvitation roomInvitation = (RoomInvitation) PacketParserUtils.parsePacketExtension(RoomInvitation.ELEMENT_NAME, "http://jabber.org/protocol/workgroup", xmlPullParser);
                    offerContent = new InvitationRequest(roomInvitation.getInviter(), roomInvitation.getRoom(), roomInvitation.getReason());
                } else if (RoomTransfer.ELEMENT_NAME.equals(name)) {
                    RoomTransfer roomTransfer = (RoomTransfer) PacketParserUtils.parsePacketExtension(RoomTransfer.ELEMENT_NAME, "http://jabber.org/protocol/workgroup", xmlPullParser);
                    offerContent = new TransferRequest(roomTransfer.getInviter(), roomTransfer.getRoom(), roomTransfer.getReason());
                }
            } else if (next == 3 && "offer".equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        OfferRequestPacket offerRequestPacket = new OfferRequestPacket(attributeValue, str2, i, map, str, offerContent);
        offerRequestPacket.setType(IQ.Type.SET);
        return offerRequestPacket;
    }

    public static class OfferRequestPacket extends IQ {
        private OfferContent content;
        private Map metaData;
        private String sessionID;
        private int timeout;
        private String userID;
        private String userJID;

        public OfferRequestPacket(String str, String str2, int i, Map map, String str3, OfferContent offerContent) {
            this.userJID = str;
            this.userID = str2;
            this.timeout = i;
            this.metaData = map;
            this.sessionID = str3;
            this.content = offerContent;
        }

        public String getUserID() {
            return this.userID;
        }

        public String getUserJID() {
            return this.userJID;
        }

        public String getSessionID() {
            return this.sessionID;
        }

        public int getTimeout() {
            return this.timeout;
        }

        public OfferContent getContent() {
            return this.content;
        }

        public Map getMetaData() {
            return this.metaData;
        }

        public String getChildElementXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<offer xmlns=\"http://jabber.org/protocol/workgroup\" jid=\"");
            sb.append(this.userJID);
            sb.append("\">");
            sb.append("<timeout>");
            sb.append(this.timeout);
            sb.append("</timeout>");
            if (this.sessionID != null) {
                sb.append(Typography.less);
                sb.append(SessionID.ELEMENT_NAME);
                sb.append(" session=\"");
                sb.append(getSessionID());
                sb.append("\" xmlns=\"");
                sb.append("http://jivesoftware.com/protocol/workgroup");
                sb.append("\"/>");
            }
            Map map = this.metaData;
            if (map != null) {
                sb.append(MetaDataUtils.serializeMetaData(map));
            }
            if (this.userID != null) {
                sb.append(Typography.less);
                sb.append(UserID.ELEMENT_NAME);
                sb.append(" id=\"");
                sb.append(this.userID);
                sb.append("\" xmlns=\"");
                sb.append("http://jivesoftware.com/protocol/workgroup");
                sb.append("\"/>");
            }
            sb.append("</offer>");
            return sb.toString();
        }
    }
}
