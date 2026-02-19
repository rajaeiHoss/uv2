package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;

public class MUCAdmin extends IQ {
    private List items = new ArrayList();

    public Iterator getItems() {
        Iterator it;
        synchronized (this.items) {
            it = Collections.unmodifiableList(new ArrayList(this.items)).iterator();
        }
        return it;
    }

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/muc#admin\">");
        synchronized (this.items) {
            for (int i = 0; i < this.items.size(); i++) {
                sb.append(((Item) this.items.get(i)).toXML());
            }
        }
        sb.append(getExtensionsXML());
        sb.append("</query>");
        return sb.toString();
    }

    public static class Item {
        private String actor;
        private String affiliation;
        private String jid;
        private String nick;
        private String reason;
        private String role;

        public Item(String str, String str2) {
            this.affiliation = str;
            this.role = str2;
        }

        public String getActor() {
            return this.actor;
        }

        public String getReason() {
            return this.reason;
        }

        public String getAffiliation() {
            return this.affiliation;
        }

        public String getJid() {
            return this.jid;
        }

        public String getNick() {
            return this.nick;
        }

        public String getRole() {
            return this.role;
        }

        public void setActor(String str) {
            this.actor = str;
        }

        public void setReason(String str) {
            this.reason = str;
        }

        public void setJid(String str) {
            this.jid = str;
        }

        public void setNick(String str) {
            this.nick = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (getAffiliation() != null) {
                sb.append(" affiliation=\"");
                sb.append(getAffiliation());
                sb.append("\"");
            }
            if (getJid() != null) {
                sb.append(" jid=\"");
                sb.append(getJid());
                sb.append("\"");
            }
            if (getNick() != null) {
                sb.append(" nick=\"");
                sb.append(getNick());
                sb.append("\"");
            }
            if (getRole() != null) {
                sb.append(" role=\"");
                sb.append(getRole());
                sb.append("\"");
            }
            if (getReason() == null && getActor() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (getReason() != null) {
                    sb.append("<reason>");
                    sb.append(getReason());
                    sb.append("</reason>");
                }
                if (getActor() != null) {
                    sb.append("<actor jid=\"");
                    sb.append(getActor());
                    sb.append("\"/>");
                }
                sb.append("</item>");
            }
            return sb.toString();
        }
    }
}
