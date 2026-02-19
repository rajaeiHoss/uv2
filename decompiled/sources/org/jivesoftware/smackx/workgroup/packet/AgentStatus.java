package org.jivesoftware.smackx.workgroup.packet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class AgentStatus implements PacketExtension {
    public static final String ELEMENT_NAME = "agent-status";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    /* access modifiers changed from: private */
    public static final SimpleDateFormat UTC_FORMAT;
    /* access modifiers changed from: private */
    public List<ChatInfo> currentChats = new ArrayList();
    /* access modifiers changed from: private */
    public int maxChats = -1;
    /* access modifiers changed from: private */
    public String workgroupJID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        UTC_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    }

    AgentStatus() {
    }

    public String getWorkgroupJID() {
        return this.workgroupJID;
    }

    public List<ChatInfo> getCurrentChats() {
        return Collections.unmodifiableList(this.currentChats);
    }

    public int getMaxChats() {
        return this.maxChats;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jabber.org/protocol/workgroup");
        sb.append("\"");
        if (this.workgroupJID != null) {
            sb.append(" jid=\"");
            sb.append(this.workgroupJID);
            sb.append("\"");
        }
        sb.append(">");
        if (this.maxChats != -1) {
            sb.append("<max-chats>");
            sb.append(this.maxChats);
            sb.append("</max-chats>");
        }
        if (!this.currentChats.isEmpty()) {
            sb.append("<current-chats xmlns= \"http://jivesoftware.com/protocol/workgroup\">");
            for (ChatInfo xml : this.currentChats) {
                sb.append(xml.toXML());
            }
            sb.append("</current-chats>");
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append("> ");
        return sb.toString();
    }

    public static class ChatInfo {
        private Date date;
        private String email;
        private String question;
        private String sessionID;
        private String userID;
        private String username;

        public ChatInfo(String str, String str2, Date date2, String str3, String str4, String str5) {
            this.sessionID = str;
            this.userID = str2;
            this.date = date2;
            this.email = str3;
            this.username = str4;
            this.question = str5;
        }

        public String getSessionID() {
            return this.sessionID;
        }

        public String getUserID() {
            return this.userID;
        }

        public Date getDate() {
            return this.date;
        }

        public String getEmail() {
            return this.email;
        }

        public String getUsername() {
            return this.username;
        }

        public String getQuestion() {
            return this.question;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<chat ");
            if (this.sessionID != null) {
                sb.append(" sessionID=\"");
                sb.append(this.sessionID);
                sb.append("\"");
            }
            if (this.userID != null) {
                sb.append(" userID=\"");
                sb.append(this.userID);
                sb.append("\"");
            }
            if (this.date != null) {
                sb.append(" startTime=\"");
                sb.append(AgentStatus.UTC_FORMAT.format(this.date));
                sb.append("\"");
            }
            if (this.email != null) {
                sb.append(" email=\"");
                sb.append(this.email);
                sb.append("\"");
            }
            if (this.username != null) {
                sb.append(" username=\"");
                sb.append(this.username);
                sb.append("\"");
            }
            if (this.question != null) {
                sb.append(" question=\"");
                sb.append(this.question);
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            AgentStatus agentStatus = new AgentStatus();
            String unused = agentStatus.workgroupJID = xmlPullParser.getAttributeValue("", "jid");
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if ("chat".equals(xmlPullParser.getName())) {
                        agentStatus.currentChats.add(parseChatInfo(xmlPullParser));
                    } else if ("max-chats".equals(xmlPullParser.getName())) {
                        int unused2 = agentStatus.maxChats = Integer.parseInt(xmlPullParser.nextText());
                    }
                } else if (next == 3 && AgentStatus.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                    z = true;
                }
            }
            return agentStatus;
        }

        private ChatInfo parseChatInfo(XmlPullParser xmlPullParser) {
            Date date;
            String attributeValue = xmlPullParser.getAttributeValue("", "sessionID");
            String attributeValue2 = xmlPullParser.getAttributeValue("", "userID");
            try {
                date = AgentStatus.UTC_FORMAT.parse(xmlPullParser.getAttributeValue("", "startTime"));
            } catch (ParseException unused) {
                date = null;
            }
            return new ChatInfo(attributeValue, attributeValue2, date, xmlPullParser.getAttributeValue("", "email"), xmlPullParser.getAttributeValue("", "username"), xmlPullParser.getAttributeValue("", "question"));
        }
    }
}
