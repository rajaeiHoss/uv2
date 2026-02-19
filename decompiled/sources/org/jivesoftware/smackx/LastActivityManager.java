package org.jivesoftware.smackx;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.IQTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.packet.LastActivity;

public class LastActivityManager {
    /* access modifiers changed from: private */
    public Connection connection;
    private long lastMessageSent;

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                new LastActivityManager(connection);
            }
        });
    }

    private LastActivityManager(Connection connection2) {
        this.connection = connection2;
        connection2.addPacketSendingListener(new PacketListener() {
            public void processPacket(Packet packet) {
                LastActivityManager.this.resetIdleTime();
            }
        }, (PacketFilter) null);
        connection2.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                LastActivity lastActivity = new LastActivity();
                lastActivity.setType(IQ.Type.RESULT);
                lastActivity.setTo(packet.getFrom());
                lastActivity.setFrom(packet.getTo());
                lastActivity.setPacketID(packet.getPacketID());
                lastActivity.setLastActivity(LastActivityManager.this.getIdleTime());
                LastActivityManager.this.connection.sendPacket(lastActivity);
            }
        }, new AndFilter(new IQTypeFilter(IQ.Type.GET), new PacketTypeFilter(LastActivity.class)));
    }

    /* access modifiers changed from: private */
    public void resetIdleTime() {
        this.lastMessageSent = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public long getIdleTime() {
        return (System.currentTimeMillis() - this.lastMessageSent) / 1000;
    }

    public static LastActivity getLastActivity(Connection connection2, String str) throws XMPPException {
        LastActivity lastActivity = new LastActivity();
        lastActivity.setTo(str);
        PacketCollector createPacketCollector = connection2.createPacketCollector(new PacketIDFilter(lastActivity.getPacketID()));
        connection2.sendPacket(lastActivity);
        LastActivity lastActivity2 = (LastActivity) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (lastActivity2 == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (lastActivity2.getError() == null) {
            return lastActivity2;
        } else {
            throw new XMPPException(lastActivity2.getError());
        }
    }
}
