package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class OfflineMessageRequest extends IQ {
    private boolean fetch = false;
    private List items = new ArrayList();
    private boolean purge = false;

    public Iterator getItems() {
        Iterator it;
        synchronized (this.items) {
            it = Collections.unmodifiableList(new ArrayList(this.items)).iterator();
        }
        return it;
    }

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public boolean isPurge() {
        return this.purge;
    }

    public void setPurge(boolean z) {
        this.purge = z;
    }

    public boolean isFetch() {
        return this.fetch;
    }

    public void setFetch(boolean z) {
        this.fetch = z;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<offline xmlns=\"http://jabber.org/protocol/offline\">");
        synchronized (this.items) {
            for (int i = 0; i < this.items.size(); i++) {
                sb.append(((Item) this.items.get(i)).toXML());
            }
        }
        if (this.purge) {
            sb.append("<purge/>");
        }
        if (this.fetch) {
            sb.append("<fetch/>");
        }
        sb.append(getExtensionsXML());
        sb.append("</offline>");
        return sb.toString();
    }

    public static class Item {
        private String action;
        private String jid;
        private String node;

        public Item(String str) {
            this.node = str;
        }

        public String getNode() {
            return this.node;
        }

        public String getAction() {
            return this.action;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public String getJid() {
            return this.jid;
        }

        public void setJid(String str) {
            this.jid = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item");
            if (getAction() != null) {
                sb.append(" action=\"");
                sb.append(getAction());
                sb.append("\"");
            }
            if (getJid() != null) {
                sb.append(" jid=\"");
                sb.append(getJid());
                sb.append("\"");
            }
            if (getNode() != null) {
                sb.append(" node=\"");
                sb.append(getNode());
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            OfflineMessageRequest offlineMessageRequest = new OfflineMessageRequest();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        offlineMessageRequest.addItem(parseItem(xmlPullParser));
                    } else if (xmlPullParser.getName().equals("purge")) {
                        offlineMessageRequest.setPurge(true);
                    } else if (xmlPullParser.getName().equals("fetch")) {
                        offlineMessageRequest.setFetch(true);
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(MessageEvent.OFFLINE)) {
                    z = true;
                }
            }
            return offlineMessageRequest;
        }

        private Item parseItem(XmlPullParser xmlPullParser) throws Exception {
            Item item = new Item(xmlPullParser.getAttributeValue("", "node"));
            item.setAction(xmlPullParser.getAttributeValue("", "action"));
            item.setJid(xmlPullParser.getAttributeValue("", "jid"));
            boolean z = false;
            while (!z) {
                if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals("item")) {
                    z = true;
                }
            }
            return item;
        }
    }
}
