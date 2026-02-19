package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.PacketExtension;

public class Item implements PacketExtension {
    private String id;

    public String getElementName() {
        return "item";
    }

    public String getNamespace() {
        return null;
    }

    public Item() {
    }

    public Item(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<item");
        if (this.id != null) {
            sb.append(" id='");
            sb.append(this.id);
            sb.append("'");
        }
        sb.append("/>");
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " | Content [" + toXML() + "]";
    }
}
