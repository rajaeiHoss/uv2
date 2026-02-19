package org.jivesoftware.smackx.muc;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.PrivacyItem;
import org.jivesoftware.smack.packet.Registration;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.NodeInformationProvider;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.packet.MUCAdmin;
import org.jivesoftware.smackx.packet.MUCInitialPresence;
import org.jivesoftware.smackx.packet.MUCOwner;
import org.jivesoftware.smackx.packet.MUCUser;

public class MultiUserChat {
    private static final String discoNamespace = "http://jabber.org/protocol/muc";
    private static final String discoNode = "http://jabber.org/protocol/muc#rooms";
    private static Map<Connection, List<String>> joinedRooms = new WeakHashMap();
    private Connection connection;
    private List<PacketListener> connectionListeners = new ArrayList();
    private final List<InvitationRejectionListener> invitationRejectionListeners = new ArrayList();
    private boolean joined = false;
    private ConnectionDetachedPacketCollector messageCollector;
    private PacketFilter messageFilter;
    /* access modifiers changed from: private */
    public String nickname = null;
    /* access modifiers changed from: private */
    public Map<String, Presence> occupantsMap = new ConcurrentHashMap();
    private final List<ParticipantStatusListener> participantStatusListeners = new ArrayList();
    private PacketFilter presenceFilter;
    private List<PacketInterceptor> presenceInterceptors = new ArrayList();
    /* access modifiers changed from: private */
    public String room;
    private RoomListenerMultiplexor roomListenerMultiplexor;
    /* access modifiers changed from: private */
    public String subject;
    private final List<SubjectUpdatedListener> subjectUpdatedListeners = new ArrayList();
    private final List<UserStatusListener> userStatusListeners = new ArrayList();

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(final Connection connection) {
                ServiceDiscoveryManager.getInstanceFor(connection).addFeature(MultiUserChat.discoNamespace);
                ServiceDiscoveryManager.getInstanceFor(connection).setNodeInformationProvider(MultiUserChat.discoNode, new NodeInformationProvider() {
                    public List<String> getNodeFeatures() {
                        return null;
                    }

                    public List<DiscoverInfo.Identity> getNodeIdentities() {
                        return null;
                    }

                    public List<DiscoverItems.Item> getNodeItems() {
                        ArrayList arrayList = new ArrayList();
                        Iterator access$000 = MultiUserChat.getJoinedRooms(connection);
                        while (access$000.hasNext()) {
                            arrayList.add(new DiscoverItems.Item((String) access$000.next()));
                        }
                        return arrayList;
                    }
                });
            }
        });
    }

    public MultiUserChat(Connection connection2, String str) {
        this.connection = connection2;
        this.room = str.toLowerCase();
        init();
    }

    public static boolean isServiceEnabled(Connection connection2, String str) {
        try {
            return ServiceDiscoveryManager.getInstanceFor(connection2).discoverInfo(str).containsFeature(discoNamespace);
        } catch (XMPPException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static Iterator<String> getJoinedRooms(Connection connection2) {
        List list = joinedRooms.get(connection2);
        if (list != null) {
            return list.iterator();
        }
        return new ArrayList().iterator();
    }

    public static Iterator<String> getJoinedRooms(Connection connection2, String str) {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<DiscoverItems.Item> items = ServiceDiscoveryManager.getInstanceFor(connection2).discoverItems(str, discoNode).getItems();
            while (items.hasNext()) {
                arrayList.add(items.next().getEntityID());
            }
            return arrayList.iterator();
        } catch (XMPPException e) {
            e.printStackTrace();
            return new ArrayList().iterator();
        }
    }

    public static RoomInfo getRoomInfo(Connection connection2, String str) throws XMPPException {
        return new RoomInfo(ServiceDiscoveryManager.getInstanceFor(connection2).discoverInfo(str));
    }

    public static Collection<String> getServiceNames(Connection connection2) throws XMPPException {
        ArrayList arrayList = new ArrayList();
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(connection2);
        Iterator<DiscoverItems.Item> items = instanceFor.discoverItems(connection2.getServiceName()).getItems();
        while (items.hasNext()) {
            DiscoverItems.Item next = items.next();
            try {
                if (instanceFor.discoverInfo(next.getEntityID()).containsFeature(discoNamespace)) {
                    arrayList.add(next.getEntityID());
                }
            } catch (XMPPException unused) {
            }
        }
        return arrayList;
    }

    public static Collection<HostedRoom> getHostedRooms(Connection connection2, String str) throws XMPPException {
        ArrayList arrayList = new ArrayList();
        Iterator<DiscoverItems.Item> items = ServiceDiscoveryManager.getInstanceFor(connection2).discoverItems(str).getItems();
        while (items.hasNext()) {
            arrayList.add(new HostedRoom(items.next()));
        }
        return arrayList;
    }

    public String getRoom() {
        return this.room;
    }

    public synchronized void create(String str) throws XMPPException {
        if (str != null) {
            if (!str.equals("")) {
                if (!this.joined) {
                    Presence presence = new Presence(Presence.Type.available);
                    presence.setTo(this.room + "/" + str);
                    presence.addExtension(new MUCInitialPresence());
                    for (PacketInterceptor interceptPacket : this.presenceInterceptors) {
                        interceptPacket.interceptPacket(presence);
                    }
                    PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new FromMatchesFilter(this.room + "/" + str), new PacketTypeFilter(Presence.class)));
                    this.connection.sendPacket(presence);
                    Presence presence2 = (Presence) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
                    createPacketCollector.cancel();
                    if (presence2 == null) {
                        throw new XMPPException("No response from server.");
                    } else if (presence2.getError() == null) {
                        this.nickname = str;
                        this.joined = true;
                        userHasJoined();
                        MUCUser mUCUserExtension = getMUCUserExtension(presence2);
                        if (mUCUserExtension == null || mUCUserExtension.getStatus() == null || !"201".equals(mUCUserExtension.getStatus().getCode())) {
                            leave();
                            throw new XMPPException("Creation failed - Missing acknowledge of room creation.");
                        }
                    } else {
                        throw new XMPPException(presence2.getError());
                    }
                } else {
                    throw new IllegalStateException("Creation failed - User already joined the room.");
                }
            }
        }
        throw new IllegalArgumentException("Nickname must not be null or blank.");
    }

    public void join(String str) throws XMPPException {
        join(str, (String) null, (DiscussionHistory) null, (long) SmackConfiguration.getPacketReplyTimeout());
    }

    public void join(String str, String str2) throws XMPPException {
        join(str, str2, (DiscussionHistory) null, (long) SmackConfiguration.getPacketReplyTimeout());
    }

    public synchronized void join(String str, String str2, DiscussionHistory discussionHistory, long j) throws XMPPException {
        if (str != null) {
            if (!str.equals("")) {
                if (this.joined) {
                    leave();
                }
                Presence presence = new Presence(Presence.Type.available);
                presence.setTo(this.room + "/" + str);
                MUCInitialPresence mUCInitialPresence = new MUCInitialPresence();
                if (str2 != null) {
                    mUCInitialPresence.setPassword(str2);
                }
                if (discussionHistory != null) {
                    mUCInitialPresence.setHistory(discussionHistory.getMUCHistory());
                }
                presence.addExtension(mUCInitialPresence);
                for (PacketInterceptor interceptPacket : this.presenceInterceptors) {
                    interceptPacket.interceptPacket(presence);
                }
                PacketCollector packetCollector = null;
                try {
                    packetCollector = this.connection.createPacketCollector(new AndFilter(new FromMatchesFilter(this.room + "/" + str), new PacketTypeFilter(Presence.class)));
                    this.connection.sendPacket(presence);
                    Presence presence2 = (Presence) packetCollector.nextResult(j);
                    if (packetCollector != null) {
                        packetCollector.cancel();
                    }
                    if (presence2 == null) {
                        throw new XMPPException("No response from server.");
                    } else if (presence2.getError() == null) {
                        this.nickname = str;
                        this.joined = true;
                        userHasJoined();
                    } else {
                        throw new XMPPException(presence2.getError());
                    }
                } catch (Throwable th) {
                    if (packetCollector != null) {
                        packetCollector.cancel();
                    }
                    throw th;
                }
            }
        }
        throw new IllegalArgumentException("Nickname must not be null or blank.");
    }

    public boolean isJoined() {
        return this.joined;
    }

    public synchronized void leave() {
        if (this.joined) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setTo(this.room + "/" + this.nickname);
            for (PacketInterceptor interceptPacket : this.presenceInterceptors) {
                interceptPacket.interceptPacket(presence);
            }
            this.connection.sendPacket(presence);
            this.occupantsMap.clear();
            this.nickname = null;
            this.joined = false;
            userHasLeft();
        }
    }

    public Form getConfigurationForm() throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.GET);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() == null) {
            return Form.getFormFrom(iq);
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public void sendConfigurationForm(Form form) throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.SET);
        mUCOwner.addExtension(form.getDataFormToSend());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    public Form getRegistrationForm() throws XMPPException {
        Registration registration = new Registration();
        registration.setType(IQ.Type.GET);
        registration.setTo(this.room);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
        this.connection.sendPacket(registration);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            return Form.getFormFrom(iq);
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public void sendRegistrationForm(Form form) throws XMPPException {
        Registration registration = new Registration();
        registration.setType(IQ.Type.SET);
        registration.setTo(this.room);
        registration.addExtension(form.getDataFormToSend());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new PacketIDFilter(registration.getPacketID()), new PacketTypeFilter(IQ.class)));
        this.connection.sendPacket(registration);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getType() == IQ.Type.ERROR) {
            throw new XMPPException(iq.getError());
        }
    }

    public void destroy(String str, String str2) throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.SET);
        MUCOwner.Destroy destroy = new MUCOwner.Destroy();
        destroy.setReason(str);
        destroy.setJid(str2);
        mUCOwner.setDestroy(destroy);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() == null) {
            this.occupantsMap.clear();
            this.nickname = null;
            this.joined = false;
            userHasLeft();
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public void invite(String str, String str2) {
        invite(new Message(), str, str2);
    }

    public void invite(Message message, String str, String str2) {
        message.setTo(this.room);
        MUCUser mUCUser = new MUCUser();
        MUCUser.Invite invite = new MUCUser.Invite();
        invite.setTo(str);
        invite.setReason(str2);
        mUCUser.setInvite(invite);
        message.addExtension(mUCUser);
        this.connection.sendPacket(message);
    }

    public static void decline(Connection connection2, String str, String str2, String str3) {
        Message message = new Message(str);
        MUCUser mUCUser = new MUCUser();
        MUCUser.Decline decline = new MUCUser.Decline();
        decline.setTo(str2);
        decline.setReason(str3);
        mUCUser.setDecline(decline);
        message.addExtension(mUCUser);
        connection2.sendPacket(message);
    }

    public static void addInvitationListener(Connection connection2, InvitationListener invitationListener) {
        InvitationsMonitor.getInvitationsMonitor(connection2).addInvitationListener(invitationListener);
    }

    public static void removeInvitationListener(Connection connection2, InvitationListener invitationListener) {
        InvitationsMonitor.getInvitationsMonitor(connection2).removeInvitationListener(invitationListener);
    }

    public void addInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        synchronized (this.invitationRejectionListeners) {
            if (!this.invitationRejectionListeners.contains(invitationRejectionListener)) {
                this.invitationRejectionListeners.add(invitationRejectionListener);
            }
        }
    }

    public void removeInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        synchronized (this.invitationRejectionListeners) {
            this.invitationRejectionListeners.remove(invitationRejectionListener);
        }
    }

    /* access modifiers changed from: private */
    public void fireInvitationRejectionListeners(String str, String str2) {
        int size;
        InvitationRejectionListener[] invitationRejectionListenerArr;
        synchronized (this.invitationRejectionListeners) {
            size = this.invitationRejectionListeners.size();
            invitationRejectionListenerArr = new InvitationRejectionListener[size];
            this.invitationRejectionListeners.toArray(invitationRejectionListenerArr);
        }
        for (int i = 0; i < size; i++) {
            invitationRejectionListenerArr[i].invitationDeclined(str, str2);
        }
    }

    public void addSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        synchronized (this.subjectUpdatedListeners) {
            if (!this.subjectUpdatedListeners.contains(subjectUpdatedListener)) {
                this.subjectUpdatedListeners.add(subjectUpdatedListener);
            }
        }
    }

    public void removeSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        synchronized (this.subjectUpdatedListeners) {
            this.subjectUpdatedListeners.remove(subjectUpdatedListener);
        }
    }

    /* access modifiers changed from: private */
    public void fireSubjectUpdatedListeners(String str, String str2) {
        int size;
        SubjectUpdatedListener[] subjectUpdatedListenerArr;
        synchronized (this.subjectUpdatedListeners) {
            size = this.subjectUpdatedListeners.size();
            subjectUpdatedListenerArr = new SubjectUpdatedListener[size];
            this.subjectUpdatedListeners.toArray(subjectUpdatedListenerArr);
        }
        for (int i = 0; i < size; i++) {
            subjectUpdatedListenerArr[i].subjectUpdated(str, str2);
        }
    }

    public void addPresenceInterceptor(PacketInterceptor packetInterceptor) {
        this.presenceInterceptors.add(packetInterceptor);
    }

    public void removePresenceInterceptor(PacketInterceptor packetInterceptor) {
        this.presenceInterceptors.remove(packetInterceptor);
    }

    public String getSubject() {
        return this.subject;
    }

    public String getReservedNickname() {
        try {
            Iterator<DiscoverInfo.Identity> identities = ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(this.room, "x-roomuser-item").getIdentities();
            if (identities.hasNext()) {
                return identities.next().getName();
            }
            return null;
        } catch (XMPPException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNickname() {
        return this.nickname;
    }

    public void changeNickname(String str) throws XMPPException {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Nickname must not be null or blank.");
        } else if (this.joined) {
            Presence presence = new Presence(Presence.Type.available);
            presence.setTo(this.room + "/" + str);
            for (PacketInterceptor interceptPacket : this.presenceInterceptors) {
                interceptPacket.interceptPacket(presence);
            }
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new FromMatchesFilter(this.room + "/" + str), new PacketTypeFilter(Presence.class)));
            this.connection.sendPacket(presence);
            Presence presence2 = (Presence) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (presence2 == null) {
                throw new XMPPException("No response from server.");
            } else if (presence2.getError() == null) {
                this.nickname = str;
            } else {
                throw new XMPPException(presence2.getError());
            }
        } else {
            throw new IllegalStateException("Must be logged into the room to change nickname.");
        }
    }

    public void changeAvailabilityStatus(String str, Presence.Mode mode) {
        String str2 = this.nickname;
        if (str2 == null || str2.equals("")) {
            throw new IllegalArgumentException("Nickname must not be null or blank.");
        } else if (this.joined) {
            Presence presence = new Presence(Presence.Type.available);
            presence.setStatus(str);
            presence.setMode(mode);
            presence.setTo(this.room + "/" + this.nickname);
            for (PacketInterceptor interceptPacket : this.presenceInterceptors) {
                interceptPacket.interceptPacket(presence);
            }
            this.connection.sendPacket(presence);
        } else {
            throw new IllegalStateException("Must be logged into the room to change the availability status.");
        }
    }

    public void kickParticipant(String str, String str2) throws XMPPException {
        changeRole(str, PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE, str2);
    }

    public void grantVoice(Collection<String> collection) throws XMPPException {
        changeRole(collection, "participant");
    }

    public void grantVoice(String str) throws XMPPException {
        changeRole(str, "participant", (String) null);
    }

    public void revokeVoice(Collection<String> collection) throws XMPPException {
        changeRole(collection, "visitor");
    }

    public void revokeVoice(String str) throws XMPPException {
        changeRole(str, "visitor", (String) null);
    }

    public void banUsers(Collection<String> collection) throws XMPPException {
        changeAffiliationByAdmin(collection, "outcast");
    }

    public void banUser(String str, String str2) throws XMPPException {
        changeAffiliationByAdmin(str, "outcast", str2);
    }

    public void grantMembership(Collection<String> collection) throws XMPPException {
        changeAffiliationByAdmin(collection, "member");
    }

    public void grantMembership(String str) throws XMPPException {
        changeAffiliationByAdmin(str, "member", (String) null);
    }

    public void revokeMembership(Collection<String> collection) throws XMPPException {
        changeAffiliationByAdmin(collection, PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE);
    }

    public void revokeMembership(String str) throws XMPPException {
        changeAffiliationByAdmin(str, PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE, (String) null);
    }

    public void grantModerator(Collection<String> collection) throws XMPPException {
        changeRole(collection, "moderator");
    }

    public void grantModerator(String str) throws XMPPException {
        changeRole(str, "moderator", (String) null);
    }

    public void revokeModerator(Collection<String> collection) throws XMPPException {
        changeRole(collection, "participant");
    }

    public void revokeModerator(String str) throws XMPPException {
        changeRole(str, "participant", (String) null);
    }

    public void grantOwnership(Collection<String> collection) throws XMPPException {
        changeAffiliationByOwner(collection, "owner");
    }

    public void grantOwnership(String str) throws XMPPException {
        changeAffiliationByOwner(str, "owner");
    }

    public void revokeOwnership(Collection<String> collection) throws XMPPException {
        changeAffiliationByOwner(collection, "admin");
    }

    public void revokeOwnership(String str) throws XMPPException {
        changeAffiliationByOwner(str, "admin");
    }

    public void grantAdmin(Collection<String> collection) throws XMPPException {
        changeAffiliationByOwner(collection, "admin");
    }

    public void grantAdmin(String str) throws XMPPException {
        changeAffiliationByOwner(str, "admin");
    }

    public void revokeAdmin(Collection<String> collection) throws XMPPException {
        changeAffiliationByOwner(collection, "member");
    }

    public void revokeAdmin(String str) throws XMPPException {
        changeAffiliationByOwner(str, "member");
    }

    private void changeAffiliationByOwner(String str, String str2) throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.SET);
        MUCOwner.Item item = new MUCOwner.Item(str2);
        item.setJid(str);
        mUCOwner.addItem(item);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    private void changeAffiliationByOwner(Collection<String> collection, String str) throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.SET);
        for (String jid : collection) {
            MUCOwner.Item item = new MUCOwner.Item(str);
            item.setJid(jid);
            mUCOwner.addItem(item);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    private void changeAffiliationByAdmin(String str, String str2, String str3) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.SET);
        MUCAdmin.Item item = new MUCAdmin.Item(str2, (String) null);
        item.setJid(str);
        item.setReason(str3);
        mUCAdmin.addItem(item);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    private void changeAffiliationByAdmin(Collection<String> collection, String str) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.SET);
        for (String jid : collection) {
            MUCAdmin.Item item = new MUCAdmin.Item(str, (String) null);
            item.setJid(jid);
            mUCAdmin.addItem(item);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    private void changeRole(String str, String str2, String str3) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.SET);
        MUCAdmin.Item item = new MUCAdmin.Item((String) null, str2);
        item.setNick(str);
        item.setReason(str3);
        mUCAdmin.addItem(item);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    private void changeRole(Collection<String> collection, String str) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.SET);
        for (String nick : collection) {
            MUCAdmin.Item item = new MUCAdmin.Item((String) null, str);
            item.setNick(nick);
            mUCAdmin.addItem(item);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from server.");
        } else if (iq.getError() != null) {
            throw new XMPPException(iq.getError());
        }
    }

    public int getOccupantsCount() {
        return this.occupantsMap.size();
    }

    public Iterator<String> getOccupants() {
        return Collections.unmodifiableList(new ArrayList(this.occupantsMap.keySet())).iterator();
    }

    public Presence getOccupantPresence(String str) {
        return this.occupantsMap.get(str);
    }

    public Occupant getOccupant(String str) {
        Presence presence = this.occupantsMap.get(str);
        if (presence != null) {
            return new Occupant(presence);
        }
        return null;
    }

    public void addParticipantListener(PacketListener packetListener) {
        this.connection.addPacketListener(packetListener, this.presenceFilter);
        this.connectionListeners.add(packetListener);
    }

    public void removeParticipantListener(PacketListener packetListener) {
        this.connection.removePacketListener(packetListener);
        this.connectionListeners.remove(packetListener);
    }

    public Collection<Affiliate> getOwners() throws XMPPException {
        return getAffiliatesByOwner("owner");
    }

    public Collection<Affiliate> getAdmins() throws XMPPException {
        return getAffiliatesByOwner("admin");
    }

    public Collection<Affiliate> getMembers() throws XMPPException {
        return getAffiliatesByAdmin("member");
    }

    public Collection<Affiliate> getOutcasts() throws XMPPException {
        return getAffiliatesByAdmin("outcast");
    }

    private Collection<Affiliate> getAffiliatesByOwner(String str) throws XMPPException {
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.GET);
        mUCOwner.addItem(new MUCOwner.Item(str));
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCOwner.getPacketID()));
        this.connection.sendPacket(mUCOwner);
        MUCOwner mUCOwner2 = (MUCOwner) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (mUCOwner2 == null) {
            throw new XMPPException("No response from server.");
        } else if (mUCOwner2.getError() == null) {
            ArrayList arrayList = new ArrayList();
            Iterator items = mUCOwner2.getItems();
            while (items.hasNext()) {
                arrayList.add(new Affiliate((MUCOwner.Item) items.next()));
            }
            return arrayList;
        } else {
            throw new XMPPException(mUCOwner2.getError());
        }
    }

    private Collection<Affiliate> getAffiliatesByAdmin(String str) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.GET);
        mUCAdmin.addItem(new MUCAdmin.Item(str, (String) null));
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        MUCAdmin mUCAdmin2 = (MUCAdmin) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (mUCAdmin2 == null) {
            throw new XMPPException("No response from server.");
        } else if (mUCAdmin2.getError() == null) {
            ArrayList arrayList = new ArrayList();
            Iterator items = mUCAdmin2.getItems();
            while (items.hasNext()) {
                arrayList.add(new Affiliate((MUCAdmin.Item) items.next()));
            }
            return arrayList;
        } else {
            throw new XMPPException(mUCAdmin2.getError());
        }
    }

    public Collection<Occupant> getModerators() throws XMPPException {
        return getOccupants("moderator");
    }

    public Collection<Occupant> getParticipants() throws XMPPException {
        return getOccupants("participant");
    }

    private Collection<Occupant> getOccupants(String str) throws XMPPException {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.GET);
        mUCAdmin.addItem(new MUCAdmin.Item((String) null, str));
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(mUCAdmin.getPacketID()));
        this.connection.sendPacket(mUCAdmin);
        MUCAdmin mUCAdmin2 = (MUCAdmin) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (mUCAdmin2 == null) {
            throw new XMPPException("No response from server.");
        } else if (mUCAdmin2.getError() == null) {
            ArrayList arrayList = new ArrayList();
            Iterator items = mUCAdmin2.getItems();
            while (items.hasNext()) {
                arrayList.add(new Occupant((MUCAdmin.Item) items.next()));
            }
            return arrayList;
        } else {
            throw new XMPPException(mUCAdmin2.getError());
        }
    }

    public void sendMessage(String str) throws XMPPException {
        Message message = new Message(this.room, Message.Type.groupchat);
        message.setBody(str);
        this.connection.sendPacket(message);
    }

    public Chat createPrivateChat(String str, MessageListener messageListener) {
        return this.connection.getChatManager().createChat(str, messageListener);
    }

    public Message createMessage() {
        return new Message(this.room, Message.Type.groupchat);
    }

    public void sendMessage(Message message) throws XMPPException {
        this.connection.sendPacket(message);
    }

    public Message pollMessage() {
        return (Message) this.messageCollector.pollResult();
    }

    public Message nextMessage() {
        return (Message) this.messageCollector.nextResult();
    }

    public Message nextMessage(long j) {
        return (Message) this.messageCollector.nextResult(j);
    }

    public void addMessageListener(PacketListener packetListener) {
        this.connection.addPacketListener(packetListener, this.messageFilter);
        this.connectionListeners.add(packetListener);
    }

    public void removeMessageListener(PacketListener packetListener) {
        this.connection.removePacketListener(packetListener);
        this.connectionListeners.remove(packetListener);
    }

    public void changeSubject(final String str) throws XMPPException {
        Message message = new Message(this.room, Message.Type.groupchat);
        message.setSubject(str);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(new AndFilter(new FromMatchesFilter(this.room), new PacketTypeFilter(Message.class)), new PacketFilter() {
            public boolean accept(Packet packet) {
                return str.equals(((Message) packet).getSubject());
            }
        }));
        this.connection.sendPacket(message);
        Message message2 = (Message) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (message2 == null) {
            throw new XMPPException("No response from server.");
        } else if (message2.getError() != null) {
            throw new XMPPException(message2.getError());
        }
    }

    private synchronized void userHasJoined() {
        List list = joinedRooms.get(this.connection);
        if (list == null) {
            list = new ArrayList();
            joinedRooms.put(this.connection, list);
        }
        list.add(this.room);
    }

    private synchronized void userHasLeft() {
        List list = joinedRooms.get(this.connection);
        if (list != null) {
            list.remove(this.room);
        }
    }

    /* access modifiers changed from: private */
    public MUCUser getMUCUserExtension(Packet packet) {
        if (packet != null) {
            return (MUCUser) packet.getExtension(GroupChatInvitation.ELEMENT_NAME, "http://jabber.org/protocol/muc#user");
        }
        return null;
    }

    public void addUserStatusListener(UserStatusListener userStatusListener) {
        synchronized (this.userStatusListeners) {
            if (!this.userStatusListeners.contains(userStatusListener)) {
                this.userStatusListeners.add(userStatusListener);
            }
        }
    }

    public void removeUserStatusListener(UserStatusListener userStatusListener) {
        synchronized (this.userStatusListeners) {
            this.userStatusListeners.remove(userStatusListener);
        }
    }

    private void fireUserStatusListeners(String str, Object[] objArr) {
        int size;
        UserStatusListener[] userStatusListenerArr;
        synchronized (this.userStatusListeners) {
            size = this.userStatusListeners.size();
            userStatusListenerArr = new UserStatusListener[size];
            this.userStatusListeners.toArray(userStatusListenerArr);
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        try {
            Method declaredMethod = UserStatusListener.class.getDeclaredMethod(str, clsArr);
            for (int i2 = 0; i2 < size; i2++) {
                declaredMethod.invoke(userStatusListenerArr[i2], objArr);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public void addParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        synchronized (this.participantStatusListeners) {
            if (!this.participantStatusListeners.contains(participantStatusListener)) {
                this.participantStatusListeners.add(participantStatusListener);
            }
        }
    }

    public void removeParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        synchronized (this.participantStatusListeners) {
            this.participantStatusListeners.remove(participantStatusListener);
        }
    }

    /* access modifiers changed from: private */
    public void fireParticipantStatusListeners(String str, List<String> list) {
        int size;
        ParticipantStatusListener[] participantStatusListenerArr;
        synchronized (this.participantStatusListeners) {
            size = this.participantStatusListeners.size();
            participantStatusListenerArr = new ParticipantStatusListener[size];
            this.participantStatusListeners.toArray(participantStatusListenerArr);
        }
        try {
            Class[] clsArr = new Class[list.size()];
            for (int i = 0; i < list.size(); i++) {
                clsArr[i] = String.class;
            }
            Method declaredMethod = ParticipantStatusListener.class.getDeclaredMethod(str, clsArr);
            for (int i2 = 0; i2 < size; i2++) {
                declaredMethod.invoke(participantStatusListenerArr[i2], list.toArray());
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
        AndFilter andFilter = new AndFilter(new FromMatchesFilter(this.room), new MessageTypeFilter(Message.Type.groupchat));
        this.messageFilter = andFilter;
        this.messageFilter = new AndFilter(andFilter, new PacketFilter() {
            public boolean accept(Packet packet) {
                return ((Message) packet).getBody() != null;
            }
        });
        this.presenceFilter = new AndFilter(new FromMatchesFilter(this.room), new PacketTypeFilter(Presence.class));
        this.messageCollector = new ConnectionDetachedPacketCollector();
        PacketListener subjectListener = new PacketListener() {
            public void processPacket(Packet packet) {
                Message message = (Message) packet;
                String unused = MultiUserChat.this.subject = message.getSubject();
                MultiUserChat.this.fireSubjectUpdatedListeners(message.getSubject(), message.getFrom());
            }
        };
        PacketMultiplexListener packetMultiplexListener = new PacketMultiplexListener(this.messageCollector, new PacketListener() {
            public void processPacket(Packet packet) {
                Presence presence = (Presence) packet;
                String from = presence.getFrom();
                String str = MultiUserChat.this.room + "/" + MultiUserChat.this.nickname;
                boolean equals = presence.getFrom().equals(str);
                if (presence.getType() == Presence.Type.available) {
                    Presence presence2 = (Presence) MultiUserChat.this.occupantsMap.put(from, presence);
                    if (presence2 != null) {
                        MUCUser access$600 = MultiUserChat.this.getMUCUserExtension(presence2);
                        String affiliation = access$600.getItem().getAffiliation();
                        String role = access$600.getItem().getRole();
                        MUCUser access$6002 = MultiUserChat.this.getMUCUserExtension(presence);
                        String affiliation2 = access$6002.getItem().getAffiliation();
                        MultiUserChat.this.checkRoleModifications(role, access$6002.getItem().getRole(), equals, from);
                        MultiUserChat.this.checkAffiliationModifications(affiliation, affiliation2, equals, from);
                    } else if (!equals) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(from);
                        MultiUserChat.this.fireParticipantStatusListeners("joined", arrayList);
                    }
                } else if (presence.getType() == Presence.Type.unavailable) {
                    MultiUserChat.this.occupantsMap.remove(from);
                    MUCUser access$6003 = MultiUserChat.this.getMUCUserExtension(presence);
                    if (access$6003 != null && access$6003.getStatus() != null) {
                        MultiUserChat.this.checkPresenceCode(access$6003.getStatus().getCode(), presence.getFrom().equals(str), access$6003, from);
                    } else if (!equals) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(from);
                        MultiUserChat.this.fireParticipantStatusListeners("left", arrayList2);
                    }
                }
            }
        }, subjectListener, new PacketListener() {
            public void processPacket(Packet packet) {
                MUCUser access$600 = MultiUserChat.this.getMUCUserExtension(packet);
                if (access$600.getDecline() != null && ((Message) packet).getType() != Message.Type.error) {
                    MultiUserChat.this.fireInvitationRejectionListeners(access$600.getDecline().getFrom(), access$600.getDecline().getReason());
                }
            }
        });
        RoomListenerMultiplexor roomMultiplexor = RoomListenerMultiplexor.getRoomMultiplexor(this.connection);
        this.roomListenerMultiplexor = roomMultiplexor;
        roomMultiplexor.addRoom(this.room, packetMultiplexListener);
    }

    /* access modifiers changed from: private */
    public void checkRoleModifications(String str, String str2, boolean z, String str3) {
        if (("visitor".equals(str) || PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE.equals(str)) && "participant".equals(str2)) {
            if (z) {
                fireUserStatusListeners("voiceGranted", new Object[0]);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str3);
                fireParticipantStatusListeners("voiceGranted", arrayList);
            }
        } else if ("participant".equals(str) && ("visitor".equals(str2) || PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE.equals(str2))) {
            if (z) {
                fireUserStatusListeners("voiceRevoked", new Object[0]);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(str3);
                fireParticipantStatusListeners("voiceRevoked", arrayList2);
            }
        }
        if (!"moderator".equals(str) && "moderator".equals(str2)) {
            if ("visitor".equals(str) || PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE.equals(str)) {
                if (z) {
                    fireUserStatusListeners("voiceGranted", new Object[0]);
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(str3);
                    fireParticipantStatusListeners("voiceGranted", arrayList3);
                }
            }
            if (z) {
                fireUserStatusListeners("moderatorGranted", new Object[0]);
                return;
            }
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(str3);
            fireParticipantStatusListeners("moderatorGranted", arrayList4);
        } else if ("moderator".equals(str) && !"moderator".equals(str2)) {
            if ("visitor".equals(str2) || PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE.equals(str2)) {
                if (z) {
                    fireUserStatusListeners("voiceRevoked", new Object[0]);
                } else {
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.add(str3);
                    fireParticipantStatusListeners("voiceRevoked", arrayList5);
                }
            }
            if (z) {
                fireUserStatusListeners("moderatorRevoked", new Object[0]);
                return;
            }
            ArrayList arrayList6 = new ArrayList();
            arrayList6.add(str3);
            fireParticipantStatusListeners("moderatorRevoked", arrayList6);
        }
    }

    /* access modifiers changed from: private */
    public void checkAffiliationModifications(String str, String str2, boolean z, String str3) {
        if (!"owner".equals(str) || "owner".equals(str2)) {
            if (!"admin".equals(str) || "admin".equals(str2)) {
                if ("member".equals(str) && !"member".equals(str2)) {
                    if (z) {
                        fireUserStatusListeners("membershipRevoked", new Object[0]);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str3);
                        fireParticipantStatusListeners("membershipRevoked", arrayList);
                    }
                }
            } else if (z) {
                fireUserStatusListeners("adminRevoked", new Object[0]);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(str3);
                fireParticipantStatusListeners("adminRevoked", arrayList2);
            }
        } else if (z) {
            fireUserStatusListeners("ownershipRevoked", new Object[0]);
        } else {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(str3);
            fireParticipantStatusListeners("ownershipRevoked", arrayList3);
        }
        if ("owner".equals(str) || !"owner".equals(str2)) {
            if ("admin".equals(str) || !"admin".equals(str2)) {
                if (!"member".equals(str) && "member".equals(str2)) {
                    if (z) {
                        fireUserStatusListeners("membershipGranted", new Object[0]);
                        return;
                    }
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.add(str3);
                    fireParticipantStatusListeners("membershipGranted", arrayList4);
                }
            } else if (z) {
                fireUserStatusListeners("adminGranted", new Object[0]);
            } else {
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(str3);
                fireParticipantStatusListeners("adminGranted", arrayList5);
            }
        } else if (z) {
            fireUserStatusListeners("ownershipGranted", new Object[0]);
        } else {
            ArrayList arrayList6 = new ArrayList();
            arrayList6.add(str3);
            fireParticipantStatusListeners("ownershipGranted", arrayList6);
        }
    }

    /* access modifiers changed from: private */
    public void checkPresenceCode(String str, boolean z, MUCUser mUCUser, String str2) {
        if ("307".equals(str)) {
            if (z) {
                this.joined = false;
                fireUserStatusListeners("kicked", new Object[]{mUCUser.getItem().getActor(), mUCUser.getItem().getReason()});
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str2);
            arrayList.add(mUCUser.getItem().getActor());
            arrayList.add(mUCUser.getItem().getReason());
            fireParticipantStatusListeners("kicked", arrayList);
        } else if ("301".equals(str)) {
            if (z) {
                this.joined = false;
                fireUserStatusListeners("banned", new Object[]{mUCUser.getItem().getActor(), mUCUser.getItem().getReason()});
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str2);
            arrayList2.add(mUCUser.getItem().getActor());
            arrayList2.add(mUCUser.getItem().getReason());
            fireParticipantStatusListeners("banned", arrayList2);
        } else if ("321".equals(str)) {
            if (z) {
                this.joined = false;
                fireUserStatusListeners("membershipRevoked", new Object[0]);
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
            }
        } else if ("303".equals(str)) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(str2);
            arrayList3.add(mUCUser.getItem().getNick());
            fireParticipantStatusListeners("nicknameChanged", arrayList3);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.connection != null) {
                this.roomListenerMultiplexor.removeRoom(this.room);
                for (PacketListener removePacketListener : this.connectionListeners) {
                    this.connection.removePacketListener(removePacketListener);
                }
            }
        } catch (Exception unused) {
        }
        super.finalize();
    }

    private static class InvitationsMonitor implements ConnectionListener {
        private static final Map<Connection, WeakReference<InvitationsMonitor>> monitors = new WeakHashMap();
        private Connection connection;
        private PacketFilter invitationFilter;
        private PacketListener invitationPacketListener;
        private final List<InvitationListener> invitationsListeners = new ArrayList();

        public void connectionClosedOnError(Exception exc) {
        }

        public void reconnectingIn(int i) {
        }

        public void reconnectionFailed(Exception exc) {
        }

        public void reconnectionSuccessful() {
        }

        public static InvitationsMonitor getInvitationsMonitor(Connection connection2) {
            InvitationsMonitor invitationsMonitor;
            Map<Connection, WeakReference<InvitationsMonitor>> map = monitors;
            synchronized (map) {
                if (!map.containsKey(connection2)) {
                    map.put(connection2, new WeakReference(new InvitationsMonitor(connection2)));
                }
                invitationsMonitor = (InvitationsMonitor) map.get(connection2).get();
            }
            return invitationsMonitor;
        }

        private InvitationsMonitor(Connection connection2) {
            this.connection = connection2;
        }

        public void addInvitationListener(InvitationListener invitationListener) {
            synchronized (this.invitationsListeners) {
                if (this.invitationsListeners.size() == 0) {
                    init();
                }
                if (!this.invitationsListeners.contains(invitationListener)) {
                    this.invitationsListeners.add(invitationListener);
                }
            }
        }

        public void removeInvitationListener(InvitationListener invitationListener) {
            synchronized (this.invitationsListeners) {
                if (this.invitationsListeners.contains(invitationListener)) {
                    this.invitationsListeners.remove(invitationListener);
                }
                if (this.invitationsListeners.size() == 0) {
                    cancel();
                }
            }
        }

        /* access modifiers changed from: private */
        public void fireInvitationListeners(String str, String str2, String str3, String str4, Message message) {
            int size;
            InvitationListener[] invitationListenerArr;
            synchronized (this.invitationsListeners) {
                size = this.invitationsListeners.size();
                invitationListenerArr = new InvitationListener[size];
                this.invitationsListeners.toArray(invitationListenerArr);
            }
            for (int i = 0; i < size; i++) {
                invitationListenerArr[i].invitationReceived(this.connection, str, str2, str3, str4, message);
            }
        }

        public void connectionClosed() {
            cancel();
        }

        private void init() {
            this.invitationFilter = new PacketExtensionFilter(GroupChatInvitation.ELEMENT_NAME, "http://jabber.org/protocol/muc#user");
            PacketListener invitationListener = new PacketListener() {
                public void processPacket(Packet packet) {
                    MUCUser mUCUser = (MUCUser) packet.getExtension(GroupChatInvitation.ELEMENT_NAME, "http://jabber.org/protocol/muc#user");
                    if (mUCUser.getInvite() != null) {
                        Message message = (Message) packet;
                        if (message.getType() != Message.Type.error) {
                            InvitationsMonitor.this.fireInvitationListeners(packet.getFrom(), mUCUser.getInvite().getFrom(), mUCUser.getInvite().getReason(), mUCUser.getPassword(), message);
                        }
                    }
                }
            };
            this.invitationPacketListener = invitationListener;
            this.connection.addPacketListener(invitationListener, this.invitationFilter);
            this.connection.addConnectionListener(this);
        }

        private void cancel() {
            this.connection.removePacketListener(this.invitationPacketListener);
            this.connection.removeConnectionListener(this);
        }
    }
}
