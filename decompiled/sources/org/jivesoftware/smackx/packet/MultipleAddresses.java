package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.PacketExtension;

public class MultipleAddresses implements PacketExtension {
    public static final String BCC = "bcc";
    public static final String CC = "cc";
    public static final String NO_REPLY = "noreply";
    public static final String REPLY_ROOM = "replyroom";
    public static final String REPLY_TO = "replyto";
    public static final String TO = "to";
    private List<Address> addresses = new ArrayList();

    public String getElementName() {
        return "addresses";
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/address";
    }

    public void addAddress(String str, String str2, String str3, String str4, boolean z, String str5) {
        Address address = new Address(str);
        address.setJid(str2);
        address.setNode(str3);
        address.setDescription(str4);
        address.setDelivered(z);
        address.setUri(str5);
        this.addresses.add(address);
    }

    public void setNoReply() {
        this.addresses.add(new Address(NO_REPLY));
    }

    public List<Address> getAddressesOfType(String str) {
        ArrayList<Address> arrayList = new ArrayList<>(this.addresses.size());
        for (Address address : this.addresses) {
            if (address.getType().equals(str)) {
                arrayList.add(address);
            }
        }
        return arrayList;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        for (Address access$600 : this.addresses) {
            sb.append(access$600.toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public static class Address {
        private boolean delivered;
        private String description;
        private String jid;
        private String node;
        private String type;
        private String uri;

        private Address(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }

        public String getJid() {
            return this.jid;
        }

        /* access modifiers changed from: private */
        public void setJid(String str) {
            this.jid = str;
        }

        public String getNode() {
            return this.node;
        }

        /* access modifiers changed from: private */
        public void setNode(String str) {
            this.node = str;
        }

        public String getDescription() {
            return this.description;
        }

        /* access modifiers changed from: private */
        public void setDescription(String str) {
            this.description = str;
        }

        public boolean isDelivered() {
            return this.delivered;
        }

        /* access modifiers changed from: private */
        public void setDelivered(boolean z) {
            this.delivered = z;
        }

        public String getUri() {
            return this.uri;
        }

        /* access modifiers changed from: private */
        public void setUri(String str) {
            this.uri = str;
        }

        /* access modifiers changed from: private */
        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<address type=\"");
            sb.append(this.type);
            sb.append("\"");
            if (this.jid != null) {
                sb.append(" jid=\"");
                sb.append(this.jid);
                sb.append("\"");
            }
            if (this.node != null) {
                sb.append(" node=\"");
                sb.append(this.node);
                sb.append("\"");
            }
            String str = this.description;
            if (str != null && str.trim().length() > 0) {
                sb.append(" desc=\"");
                sb.append(this.description);
                sb.append("\"");
            }
            if (this.delivered) {
                sb.append(" delivered=\"true\"");
            }
            if (this.uri != null) {
                sb.append(" uri=\"");
                sb.append(this.uri);
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }
}
