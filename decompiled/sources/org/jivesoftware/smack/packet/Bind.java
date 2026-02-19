package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ;

public class Bind extends IQ {
    private String jid = null;
    private String resource = null;

    public Bind() {
        setType(IQ.Type.SET);
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public String getJid() {
        return this.jid;
    }

    public void setJid(String str) {
        this.jid = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<bind xmlns=\"urn:ietf:params:xml:ns:xmpp-bind\">");
        if (this.resource != null) {
            sb.append("<resource>");
            sb.append(this.resource);
            sb.append("</resource>");
        }
        if (this.jid != null) {
            sb.append("<jid>");
            sb.append(this.jid);
            sb.append("</jid>");
        }
        sb.append("</bind>");
        return sb.toString();
    }
}
