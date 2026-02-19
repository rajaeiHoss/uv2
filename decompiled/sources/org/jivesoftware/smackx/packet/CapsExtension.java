package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;

public class CapsExtension implements PacketExtension {
    public static final String NODE_NAME = "c";
    public static final String XMLNS = "http://jabber.org/protocol/caps";
    private String hash;
    private String node;
    private String version;

    public String getElementName() {
        return NODE_NAME;
    }

    public String getNamespace() {
        return XMLNS;
    }

    public CapsExtension() {
    }

    public CapsExtension(String str, String str2, String str3) {
        this.node = str;
        this.version = str2;
        this.hash = str3;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public String toXML() {
        return "<c xmlns='http://jabber.org/protocol/caps' hash='" + this.hash + "' " + "node='" + this.node + "' " + "ver='" + this.version + "'/>";
    }
}
