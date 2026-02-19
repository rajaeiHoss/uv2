package org.jivesoftware.smack.packet;

import java.util.Objects;
import org.jivesoftware.smack.util.StringUtils;

public class Presence extends Packet {
    private String language;
    private Mode mode = null;
    private int priority = Integer.MIN_VALUE;
    private String status = null;
    private Type type = Type.available;

    public enum Mode {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error
    }

    public Presence(Type type2) {
        setType(type2);
    }

    public Presence(Type type2, String str, int i, Mode mode2) {
        setType(type2);
        setStatus(str);
        setPriority(i);
        setMode(mode2);
    }

    public boolean isAvailable() {
        return this.type == Type.available;
    }

    public boolean isAway() {
        return this.type == Type.available && (this.mode == Mode.away || this.mode == Mode.xa || this.mode == Mode.dnd);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        Objects.requireNonNull(type2, "Type cannot be null");
        this.type = type2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.priority = i;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    private String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (getXmlns() != null) {
            sb.append(" xmlns=\"");
            sb.append(getXmlns());
            sb.append("\"");
        }
        if (this.language != null) {
            sb.append(" xml:lang=\"");
            sb.append(getLanguage());
            sb.append("\"");
        }
        if (getPacketID() != null) {
            sb.append(" id=\"");
            sb.append(getPacketID());
            sb.append("\"");
        }
        if (getTo() != null) {
            sb.append(" to=\"");
            sb.append(StringUtils.escapeForXML(getTo()));
            sb.append("\"");
        }
        if (getFrom() != null) {
            sb.append(" from=\"");
            sb.append(StringUtils.escapeForXML(getFrom()));
            sb.append("\"");
        }
        if (this.type != Type.available) {
            sb.append(" type=\"");
            sb.append(this.type);
            sb.append("\"");
        }
        sb.append(">");
        if (this.status != null) {
            sb.append("<status>");
            sb.append(StringUtils.escapeForXML(this.status));
            sb.append("</status>");
        }
        if (this.priority != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.priority);
            sb.append("</priority>");
        }
        Mode mode2 = this.mode;
        if (!(mode2 == null || mode2 == Mode.available)) {
            sb.append("<show>");
            sb.append(this.mode);
            sb.append("</show>");
        }
        sb.append(getExtensionsXML());
        XMPPError error = getError();
        if (error != null) {
            sb.append(error.toXML());
        }
        sb.append("</presence>");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        if (this.mode != null) {
            sb.append(": ");
            sb.append(this.mode);
        }
        if (getStatus() != null) {
            sb.append(" (");
            sb.append(getStatus());
            sb.append(")");
        }
        return sb.toString();
    }
}
