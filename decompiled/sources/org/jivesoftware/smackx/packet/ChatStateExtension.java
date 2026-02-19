package org.jivesoftware.smackx.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.ChatState;
import org.xmlpull.v1.XmlPullParser;

public class ChatStateExtension implements PacketExtension {
    private ChatState state;

    public String getNamespace() {
        return "http://jabber.org/protocol/chatstates";
    }

    public ChatStateExtension(ChatState chatState) {
        this.state = chatState;
    }

    public String getElementName() {
        return this.state.name();
    }

    public String toXML() {
        return "<" + getElementName() + " xmlns=\"" + getNamespace() + "\" />";
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            ChatState chatState;
            try {
                chatState = ChatState.valueOf(xmlPullParser.getName());
            } catch (Exception unused) {
                chatState = ChatState.active;
            }
            return new ChatStateExtension(chatState);
        }
    }
}
