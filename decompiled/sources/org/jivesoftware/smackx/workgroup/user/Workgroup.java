package org.jivesoftware.smackx.workgroup.user;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.MUCUser;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.jivesoftware.smackx.workgroup.WorkgroupInvitation;
import org.jivesoftware.smackx.workgroup.WorkgroupInvitationListener;
import org.jivesoftware.smackx.workgroup.ext.forms.WorkgroupForm;
import org.jivesoftware.smackx.workgroup.packet.DepartQueuePacket;
import org.jivesoftware.smackx.workgroup.packet.QueueUpdate;
import org.jivesoftware.smackx.workgroup.packet.SessionID;
import org.jivesoftware.smackx.workgroup.packet.UserID;
import org.jivesoftware.smackx.workgroup.settings.ChatSetting;
import org.jivesoftware.smackx.workgroup.settings.ChatSettings;
import org.jivesoftware.smackx.workgroup.settings.OfflineSettings;
import org.jivesoftware.smackx.workgroup.settings.SoundSettings;
import org.jivesoftware.smackx.workgroup.settings.WorkgroupProperties;

public class Workgroup {
    /* access modifiers changed from: private */
    public Connection connection;
    /* access modifiers changed from: private */
    public boolean inQueue;
    private List<WorkgroupInvitationListener> invitationListeners;
    private List<QueueListener> queueListeners;
    /* access modifiers changed from: private */
    public int queuePosition = -1;
    /* access modifiers changed from: private */
    public int queueRemainingTime = -1;
    private List siteInviteListeners;
    private String workgroupJID;

    public Workgroup(String str, Connection connection2) {
        if (connection2.isAuthenticated()) {
            this.workgroupJID = str;
            this.connection = connection2;
            this.inQueue = false;
            this.invitationListeners = new ArrayList();
            this.queueListeners = new ArrayList();
            this.siteInviteListeners = new ArrayList();
            addQueueListener(new QueueListener() {
                public void joinedQueue() {
                    boolean unused = Workgroup.this.inQueue = true;
                }

                public void departedQueue() {
                    boolean unused = Workgroup.this.inQueue = false;
                    int unused2 = Workgroup.this.queuePosition = -1;
                    int unused3 = Workgroup.this.queueRemainingTime = -1;
                }

                public void queuePositionUpdated(int i) {
                    int unused = Workgroup.this.queuePosition = i;
                }

                public void queueWaitTimeUpdated(int i) {
                    int unused = Workgroup.this.queueRemainingTime = i;
                }
            });
            MultiUserChat.addInvitationListener(connection2, new InvitationListener() {
                public void invitationReceived(Connection connection, String str, String str2, String str3, String str4, Message message) {
                    boolean unused = Workgroup.this.inQueue = false;
                    int unused2 = Workgroup.this.queuePosition = -1;
                    int unused3 = Workgroup.this.queueRemainingTime = -1;
                }
            });
            connection2.addPacketListener(new PacketListener() {
                public void processPacket(Packet packet) {
                    Workgroup.this.handlePacket(packet);
                }
            }, new PacketTypeFilter(Message.class));
            return;
        }
        throw new IllegalStateException("Must login to server before creating workgroup.");
    }

    public String getWorkgroupJID() {
        return this.workgroupJID;
    }

    public boolean isInQueue() {
        return this.inQueue;
    }

    public boolean isAvailable() {
        Presence presence = new Presence(Presence.Type.available);
        presence.setTo(this.workgroupJID);
        PacketTypeFilter packetTypeFilter = new PacketTypeFilter(Presence.class);
        FromContainsFilter fromContainsFilter = new FromContainsFilter(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(fromContainsFilter, packetTypeFilter));
        this.connection.sendPacket(presence);
        Presence presence2 = (Presence) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (presence2 != null && presence2.getError() == null && Presence.Type.available == presence2.getType()) {
            return true;
        }
        return false;
    }

    public int getQueuePosition() {
        return this.queuePosition;
    }

    public int getQueueRemainingTime() {
        return this.queueRemainingTime;
    }

    public void joinQueue() throws XMPPException {
        joinQueue((Form) null);
    }

    public void joinQueue(Form form) throws XMPPException {
        joinQueue(form, (String) null);
    }

