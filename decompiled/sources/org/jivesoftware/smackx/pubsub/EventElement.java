package org.jivesoftware.smackx.pubsub;

import java.util.Arrays;
import java.util.List;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public class EventElement implements EmbeddedPacketExtension {
    private NodeExtension ext;
    private EventElementType type;

    public String getElementName() {
        return "event";
    }

    public EventElement(EventElementType eventElementType, NodeExtension nodeExtension) {
        this.type = eventElementType;
        this.ext = nodeExtension;
    }

    public EventElementType getEventType() {
        return this.type;
    }

    public List<PacketExtension> getExtensions() {
        return Arrays.asList(new PacketExtension[]{getEvent()});
    }

    public NodeExtension getEvent() {
        return this.ext;
    }

    public String getNamespace() {
        return PubSubNamespace.EVENT.getXmlns();
    }

    public String toXML() {
        return ("<event xmlns='" + PubSubNamespace.EVENT.getXmlns() + "'>") + this.ext.toXML() + "</event>";
    }
}
