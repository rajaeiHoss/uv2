package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

public class ToContainsFilter implements PacketFilter {
    private String to;

    public ToContainsFilter(String str) {
        if (str != null) {
            this.to = str.toLowerCase();
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        if (packet.getTo() == null || packet.getTo().toLowerCase().indexOf(this.to) == -1) {
            return false;
        }
        return true;
    }
}
