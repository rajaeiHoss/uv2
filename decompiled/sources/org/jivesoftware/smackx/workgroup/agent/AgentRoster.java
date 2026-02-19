package org.jivesoftware.smackx.workgroup.agent;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.workgroup.packet.AgentStatus;
import org.jivesoftware.smackx.workgroup.packet.AgentStatusRequest;

public class AgentRoster {
    private static final int EVENT_AGENT_ADDED = 0;
    private static final int EVENT_AGENT_REMOVED = 1;
    private static final int EVENT_PRESENCE_CHANGED = 2;
    private Connection connection;
    /* access modifiers changed from: private */
    public List<String> entries;
    private List<AgentRosterListener> listeners;
    /* access modifiers changed from: private */
    public Map<String, Map<String, Presence>> presenceMap;
    boolean rosterInitialized = false;
    /* access modifiers changed from: private */
    public String workgroupJID;

    AgentRoster(Connection connection2, String str) {
        this.connection = connection2;
        this.workgroupJID = str;
        this.entries = new ArrayList();
        this.listeners = new ArrayList();
        this.presenceMap = new HashMap();
        connection2.addPacketListener(new AgentStatusListener(), new PacketTypeFilter(AgentStatusRequest.class));
        connection2.addPacketListener(new PresencePacketListener(), new PacketTypeFilter(Presence.class));
        AgentStatusRequest agentStatusRequest = new AgentStatusRequest();
        agentStatusRequest.setTo(str);
        connection2.sendPacket(agentStatusRequest);
    }

    public void reload() {
        AgentStatusRequest agentStatusRequest = new AgentStatusRequest();
        agentStatusRequest.setTo(this.workgroupJID);
        this.connection.sendPacket(agentStatusRequest);
    }

    public void addListener(AgentRosterListener agentRosterListener) {
        synchronized (this.listeners) {
            if (!this.listeners.contains(agentRosterListener)) {
                this.listeners.add(agentRosterListener);
                for (String next : getAgents()) {
                    if (this.entries.contains(next)) {
                        agentRosterListener.agentAdded(next);
                        Map<String, Presence> map = this.presenceMap.get(next);
                        if (map != null) {
                            for (Presence presenceChanged : map.values()) {
                                agentRosterListener.presenceChanged(presenceChanged);
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeListener(AgentRosterListener agentRosterListener) {
        synchronized (this.listeners) {
            this.listeners.remove(agentRosterListener);
        }
    }

    public int getAgentCount() {
        return this.entries.size();
    }

    public Set<String> getAgents() {
        HashSet hashSet = new HashSet();
        synchronized (this.entries) {
            for (String add : this.entries) {
                hashSet.add(add);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public boolean contains(String str) {
        if (str == null) {
            return false;
        }
        synchronized (this.entries) {
            for (String lowerCase : this.entries) {
                if (lowerCase.toLowerCase().equals(str.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    public Presence getPresence(String str) {
        Map map = this.presenceMap.get(getPresenceMapKey(str));
        if (map == null) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setFrom(str);
            return presence;
        }
        Presence presence2 = null;
        for (Object obj : map.keySet()) {
            Presence presence3 = (Presence) map.get(obj);
            if (presence2 == null || presence3.getPriority() > presence2.getPriority()) {
                presence2 = presence3;
            }
        }
        if (presence2 != null) {
            return presence2;
        }
        Presence presence4 = new Presence(Presence.Type.unavailable);
        presence4.setFrom(str);
        return presence4;
    }

    /* access modifiers changed from: private */
    public String getPresenceMapKey(String str) {
        return !contains(str) ? StringUtils.parseBareAddress(str).toLowerCase() : str;
    }

    /* access modifiers changed from: private */
    public void fireEvent(int i, Object obj) {
        int size;
        AgentRosterListener[] agentRosterListenerArr;
        synchronized (this.listeners) {
            size = this.listeners.size();
            agentRosterListenerArr = new AgentRosterListener[size];
            this.listeners.toArray(agentRosterListenerArr);
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (i == 0) {
                agentRosterListenerArr[i2].agentAdded((String) obj);
            } else if (i == 1) {
                agentRosterListenerArr[i2].agentRemoved((String) obj);
            } else if (i == 2) {
                agentRosterListenerArr[i2].presenceChanged((Presence) obj);
            }
        }
    }

    private class PresencePacketListener implements PacketListener {
        private PresencePacketListener() {
        }

        public void processPacket(Packet packet) {
            Map map;
            Presence presence = (Presence) packet;
            String from = presence.getFrom();
            if (from == null) {
                PrintStream printStream = System.out;
                printStream.println("Presence with no FROM: " + presence.toXML());
                return;
            }
            String access$200 = AgentRoster.this.getPresenceMapKey(from);
            if (presence.getType() == Presence.Type.available) {
                AgentStatus agentStatus = (AgentStatus) presence.getExtension(AgentStatus.ELEMENT_NAME, "http://jabber.org/protocol/workgroup");
                if (agentStatus != null && AgentRoster.this.workgroupJID.equals(agentStatus.getWorkgroupJID())) {
                    if (AgentRoster.this.presenceMap.get(access$200) == null) {
                        map = new HashMap();
                        AgentRoster.this.presenceMap.put(access$200, map);
                    } else {
                        map = (Map) AgentRoster.this.presenceMap.get(access$200);
                    }
                    synchronized (map) {
                        map.put(StringUtils.parseResource(from), presence);
                    }
                    synchronized (AgentRoster.this.entries) {
                        for (String lowerCase : AgentRoster.this.entries) {
                            if (lowerCase.toLowerCase().equals(StringUtils.parseBareAddress(access$200).toLowerCase())) {
                                AgentRoster.this.fireEvent(2, packet);
                            }
                        }
                    }
                }
            } else if (presence.getType() == Presence.Type.unavailable) {
                if (AgentRoster.this.presenceMap.get(access$200) != null) {
                    Map map2 = (Map) AgentRoster.this.presenceMap.get(access$200);
                    synchronized (map2) {
                        map2.remove(StringUtils.parseResource(from));
                    }
                    if (map2.isEmpty()) {
                        AgentRoster.this.presenceMap.remove(access$200);
                    }
                }
                synchronized (AgentRoster.this.entries) {
                    for (String lowerCase2 : AgentRoster.this.entries) {
                        if (lowerCase2.toLowerCase().equals(StringUtils.parseBareAddress(access$200).toLowerCase())) {
                            AgentRoster.this.fireEvent(2, packet);
                        }
                    }
                }
            }
        }
    }

    private class AgentStatusListener implements PacketListener {
        private AgentStatusListener() {
        }

        public void processPacket(Packet packet) {
            if (packet instanceof AgentStatusRequest) {
                for (AgentStatusRequest.Item next : ((AgentStatusRequest) packet).getAgents()) {
                    String jid = next.getJID();
                    if ("remove".equals(next.getType())) {
                        AgentRoster.this.presenceMap.remove(StringUtils.parseName(StringUtils.parseName(jid) + "@" + StringUtils.parseServer(jid)));
                        AgentRoster.this.fireEvent(1, jid);
                    } else {
                        AgentRoster.this.entries.add(jid);
                        AgentRoster.this.fireEvent(0, jid);
                    }
                }
                AgentRoster.this.rosterInitialized = true;
            }
        }
    }
}
