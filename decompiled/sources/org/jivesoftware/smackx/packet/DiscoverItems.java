package org.jivesoftware.smackx.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.packet.IQ;

public class DiscoverItems extends IQ {
    private final List<Item> items = new CopyOnWriteArrayList();
    private String node;

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public Iterator<Item> getItems() {
        Iterator<Item> it;
        synchronized (this.items) {
            it = Collections.unmodifiableList(this.items).iterator();
        }
        return it;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/disco#items\"");
        if (getNode() != null) {
            sb.append(" node=\"");
            sb.append(getNode());
            sb.append("\"");
        }
        sb.append(">");
        synchronized (this.items) {
            for (Item xml : this.items) {
                sb.append(xml.toXML());
            }
        }
        sb.append("</query>");
        return sb.toString();
    }

    public static class Item {
        public static final String REMOVE_ACTION = "remove";
        public static final String UPDATE_ACTION = "update";
        private String action;
        private String entityID;
        private String name;
        private String node;

        public Item(String str) {
            this.entityID = str;
        }

        public String getEntityID() {
            return this.entityID;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getNode() {
            return this.node;
        }

        public void setNode(String str) {
            this.node = str;
        }

        public String getAction() {
            return this.action;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item jid=\"");
            sb.append(this.entityID);
            sb.append("\"");
            if (this.name != null) {
                sb.append(" name=\"");
                sb.append(this.name);
                sb.append("\"");
            }
            if (this.node != null) {
                sb.append(" node=\"");
                sb.append(this.node);
                sb.append("\"");
            }
            if (this.action != null) {
                sb.append(" action=\"");
                sb.append(this.action);
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }
}
