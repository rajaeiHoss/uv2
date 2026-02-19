package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import org.jivesoftware.smackx.pubsub.Item;

public class PublishItem<T extends Item> extends NodeExtension {
    protected Collection<T> items;

    public PublishItem(String str, T t) {
        super(PubSubElementType.PUBLISH, str);
        ArrayList arrayList = new ArrayList(1);
        this.items = arrayList;
        arrayList.add(t);
    }

    public PublishItem(String str, Collection<T> collection) {
        super(PubSubElementType.PUBLISH, str);
        this.items = collection;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        sb.append(" node='");
        sb.append(getNode());
        sb.append("'>");
        for (T xml : this.items) {
            sb.append(xml.toXML());
        }
        sb.append("</publish>");
        return sb.toString();
    }
}
