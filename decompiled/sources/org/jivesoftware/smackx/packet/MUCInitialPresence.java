package org.jivesoftware.smackx.packet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.GroupChatInvitation;

public class MUCInitialPresence implements PacketExtension {
    private History history;
    private String password;

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/muc";
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (getPassword() != null) {
            sb.append("<password>");
            sb.append(getPassword());
            sb.append("</password>");
        }
        if (getHistory() != null) {
            sb.append(getHistory().toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public History getHistory() {
        return this.history;
    }

    public String getPassword() {
        return this.password;
    }

    public void setHistory(History history2) {
        this.history = history2;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public static class History {
        private int maxChars = -1;
        private int maxStanzas = -1;
        private int seconds = -1;
        private Date since;

        public int getMaxChars() {
            return this.maxChars;
        }

        public int getMaxStanzas() {
            return this.maxStanzas;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public Date getSince() {
            return this.since;
        }

        public void setMaxChars(int i) {
            this.maxChars = i;
        }

        public void setMaxStanzas(int i) {
            this.maxStanzas = i;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }

        public void setSince(Date date) {
            this.since = date;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<history");
            if (getMaxChars() != -1) {
                sb.append(" maxchars=\"");
                sb.append(getMaxChars());
                sb.append("\"");
            }
            if (getMaxStanzas() != -1) {
                sb.append(" maxstanzas=\"");
                sb.append(getMaxStanzas());
                sb.append("\"");
            }
            if (getSeconds() != -1) {
                sb.append(" seconds=\"");
                sb.append(getSeconds());
                sb.append("\"");
            }
            if (getSince() != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                sb.append(" since=\"");
                sb.append(simpleDateFormat.format(getSince()));
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }
}
