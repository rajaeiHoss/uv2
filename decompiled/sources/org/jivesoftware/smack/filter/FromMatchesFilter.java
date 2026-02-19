package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;

public class FromMatchesFilter implements PacketFilter {
    private String address;
    private boolean matchBareJID = false;

    public FromMatchesFilter(String str) {
        if (str != null) {
            this.address = str.toLowerCase();
            this.matchBareJID = "".equals(StringUtils.parseResource(str));
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        if (packet.getFrom() == null) {
            return false;
        }
        if (this.matchBareJID) {
            return packet.getFrom().toLowerCase().startsWith(this.address);
        }
        return this.address.equals(packet.getFrom().toLowerCase());
    }

    public String toString() {
        return "FromMatchesFilter: " + this.address;
    }
}
