package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.PacketExtension;

public class PayloadItem<E extends PacketExtension> extends Item {
    private E payload;

    public PayloadItem(String str, E e) {
        super(str);
        if (e != null) {
            this.payload = e;
            return;
        }
        throw new IllegalArgumentException("payload cannot be 'null'");
    }

    public E getPayload() {
        return this.payload;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<item");
        if (getId() != null) {
            sb.append(" id='");
            sb.append(getId());
            sb.append("'");
        }
        sb.append(">");
        sb.append(this.payload.toXML());
        sb.append("</item>");
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " | Content [" + toXML() + "]";
    }
}
