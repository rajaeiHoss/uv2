package org.jivesoftware.smackx.packet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DefaultPrivateData implements PrivateData {
    private String elementName;
    private Map map;
    private String namespace;

    public DefaultPrivateData(String str, String str2) {
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
        Iterator names = getNames();
        while (names.hasNext()) {
            String str = (String) names.next();
            String value = getValue(str);
            sb.append("<");
            sb.append(str);
            sb.append(">");
            sb.append(value);
            sb.append("</");
            sb.append(str);
            sb.append(">");
        }
        sb.append("</");
        sb.append(this.elementName);
        sb.append(">");
        return sb.toString();
    }

    public synchronized Iterator getNames() {
        if (this.map == null) {
            return Collections.EMPTY_LIST.iterator();
        }
        return Collections.unmodifiableMap(new HashMap(this.map)).keySet().iterator();
    }

    public synchronized String getValue(String str) {
        Map map2 = this.map;
        if (map2 == null) {
            return null;
        }
        return (String) map2.get(str);
    }

    public synchronized void setValue(String str, String str2) {
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(str, str2);
    }
}
