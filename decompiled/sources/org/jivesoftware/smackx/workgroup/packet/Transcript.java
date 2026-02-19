package org.jivesoftware.smackx.workgroup.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;

public class Transcript extends IQ {
    private List<Packet> packets;
    private String sessionID;

    public Transcript(String str) {
        this.sessionID = str;
        this.packets = new ArrayList();
    }

    public Transcript(String str, List<Packet> list) {
        this.sessionID = str;
        this.packets = list;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public List<Packet> getPackets() {
        return Collections.unmodifiableList(this.packets);
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<transcript xmlns=\"http://jivesoftware.com/protocol/workgroup\" sessionID=\"");
        sb.append(this.sessionID);
        sb.append("\">");
        for (Packet xml : this.packets) {
            sb.append(xml.toXML());
        }
        sb.append("</transcript>");
        return sb.toString();
    }
}
