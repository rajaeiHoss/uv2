package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smack.util.StringUtils;

public class RosterGroup {
    private Connection connection;
    private final List<RosterEntry> entries = new ArrayList();
    private String name;

    RosterGroup(String str, Connection connection2) {
        this.name = str;
        this.connection = connection2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        synchronized (this.entries) {
            for (RosterEntry rosterItem : this.entries) {
                RosterPacket rosterPacket = new RosterPacket();
                rosterPacket.setType(IQ.Type.SET);
                RosterPacket.Item rosterItem2 = RosterEntry.toRosterItem(rosterItem);
                rosterItem2.removeGroupName(this.name);
                rosterItem2.addGroupName(str);
                rosterPacket.addRosterItem(rosterItem2);
                this.connection.sendPacket(rosterPacket);
            }
        }
    }

    public int getEntryCount() {
        int size;
        synchronized (this.entries) {
            size = this.entries.size();
        }
        return size;
    }

    public Collection<RosterEntry> getEntries() {
        List unmodifiableList;
        synchronized (this.entries) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.entries));
        }
        return unmodifiableList;
    }

    public RosterEntry getEntry(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = StringUtils.parseBareAddress(str).toLowerCase();
        synchronized (this.entries) {
            for (RosterEntry next : this.entries) {
                if (next.getUser().equals(lowerCase)) {
                    return next;
                }
            }
            return null;
        }
    }

    public boolean contains(RosterEntry rosterEntry) {
        boolean contains;
        synchronized (this.entries) {
            contains = this.entries.contains(rosterEntry);
        }
        return contains;
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public void addEntry(RosterEntry rosterEntry) throws XMPPException {
        PacketCollector packetCollector;
        synchronized (this.entries) {
            if (!this.entries.contains(rosterEntry)) {
                RosterPacket rosterPacket = new RosterPacket();
                rosterPacket.setType(IQ.Type.SET);
                RosterPacket.Item rosterItem = RosterEntry.toRosterItem(rosterEntry);
                rosterItem.addGroupName(getName());
                rosterPacket.addRosterItem(rosterItem);
                packetCollector = this.connection.createPacketCollector(new PacketIDFilter(rosterPacket.getPacketID()));
                this.connection.sendPacket(rosterPacket);
            } else {
                packetCollector = null;
            }
        }
        if (packetCollector != null) {
            IQ iq = (IQ) packetCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            packetCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getType() == IQ.Type.ERROR) {
                throw new XMPPException(iq.getError());
            }
        }
    }

    public void removeEntry(RosterEntry rosterEntry) throws XMPPException {
        PacketCollector packetCollector;
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                RosterPacket rosterPacket = new RosterPacket();
                rosterPacket.setType(IQ.Type.SET);
                RosterPacket.Item rosterItem = RosterEntry.toRosterItem(rosterEntry);
                rosterItem.removeGroupName(getName());
                rosterPacket.addRosterItem(rosterItem);
                packetCollector = this.connection.createPacketCollector(new PacketIDFilter(rosterPacket.getPacketID()));
                this.connection.sendPacket(rosterPacket);
            } else {
                packetCollector = null;
            }
        }
        if (packetCollector != null) {
            IQ iq = (IQ) packetCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            packetCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getType() == IQ.Type.ERROR) {
                throw new XMPPException(iq.getError());
            }
        }
    }

    public void addEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            this.entries.remove(rosterEntry);
            this.entries.add(rosterEntry);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                this.entries.remove(rosterEntry);
            }
        }
    }
}
