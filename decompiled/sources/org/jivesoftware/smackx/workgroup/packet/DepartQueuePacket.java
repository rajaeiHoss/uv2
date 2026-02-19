package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.IQ;

public class DepartQueuePacket extends IQ {
    private String user;

    public DepartQueuePacket(String str) {
        this(str, (String) null);
    }

    public DepartQueuePacket(String str, String str2) {
        this.user = str2;
        setTo(str);
        setType(IQ.Type.SET);
        setFrom(str2);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder("<depart-queue xmlns=\"http://jabber.org/protocol/workgroup\"");
        if (this.user != null) {
            sb.append("><jid>");
            sb.append(this.user);
            sb.append("</jid></depart-queue>");
        } else {
            sb.append("/>");
        }
        return sb.toString();
    }
}
