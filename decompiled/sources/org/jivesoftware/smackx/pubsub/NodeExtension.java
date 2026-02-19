package org.jivesoftware.smackx.pubsub;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.PacketExtension;

public class NodeExtension implements PacketExtension {
    private PubSubElementType element;
    private String node;

    public NodeExtension(PubSubElementType pubSubElementType, String str) {
        this.element = pubSubElementType;
        this.node = str;
    }

    public NodeExtension(PubSubElementType pubSubElementType) {
        this(pubSubElementType, (String) null);
    }

    public String getNode() {
        return this.node;
    }

    public String getElementName() {
        return this.element.getElementName();
    }

    public String getNamespace() {
        return this.element.getNamespace().getXmlns();
    }

    public String toXML() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.less);
        sb.append(getElementName());
        if (this.node == null) {
            str = "";
        } else {
            str = " node='" + this.node + '\'';
        }
        sb.append(str);
        sb.append("/>");
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " - content [" + toXML() + "]";
    }
}
