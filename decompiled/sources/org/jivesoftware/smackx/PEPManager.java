package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.packet.PEPEvent;
import org.jivesoftware.smackx.packet.PEPItem;
import org.jivesoftware.smackx.packet.PEPPubSub;

public class PEPManager {
    private Connection connection;
    private PacketFilter packetFilter = new PacketExtensionFilter("event", "http://jabber.org/protocol/pubsub#event");
    private PacketListener packetListener;
    private List<PEPListener> pepListeners = new ArrayList();

    public PEPManager(Connection connection2) {
        this.connection = connection2;
        init();
    }

    public void addPEPListener(PEPListener pEPListener) {
        synchronized (this.pepListeners) {
            if (!this.pepListeners.contains(pEPListener)) {
                this.pepListeners.add(pEPListener);
            }
        }
    }

    public void removePEPListener(PEPListener pEPListener) {
        synchronized (this.pepListeners) {
            this.pepListeners.remove(pEPListener);
        }
    }

    public void publish(PEPItem pEPItem) {
        PEPPubSub pEPPubSub = new PEPPubSub(pEPItem);
        pEPPubSub.setType(IQ.Type.SET);
        this.connection.sendPacket(pEPPubSub);
    }

    /* access modifiers changed from: private */
    public void firePEPListeners(String str, PEPEvent pEPEvent) {
        int size;
        PEPListener[] pEPListenerArr;
        synchronized (this.pepListeners) {
            size = this.pepListeners.size();
            pEPListenerArr = new PEPListener[size];
            this.pepListeners.toArray(pEPListenerArr);
        }
        for (int i = 0; i < size; i++) {
            pEPListenerArr[i].eventReceived(str, pEPEvent);
        }
    }

    private void init() {
        PacketListener listener = new PacketListener() {
            public void processPacket(Packet packet) {
                Message message = (Message) packet;
                PEPManager.this.firePEPListeners(message.getFrom(), (PEPEvent) message.getExtension("event", "http://jabber.org/protocol/pubsub#event"));
            }
        };
        this.packetListener = listener;
        this.connection.addPacketListener(listener, this.packetFilter);
    }

    public void destroy() {
        Connection connection2 = this.connection;
        if (connection2 != null) {
            connection2.removePacketListener(this.packetListener);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
