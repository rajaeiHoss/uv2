package org.jivesoftware.smack;

import java.util.List;
import org.jivesoftware.smack.packet.RosterPacket;

public interface RosterStorage {
    void addEntry(RosterPacket.Item item, String str);

    List<RosterPacket.Item> getEntries();

    RosterPacket.Item getEntry(String str);

    int getEntryCount();

    String getRosterVersion();

    void removeEntry(String str);

    void updateLocalEntry(RosterPacket.Item item);
}
