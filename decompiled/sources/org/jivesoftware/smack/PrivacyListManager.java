package org.jivesoftware.smack;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.IQTypeFilter;
import org.jivesoftware.smack.filter.PacketExtensionFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Privacy;
import org.jivesoftware.smack.packet.PrivacyItem;

public class PrivacyListManager {
    /* access modifiers changed from: private */
    public static Map<Connection, PrivacyListManager> instances = new Hashtable();
    /* access modifiers changed from: private */
    public Connection connection;
    /* access modifiers changed from: private */
    public final List<PrivacyListListener> listeners;
    PacketFilter packetFilter;

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                new PrivacyListManager(connection);
            }
        });
    }

    private PrivacyListManager(Connection connection2) {
        this.listeners = new ArrayList();
        this.packetFilter = new AndFilter(new IQTypeFilter(IQ.Type.SET), new PacketExtensionFilter(SearchIntents.EXTRA_QUERY, "jabber:iq:privacy"));
        this.connection = connection2;
        init();
    }

    private String getUser() {
        return this.connection.getUser();
    }

    private void init() {
        instances.put(this.connection, this);
        this.connection.addConnectionListener(new ConnectionListener() {
            public void connectionClosedOnError(Exception exc) {
            }

            public void reconnectingIn(int i) {
            }

            public void reconnectionFailed(Exception exc) {
            }

            public void reconnectionSuccessful() {
            }

            public void connectionClosed() {
                PrivacyListManager.instances.remove(PrivacyListManager.this.connection);
            }
        });
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                if (packet != null && packet.getError() == null) {
                    Privacy privacy = (Privacy) packet;
                    synchronized (PrivacyListManager.this.listeners) {
                        for (PrivacyListListener privacyListListener : PrivacyListManager.this.listeners) {
                            for (Map.Entry next : privacy.getItemLists().entrySet()) {
                                String str = (String) next.getKey();
                                List list = (List) next.getValue();
                                if (list.isEmpty()) {
                                    privacyListListener.updatedPrivacyList(str);
                                } else {
                                    privacyListListener.setPrivacyList(str, list);
                                }
                            }
                        }
                    }
                    IQ response = new IQ() {
                        public String getChildElementXML() {
                            return "";
                        }
                    };
                    response.setType(IQ.Type.RESULT);
                    response.setFrom(packet.getFrom());
                    response.setPacketID(packet.getPacketID());
                    PrivacyListManager.this.connection.sendPacket(response);
                }
            }
        }, this.packetFilter);
    }

    public static PrivacyListManager getInstanceFor(Connection connection2) {
        return instances.get(connection2);
    }

    private Privacy getRequest(Privacy privacy) throws XMPPException {
        privacy.setType(IQ.Type.GET);
        privacy.setFrom(getUser());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(privacy.getPacketID()));
        this.connection.sendPacket(privacy);
        Privacy privacy2 = (Privacy) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (privacy2 == null) {
            throw new XMPPException("No response from server.");
        } else if (privacy2.getError() == null) {
            return privacy2;
        } else {
            throw new XMPPException(privacy2.getError());
        }
    }

    private Packet setRequest(Privacy privacy) throws XMPPException {
        privacy.setType(IQ.Type.SET);
        privacy.setFrom(getUser());
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(privacy.getPacketID()));
        this.connection.sendPacket(privacy);
        Packet nextResult = createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (nextResult == null) {
            throw new XMPPException("No response from server.");
        } else if (nextResult.getError() == null) {
            return nextResult;
        } else {
            throw new XMPPException(nextResult.getError());
        }
    }

    private Privacy getPrivacyWithListNames() throws XMPPException {
        return getRequest(new Privacy());
    }

    public PrivacyList getActiveList() throws XMPPException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        String activeName = privacyWithListNames.getActiveName();
        return new PrivacyList(true, (privacyWithListNames.getActiveName() == null || privacyWithListNames.getDefaultName() == null || !privacyWithListNames.getActiveName().equals(privacyWithListNames.getDefaultName())) ? false : true, activeName, getPrivacyListItems(activeName));
    }

    public PrivacyList getDefaultList() throws XMPPException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        String defaultName = privacyWithListNames.getDefaultName();
        return new PrivacyList((privacyWithListNames.getActiveName() == null || privacyWithListNames.getDefaultName() == null || !privacyWithListNames.getActiveName().equals(privacyWithListNames.getDefaultName())) ? false : true, true, defaultName, getPrivacyListItems(defaultName));
    }

    private List<PrivacyItem> getPrivacyListItems(String str) throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, new ArrayList());
        return getRequest(privacy).getPrivacyList(str);
    }

    public PrivacyList getPrivacyList(String str) throws XMPPException {
        return new PrivacyList(false, false, str, getPrivacyListItems(str));
    }

    public PrivacyList[] getPrivacyLists() throws XMPPException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        Set<String> privacyListNames = privacyWithListNames.getPrivacyListNames();
        PrivacyList[] privacyListArr = new PrivacyList[privacyListNames.size()];
        int i = 0;
        for (String next : privacyListNames) {
            privacyListArr[i] = new PrivacyList(next.equals(privacyWithListNames.getActiveName()), next.equals(privacyWithListNames.getDefaultName()), next, getPrivacyListItems(next));
            i++;
        }
        return privacyListArr;
    }

    public void setActiveListName(String str) throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setActiveName(str);
        setRequest(privacy);
    }

    public void declineActiveList() throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setDeclineActiveList(true);
        setRequest(privacy);
    }

    public void setDefaultListName(String str) throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setDefaultName(str);
        setRequest(privacy);
    }

    public void declineDefaultList() throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setDeclineDefaultList(true);
        setRequest(privacy);
    }

    public void createPrivacyList(String str, List<PrivacyItem> list) throws XMPPException {
        updatePrivacyList(str, list);
    }

    public void updatePrivacyList(String str, List<PrivacyItem> list) throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, list);
        setRequest(privacy);
    }

    public void deletePrivacyList(String str) throws XMPPException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, new ArrayList());
        setRequest(privacy);
    }

    public void addListener(PrivacyListListener privacyListListener) {
        synchronized (this.listeners) {
            this.listeners.add(privacyListListener);
        }
    }
}
