package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.GroupChatInvitation;

public class DeafOccupantInterceptor implements PacketInterceptor {
    public void interceptPacket(Packet packet) {
        Presence presence = (Presence) packet;
        if (Presence.Type.available == presence.getType() && presence.getExtension(GroupChatInvitation.ELEMENT_NAME, "http://jabber.org/protocol/muc") != null) {
            packet.addExtension(new DeafExtension());
        }
    }

    private static class DeafExtension implements PacketExtension {
        public String getElementName() {
            return GroupChatInvitation.ELEMENT_NAME;
        }

        public String getNamespace() {
            return "http://jivesoftware.org/protocol/muc";
        }

        private DeafExtension() {
        }

        public String toXML() {
            return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\">" + "<deaf-occupant/>" + "</" + getElementName() + ">";
        }
    }
}
