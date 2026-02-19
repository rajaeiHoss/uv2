package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

public class SubscriptionsExtension extends NodeExtension {
    protected List<Subscription> items = Collections.EMPTY_LIST;

    public SubscriptionsExtension(List<Subscription> list) {
        super(PubSubElementType.SUBSCRIPTIONS);
        if (list != null) {
            this.items = list;
        }
    }

    public SubscriptionsExtension(String str, List<Subscription> list) {
        super(PubSubElementType.SUBSCRIPTIONS, str);
        if (list != null) {
            this.items = list;
        }
    }

    public List<Subscription> getSubscriptions() {
        return this.items;
    }

    public String toXML() {
        List<Subscription> list = this.items;
        if (list == null || list.size() == 0) {
            return super.toXML();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        if (getNode() != null) {
            sb.append(" node='");
            sb.append(getNode());
            sb.append("'");
        }
        sb.append(">");
        for (Subscription xml : this.items) {
            sb.append(xml.toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
