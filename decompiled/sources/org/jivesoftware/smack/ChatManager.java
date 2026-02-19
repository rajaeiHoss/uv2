package org.jivesoftware.smack;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.ThreadFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.collections.ReferenceMap;

public class ChatManager {
    private static long id = 0;
    private static String prefix = StringUtils.randomString(5);
    private Set<ChatManagerListener> chatManagerListeners = new CopyOnWriteArraySet();
    private Connection connection;
    private Map<PacketInterceptor, PacketFilter> interceptors = new WeakHashMap();
    private Map<String, Chat> jidChats = new ReferenceMap(0, 2);
    private Map<String, Chat> threadChats = new ReferenceMap(0, 2);

    private static synchronized String nextID() {
        String sb;
        synchronized (ChatManager.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(prefix);
            long j = id;
            id = 1 + j;
            sb2.append(Long.toString(j));
            sb = sb2.toString();
        }
        return sb;
    }

    ChatManager(Connection connection2) {
        this.connection = connection2;
        connection2.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                Chat chat;
                Message message = (Message) packet;
                if (message.getThread() == null) {
                    chat = ChatManager.this.getUserChat(message.getFrom());
                } else {
                    chat = ChatManager.this.getThreadChat(message.getThread());
                    if (chat == null) {
                        chat = ChatManager.this.getUserChat(message.getFrom());
                    }
                }
                if (chat == null) {
                    chat = ChatManager.this.createChat(message);
                }
                ChatManager.this.deliverMessage(chat, message);
            }
        }, new PacketFilter() {
            public boolean accept(Packet packet) {
                Message.Type type;
                if (!(packet instanceof Message) || (type = ((Message) packet).getType()) == Message.Type.groupchat || type == Message.Type.headline) {
                    return false;
                }
                return true;
            }
        });
    }

    public Chat createChat(String str, MessageListener messageListener) {
        String nextID;
        do {
            nextID = nextID();
        } while (this.threadChats.get(nextID) != null);
        return createChat(str, nextID, messageListener);
    }

    public Chat createChat(String str, String str2, MessageListener messageListener) {
        if (str2 == null) {
            str2 = nextID();
        }
        if (this.threadChats.get(str2) == null) {
            Chat createChat = createChat(str, str2, true);
            createChat.addMessageListener(messageListener);
            return createChat;
        }
        throw new IllegalArgumentException("ThreadID is already used");
    }

    private Chat createChat(String str, String str2, boolean z) {
        Chat chat = new Chat(this, str, str2);
        this.threadChats.put(str2, chat);
        this.jidChats.put(str, chat);
        for (ChatManagerListener chatCreated : this.chatManagerListeners) {
            chatCreated.chatCreated(chat, z);
        }
        return chat;
    }

    /* access modifiers changed from: private */
    public Chat createChat(Message message) {
        String thread = message.getThread();
        if (thread == null) {
            thread = nextID();
        }
        return createChat(message.getFrom(), thread, false);
    }

    /* access modifiers changed from: private */
    public Chat getUserChat(String str) {
        return this.jidChats.get(str);
    }

    public Chat getThreadChat(String str) {
        return this.threadChats.get(str);
    }

    public void addChatListener(ChatManagerListener chatManagerListener) {
        this.chatManagerListeners.add(chatManagerListener);
    }

    public void removeChatListener(ChatManagerListener chatManagerListener) {
        this.chatManagerListeners.remove(chatManagerListener);
    }

    public Collection<ChatManagerListener> getChatListeners() {
        return Collections.unmodifiableCollection(this.chatManagerListeners);
    }

    /* access modifiers changed from: private */
    public void deliverMessage(Chat chat, Message message) {
        chat.deliver(message);
    }

    /* access modifiers changed from: package-private */
    public void sendMessage(Chat chat, Message message) {
        for (Map.Entry next : this.interceptors.entrySet()) {
            PacketFilter packetFilter = (PacketFilter) next.getValue();
            if (packetFilter != null && packetFilter.accept(message)) {
                ((PacketInterceptor) next.getKey()).interceptPacket(message);
            }
        }
        if (message.getFrom() == null) {
            message.setFrom(this.connection.getUser());
        }
        this.connection.sendPacket(message);
    }

    /* access modifiers changed from: package-private */
    public PacketCollector createPacketCollector(Chat chat) {
        return this.connection.createPacketCollector(new AndFilter(new ThreadFilter(chat.getThreadID()), new FromContainsFilter(chat.getParticipant())));
    }

    public void addOutgoingMessageInterceptor(PacketInterceptor packetInterceptor) {
        addOutgoingMessageInterceptor(packetInterceptor, (PacketFilter) null);
    }

    public void addOutgoingMessageInterceptor(PacketInterceptor packetInterceptor, PacketFilter packetFilter) {
        if (packetInterceptor != null) {
            this.interceptors.put(packetInterceptor, packetFilter);
        }
    }
}
