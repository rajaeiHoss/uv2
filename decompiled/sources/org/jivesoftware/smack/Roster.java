package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smack.util.StringUtils;

public class Roster {
    private static SubscriptionMode defaultSubscriptionMode = SubscriptionMode.accept_all;
    /* access modifiers changed from: private */
    public Connection connection;
    /* access modifiers changed from: private */
    public final Map<String, RosterEntry> entries;
    private final Map<String, RosterGroup> groups;
    /* access modifiers changed from: private */
    public RosterStorage persistentStorage;
    /* access modifiers changed from: private */
    public Map<String, Map<String, Presence>> presenceMap;
    private PresencePacketListener presencePacketListener;
    private String requestPacketId;
    boolean rosterInitialized;
    private final List<RosterListener> rosterListeners;
    /* access modifiers changed from: private */
    public SubscriptionMode subscriptionMode;
    private final List<RosterEntry> unfiledEntries;

    public enum SubscriptionMode {
        accept_all,
        reject_all,
        manual
    }

    public static SubscriptionMode getDefaultSubscriptionMode() {
        return defaultSubscriptionMode;
    }

    public static void setDefaultSubscriptionMode(SubscriptionMode subscriptionMode2) {
        defaultSubscriptionMode = subscriptionMode2;
    }

    Roster(Connection connection2, RosterStorage rosterStorage) {
        this(connection2);
        this.persistentStorage = rosterStorage;
    }

    Roster(Connection connection2) {
        this.rosterInitialized = false;
        this.subscriptionMode = getDefaultSubscriptionMode();
        this.connection = connection2;
        if (!connection2.getConfiguration().isRosterVersioningAvailable()) {
            this.persistentStorage = null;
        }
        this.groups = new ConcurrentHashMap();
        this.unfiledEntries = new CopyOnWriteArrayList();
        this.entries = new ConcurrentHashMap();
        this.rosterListeners = new CopyOnWriteArrayList();
        this.presenceMap = new ConcurrentHashMap();
        connection2.addPacketListener(new RosterPacketListener(), new PacketTypeFilter(RosterPacket.class));
        PacketTypeFilter packetTypeFilter = new PacketTypeFilter(Presence.class);
        PresencePacketListener presencePacketListener2 = new PresencePacketListener();
        this.presencePacketListener = presencePacketListener2;
        connection2.addPacketListener(presencePacketListener2, packetTypeFilter);
        final AbstractConnectionListener connectionListener = new AbstractConnectionListener() {
            public void connectionClosed() {
                Roster.this.setOfflinePresences();
            }

            public void connectionClosedOnError(Exception exc) {
                Roster.this.setOfflinePresences();
            }
        };
        if (!this.connection.isConnected()) {
            Connection.addConnectionCreationListener(new ConnectionCreationListener() {
                public void connectionCreated(Connection connection) {
                    if (connection.equals(Roster.this.connection)) {
                        Roster.this.connection.addConnectionListener(connectionListener);
                    }
                }
            });
        } else {
            connection2.addConnectionListener(connectionListener);
        }
    }

    public SubscriptionMode getSubscriptionMode() {
        return this.subscriptionMode;
    }

    public void setSubscriptionMode(SubscriptionMode subscriptionMode2) {
        this.subscriptionMode = subscriptionMode2;
    }

