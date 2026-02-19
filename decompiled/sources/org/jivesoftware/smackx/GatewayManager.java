package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class GatewayManager {
    private static Map<Connection, GatewayManager> instances = new HashMap();
    private Connection connection;
    private Map<String, Gateway> gateways = new HashMap();
    private Map<String, Gateway> localGateways = new HashMap();
    private Map<String, Gateway> nonLocalGateways = new HashMap();
    private Roster roster;
    private ServiceDiscoveryManager sdManager;

    private GatewayManager() {
    }

    private GatewayManager(Connection connection2) throws XMPPException {
        this.connection = connection2;
        this.roster = connection2.getRoster();
        this.sdManager = ServiceDiscoveryManager.getInstanceFor(connection2);
    }

    private void loadLocalGateways() throws XMPPException {
        Iterator<DiscoverItems.Item> items = this.sdManager.discoverItems(this.connection.getHost()).getItems();
        while (items.hasNext()) {
            discoverGateway(items.next().getEntityID());
        }
    }

    private void discoverGateway(String str) throws XMPPException {
        DiscoverInfo discoverInfo = this.sdManager.discoverInfo(str);
        Iterator<DiscoverInfo.Identity> identities = discoverInfo.getIdentities();
        while (identities.hasNext()) {
            DiscoverInfo.Identity next = identities.next();
            if (next.getCategory().toLowerCase().equals("gateway")) {
                this.gateways.put(str, new Gateway(this.connection, str));
                if (str.contains(this.connection.getHost())) {
                    this.localGateways.put(str, new Gateway(this.connection, str, discoverInfo, next));
                    return;
                } else {
                    this.nonLocalGateways.put(str, new Gateway(this.connection, str, discoverInfo, next));
                    return;
                }
            }
        }
    }

    private void loadNonLocalGateways() throws XMPPException {
        Roster roster2 = this.roster;
        if (roster2 != null) {
            for (RosterEntry next : roster2.getEntries()) {
                if (next.getUser().equalsIgnoreCase(StringUtils.parseServer(next.getUser())) && !next.getUser().contains(this.connection.getHost())) {
                    discoverGateway(next.getUser());
                }
            }
        }
    }

    public GatewayManager getInstanceFor(Connection connection2) throws XMPPException {
        synchronized (instances) {
            if (instances.containsKey(connection2)) {
                GatewayManager gatewayManager = instances.get(connection2);
                return gatewayManager;
            }
            GatewayManager gatewayManager2 = new GatewayManager(connection2);
            instances.put(connection2, gatewayManager2);
            return gatewayManager2;
        }
    }

    public List<Gateway> getLocalGateways() throws XMPPException {
        if (this.localGateways.size() == 0) {
            loadLocalGateways();
        }
        return new ArrayList(this.localGateways.values());
    }

    public List<Gateway> getNonLocalGateways() throws XMPPException {
        if (this.nonLocalGateways.size() == 0) {
            loadNonLocalGateways();
        }
        return new ArrayList(this.nonLocalGateways.values());
    }

    public void refreshNonLocalGateways() throws XMPPException {
        loadNonLocalGateways();
    }

    public Gateway getGateway(String str) {
        if (this.localGateways.containsKey(str)) {
            return this.localGateways.get(str);
        }
        if (this.nonLocalGateways.containsKey(str)) {
            return this.nonLocalGateways.get(str);
        }
        if (this.gateways.containsKey(str)) {
            return this.gateways.get(str);
        }
        Gateway gateway = new Gateway(this.connection, str);
        if (str.contains(this.connection.getHost())) {
            this.localGateways.put(str, gateway);
        } else {
            this.nonLocalGateways.put(str, gateway);
        }
        this.gateways.put(str, gateway);
        return gateway;
    }
}