    public void joinQueue(Form form, String str) throws XMPPException {
        if (!this.inQueue) {
            JoinQueuePacket joinQueuePacket = new JoinQueuePacket(this.workgroupJID, form, str);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(joinQueuePacket.getPacketID()));
            this.connection.sendPacket(joinQueuePacket);
            IQ iq = (IQ) createPacketCollector.nextResult(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getError() == null) {
                fireQueueJoinedEvent();
            } else {
                throw new XMPPException(iq.getError());
            }
        } else {
            throw new IllegalStateException("Already in queue " + this.workgroupJID);
        }
    }

    public void joinQueue(Map map, String str) throws XMPPException {
        if (!this.inQueue) {
            Form form = new Form(Form.TYPE_SUBMIT);
            for (Object keyObj : map.keySet()) {
                String str2 = (String) keyObj;
                String obj = map.get(str2).toString();
                String escapeForXML = StringUtils.escapeForXML(str2);
                String escapeForXML2 = StringUtils.escapeForXML(obj);
                FormField formField = new FormField(escapeForXML);
                formField.setType(FormField.TYPE_TEXT_SINGLE);
                form.addField(formField);
                form.setAnswer(escapeForXML, escapeForXML2);
            }
            joinQueue(form, str);
            return;
        }
        throw new IllegalStateException("Already in queue " + this.workgroupJID);
    }

    public void departQueue() throws XMPPException {
        if (this.inQueue) {
            DepartQueuePacket departQueuePacket = new DepartQueuePacket(this.workgroupJID);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(departQueuePacket.getPacketID()));
            this.connection.sendPacket(departQueuePacket);
            IQ iq = (IQ) createPacketCollector.nextResult(5000);
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getError() == null) {
                fireQueueDepartedEvent();
            } else {
                throw new XMPPException(iq.getError());
            }
        }
    }

    public void addQueueListener(QueueListener queueListener) {
        synchronized (this.queueListeners) {
            if (!this.queueListeners.contains(queueListener)) {
                this.queueListeners.add(queueListener);
            }
        }
    }

    public void removeQueueListener(QueueListener queueListener) {
        synchronized (this.queueListeners) {
            this.queueListeners.remove(queueListener);
        }
    }

    public void addInvitationListener(WorkgroupInvitationListener workgroupInvitationListener) {
        synchronized (this.invitationListeners) {
            if (!this.invitationListeners.contains(workgroupInvitationListener)) {
                this.invitationListeners.add(workgroupInvitationListener);
            }
        }
    }

    public void removeQueueListener(WorkgroupInvitationListener workgroupInvitationListener) {
        synchronized (this.invitationListeners) {
            this.invitationListeners.remove(workgroupInvitationListener);
        }
    }

    private void fireInvitationEvent(WorkgroupInvitation workgroupInvitation) {
        synchronized (this.invitationListeners) {
            for (WorkgroupInvitationListener invitationReceived : this.invitationListeners) {
                invitationReceived.invitationReceived(workgroupInvitation);
            }
        }
    }

    private void fireQueueJoinedEvent() {
        synchronized (this.queueListeners) {
            for (QueueListener joinedQueue : this.queueListeners) {
                joinedQueue.joinedQueue();
            }
        }
    }

    private void fireQueueDepartedEvent() {
        synchronized (this.queueListeners) {
            for (QueueListener departedQueue : this.queueListeners) {
                departedQueue.departedQueue();
            }
        }
    }

    private void fireQueuePositionEvent(int i) {
        synchronized (this.queueListeners) {
            for (QueueListener queuePositionUpdated : this.queueListeners) {
                queuePositionUpdated.queuePositionUpdated(i);
            }
        }
    }

    private void fireQueueTimeEvent(int i) {
        synchronized (this.queueListeners) {
            for (QueueListener queueWaitTimeUpdated : this.queueListeners) {
                queueWaitTimeUpdated.queueWaitTimeUpdated(i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handlePacket(Packet packet) {
        if (packet instanceof Message) {
            Message message = (Message) packet;
            PacketExtension extension = message.getExtension("depart-queue", "http://jabber.org/protocol/workgroup");
            PacketExtension extension2 = message.getExtension(QueueUpdate.ELEMENT_NAME, "http://jabber.org/protocol/workgroup");
            if (extension != null) {
                fireQueueDepartedEvent();
            } else if (extension2 != null) {
                QueueUpdate queueUpdate = (QueueUpdate) extension2;
                if (queueUpdate.getPosition() != -1) {
                    fireQueuePositionEvent(queueUpdate.getPosition());
                }
                if (queueUpdate.getRemaingTime() != -1) {
                    fireQueueTimeEvent(queueUpdate.getRemaingTime());
                }
            } else {
                MUCUser mUCUser = (MUCUser) message.getExtension(GroupChatInvitation.ELEMENT_NAME, "http://jabber.org/protocol/muc#user");
                Map map = null;
                MUCUser.Invite invite = mUCUser != null ? mUCUser.getInvite() : null;
                if (invite != null && this.workgroupJID.equals(invite.getFrom())) {
                    PacketExtension extension3 = message.getExtension(SessionID.ELEMENT_NAME, "http://jivesoftware.com/protocol/workgroup");
                    String sessionID = extension3 != null ? ((SessionID) extension3).getSessionID() : null;
                    PacketExtension extension4 = message.getExtension(MetaData.ELEMENT_NAME, "http://jivesoftware.com/protocol/workgroup");
                    if (extension4 != null) {
                        map = ((MetaData) extension4).getMetaData();
                    }
                    fireInvitationEvent(new WorkgroupInvitation(this.connection.getUser(), message.getFrom(), this.workgroupJID, sessionID, message.getBody(), message.getFrom(), map));
                }
            }
        }
    }

    private class JoinQueuePacket extends IQ {
        private DataForm form;
        private String userID = null;

        public JoinQueuePacket(String str, Form form2, String str2) {
            this.userID = str2;
            setTo(str);
            setType(IQ.Type.SET);
            DataForm dataFormToSend = form2.getDataFormToSend();
            this.form = dataFormToSend;
            addExtension(dataFormToSend);
        }

        public String getChildElementXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<join-queue xmlns=\"http://jabber.org/protocol/workgroup\">");
            sb.append("<queue-notifications/>");
            if (Workgroup.this.connection.isAnonymous()) {
                sb.append(new UserID(this.userID).toXML());
            }
            sb.append(this.form.toXML());
            sb.append("</join-queue>");
            return sb.toString();
        }
    }

    public ChatSetting getChatSetting(String str) throws XMPPException {
        return getChatSettings(str, -1).getFirstEntry();
    }

    public ChatSettings getChatSettings(int i) throws XMPPException {
        return getChatSettings((String) null, i);
    }

    public ChatSettings getChatSettings() throws XMPPException {
        return getChatSettings((String) null, -1);
    }

    private ChatSettings getChatSettings(String str, int i) throws XMPPException {
        ChatSettings chatSettings = new ChatSettings();
        if (str != null) {
            chatSettings.setKey(str);
        }
        if (i != -1) {
            chatSettings.setType(i);
        }
        chatSettings.setType(IQ.Type.GET);
        chatSettings.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(chatSettings.getPacketID()));
        this.connection.sendPacket(chatSettings);
        ChatSettings chatSettings2 = (ChatSettings) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (chatSettings2 == null) {
            throw new XMPPException("No response from server.");
        } else if (chatSettings2.getError() == null) {
            return chatSettings2;
        } else {
            throw new XMPPException(chatSettings2.getError());
        }
    }

    public boolean isEmailAvailable() {
        try {
            return ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(StringUtils.parseServer(this.workgroupJID)).containsFeature("jive:email:provider");
        } catch (XMPPException unused) {
            return false;
        }
    }

    public OfflineSettings getOfflineSettings() throws XMPPException {
        OfflineSettings offlineSettings = new OfflineSettings();
        offlineSettings.setType(IQ.Type.GET);
        offlineSettings.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(offlineSettings.getPacketID()));
        this.connection.sendPacket(offlineSettings);
        OfflineSettings offlineSettings2 = (OfflineSettings) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (offlineSettings2 == null) {
            throw new XMPPException("No response from server.");
        } else if (offlineSettings2.getError() == null) {
            return offlineSettings2;
        } else {
            throw new XMPPException(offlineSettings2.getError());
        }
    }

    public SoundSettings getSoundSettings() throws XMPPException {
        SoundSettings soundSettings = new SoundSettings();
        soundSettings.setType(IQ.Type.GET);
        soundSettings.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(soundSettings.getPacketID()));
        this.connection.sendPacket(soundSettings);
        SoundSettings soundSettings2 = (SoundSettings) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (soundSettings2 == null) {
            throw new XMPPException("No response from server.");
        } else if (soundSettings2.getError() == null) {
            return soundSettings2;
        } else {
            throw new XMPPException(soundSettings2.getError());
        }
    }

    public WorkgroupProperties getWorkgroupProperties() throws XMPPException {
        WorkgroupProperties workgroupProperties = new WorkgroupProperties();
        workgroupProperties.setType(IQ.Type.GET);
        workgroupProperties.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(workgroupProperties.getPacketID()));
        this.connection.sendPacket(workgroupProperties);
        WorkgroupProperties workgroupProperties2 = (WorkgroupProperties) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (workgroupProperties2 == null) {
            throw new XMPPException("No response from server.");
        } else if (workgroupProperties2.getError() == null) {
            return workgroupProperties2;
        } else {
            throw new XMPPException(workgroupProperties2.getError());
        }
    }

    public WorkgroupProperties getWorkgroupProperties(String str) throws XMPPException {
        WorkgroupProperties workgroupProperties = new WorkgroupProperties();
        workgroupProperties.setJid(str);
        workgroupProperties.setType(IQ.Type.GET);
        workgroupProperties.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(workgroupProperties.getPacketID()));
        this.connection.sendPacket(workgroupProperties);
        WorkgroupProperties workgroupProperties2 = (WorkgroupProperties) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (workgroupProperties2 == null) {
            throw new XMPPException("No response from server.");
        } else if (workgroupProperties2.getError() == null) {
            return workgroupProperties2;
        } else {
            throw new XMPPException(workgroupProperties2.getError());
        }
    }

    public Form getWorkgroupForm() throws XMPPException {
        WorkgroupForm workgroupForm = new WorkgroupForm();
        workgroupForm.setType(IQ.Type.GET);
        workgroupForm.setTo(this.workgroupJID);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(workgroupForm.getPacketID()));
        this.connection.sendPacket(workgroupForm);
        WorkgroupForm workgroupForm2 = (WorkgroupForm) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (workgroupForm2 == null) {
            throw new XMPPException("No response from server on status set.");
        } else if (workgroupForm2.getError() == null) {
            return Form.getFormFrom(workgroupForm2);
        } else {
            throw new XMPPException(workgroupForm2.getError());
        }
    }
}
