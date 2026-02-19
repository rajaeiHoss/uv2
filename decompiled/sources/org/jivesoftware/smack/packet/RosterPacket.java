package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.util.StringUtils;

public class RosterPacket extends IQ {
    private final List<Item> rosterItems = new ArrayList();
    private String version;

    public enum ItemType {
        none,
        to,
        from,
        both,
        remove
    }

    public void addRosterItem(Item item) {
        synchronized (this.rosterItems) {
            this.rosterItems.add(item);
        }
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public int getRosterItemCount() {
        int size;
        synchronized (this.rosterItems) {
            size = this.rosterItems.size();
        }
        return size;
    }

    public Collection<Item> getRosterItems() {
        List unmodifiableList;
        synchronized (this.rosterItems) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.rosterItems));
        }
        return unmodifiableList;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"jabber:iq:roster\" ");
        if (this.version != null) {
            sb.append(" ver=\"" + this.version + "\" ");
        }
        sb.append(">");
        synchronized (this.rosterItems) {
            for (Item xml : this.rosterItems) {
                sb.append(xml.toXML());
            }
        }
        sb.append("</query>");
        return sb.toString();
    }

    public static class Item {
        private final Set<String> groupNames = new CopyOnWriteArraySet();
        private ItemStatus itemStatus = null;
        private ItemType itemType = null;
        private String name;
        private String user;

        public Item(String str, String str2) {
            this.user = str.toLowerCase();
            this.name = str2;
        }

        public String getUser() {
            return this.user;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public ItemType getItemType() {
            return this.itemType;
        }

        public void setItemType(ItemType itemType2) {
            this.itemType = itemType2;
        }

        public ItemStatus getItemStatus() {
            return this.itemStatus;
        }

        public void setItemStatus(ItemStatus itemStatus2) {
            this.itemStatus = itemStatus2;
        }

        public Set<String> getGroupNames() {
            return Collections.unmodifiableSet(this.groupNames);
        }

        public void addGroupName(String str) {
            this.groupNames.add(str);
        }

        public void removeGroupName(String str) {
            this.groupNames.remove(str);
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item jid=\"");
            sb.append(this.user);
            sb.append("\"");
            if (this.name != null) {
                sb.append(" name=\"");
                sb.append(StringUtils.escapeForXML(this.name));
                sb.append("\"");
            }
            if (this.itemType != null) {
                sb.append(" subscription=\"");
                sb.append(this.itemType);
                sb.append("\"");
            }
            if (this.itemStatus != null) {
                sb.append(" ask=\"");
                sb.append(this.itemStatus);
                sb.append("\"");
            }
            sb.append(">");
            for (String escapeForXML : this.groupNames) {
                sb.append("<group>");
                sb.append(StringUtils.escapeForXML(escapeForXML));
                sb.append("</group>");
            }
            sb.append("</item>");
            return sb.toString();
        }
    }

    public static class ItemStatus {
        public static final ItemStatus SUBSCRIPTION_PENDING = new ItemStatus("subscribe");
        public static final ItemStatus UNSUBSCRIPTION_PENDING = new ItemStatus("unsubscribe");
        private String value;

        public static ItemStatus fromString(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if ("unsubscribe".equals(lowerCase)) {
                return UNSUBSCRIPTION_PENDING;
            }
            if ("subscribe".equals(lowerCase)) {
                return SUBSCRIPTION_PENDING;
            }
            return null;
        }

        private ItemStatus(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }
}
