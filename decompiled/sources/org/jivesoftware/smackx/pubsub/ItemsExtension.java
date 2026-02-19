package org.jivesoftware.smackx.pubsub;

import java.util.List;
import org.jivesoftware.smack.packet.PacketExtension;

public class ItemsExtension extends NodeExtension implements EmbeddedPacketExtension {
    protected List<? extends PacketExtension> items;
    protected Boolean notify;
    protected ItemsElementType type;

    public enum ItemsElementType {
        items(PubSubElementType.ITEMS, "max_items"),
        retract(PubSubElementType.RETRACT, "notify");
        
        private String att;
        private PubSubElementType elem;

        private ItemsElementType(PubSubElementType pubSubElementType, String str) {
            this.elem = pubSubElementType;
            this.att = str;
        }

        public PubSubElementType getNodeElement() {
            return this.elem;
        }

        public String getElementAttribute() {
            return this.att;
        }
    }

    public ItemsExtension(ItemsElementType itemsElementType, String str, List<? extends PacketExtension> list) {
        super(itemsElementType.getNodeElement(), str);
        this.type = itemsElementType;
        this.items = list;
    }

    public ItemsExtension(String str, List<? extends PacketExtension> list, boolean z) {
        super(ItemsElementType.retract.getNodeElement(), str);
        this.type = ItemsElementType.retract;
        this.items = list;
        this.notify = Boolean.valueOf(z);
    }

    public ItemsElementType getItemsElementType() {
        return this.type;
    }

    public List<PacketExtension> getExtensions() {
        return (List<PacketExtension>) this.items;
    }

    public List<? extends PacketExtension> getItems() {
        return this.items;
    }

    public boolean getNotify() {
        return this.notify.booleanValue();
    }

    public String toXML() {
        List<? extends PacketExtension> list = this.items;
        if (list == null || list.size() == 0) {
            return super.toXML();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        sb.append(" node='");
        sb.append(getNode());
        if (this.notify != null) {
            sb.append("' ");
            sb.append(this.type.getElementAttribute());
            sb.append("='");
            sb.append(this.notify.equals(Boolean.TRUE) ? 1 : 0);
            sb.append("'>");
        } else {
            sb.append("'>");
            for (PacketExtension xml : this.items) {
                sb.append(xml.toXML());
            }
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + "Content [" + toXML() + "]";
    }
}
