package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

public class AffiliationsExtension extends NodeExtension {
    protected List<Affiliation> items = Collections.EMPTY_LIST;

    public AffiliationsExtension() {
        super(PubSubElementType.AFFILIATIONS);
    }

    public AffiliationsExtension(List<Affiliation> list) {
        super(PubSubElementType.AFFILIATIONS);
        this.items = list;
    }

    public List<Affiliation> getAffiliations() {
        return this.items;
    }

    public String toXML() {
        List<Affiliation> list = this.items;
        if (list == null || list.size() == 0) {
            return super.toXML();
        }
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        sb.append(">");
        for (Affiliation xml : this.items) {
            sb.append(xml.toXML());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
