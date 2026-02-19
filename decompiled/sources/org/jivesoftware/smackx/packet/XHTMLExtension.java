package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.PacketExtension;

public class XHTMLExtension implements PacketExtension {
    private List bodies = new ArrayList();

    public String getElementName() {
        return "html";
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/xhtml-im";
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        Iterator bodies2 = getBodies();
        while (bodies2.hasNext()) {
            sb.append((String) bodies2.next());
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }

    public Iterator getBodies() {
        Iterator it;
        synchronized (this.bodies) {
            it = Collections.unmodifiableList(new ArrayList(this.bodies)).iterator();
        }
        return it;
    }

    public void addBody(String str) {
        synchronized (this.bodies) {
            this.bodies.add(str);
        }
    }

    public int getBodiesCount() {
        return this.bodies.size();
    }
}
