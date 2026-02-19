package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;

public abstract class PEPItem implements PacketExtension {
    String id;

    public String getElementName() {
        return "item";
    }

    /* access modifiers changed from: package-private */
    public abstract String getItemDetailsXML();

    public String getNamespace() {
        return "http://jabber.org/protocol/pubsub";
    }

    /* access modifiers changed from: package-private */
    public abstract String getNode();

    public PEPItem(String str) {
        this.id = str;
    }

    public String toXML() {
        return "<" + getElementName() + " id=\"" + this.id + "\">" + getItemDetailsXML() + "</" + getElementName() + ">";
    }
}
