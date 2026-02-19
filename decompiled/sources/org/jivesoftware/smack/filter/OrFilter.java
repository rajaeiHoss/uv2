package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

public class OrFilter implements PacketFilter {
    private PacketFilter[] filters;
    private int size;

    public OrFilter() {
        this.size = 0;
        this.filters = new PacketFilter[3];
    }

    public OrFilter(PacketFilter packetFilter, PacketFilter packetFilter2) {
        if (packetFilter == null || packetFilter2 == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        this.size = 2;
        PacketFilter[] packetFilterArr = new PacketFilter[2];
        this.filters = packetFilterArr;
        packetFilterArr[0] = packetFilter;
        packetFilterArr[1] = packetFilter2;
    }

    public void addFilter(PacketFilter packetFilter) {
        if (packetFilter != null) {
            int i = this.size;
            PacketFilter[] packetFilterArr = this.filters;
            if (i == packetFilterArr.length) {
                PacketFilter[] packetFilterArr2 = new PacketFilter[(packetFilterArr.length + 2)];
                int i2 = 0;
                while (true) {
                    PacketFilter[] packetFilterArr3 = this.filters;
                    if (i2 >= packetFilterArr3.length) {
                        break;
                    }
                    packetFilterArr2[i2] = packetFilterArr3[i2];
                    i2++;
                }
                this.filters = packetFilterArr2;
            }
            PacketFilter[] packetFilterArr4 = this.filters;
            int i3 = this.size;
            packetFilterArr4[i3] = packetFilter;
            this.size = i3 + 1;
            return;
        }
        throw new IllegalArgumentException("Parameter cannot be null.");
    }

    public boolean accept(Packet packet) {
        for (int i = 0; i < this.size; i++) {
            if (this.filters[i].accept(packet)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.filters.toString();
    }
}
