package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

public class NotFilter implements PacketFilter {
    private PacketFilter filter;

    public NotFilter(PacketFilter packetFilter) {
        if (packetFilter != null) {
            this.filter = packetFilter;
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        return !this.filter.accept(packet);
    }
}
