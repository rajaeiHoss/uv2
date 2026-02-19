package org.jivesoftware.smackx.pubsub.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

public class PubSub extends IQ {
    private PubSubNamespace ns = PubSubNamespace.BASIC;

    public String getElementName() {
        return "pubsub";
    }

    public String getNamespace() {
        return this.ns.getXmlns();
    }

    public void setPubSubNamespace(PubSubNamespace pubSubNamespace) {
        this.ns = pubSubNamespace;
    }

    public PacketExtension getExtension(PubSubElementType pubSubElementType) {
        return getExtension(pubSubElementType.getElementName(), pubSubElementType.getNamespace().getXmlns());
    }

    public PubSubNamespace getPubSubNamespace() {
        return this.ns;
    }

    public String getChildElementXML() {
        return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\">" + getExtensionsXML() + "</" + getElementName() + ">";
    }
}
