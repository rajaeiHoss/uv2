package org.jivesoftware.smackx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.packet.MessageEvent;

public class MessageEventManager {
    private Connection con;
    private List<MessageEventNotificationListener> messageEventNotificationListeners = new ArrayList();
    private List<MessageEventRequestListener> messageEventRequestListeners = new ArrayList();
    private PacketFilter packetFilter = new PacketExtensionFilter(GroupChatInvitation.ELEMENT_NAME, "jabber:x:event");
    private PacketListener packetListener;

    public MessageEventManager(Connection connection) {
        this.con = connection;
        init();
    }

    public static void addNotificationsRequests(Message message, boolean z, boolean z2, boolean z3, boolean z4) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setOffline(z);
        messageEvent.setDelivered(z2);
        messageEvent.setDisplayed(z3);
        messageEvent.setComposing(z4);
        message.addExtension(messageEvent);
    }

    public void addMessageEventRequestListener(MessageEventRequestListener messageEventRequestListener) {
        synchronized (this.messageEventRequestListeners) {
            if (!this.messageEventRequestListeners.contains(messageEventRequestListener)) {
                this.messageEventRequestListeners.add(messageEventRequestListener);
            }
        }
    }

    public void removeMessageEventRequestListener(MessageEventRequestListener messageEventRequestListener) {
        synchronized (this.messageEventRequestListeners) {
            this.messageEventRequestListeners.remove(messageEventRequestListener);
        }
    }

    public void addMessageEventNotificationListener(MessageEventNotificationListener messageEventNotificationListener) {
        synchronized (this.messageEventNotificationListeners) {
            if (!this.messageEventNotificationListeners.contains(messageEventNotificationListener)) {
                this.messageEventNotificationListeners.add(messageEventNotificationListener);
            }
        }
    }

    public void removeMessageEventNotificationListener(MessageEventNotificationListener messageEventNotificationListener) {
        synchronized (this.messageEventNotificationListeners) {
            this.messageEventNotificationListeners.remove(messageEventNotificationListener);
        }
    }

    /* access modifiers changed from: private */
    public void fireMessageEventRequestListeners(String str, String str2, String str3) {
        int size;
        MessageEventRequestListener[] messageEventRequestListenerArr;
        synchronized (this.messageEventRequestListeners) {
            size = this.messageEventRequestListeners.size();
            messageEventRequestListenerArr = new MessageEventRequestListener[size];
            this.messageEventRequestListeners.toArray(messageEventRequestListenerArr);
        }
        try {
            Method declaredMethod = MessageEventRequestListener.class.getDeclaredMethod(str3, new Class[]{String.class, String.class, MessageEventManager.class});
            for (int i = 0; i < size; i++) {
                declaredMethod.invoke(messageEventRequestListenerArr[i], new Object[]{str, str2, this});
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void fireMessageEventNotificationListeners(String str, String str2, String str3) {
        int size;
        MessageEventNotificationListener[] messageEventNotificationListenerArr;
        synchronized (this.messageEventNotificationListeners) {
            size = this.messageEventNotificationListeners.size();
            messageEventNotificationListenerArr = new MessageEventNotificationListener[size];
            this.messageEventNotificationListeners.toArray(messageEventNotificationListenerArr);
        }
        try {
            Method declaredMethod = MessageEventNotificationListener.class.getDeclaredMethod(str3, new Class[]{String.class, String.class});
            for (int i = 0; i < size; i++) {
                declaredMethod.invoke(messageEventNotificationListenerArr[i], new Object[]{str, str2});
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    private void init() {
        PacketListener listener = new PacketListener() {
            public void processPacket(Packet packet) {
                Message message = (Message) packet;
                MessageEvent messageEvent = (MessageEvent) message.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:event");
                if (messageEvent.isMessageEventRequest()) {
                    Iterator eventTypes = messageEvent.getEventTypes();
                    while (eventTypes.hasNext()) {
                        MessageEventManager.this.fireMessageEventRequestListeners(message.getFrom(), message.getPacketID(), ((String) eventTypes.next()).concat("NotificationRequested"));
                    }
                    return;
                }
                Iterator eventTypes2 = messageEvent.getEventTypes();
                while (eventTypes2.hasNext()) {
                    MessageEventManager.this.fireMessageEventNotificationListeners(message.getFrom(), messageEvent.getPacketID(), ((String) eventTypes2.next()).concat("Notification"));
                }
            }
        };
        this.packetListener = listener;
        this.con.addPacketListener(listener, this.packetFilter);
    }

    public void sendDeliveredNotification(String str, String str2) {
        Message message = new Message(str);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setDelivered(true);
        messageEvent.setPacketID(str2);
        message.addExtension(messageEvent);
        this.con.sendPacket(message);
    }

    public void sendDisplayedNotification(String str, String str2) {
        Message message = new Message(str);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setDisplayed(true);
        messageEvent.setPacketID(str2);
        message.addExtension(messageEvent);
        this.con.sendPacket(message);
    }

    public void sendComposingNotification(String str, String str2) {
        Message message = new Message(str);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setComposing(true);
        messageEvent.setPacketID(str2);
        message.addExtension(messageEvent);
        this.con.sendPacket(message);
    }

    public void sendCancelledNotification(String str, String str2) {
        Message message = new Message(str);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCancelled(true);
        messageEvent.setPacketID(str2);
        message.addExtension(messageEvent);
        this.con.sendPacket(message);
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
