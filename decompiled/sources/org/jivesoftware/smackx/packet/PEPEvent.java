package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;

public class PEPEvent implements PacketExtension {
    PEPItem item;

    public String getElementName() {
        return "event";
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/pubsub";
    }

    public PEPEvent() {
    }

    public PEPEvent(PEPItem pEPItem) {
        this.item = pEPItem;
    }

    public void addPEPItem(PEPItem pEPItem) {
        this.item = pEPItem;
    }

    public String toXML() {
        return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\">" + this.item.toXML() + "</" + getElementName() + ">";
    }
}
