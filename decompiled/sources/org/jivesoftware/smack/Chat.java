package org.jivesoftware.smack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.packet.Message;

public class Chat {
    private ChatManager chatManager;
    private final Set<MessageListener> listeners = new CopyOnWriteArraySet();
    private String participant;
    private String threadID;

    Chat(ChatManager chatManager2, String str, String str2) {
        this.chatManager = chatManager2;
        this.participant = str;
        this.threadID = str2;
    }

    public String getThreadID() {
        return this.threadID;
    }

    public String getParticipant() {
        return this.participant;
    }

    public void sendMessage(String str) throws XMPPException {
        Message message = new Message(this.participant, Message.Type.chat);
        message.setThread(this.threadID);
        message.setBody(str);
        this.chatManager.sendMessage(this, message);
    }

    public void sendMessage(Message message) throws XMPPException {
        message.setTo(this.participant);
        message.setType(Message.Type.chat);
        message.setThread(this.threadID);
        this.chatManager.sendMessage(this, message);
    }

    public void addMessageListener(MessageListener messageListener) {
        if (messageListener != null) {
            this.listeners.add(messageListener);
        }
    }

    public void removeMessageListener(MessageListener messageListener) {
        this.listeners.remove(messageListener);
    }

    public Collection<MessageListener> getListeners() {
        return Collections.unmodifiableCollection(this.listeners);
    }

    public PacketCollector createCollector() {
        return this.chatManager.createPacketCollector(this);
    }

    /* access modifiers changed from: package-private */
    public void deliver(Message message) {
        message.setThread(this.threadID);
        for (MessageListener processMessage : this.listeners) {
            processMessage.processMessage(this, message);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Chat) {
            Chat chat = (Chat) obj;
            return this.threadID.equals(chat.getThreadID()) && this.participant.equals(chat.getParticipant());
        }
        return false;
    }
}
