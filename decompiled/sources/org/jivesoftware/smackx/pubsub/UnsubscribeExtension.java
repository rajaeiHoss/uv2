package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.pubsub.util.XmlUtils;

public class UnsubscribeExtension extends NodeExtension {
    protected String id;
    protected String jid;

    public UnsubscribeExtension(String str) {
        this(str, (String) null, (String) null);
    }

    public UnsubscribeExtension(String str, String str2) {
        this(str, str2, (String) null);
    }

    public UnsubscribeExtension(String str, String str2, String str3) {
        super(PubSubElementType.UNSUBSCRIBE, str2);
        this.jid = str;
        this.id = str3;
    }

    public String getJid() {
        return this.jid;
    }

    public String getId() {
        return this.id;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getElementName());
        XmlUtils.appendAttribute(sb, "jid", this.jid);
        if (getNode() != null) {
            XmlUtils.appendAttribute(sb, "node", getNode());
        }
        String str = this.id;
        if (str != null) {
            XmlUtils.appendAttribute(sb, "subid", str);
        }
        sb.append("/>");
        return sb.toString();
    }
}
