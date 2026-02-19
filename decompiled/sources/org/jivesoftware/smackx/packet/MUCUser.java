package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.GroupChatInvitation;

public class MUCUser implements PacketExtension {
    private Decline decline;
    private Destroy destroy;
    private Invite invite;
    private Item item;
    private String password;
    private Status status;

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/muc#user";
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (getInvite() != null) {
            sb.append(getInvite().toXML());
        }
        if (getDecline() != null) {
            sb.append(getDecline().toXML());
        }
        if (getItem() != null) {
            sb.append(getItem().toXML());
        }
        if (getPassword() != null) {
            sb.append("<password>");
            sb.append(getPassword());
            sb.append("</password>");
        }
        if (getStatus() != null) {
            sb.append(getStatus().toXML());
        }
        if (getDestroy() != null) {
            sb.append(getDestroy().toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public Invite getInvite() {
        return this.invite;
    }

    public Decline getDecline() {
        return this.decline;
    }

    public Item getItem() {
        return this.item;
    }

    public String getPassword() {
        return this.password;
    }

    public Status getStatus() {
        return this.status;
    }

    public Destroy getDestroy() {
        return this.destroy;
    }

    public void setInvite(Invite invite2) {
        this.invite = invite2;
    }

    public void setDecline(Decline decline2) {
        this.decline = decline2;
    }

    public void setItem(Item item2) {
        this.item = item2;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setStatus(Status status2) {
        this.status = status2;
    }

    public void setDestroy(Destroy destroy2) {
        this.destroy = destroy2;
    }

    public static class Invite {
        private String from;
        private String reason;
        private String to;

        public String getFrom() {
            return this.from;
        }

        public String getReason() {
            return this.reason;
        }

        public String getTo() {
            return this.to;
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public void setReason(String str) {
            this.reason = str;
        }

        public void setTo(String str) {
            this.to = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<invite ");
            if (getTo() != null) {
                sb.append(" to=\"");
                sb.append(getTo());
                sb.append("\"");
            }
            if (getFrom() != null) {
                sb.append(" from=\"");
                sb.append(getFrom());
                sb.append("\"");
            }
            sb.append(">");
            if (getReason() != null) {
                sb.append("<reason>");
                sb.append(getReason());
                sb.append("</reason>");
            }
            sb.append("</invite>");
            return sb.toString();
        }
    }

    public static class Decline {
        private String from;
        private String reason;
        private String to;

        public String getFrom() {
            return this.from;
        }

        public String getReason() {
            return this.reason;
        }

        public String getTo() {
            return this.to;
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public void setReason(String str) {
            this.reason = str;
        }

        public void setTo(String str) {
            this.to = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<decline ");
            if (getTo() != null) {
                sb.append(" to=\"");
                sb.append(getTo());
                sb.append("\"");
            }
            if (getFrom() != null) {
                sb.append(" from=\"");
                sb.append(getFrom());
                sb.append("\"");
            }
            sb.append(">");
            if (getReason() != null) {
                sb.append("<reason>");
                sb.append(getReason());
                sb.append("</reason>");
            }
            sb.append("</decline>");
            return sb.toString();
        }
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
            String str = this.actor;
            return str == null ? "" : str;
        }

        public String getReason() {
            String str = this.reason;
            return str == null ? "" : str;
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

    public static class Status {
        private String code;

        public Status(String str) {
            this.code = str;
        }

        public String getCode() {
            return this.code;
        }

        public String toXML() {
            return "<status code=\"" + getCode() + "\"/>";
        }
    }

    public static class Destroy {
        private String jid;
        private String reason;

        public String getJid() {
            return this.jid;
        }

        public String getReason() {
            return this.reason;
        }

        public void setJid(String str) {
            this.jid = str;
        }

        public void setReason(String str) {
            this.reason = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<destroy");
            if (getJid() != null) {
                sb.append(" jid=\"");
                sb.append(getJid());
                sb.append("\"");
            }
            if (getReason() == null) {
                sb.append("/>");
            } else {
                sb.append(">");
                if (getReason() != null) {
                    sb.append("<reason>");
                    sb.append(getReason());
                    sb.append("</reason>");
                }
                sb.append("</destroy>");
            }
            return sb.toString();
        }
    }
}
