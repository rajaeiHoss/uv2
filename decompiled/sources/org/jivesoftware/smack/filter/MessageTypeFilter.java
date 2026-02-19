package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class MessageTypeFilter implements PacketFilter {
    private final Message.Type type;

    public MessageTypeFilter(Message.Type type2) {
        this.type = type2;
    }

    public boolean accept(Packet packet) {
        if (!(packet instanceof Message)) {
            return false;
        }
        return ((Message) packet).getType().equals(this.type);
    }
}
