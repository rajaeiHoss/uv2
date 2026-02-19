package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

public class FromContainsFilter implements PacketFilter {
    private String from;

    public FromContainsFilter(String str) {
        if (str != null) {
            this.from = str.toLowerCase();
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        if (packet.getFrom() == null || packet.getFrom().toLowerCase().indexOf(this.from) == -1) {
            return false;
        }
        return true;
    }
}