    public void reload() {
        if (!this.connection.isAuthenticated()) {
            throw new IllegalStateException("Not logged in to server.");
        } else if (!this.connection.isAnonymous()) {
            RosterPacket rosterPacket = new RosterPacket();
            RosterStorage rosterStorage = this.persistentStorage;
            if (rosterStorage != null) {
                rosterPacket.setVersion(rosterStorage.getRosterVersion());
            }
            String packetID = rosterPacket.getPacketID();
            this.requestPacketId = packetID;
            this.connection.addPacketListener(new RosterResultListener(), new PacketIDFilter(packetID));
            this.connection.sendPacket(rosterPacket);
        } else {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
    }

    public void addRosterListener(RosterListener rosterListener) {
        if (!this.rosterListeners.contains(rosterListener)) {
            this.rosterListeners.add(rosterListener);
        }
    }

    public void removeRosterListener(RosterListener rosterListener) {
        this.rosterListeners.remove(rosterListener);
    }

    public RosterGroup createGroup(String str) {
        if (!this.connection.isAuthenticated()) {
            throw new IllegalStateException("Not logged in to server.");
        } else if (this.connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else if (!this.groups.containsKey(str)) {
            RosterGroup rosterGroup = new RosterGroup(str, this.connection);
            this.groups.put(str, rosterGroup);
            return rosterGroup;
        } else {
            throw new IllegalArgumentException("Group with name " + str + " alread exists.");
        }
    }

    public void createEntry(String str, String str2, String[] strArr) throws XMPPException {
        if (!this.connection.isAuthenticated()) {
            throw new IllegalStateException("Not logged in to server.");
        } else if (!this.connection.isAnonymous()) {
            RosterPacket rosterPacket = new RosterPacket();
            rosterPacket.setType(IQ.Type.SET);
            RosterPacket.Item item = new RosterPacket.Item(str, str2);
            if (strArr != null) {
                for (String str3 : strArr) {
                    if (str3 != null && str3.trim().length() > 0) {
                        item.addGroupName(str3);
                    }
                }
            }
            rosterPacket.addRosterItem(item);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(rosterPacket.getPacketID()));
            this.connection.sendPacket(rosterPacket);
            IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getType() != IQ.Type.ERROR) {
                Presence presence = new Presence(Presence.Type.subscribe);
                presence.setTo(str);
                this.connection.sendPacket(presence);
            } else {
                throw new XMPPException(iq.getError());
            }
        } else {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        }
    }

    private void insertRosterItems(List<RosterPacket.Item> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (RosterPacket.Item insertRosterItem : list) {
            insertRosterItem(insertRosterItem, arrayList, arrayList2, arrayList3);
        }
        fireRosterChangedEvent(arrayList, arrayList2, arrayList3);
    }

    /* access modifiers changed from: private */
    public void insertRosterItem(RosterPacket.Item item, Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        RosterEntry rosterEntry = new RosterEntry(item.getUser(), item.getName(), item.getItemType(), item.getItemStatus(), this, this.connection);
        if (RosterPacket.ItemType.remove.equals(item.getItemType())) {
            if (this.entries.containsKey(item.getUser())) {
                this.entries.remove(item.getUser());
            }
            if (this.unfiledEntries.contains(rosterEntry)) {
                this.unfiledEntries.remove(rosterEntry);
            }
            this.presenceMap.remove(StringUtils.parseName(item.getUser()) + "@" + StringUtils.parseServer(item.getUser()));
            if (collection3 != null) {
                collection3.add(item.getUser());
            }
        } else {
            if (!this.entries.containsKey(item.getUser())) {
                this.entries.put(item.getUser(), rosterEntry);
                if (collection != null) {
                    collection.add(item.getUser());
                }
            } else {
                this.entries.put(item.getUser(), rosterEntry);
                if (collection2 != null) {
                    collection2.add(item.getUser());
                }
            }
            if (!item.getGroupNames().isEmpty()) {
                this.unfiledEntries.remove(rosterEntry);
            } else if (!this.unfiledEntries.contains(rosterEntry)) {
                this.unfiledEntries.add(rosterEntry);
            }
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (RosterGroup next : getGroups()) {
            if (next.contains(rosterEntry)) {
                arrayList.add(next.getName());
            }
        }
        if (!RosterPacket.ItemType.remove.equals(item.getItemType())) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            for (String next2 : item.getGroupNames()) {
                arrayList2.add(next2);
                RosterGroup group = getGroup(next2);
                if (group == null) {
                    group = createGroup(next2);
                    this.groups.put(next2, group);
                }
                group.addEntryLocal(rosterEntry);
            }
            for (String remove : arrayList2) {
                arrayList.remove(remove);
            }
        }
        for (String str : arrayList) {
            RosterGroup group2 = getGroup(str);
            group2.removeEntryLocal(rosterEntry);
            if (group2.getEntryCount() == 0) {
                this.groups.remove(str);
            }
        }
        for (RosterGroup next3 : getGroups()) {
            if (next3.getEntryCount() == 0) {
                this.groups.remove(next3.getName());
            }
        }
    }

