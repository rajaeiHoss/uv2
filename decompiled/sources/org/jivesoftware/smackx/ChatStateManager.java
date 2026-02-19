package org.jivesoftware.smackx;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.util.collections.ReferenceMap;
import org.jivesoftware.smackx.packet.ChatStateExtension;

public class ChatStateManager {
    private static final PacketFilter filter = new NotFilter(new PacketExtensionFilter("http://jabber.org/protocol/chatstates"));
    private static final Map<Connection, ChatStateManager> managers = new WeakHashMap();
    private final Map<Chat, ChatState> chatStates = new ReferenceMap(2, 0);
    /* access modifiers changed from: private */
    public final Connection connection;
    private final IncomingMessageInterceptor incomingInterceptor = new IncomingMessageInterceptor();
    private final OutgoingMessageInterceptor outgoingInterceptor = new OutgoingMessageInterceptor();

    public static ChatStateManager getInstance(Connection connection2) {
        ChatStateManager chatStateManager;
        if (connection2 == null) {
            return null;
        }
        Map<Connection, ChatStateManager> map = managers;
        synchronized (map) {
            chatStateManager = map.get(connection2);
            if (chatStateManager == null) {
                chatStateManager = new ChatStateManager(connection2);
                chatStateManager.init();
                map.put(connection2, chatStateManager);
            }
        }
        return chatStateManager;
    }

    private ChatStateManager(Connection connection2) {
        this.connection = connection2;
    }

    private void init() {
        this.connection.getChatManager().addOutgoingMessageInterceptor(this.outgoingInterceptor, filter);
        this.connection.getChatManager().addChatListener(this.incomingInterceptor);
        ServiceDiscoveryManager.getInstanceFor(this.connection).addFeature("http://jabber.org/protocol/chatstates");
    }

    public void setCurrentState(ChatState chatState, Chat chat) throws XMPPException {
        if (chat == null || chatState == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        } else if (updateChatState(chat, chatState)) {
            Message message = new Message();
            message.addExtension(new ChatStateExtension(chatState));
            chat.sendMessage(message);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.connection.equals(((ChatStateManager) obj).connection);
    }

    public int hashCode() {
        return this.connection.hashCode();
    }

    /* access modifiers changed from: private */
    public boolean updateChatState(Chat chat, ChatState chatState) {
        if (this.chatStates.get(chat) == chatState) {
            return false;
        }
        this.chatStates.put(chat, chatState);
        return true;
    }

    /* access modifiers changed from: private */
    public void fireNewChatState(Chat chat, ChatState chatState) {
        for (MessageListener next : chat.getListeners()) {
            if (next instanceof ChatStateListener) {
                ((ChatStateListener) next).stateChanged(chat, chatState);
            }
        }
    }

    private class OutgoingMessageInterceptor implements PacketInterceptor {
        private OutgoingMessageInterceptor() {
        }

        public void interceptPacket(Packet packet) {
            Message message = (Message) packet;
            Chat threadChat = ChatStateManager.this.connection.getChatManager().getThreadChat(message.getThread());
            if (threadChat != null && ChatStateManager.this.updateChatState(threadChat, ChatState.active)) {
                message.addExtension(new ChatStateExtension(ChatState.active));
            }
        }
    }

    private class IncomingMessageInterceptor implements ChatManagerListener, MessageListener {
        private IncomingMessageInterceptor() {
        }

        public void chatCreated(Chat chat, boolean z) {
            chat.addMessageListener(this);
        }

        public void processMessage(Chat chat, Message message) {
            PacketExtension extension = message.getExtension("http://jabber.org/protocol/chatstates");
            if (extension != null) {
                try {
                    ChatStateManager.this.fireNewChatState(chat, ChatState.valueOf(extension.getElementName()));
                } catch (Exception unused) {
                }
            }
        }
    }
}
