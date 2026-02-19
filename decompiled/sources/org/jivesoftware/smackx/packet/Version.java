package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.IQ;

public class Version extends IQ {
    private String name;
    private String os;
    private String version;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:version\">");
        if (this.name != null) {
            sb.append("<name>");
            sb.append(this.name);
            sb.append("</name>");
        }
        if (this.version != null) {
            sb.append("<version>");
            sb.append(this.version);
            sb.append("</version>");
        }
        if (this.os != null) {
            sb.append("<os>");
            sb.append(this.os);
            sb.append("</os>");
        }
        sb.append("</query>");
        return sb.toString();
    }
}
