package org.jivesoftware.smackx.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PacketExtension;

public class DiscoverInfo extends IQ {
    private final List<Feature> features = new CopyOnWriteArrayList();
    private final List<Identity> identities = new CopyOnWriteArrayList();
    private String node;

    public void addFeature(String str) {
        addFeature(new Feature(str));
    }

    private void addFeature(Feature feature) {
        synchronized (this.features) {
            this.features.add(feature);
        }
    }

    public Iterator<Feature> getFeatures() {
        Iterator<Feature> it;
        synchronized (this.features) {
            it = Collections.unmodifiableList(this.features).iterator();
        }
        return it;
    }

    public void addIdentity(Identity identity) {
        synchronized (this.identities) {
            this.identities.add(identity);
        }
    }

    public Iterator<Identity> getIdentities() {
        Iterator<Identity> it;
        synchronized (this.identities) {
            it = Collections.unmodifiableList(this.identities).iterator();
        }
        return it;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public boolean containsFeature(String str) {
        Iterator<Feature> features2 = getFeatures();
        while (features2.hasNext()) {
            if (str.equals(features2.next().getVar())) {
                return true;
            }
        }
        return false;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"http://jabber.org/protocol/disco#info\"");
        if (getNode() != null) {
            sb.append(" node=\"");
            sb.append(getNode());
            sb.append("\"");
        }
        sb.append(">");
        synchronized (this.identities) {
            for (Identity xml : this.identities) {
                sb.append(xml.toXML());
            }
        }
        synchronized (this.features) {
            for (Feature xml2 : this.features) {
                sb.append(xml2.toXML());
            }
        }
        sb.append(getExtensionsXML());
        sb.append("</query>");
        return sb.toString();
    }

    public DiscoverInfo clone() {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setNode(getNode());
        synchronized (this.features) {
            for (Feature addFeature : this.features) {
                discoverInfo.addFeature(addFeature);
            }
        }
        synchronized (this.identities) {
            for (Identity addIdentity : this.identities) {
                discoverInfo.addIdentity(addIdentity);
            }
        }
        for (PacketExtension addExtension : getExtensions()) {
            discoverInfo.addExtension(addExtension);
        }
        return discoverInfo;
    }

    public static class Identity {
        private String category;
        private String name;
        private String type;

        public Identity(String str, String str2) {
            this.category = str;
            this.name = str2;
        }

        public String getCategory() {
            return this.category;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<identity category=\"");
            sb.append(this.category);
            sb.append("\"");
            sb.append(" name=\"");
            sb.append(this.name);
            sb.append("\"");
            if (this.type != null) {
                sb.append(" type=\"");
                sb.append(this.type);
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }
    }

    public static class Feature {
        private String variable;

        public Feature(String str) {
            this.variable = str;
        }

        public String getVar() {
            return this.variable;
        }

        public String toXML() {
            return "<feature var=\"" + this.variable + "\"/>";
        }
    }
}
