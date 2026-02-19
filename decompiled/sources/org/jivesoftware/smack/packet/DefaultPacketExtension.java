package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultPacketExtension implements PacketExtension {
    private String elementName;
    private Map<String, String> map;
    private String namespace;

    public DefaultPacketExtension(String str, String str2) {
        this.elementName = str;
        this.namespace = str2;
    }

    public String getElementName() {
        return this.elementName;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.elementName);
        sb.append(" xmlns=\"");
        sb.append(this.namespace);
        sb.append("\">");
        for (String next : getNames()) {
            String value = getValue(next);
            sb.append("<");
            sb.append(next);
            sb.append(">");
            sb.append(value);
            sb.append("</");
            sb.append(next);
            sb.append(">");
        }
        sb.append("</");
        sb.append(this.elementName);
        sb.append(">");
        return sb.toString();
    }

    public synchronized Collection<String> getNames() {
        if (this.map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashMap(this.map).keySet());
    }

    public synchronized String getValue(String str) {
        Map<String, String> map2 = this.map;
        if (map2 == null) {
            return null;
        }
        return map2.get(str);
    }

    public synchronized void setValue(String str, String str2) {
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(str, str2);
    }
}
