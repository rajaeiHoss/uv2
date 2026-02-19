package org.jivesoftware.smackx.packet;

import java.util.Collection;
import java.util.Collections;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.PacketExtension;

public class HeadersExtension implements PacketExtension {
    public static final String NAMESPACE = "http://jabber.org/protocol/shim";
    private Collection<Header> headers = Collections.EMPTY_LIST;

    public String getElementName() {
        return "headers";
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public HeadersExtension(Collection<Header> collection) {
        if (collection != null) {
            this.headers = collection;
        }
    }

    public Collection<Header> getHeaders() {
        return this.headers;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<" + getElementName() + " xmlns='" + getNamespace() + "'>");
        for (Header xml : this.headers) {
            sb.append(xml.toXML());
        }
        sb.append("</" + getElementName() + Typography.greater);
        return sb.toString();
    }
}
