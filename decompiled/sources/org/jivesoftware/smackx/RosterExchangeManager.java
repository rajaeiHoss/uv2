package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.packet.RosterExchange;

public class RosterExchangeManager {
    private Connection con;
    private PacketFilter packetFilter = new PacketExtensionFilter(GroupChatInvitation.ELEMENT_NAME, "jabber:x:roster");
    private PacketListener packetListener;
    private List<RosterExchangeListener> rosterExchangeListeners = new ArrayList();

    public RosterExchangeManager(Connection connection) {
        this.con = connection;
        init();
    }

    public void addRosterListener(RosterExchangeListener rosterExchangeListener) {
        synchronized (this.rosterExchangeListeners) {
            if (!this.rosterExchangeListeners.contains(rosterExchangeListener)) {
                this.rosterExchangeListeners.add(rosterExchangeListener);
            }
        }
    }

    public void removeRosterListener(RosterExchangeListener rosterExchangeListener) {
        synchronized (this.rosterExchangeListeners) {
            this.rosterExchangeListeners.remove(rosterExchangeListener);
        }
    }

    public void send(Roster roster, String str) {
        Message message = new Message(str);
        message.addExtension(new RosterExchange(roster));
        this.con.sendPacket(message);
    }

    public void send(RosterEntry rosterEntry, String str) {
        Message message = new Message(str);
        RosterExchange rosterExchange = new RosterExchange();
        rosterExchange.addRosterEntry(rosterEntry);
        message.addExtension(rosterExchange);
        this.con.sendPacket(message);
    }

    public void send(RosterGroup rosterGroup, String str) {
        Message message = new Message(str);
        RosterExchange rosterExchange = new RosterExchange();
        for (RosterEntry addRosterEntry : rosterGroup.getEntries()) {
            rosterExchange.addRosterEntry(addRosterEntry);
        }
        message.addExtension(rosterExchange);
        this.con.sendPacket(message);
    }

    /* access modifiers changed from: private */
    public void fireRosterExchangeListeners(String str, Iterator it) {
        int size;
        RosterExchangeListener[] rosterExchangeListenerArr;
        synchronized (this.rosterExchangeListeners) {
            size = this.rosterExchangeListeners.size();
            rosterExchangeListenerArr = new RosterExchangeListener[size];
            this.rosterExchangeListeners.toArray(rosterExchangeListenerArr);
        }
        for (int i = 0; i < size; i++) {
            rosterExchangeListenerArr[i].entriesReceived(str, it);
        }
    }

    private void init() {
        PacketListener listener = new PacketListener() {
            public void processPacket(Packet packet) {
                Message message = (Message) packet;
                RosterExchangeManager.this.fireRosterExchangeListeners(message.getFrom(), ((RosterExchange) message.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:roster")).getRosterEntries());
            }
        };
        this.packetListener = listener;
        this.con.addPacketListener(listener, this.packetFilter);
    }

    public void destroy() {
        Connection connection = this.con;
        if (connection != null) {
            connection.removePacketListener(this.packetListener);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
