package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;

public class IQTypeFilter implements PacketFilter {
    private IQ.Type type;

    public IQTypeFilter(IQ.Type type2) {
        this.type = type2;
    }

    public boolean accept(Packet packet) {
        return (packet instanceof IQ) && ((IQ) packet).getType().equals(this.type);
    }
}