    public void removeEntry(RosterEntry rosterEntry) throws XMPPException {
        if (!this.connection.isAuthenticated()) {
            throw new IllegalStateException("Not logged in to server.");
        } else if (this.connection.isAnonymous()) {
            throw new IllegalStateException("Anonymous users can't have a roster.");
        } else if (this.entries.containsKey(rosterEntry.getUser())) {
            RosterPacket rosterPacket = new RosterPacket();
            rosterPacket.setType(IQ.Type.SET);
            RosterPacket.Item rosterItem = RosterEntry.toRosterItem(rosterEntry);
            rosterItem.setItemType(RosterPacket.ItemType.remove);
            rosterPacket.addRosterItem(rosterItem);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(rosterPacket.getPacketID()));
            this.connection.sendPacket(rosterPacket);
            IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (iq == null) {
                throw new XMPPException("No response from the server.");
            } else if (iq.getType() == IQ.Type.ERROR) {
                throw new XMPPException(iq.getError());
            }
        }
    }

    public int getEntryCount() {
        return getEntries().size();
    }

    public Collection<RosterEntry> getEntries() {
        HashSet hashSet = new HashSet();
        for (RosterGroup entries2 : getGroups()) {
            hashSet.addAll(entries2.getEntries());
        }
        hashSet.addAll(this.unfiledEntries);
        return Collections.unmodifiableCollection(hashSet);
    }

    public int getUnfiledEntryCount() {
        return this.unfiledEntries.size();
    }

    public Collection<RosterEntry> getUnfiledEntries() {
        return Collections.unmodifiableList(this.unfiledEntries);
    }

    public RosterEntry getEntry(String str) {
        if (str == null) {
            return null;
        }
        return this.entries.get(str.toLowerCase());
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public RosterGroup getGroup(String str) {
        return this.groups.get(str);
    }

    public int getGroupCount() {
        return this.groups.size();
    }

    public Collection<RosterGroup> getGroups() {
        return Collections.unmodifiableCollection(this.groups.values());
    }

    public Presence getPresence(String str) {
        Map<String, Presence> map = this.presenceMap.get(getPresenceMapKey(StringUtils.parseBareAddress(str)));
        if (map == null) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setFrom(str);
            return presence;
        }
        Presence presence2 = null;
        for (String str2 : map.keySet()) {
            Presence presence3 = map.get(str2);
            if (presence3.isAvailable()) {
                if (presence2 != null && presence3.getPriority() <= presence2.getPriority()) {
                    if (presence3.getPriority() == presence2.getPriority()) {
                        Presence.Mode mode = presence3.getMode();
                        if (mode == null) {
                            mode = Presence.Mode.available;
                        }
                        Presence.Mode mode2 = presence2.getMode();
                        if (mode2 == null) {
                            mode2 = Presence.Mode.available;
                        }
                        if (mode.compareTo(mode2) >= 0) {
                        }
                    }
                }
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

    public Presence getPresenceResource(String str) {
        String presenceMapKey = getPresenceMapKey(str);
        String parseResource = StringUtils.parseResource(str);
        Map<String, Presence> map = this.presenceMap.get(presenceMapKey);
        if (map == null) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setFrom(str);
            return presence;
        }
        Presence presence2 = map.get(parseResource);
        if (presence2 != null) {
            return presence2;
        }
        Presence presence3 = new Presence(Presence.Type.unavailable);
        presence3.setFrom(str);
        return presence3;
    }

    public Iterator<Presence> getPresences(String str) {
        Map<String, Presence> map = this.presenceMap.get(getPresenceMapKey(str));
        if (map == null) {
            Presence presence = new Presence(Presence.Type.unavailable);
            presence.setFrom(str);
            return Arrays.asList(new Presence[]{presence}).iterator();
        }
        ArrayList arrayList = new ArrayList();
        for (Presence presence2 : map.values()) {
            if (presence2.isAvailable()) {
                arrayList.add(presence2);
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList.iterator();
        }
        Presence presence3 = new Presence(Presence.Type.unavailable);
        presence3.setFrom(str);
        return Arrays.asList(new Presence[]{presence3}).iterator();
    }

    /* access modifiers changed from: package-private */
    public void cleanup() {
        this.rosterListeners.clear();
    }

    /* access modifiers changed from: private */
    public String getPresenceMapKey(String str) {
        if (str == null) {
            return null;
        }
        if (!contains(str)) {
            str = StringUtils.parseBareAddress(str);
        }
        return str.toLowerCase();
    }

    /* access modifiers changed from: private */
    public void setOfflinePresences() {
        for (String next : this.presenceMap.keySet()) {
            Map<String, Presence> map = this.presenceMap.get(next);
            if (map != null) {
                for (String str : map.keySet()) {
                    Presence presence = new Presence(Presence.Type.unavailable);
                    presence.setFrom(next + "/" + str);
                    this.presencePacketListener.processPacket(presence);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void fireRosterChangedEvent(Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        for (RosterListener next : this.rosterListeners) {
            if (!collection.isEmpty()) {
                next.entriesAdded(collection);
            }
            if (!collection2.isEmpty()) {
                next.entriesUpdated(collection2);
            }
            if (!collection3.isEmpty()) {
                next.entriesDeleted(collection3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void fireRosterPresenceEvent(Presence presence) {
        for (RosterListener presenceChanged : this.rosterListeners) {
            presenceChanged.presenceChanged(presence);
        }
    }

    private class PresencePacketListener implements PacketListener {
        private PresencePacketListener() {
        }

        public void processPacket(Packet packet) {
            Map map;
            Map map2;
            Map map3;
            Presence presence = (Presence) packet;
            String from = presence.getFrom();
            String access$500 = Roster.this.getPresenceMapKey(from);
            if (presence.getType() == Presence.Type.available) {
                if (Roster.this.presenceMap.get(access$500) == null) {
                    map3 = new ConcurrentHashMap();
                    Roster.this.presenceMap.put(access$500, map3);
                } else {
                    map3 = (Map) Roster.this.presenceMap.get(access$500);
                }
                map3.remove("");
                map3.put(StringUtils.parseResource(from), presence);
                if (((RosterEntry) Roster.this.entries.get(access$500)) != null) {
                    Roster.this.fireRosterPresenceEvent(presence);
                }
            } else if (presence.getType() == Presence.Type.unavailable) {
                if ("".equals(StringUtils.parseResource(from))) {
                    if (Roster.this.presenceMap.get(access$500) == null) {
                        map2 = new ConcurrentHashMap();
                        Roster.this.presenceMap.put(access$500, map2);
                    } else {
                        map2 = (Map) Roster.this.presenceMap.get(access$500);
                    }
                    map2.put("", presence);
                } else if (Roster.this.presenceMap.get(access$500) != null) {
                    ((Map) Roster.this.presenceMap.get(access$500)).put(StringUtils.parseResource(from), presence);
                }
                if (((RosterEntry) Roster.this.entries.get(access$500)) != null) {
                    Roster.this.fireRosterPresenceEvent(presence);
                }
            } else if (presence.getType() == Presence.Type.subscribe) {
                if (Roster.this.subscriptionMode == SubscriptionMode.accept_all) {
                    Presence presence2 = new Presence(Presence.Type.subscribed);
                    presence2.setTo(presence.getFrom());
                    Roster.this.connection.sendPacket(presence2);
                } else if (Roster.this.subscriptionMode == SubscriptionMode.reject_all) {
                    Presence presence3 = new Presence(Presence.Type.unsubscribed);
                    presence3.setTo(presence.getFrom());
                    Roster.this.connection.sendPacket(presence3);
                }
            } else if (presence.getType() == Presence.Type.unsubscribe) {
                if (Roster.this.subscriptionMode != SubscriptionMode.manual) {
                    Presence presence4 = new Presence(Presence.Type.unsubscribed);
                    presence4.setTo(presence.getFrom());
                    Roster.this.connection.sendPacket(presence4);
                }
            } else if (presence.getType() == Presence.Type.error && "".equals(StringUtils.parseResource(from))) {
                if (!Roster.this.presenceMap.containsKey(access$500)) {
                    map = new ConcurrentHashMap();
                    Roster.this.presenceMap.put(access$500, map);
                } else {
                    map = (Map) Roster.this.presenceMap.get(access$500);
                    map.clear();
                }
                map.put("", presence);
                if (((RosterEntry) Roster.this.entries.get(access$500)) != null) {
                    Roster.this.fireRosterPresenceEvent(presence);
                }
            }
        }
    }

    private class RosterResultListener implements PacketListener {
        private RosterResultListener() {
        }

        public void processPacket(Packet packet) {
            if (packet instanceof IQ) {
                IQ iq = (IQ) packet;
                if (iq.getType().equals(IQ.Type.RESULT) && iq.getExtensions().isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    if (Roster.this.persistentStorage != null) {
                        for (RosterPacket.Item access$1100 : Roster.this.persistentStorage.getEntries()) {
                            Roster.this.insertRosterItem(access$1100, arrayList, arrayList2, arrayList3);
                        }
                    }
                    synchronized (Roster.this) {
                        Roster.this.rosterInitialized = true;
                        Roster.this.notifyAll();
                    }
                    Roster.this.fireRosterChangedEvent(arrayList, arrayList2, arrayList3);
                }
            }
            Roster.this.connection.removePacketListener(this);
        }
    }

    private class RosterPacketListener implements PacketListener {
        private RosterPacketListener() {
        }

        public void processPacket(Packet packet) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            RosterPacket rosterPacket = (RosterPacket) packet;
            ArrayList<RosterPacket.Item> arrayList4 = new ArrayList<>();
            for (RosterPacket.Item add : rosterPacket.getRosterItems()) {
                arrayList4.add(add);
            }
            String str = null;
            if (rosterPacket.getVersion() == null) {
                RosterStorage unused = Roster.this.persistentStorage = null;
            } else {
                str = rosterPacket.getVersion();
            }
            if (Roster.this.persistentStorage != null && !Roster.this.rosterInitialized) {
                for (RosterPacket.Item add2 : Roster.this.persistentStorage.getEntries()) {
                    arrayList4.add(add2);
                }
            }
            for (RosterPacket.Item access$1100 : arrayList4) {
                Roster.this.insertRosterItem(access$1100, arrayList, arrayList2, arrayList3);
            }
            if (Roster.this.persistentStorage != null) {
                for (RosterPacket.Item next : rosterPacket.getRosterItems()) {
                    if (next.getItemType().equals(RosterPacket.ItemType.remove)) {
                        Roster.this.persistentStorage.removeEntry(next.getUser());
                    } else {
                        Roster.this.persistentStorage.addEntry(next, str);
                    }
                }
            }
            synchronized (Roster.this) {
                Roster.this.rosterInitialized = true;
                Roster.this.notifyAll();
            }
            Roster.this.fireRosterChangedEvent(arrayList, arrayList2, arrayList3);
        }
    }
}
