package org.jivesoftware.smack.filter;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.Packet;

public class AndFilter implements PacketFilter {
    private List<PacketFilter> filters = new ArrayList();

    public AndFilter() {
    }

    public AndFilter(PacketFilter... packetFilterArr) {
        if (packetFilterArr != null) {
            int length = packetFilterArr.length;
            int i = 0;
            while (i < length) {
                PacketFilter packetFilter = packetFilterArr[i];
                if (packetFilter != null) {
                    this.filters.add(packetFilter);
                    i++;
                } else {
                    throw new IllegalArgumentException("Parameter cannot be null.");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public void addFilter(PacketFilter packetFilter) {
        if (packetFilter != null) {
            this.filters.add(packetFilter);
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        for (PacketFilter accept : this.filters) {
            if (!accept.accept(packet)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return this.filters.toString();
    }
}
