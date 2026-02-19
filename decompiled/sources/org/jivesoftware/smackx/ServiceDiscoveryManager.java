package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.packet.CapsExtension;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class ServiceDiscoveryManager {
    private static boolean cacheNonCaps = true;
    private static String entityNode = "http://www.igniterealtime.org/projects/smack/";
    private static String identityName = "Smack";
    private static String identityType = "pc";
    /* access modifiers changed from: private */
    public static Map<Connection, ServiceDiscoveryManager> instances = new HashMap();
    /* access modifiers changed from: private */
    public EntityCapsManager capsManager;
    /* access modifiers changed from: private */
    public Connection connection;
    private String currentCapsVersion = null;
    private DataForm extendedInfo = null;
    private final List<String> features = new ArrayList();
    private Map<String, NodeInformationProvider> nodeInformationProviders = new ConcurrentHashMap();
    private Map<String, DiscoverInfo> nonCapsCache = new ConcurrentHashMap();
    private boolean sendPresence = false;

    static {
        XMPPConnection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                ServiceDiscoveryManager.getInstanceFor(connection);
            }
        });
    }

    public ServiceDiscoveryManager(Connection connection2) {
        this.connection = connection2;
        if (connection2 instanceof XMPPConnection) {
            setEntityCapsManager(new EntityCapsManager());
            this.capsManager.addCapsVerListener(new CapsPresenceRenewer());
        }
        renewEntityCapsVersion();
        init();
    }

    public static ServiceDiscoveryManager getInstanceFor(Connection connection2) {
        synchronized (instances) {
            ServiceDiscoveryManager serviceDiscoveryManager = instances.get(connection2);
            if (serviceDiscoveryManager != null) {
                return serviceDiscoveryManager;
            }
            ServiceDiscoveryManager serviceDiscoveryManager2 = new ServiceDiscoveryManager(connection2);
            return serviceDiscoveryManager2;
        }
    }

    public static String getIdentityName() {
        return identityName;
    }

    public static void setIdentityName(String str) {
        identityName = str;
    }

    public static String getIdentityType() {
        return identityType;
    }

    public static void setIdentityType(String str) {
        identityType = str;
    }

    public static void setNonCapsCaching(boolean z) {
        cacheNonCaps = true;
    }

    public static boolean isNonCapsCachingEnabled() {
        return cacheNonCaps;
    }

    public void addDiscoverInfoTo(DiscoverInfo discoverInfo) {
        DiscoverInfo.Identity identity = new DiscoverInfo.Identity("client", getIdentityName());
        identity.setType(getIdentityType());
        discoverInfo.addIdentity(identity);
        synchronized (this.features) {
            discoverInfo.addFeature(CapsExtension.XMLNS);
            Iterator<String> features2 = getFeatures();
            while (features2.hasNext()) {
                discoverInfo.addFeature(features2.next());
            }
            DataForm dataForm = this.extendedInfo;
            if (dataForm != null) {
                discoverInfo.addExtension(dataForm);
            }
        }
    }

    public DiscoverInfo getOwnDiscoverInfo() {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setType(IQ.Type.RESULT);
        discoverInfo.setNode(this.capsManager.getNode() + "#" + getEntityCapsVersion());
        addDiscoverInfoTo(discoverInfo);
        return discoverInfo;
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
                synchronized (ServiceDiscoveryManager.instances) {
                    ServiceDiscoveryManager.instances.remove(ServiceDiscoveryManager.this.connection);
                }
            }
        });
        PacketTypeFilter packetTypeFilter = new PacketTypeFilter(Presence.class);
        this.connection.addPacketInterceptor(new PacketInterceptor() {
            public void interceptPacket(Packet packet) {
                if (ServiceDiscoveryManager.this.capsManager != null) {
                    packet.addExtension(new CapsExtension(ServiceDiscoveryManager.this.capsManager.getNode(), ServiceDiscoveryManager.this.getEntityCapsVersion(), EntityCapsManager.HASH_METHOD));
                }
            }
        }, packetTypeFilter);
        PacketTypeFilter packetTypeFilter2 = new PacketTypeFilter(DiscoverItems.class);
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                DiscoverItems discoverItems = (DiscoverItems) packet;
                if (discoverItems != null && discoverItems.getType() == IQ.Type.GET) {
                    DiscoverItems discoverItems2 = new DiscoverItems();
                    discoverItems2.setType(IQ.Type.RESULT);
                    discoverItems2.setTo(discoverItems.getFrom());
                    discoverItems2.setPacketID(discoverItems.getPacketID());
                    discoverItems2.setNode(discoverItems.getNode());
                    NodeInformationProvider access$500 = ServiceDiscoveryManager.this.getNodeInformationProvider(discoverItems.getNode());
                    if (access$500 != null) {
                        List<DiscoverItems.Item> nodeItems = access$500.getNodeItems();
                        if (nodeItems != null) {
                            for (DiscoverItems.Item addItem : nodeItems) {
                                discoverItems2.addItem(addItem);
                            }
                        }
                    } else if (discoverItems.getNode() != null) {
                        discoverItems2.setType(IQ.Type.ERROR);
                        discoverItems2.setError(new XMPPError(XMPPError.Condition.item_not_found));
                    }
                    ServiceDiscoveryManager.this.connection.sendPacket(discoverItems2);
                }
            }
        }, packetTypeFilter2);
        PacketTypeFilter packetTypeFilter3 = new PacketTypeFilter(DiscoverInfo.class);
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                DiscoverInfo discoverInfo = (DiscoverInfo) packet;
                if (discoverInfo != null && discoverInfo.getType() == IQ.Type.GET) {
                    DiscoverInfo discoverInfo2 = new DiscoverInfo();
                    discoverInfo2.setType(IQ.Type.RESULT);
                    discoverInfo2.setTo(discoverInfo.getFrom());
                    discoverInfo2.setPacketID(discoverInfo.getPacketID());
                    discoverInfo2.setNode(discoverInfo.getNode());
                    if (!(discoverInfo.getNode() == null || ServiceDiscoveryManager.this.capsManager == null)) {
                        if (!(ServiceDiscoveryManager.this.capsManager.getNode() + "#" + ServiceDiscoveryManager.this.getEntityCapsVersion()).equals(discoverInfo.getNode())) {
                            NodeInformationProvider access$500 = ServiceDiscoveryManager.this.getNodeInformationProvider(discoverInfo.getNode());
                            if (access$500 != null) {
                                List<String> nodeFeatures = access$500.getNodeFeatures();
                                if (nodeFeatures != null) {
                                    for (String addFeature : nodeFeatures) {
                                        discoverInfo2.addFeature(addFeature);
                                    }
                                }
                                List<DiscoverInfo.Identity> nodeIdentities = access$500.getNodeIdentities();
                                if (nodeIdentities != null) {
                                    for (DiscoverInfo.Identity addIdentity : nodeIdentities) {
                                        discoverInfo2.addIdentity(addIdentity);
                                    }
                                }
                            } else {
                                discoverInfo2.setType(IQ.Type.ERROR);
                                discoverInfo2.setError(new XMPPError(XMPPError.Condition.item_not_found));
                            }
                            ServiceDiscoveryManager.this.connection.sendPacket(discoverInfo2);
                        }
                    }
                    ServiceDiscoveryManager.this.addDiscoverInfoTo(discoverInfo2);
                    ServiceDiscoveryManager.this.connection.sendPacket(discoverInfo2);
                }
            }
        }, packetTypeFilter3);
    }

    /* access modifiers changed from: private */
    public NodeInformationProvider getNodeInformationProvider(String str) {
        if (str == null) {
            return null;
        }
        return this.nodeInformationProviders.get(str);
    }

    public void setNodeInformationProvider(String str, NodeInformationProvider nodeInformationProvider) {
        this.nodeInformationProviders.put(str, nodeInformationProvider);
    }

    public void removeNodeInformationProvider(String str) {
        this.nodeInformationProviders.remove(str);
    }

    public Iterator<String> getFeatures() {
        Iterator<String> it;
        synchronized (this.features) {
            it = Collections.unmodifiableList(new ArrayList(this.features)).iterator();
        }
        return it;
    }

    public void addFeature(String str) {
        synchronized (this.features) {
            this.features.add(str);
            renewEntityCapsVersion();
        }
    }

    public void removeFeature(String str) {
        synchronized (this.features) {
            this.features.remove(str);
            renewEntityCapsVersion();
        }
    }

    public boolean includesFeature(String str) {
        boolean contains;
        synchronized (this.features) {
            contains = this.features.contains(str);
        }
        return contains;
    }

    public void setExtendedInfo(DataForm dataForm) {
        this.extendedInfo = dataForm;
        renewEntityCapsVersion();
    }

    public void removeExtendedInfo() {
        this.extendedInfo = null;
        renewEntityCapsVersion();
    }

    public DiscoverInfo discoverInfoByCaps(String str) throws XMPPException {
        DiscoverInfo discoverInfoByUser = this.capsManager.getDiscoverInfoByUser(str);
        if (discoverInfoByUser == null) {
            return null;
        }
        DiscoverInfo cloneDiscoverInfo = cloneDiscoverInfo(discoverInfoByUser);
        cloneDiscoverInfo.setFrom(str);
        return cloneDiscoverInfo;
    }

    public DiscoverInfo discoverInfo(String str) throws XMPPException {
        DiscoverInfo discoverInfoByCaps = discoverInfoByCaps(str);
        if (discoverInfoByCaps != null) {
            return discoverInfoByCaps;
        }
        String str2 = null;
        EntityCapsManager entityCapsManager = this.capsManager;
        if (entityCapsManager != null) {
            str2 = entityCapsManager.getNodeVersionByUser(str);
        }
        if (cacheNonCaps && str2 == null && this.nonCapsCache.containsKey(str)) {
            return this.nonCapsCache.get(str);
        }
        DiscoverInfo discoverInfo = discoverInfo(str, str2);
        if (str2 != null && this.capsManager != null) {
            EntityCapsManager.addDiscoverInfoByNode(str2, discoverInfo);
        } else if (cacheNonCaps && str2 == null) {
            this.nonCapsCache.put(str, discoverInfo);
        }
        return discoverInfo;
    }

    public DiscoverInfo discoverInfo(String str, String str2) throws XMPPException {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setType(IQ.Type.GET);
        discoverInfo.setTo(str);
        discoverInfo.setNode(str2);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(discoverInfo.getPacketID()));
        this.connection.sendPacket(discoverInfo);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            return (DiscoverInfo) iq;
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public DiscoverItems discoverItems(String str) throws XMPPException {
        return discoverItems(str, (String) null);
    }

    public DiscoverItems discoverItems(String str, String str2) throws XMPPException {
        DiscoverItems discoverItems = new DiscoverItems();
        discoverItems.setType(IQ.Type.GET);
        discoverItems.setTo(str);
        discoverItems.setNode(str2);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(discoverItems.getPacketID()));
        this.connection.sendPacket(discoverItems);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            return (DiscoverItems) iq;
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public boolean canPublishItems(String str) throws XMPPException {
        return canPublishItems(discoverInfo(str));
    }

    public static boolean canPublishItems(DiscoverInfo discoverInfo) {
        return discoverInfo.containsFeature("http://jabber.org/protocol/disco#publish");
    }

    public void publishItems(String str, DiscoverItems discoverItems) throws XMPPException {
        publishItems(str, (String) null, discoverItems);
    }

    public void publishItems(String str, String str2, DiscoverItems discoverItems) throws XMPPException {
        discoverItems.setType(IQ.Type.SET);
        discoverItems.setTo(str);
        discoverItems.setNode(str2);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(discoverItems.getPacketID()));
        this.connection.sendPacket(discoverItems);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() == IQ.Type.ERROR) {
            throw new XMPPException(iq.getError());
        }
    }

    private DiscoverInfo cloneDiscoverInfo(DiscoverInfo discoverInfo) {
        return discoverInfo.clone();
    }

    public void setEntityCapsManager(EntityCapsManager entityCapsManager) {
        this.capsManager = entityCapsManager;
        if (!(this.connection.getCapsNode() == null || this.connection.getHost() == null)) {
            this.capsManager.addUserCapsNode(this.connection.getHost(), this.connection.getCapsNode());
        }
        this.capsManager.addPacketListener(this.connection);
    }

    private void renewEntityCapsVersion() {
        EntityCapsManager entityCapsManager;
        if ((this.connection instanceof XMPPConnection) && (entityCapsManager = this.capsManager) != null) {
            entityCapsManager.calculateEntityCapsVersion(getOwnDiscoverInfo(), identityType, identityName, this.features, this.extendedInfo);
        }
    }

    /* access modifiers changed from: private */
    public String getEntityCapsVersion() {
        EntityCapsManager entityCapsManager = this.capsManager;
        if (entityCapsManager != null) {
            return entityCapsManager.getCapsVersion();
        }
        return null;
    }

    public EntityCapsManager getEntityCapsManager() {
        return this.capsManager;
    }

    private void setSendPresence() {
        this.sendPresence = true;
    }

    /* access modifiers changed from: private */
    public boolean isSendPresence() {
        return this.sendPresence;
    }

    private class CapsPresenceRenewer implements CapsVerListener {
        private CapsPresenceRenewer() {
        }

        public void capsVerUpdated(String str) {
            if (!((XMPPConnection) ServiceDiscoveryManager.this.connection).isAuthenticated()) {
                return;
            }
            if (((XMPPConnection) ServiceDiscoveryManager.this.connection).isSendPresence() || ServiceDiscoveryManager.this.isSendPresence()) {
                ServiceDiscoveryManager.this.connection.sendPacket(new Presence(Presence.Type.available));
            }
        }
    }
}
