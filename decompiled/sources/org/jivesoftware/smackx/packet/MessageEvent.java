package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Iterator;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.GroupChatInvitation;

public class MessageEvent implements PacketExtension {
    public static final String CANCELLED = "cancelled";
    public static final String COMPOSING = "composing";
    public static final String DELIVERED = "delivered";
    public static final String DISPLAYED = "displayed";
    public static final String OFFLINE = "offline";
    private boolean cancelled = true;
    private boolean composing = false;
    private boolean delivered = false;
    private boolean displayed = false;
    private boolean offline = false;
    private String packetID = null;

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "jabber:x:event";
    }

    public boolean isComposing() {
        return this.composing;
    }

    public boolean isDelivered() {
        return this.delivered;
    }

    public boolean isDisplayed() {
        return this.displayed;
    }

    public boolean isOffline() {
        return this.offline;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public String getPacketID() {
        return this.packetID;
    }

    public Iterator getEventTypes() {
        ArrayList arrayList = new ArrayList();
        if (isDelivered()) {
            arrayList.add(DELIVERED);
        }
        if (!isMessageEventRequest() && isCancelled()) {
            arrayList.add(CANCELLED);
        }
        if (isComposing()) {
            arrayList.add(COMPOSING);
        }
        if (isDisplayed()) {
            arrayList.add(DISPLAYED);
        }
        if (isOffline()) {
            arrayList.add(OFFLINE);
        }
        return arrayList.iterator();
    }

    public void setComposing(boolean z) {
        this.composing = z;
        setCancelled(false);
    }

    public void setDelivered(boolean z) {
        this.delivered = z;
        setCancelled(false);
    }

    public void setDisplayed(boolean z) {
        this.displayed = z;
        setCancelled(false);
    }

    public void setOffline(boolean z) {
        this.offline = z;
        setCancelled(false);
    }

    public void setCancelled(boolean z) {
        this.cancelled = z;
    }

    public void setPacketID(String str) {
        this.packetID = str;
    }

    public boolean isMessageEventRequest() {
        return this.packetID == null;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (isOffline()) {
            sb.append("<");
            sb.append(OFFLINE);
            sb.append("/>");
        }
        if (isDelivered()) {
            sb.append("<");
            sb.append(DELIVERED);
            sb.append("/>");
        }
        if (isDisplayed()) {
            sb.append("<");
            sb.append(DISPLAYED);
            sb.append("/>");
        }
        if (isComposing()) {
            sb.append("<");
            sb.append(COMPOSING);
            sb.append("/>");
        }
        if (getPacketID() != null) {
            sb.append("<id>");
            sb.append(getPacketID());
            sb.append("</id>");
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
