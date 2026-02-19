package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.IQ;

public class PEPPubSub extends IQ {
    PEPItem item;

    public String getElementName() {
        return "pubsub";
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/pubsub";
    }

    public PEPPubSub(PEPItem pEPItem) {
        this.item = pEPItem;
    }

    public String getChildElementXML() {
        return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\">" + "<publish node=\"" + this.item.getNode() + "\">" + this.item.toXML() + "</publish>" + "</" + getElementName() + ">";
    }
}
