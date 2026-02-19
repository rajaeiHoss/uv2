package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.NodeInformationProvider;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.packet.AdHocCommandData;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class AdHocCommandManager {
    private static final String DISCO_NAMESPACE = "http://jabber.org/protocol/commands";
    private static final int SESSION_TIMEOUT = 120;
    private static final String discoNode = "http://jabber.org/protocol/commands";
    /* access modifiers changed from: private */
    public static Map<Connection, AdHocCommandManager> instances = new ConcurrentHashMap();
    private Map<String, AdHocCommandInfo> commands;
    /* access modifiers changed from: private */
    public Connection connection;
    /* access modifiers changed from: private */
    public Map<String, LocalCommand> executingCommands;
    private Thread sessionsSweeper;

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                new AdHocCommandManager(connection);
            }
        });
    }

    public static AdHocCommandManager getAddHocCommandsManager(Connection connection2) {
        return instances.get(connection2);
    }

    private AdHocCommandManager(Connection connection2) {
        this.commands = new ConcurrentHashMap();
        this.executingCommands = new ConcurrentHashMap();
        this.connection = connection2;
        init();
    }

    public void registerCommand(String str, String str2, final Class cls) {
        registerCommand(str, str2, (LocalCommandFactory) new LocalCommandFactory() {
            public LocalCommand getInstance() throws InstantiationException, IllegalAccessException {
                return (LocalCommand) cls.newInstance();
            }
        });
    }

    public void registerCommand(String str, final String str2, LocalCommandFactory localCommandFactory) {
        this.commands.put(str, new AdHocCommandInfo(str, str2, this.connection.getUser(), localCommandFactory));
        ServiceDiscoveryManager.getInstanceFor(this.connection).setNodeInformationProvider(str, new NodeInformationProvider() {
            public List<DiscoverItems.Item> getNodeItems() {
                return null;
            }

            public List<String> getNodeFeatures() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(AdHocCommandData.SpecificError.namespace);
                arrayList.add("jabber:x:data");
                return arrayList;
            }

            public List<DiscoverInfo.Identity> getNodeIdentities() {
                ArrayList arrayList = new ArrayList();
                DiscoverInfo.Identity identity = new DiscoverInfo.Identity("automation", str2);
                identity.setType("command-node");
                arrayList.add(identity);
                return arrayList;
            }
        });
    }

    public DiscoverItems discoverCommands(String str) throws XMPPException {
        return ServiceDiscoveryManager.getInstanceFor(this.connection).discoverItems(str, AdHocCommandData.SpecificError.namespace);
    }

    public void publishCommands(String str) throws XMPPException {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        DiscoverItems discoverItems = new DiscoverItems();
        for (AdHocCommandInfo next : getRegisteredCommands()) {
            DiscoverItems.Item item = new DiscoverItems.Item(next.getOwnerJID());
            item.setName(next.getName());
            item.setNode(next.getNode());
            discoverItems.addItem(item);
        }
        instanceFor.publishItems(str, AdHocCommandData.SpecificError.namespace, discoverItems);
    }

    public RemoteCommand getRemoteCommand(String str, String str2) {
        return new RemoteCommand(this.connection, str2, str);
    }

    private void init() {
        instances.put(this.connection, this);
        this.connection.addConnectionListener(new ConnectionListener() {
            public void reconnectingIn(int i) {
            }

            public void reconnectionFailed(Exception exc) {
            }

            public void connectionClosed() {
                AdHocCommandManager.instances.remove(AdHocCommandManager.this.connection);
            }

            public void connectionClosedOnError(Exception exc) {
                AdHocCommandManager.instances.remove(AdHocCommandManager.this.connection);
            }

            public void reconnectionSuccessful() {
                AdHocCommandManager.instances.put(AdHocCommandManager.this.connection, AdHocCommandManager.this);
            }
        });
        ServiceDiscoveryManager.getInstanceFor(this.connection).addFeature(AdHocCommandData.SpecificError.namespace);
        ServiceDiscoveryManager.getInstanceFor(this.connection).setNodeInformationProvider(AdHocCommandData.SpecificError.namespace, new NodeInformationProvider() {
            public List<String> getNodeFeatures() {
                return null;
            }

            public List<DiscoverInfo.Identity> getNodeIdentities() {
                return null;
            }

            public List<DiscoverItems.Item> getNodeItems() {
                ArrayList arrayList = new ArrayList();
                for (AdHocCommandInfo adHocCommandInfo : AdHocCommandManager.this.getRegisteredCommands()) {
                    DiscoverItems.Item item = new DiscoverItems.Item(adHocCommandInfo.getOwnerJID());
                    item.setName(adHocCommandInfo.getName());
                    item.setNode(adHocCommandInfo.getNode());
                    arrayList.add(item);
                }
                return arrayList;
            }
        });
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                AdHocCommandManager.this.processAdHocCommand((AdHocCommandData) packet);
            }
        }, new PacketTypeFilter(AdHocCommandData.class));
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    for (String str : AdHocCommandManager.this.executingCommands.keySet()) {
                        LocalCommand localCommand = (LocalCommand) AdHocCommandManager.this.executingCommands.get(str);
                        if (localCommand != null) {
                            if (System.currentTimeMillis() - localCommand.getCreationDate() > 240000) {
                                AdHocCommandManager.this.executingCommands.remove(str);
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        });
        this.sessionsSweeper = thread;
        thread.setDaemon(true);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processAdHocCommand(org.jivesoftware.smackx.packet.AdHocCommandData r9) {
        /*
            r8 = this;
            org.jivesoftware.smack.packet.IQ$Type r0 = r9.getType()
            org.jivesoftware.smack.packet.IQ$Type r1 = org.jivesoftware.smack.packet.IQ.Type.SET
            if (r0 == r1) goto L_0x0009
            return
        L_0x0009:
            org.jivesoftware.smackx.packet.AdHocCommandData r0 = new org.jivesoftware.smackx.packet.AdHocCommandData
            r0.<init>()
            java.lang.String r1 = r9.getFrom()
            r0.setTo(r1)
            java.lang.String r1 = r9.getPacketID()
            r0.setPacketID(r1)
            java.lang.String r1 = r9.getNode()
            r0.setNode(r1)
            java.lang.String r1 = r9.getTo()
            r0.setId(r1)
            java.lang.String r1 = r9.getSessionID()
            java.lang.String r2 = r9.getNode()
            if (r1 != 0) goto L_0x00df
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.AdHocCommandManager$AdHocCommandInfo> r1 = r8.commands
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L_0x0042
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found
            r8.respondError((org.jivesoftware.smackx.packet.AdHocCommandData) r0, (org.jivesoftware.smack.packet.XMPPError.Condition) r9)
            return
        L_0x0042:
            r1 = 15
            java.lang.String r1 = org.jivesoftware.smack.util.StringUtils.randomString(r1)
            org.jivesoftware.smackx.commands.LocalCommand r2 = r8.newInstanceOfCmd(r2, r1)     // Catch:{ XMPPException -> 0x00bc }
            org.jivesoftware.smack.packet.IQ$Type r3 = org.jivesoftware.smack.packet.IQ.Type.RESULT     // Catch:{ XMPPException -> 0x00bc }
            r0.setType(r3)     // Catch:{ XMPPException -> 0x00bc }
            r2.setData(r0)     // Catch:{ XMPPException -> 0x00bc }
            java.lang.String r3 = r9.getFrom()     // Catch:{ XMPPException -> 0x00bc }
            boolean r3 = r2.hasPermission(r3)     // Catch:{ XMPPException -> 0x00bc }
            if (r3 != 0) goto L_0x0064
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.forbidden     // Catch:{ XMPPException -> 0x00bc }
            r8.respondError((org.jivesoftware.smackx.packet.AdHocCommandData) r0, (org.jivesoftware.smack.packet.XMPPError.Condition) r9)     // Catch:{ XMPPException -> 0x00bc }
            return
        L_0x0064:
            org.jivesoftware.smackx.commands.AdHocCommand$Action r9 = r9.getAction()     // Catch:{ XMPPException -> 0x00bc }
            if (r9 == 0) goto L_0x007a
            org.jivesoftware.smackx.commands.AdHocCommand$Action r3 = org.jivesoftware.smackx.commands.AdHocCommand.Action.unknown     // Catch:{ XMPPException -> 0x00bc }
            boolean r3 = r9.equals(r3)     // Catch:{ XMPPException -> 0x00bc }
            if (r3 == 0) goto L_0x007a
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request     // Catch:{ XMPPException -> 0x00bc }
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r2 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.malformedAction     // Catch:{ XMPPException -> 0x00bc }
            r8.respondError(r0, r9, r2)     // Catch:{ XMPPException -> 0x00bc }
            return
        L_0x007a:
            if (r9 == 0) goto L_0x008c
            org.jivesoftware.smackx.commands.AdHocCommand$Action r3 = org.jivesoftware.smackx.commands.AdHocCommand.Action.execute     // Catch:{ XMPPException -> 0x00bc }
            boolean r9 = r9.equals(r3)     // Catch:{ XMPPException -> 0x00bc }
            if (r9 != 0) goto L_0x008c
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request     // Catch:{ XMPPException -> 0x00bc }
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r2 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badAction     // Catch:{ XMPPException -> 0x00bc }
            r8.respondError(r0, r9, r2)     // Catch:{ XMPPException -> 0x00bc }
            return
        L_0x008c:
            r2.incrementStage()     // Catch:{ XMPPException -> 0x00bc }
            r2.execute()     // Catch:{ XMPPException -> 0x00bc }
            boolean r9 = r2.isLastStage()     // Catch:{ XMPPException -> 0x00bc }
            if (r9 == 0) goto L_0x009e
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.completed     // Catch:{ XMPPException -> 0x00bc }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x00bc }
            goto L_0x00b5
        L_0x009e:
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.executing     // Catch:{ XMPPException -> 0x00bc }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x00bc }
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r9 = r8.executingCommands     // Catch:{ XMPPException -> 0x00bc }
            r9.put(r1, r2)     // Catch:{ XMPPException -> 0x00bc }
            java.lang.Thread r9 = r8.sessionsSweeper     // Catch:{ XMPPException -> 0x00bc }
            boolean r9 = r9.isAlive()     // Catch:{ XMPPException -> 0x00bc }
            if (r9 != 0) goto L_0x00b5
            java.lang.Thread r9 = r8.sessionsSweeper     // Catch:{ XMPPException -> 0x00bc }
            r9.start()     // Catch:{ XMPPException -> 0x00bc }
        L_0x00b5:
            org.jivesoftware.smack.Connection r9 = r8.connection     // Catch:{ XMPPException -> 0x00bc }
            r9.sendPacket(r0)     // Catch:{ XMPPException -> 0x00bc }
            goto L_0x01e2
        L_0x00bc:
            r9 = move-exception
            org.jivesoftware.smack.packet.XMPPError r2 = r9.getXMPPError()
            org.jivesoftware.smack.packet.XMPPError$Type r3 = org.jivesoftware.smack.packet.XMPPError.Type.CANCEL
            org.jivesoftware.smack.packet.XMPPError$Type r4 = r2.getType()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00d7
            org.jivesoftware.smackx.commands.AdHocCommand$Status r3 = org.jivesoftware.smackx.commands.AdHocCommand.Status.canceled
            r0.setStatus(r3)
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r3 = r8.executingCommands
            r3.remove(r1)
        L_0x00d7:
            r8.respondError((org.jivesoftware.smackx.packet.AdHocCommandData) r0, (org.jivesoftware.smack.packet.XMPPError) r2)
            r9.printStackTrace()
            goto L_0x01e2
        L_0x00df:
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r2 = r8.executingCommands
            java.lang.Object r2 = r2.get(r1)
            org.jivesoftware.smackx.commands.LocalCommand r2 = (org.jivesoftware.smackx.commands.LocalCommand) r2
            if (r2 != 0) goto L_0x00f1
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r1 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badSessionid
            r8.respondError(r0, r9, r1)
            return
        L_0x00f1:
            long r3 = r2.getCreationDate()
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r3
            r3 = 120000(0x1d4c0, double:5.9288E-319)
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x010e
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r9 = r8.executingCommands
            r9.remove(r1)
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.not_allowed
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r1 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.sessionExpired
            r8.respondError(r0, r9, r1)
            return
        L_0x010e:
            monitor-enter(r2)
            org.jivesoftware.smackx.commands.AdHocCommand$Action r3 = r9.getAction()     // Catch:{ all -> 0x01e3 }
            if (r3 == 0) goto L_0x0126
            org.jivesoftware.smackx.commands.AdHocCommand$Action r4 = org.jivesoftware.smackx.commands.AdHocCommand.Action.unknown     // Catch:{ all -> 0x01e3 }
            boolean r4 = r3.equals(r4)     // Catch:{ all -> 0x01e3 }
            if (r4 == 0) goto L_0x0126
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request     // Catch:{ all -> 0x01e3 }
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r1 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.malformedAction     // Catch:{ all -> 0x01e3 }
            r8.respondError(r0, r9, r1)     // Catch:{ all -> 0x01e3 }
            monitor-exit(r2)     // Catch:{ all -> 0x01e3 }
            return
        L_0x0126:
            if (r3 == 0) goto L_0x0130
            org.jivesoftware.smackx.commands.AdHocCommand$Action r4 = org.jivesoftware.smackx.commands.AdHocCommand.Action.execute     // Catch:{ all -> 0x01e3 }
            boolean r4 = r4.equals(r3)     // Catch:{ all -> 0x01e3 }
            if (r4 == 0) goto L_0x0134
        L_0x0130:
            org.jivesoftware.smackx.commands.AdHocCommand$Action r3 = r2.getExecuteAction()     // Catch:{ all -> 0x01e3 }
        L_0x0134:
            boolean r4 = r2.isValidAction(r3)     // Catch:{ all -> 0x01e3 }
            if (r4 != 0) goto L_0x0143
            org.jivesoftware.smack.packet.XMPPError$Condition r9 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request     // Catch:{ all -> 0x01e3 }
            org.jivesoftware.smackx.commands.AdHocCommand$SpecificErrorCondition r1 = org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badAction     // Catch:{ all -> 0x01e3 }
            r8.respondError(r0, r9, r1)     // Catch:{ all -> 0x01e3 }
            monitor-exit(r2)     // Catch:{ all -> 0x01e3 }
            return
        L_0x0143:
            org.jivesoftware.smack.packet.IQ$Type r4 = org.jivesoftware.smack.packet.IQ.Type.RESULT     // Catch:{ XMPPException -> 0x01c0 }
            r0.setType(r4)     // Catch:{ XMPPException -> 0x01c0 }
            r2.setData(r0)     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.commands.AdHocCommand$Action r4 = org.jivesoftware.smackx.commands.AdHocCommand.Action.next     // Catch:{ XMPPException -> 0x01c0 }
            boolean r4 = r4.equals(r3)     // Catch:{ XMPPException -> 0x01c0 }
            if (r4 == 0) goto L_0x0174
            r2.incrementStage()     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.Form r3 = new org.jivesoftware.smackx.Form     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.packet.DataForm r9 = r9.getForm()     // Catch:{ XMPPException -> 0x01c0 }
            r3.<init>((org.jivesoftware.smackx.packet.DataForm) r9)     // Catch:{ XMPPException -> 0x01c0 }
            r2.next(r3)     // Catch:{ XMPPException -> 0x01c0 }
            boolean r9 = r2.isLastStage()     // Catch:{ XMPPException -> 0x01c0 }
            if (r9 == 0) goto L_0x016e
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.completed     // Catch:{ XMPPException -> 0x01c0 }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x01c0 }
            goto L_0x01ba
        L_0x016e:
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.executing     // Catch:{ XMPPException -> 0x01c0 }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x01c0 }
            goto L_0x01ba
        L_0x0174:
            org.jivesoftware.smackx.commands.AdHocCommand$Action r4 = org.jivesoftware.smackx.commands.AdHocCommand.Action.complete     // Catch:{ XMPPException -> 0x01c0 }
            boolean r4 = r4.equals(r3)     // Catch:{ XMPPException -> 0x01c0 }
            if (r4 == 0) goto L_0x0196
            r2.incrementStage()     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.Form r3 = new org.jivesoftware.smackx.Form     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.packet.DataForm r9 = r9.getForm()     // Catch:{ XMPPException -> 0x01c0 }
            r3.<init>((org.jivesoftware.smackx.packet.DataForm) r9)     // Catch:{ XMPPException -> 0x01c0 }
            r2.complete(r3)     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.completed     // Catch:{ XMPPException -> 0x01c0 }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x01c0 }
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r9 = r8.executingCommands     // Catch:{ XMPPException -> 0x01c0 }
            r9.remove(r1)     // Catch:{ XMPPException -> 0x01c0 }
            goto L_0x01ba
        L_0x0196:
            org.jivesoftware.smackx.commands.AdHocCommand$Action r9 = org.jivesoftware.smackx.commands.AdHocCommand.Action.prev     // Catch:{ XMPPException -> 0x01c0 }
            boolean r9 = r9.equals(r3)     // Catch:{ XMPPException -> 0x01c0 }
            if (r9 == 0) goto L_0x01a5
            r2.decrementStage()     // Catch:{ XMPPException -> 0x01c0 }
            r2.prev()     // Catch:{ XMPPException -> 0x01c0 }
            goto L_0x01ba
        L_0x01a5:
            org.jivesoftware.smackx.commands.AdHocCommand$Action r9 = org.jivesoftware.smackx.commands.AdHocCommand.Action.cancel     // Catch:{ XMPPException -> 0x01c0 }
            boolean r9 = r9.equals(r3)     // Catch:{ XMPPException -> 0x01c0 }
            if (r9 == 0) goto L_0x01ba
            r2.cancel()     // Catch:{ XMPPException -> 0x01c0 }
            org.jivesoftware.smackx.commands.AdHocCommand$Status r9 = org.jivesoftware.smackx.commands.AdHocCommand.Status.canceled     // Catch:{ XMPPException -> 0x01c0 }
            r0.setStatus(r9)     // Catch:{ XMPPException -> 0x01c0 }
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r9 = r8.executingCommands     // Catch:{ XMPPException -> 0x01c0 }
            r9.remove(r1)     // Catch:{ XMPPException -> 0x01c0 }
        L_0x01ba:
            org.jivesoftware.smack.Connection r9 = r8.connection     // Catch:{ XMPPException -> 0x01c0 }
            r9.sendPacket(r0)     // Catch:{ XMPPException -> 0x01c0 }
            goto L_0x01e1
        L_0x01c0:
            r9 = move-exception
            org.jivesoftware.smack.packet.XMPPError r3 = r9.getXMPPError()     // Catch:{ all -> 0x01e3 }
            org.jivesoftware.smack.packet.XMPPError$Type r4 = org.jivesoftware.smack.packet.XMPPError.Type.CANCEL     // Catch:{ all -> 0x01e3 }
            org.jivesoftware.smack.packet.XMPPError$Type r5 = r3.getType()     // Catch:{ all -> 0x01e3 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x01e3 }
            if (r4 == 0) goto L_0x01db
            org.jivesoftware.smackx.commands.AdHocCommand$Status r4 = org.jivesoftware.smackx.commands.AdHocCommand.Status.canceled     // Catch:{ all -> 0x01e3 }
            r0.setStatus(r4)     // Catch:{ all -> 0x01e3 }
            java.util.Map<java.lang.String, org.jivesoftware.smackx.commands.LocalCommand> r4 = r8.executingCommands     // Catch:{ all -> 0x01e3 }
            r4.remove(r1)     // Catch:{ all -> 0x01e3 }
        L_0x01db:
            r8.respondError((org.jivesoftware.smackx.packet.AdHocCommandData) r0, (org.jivesoftware.smack.packet.XMPPError) r3)     // Catch:{ all -> 0x01e3 }
            r9.printStackTrace()     // Catch:{ all -> 0x01e3 }
        L_0x01e1:
            monitor-exit(r2)     // Catch:{ all -> 0x01e3 }
        L_0x01e2:
            return
        L_0x01e3:
            r9 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01e3 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.commands.AdHocCommandManager.processAdHocCommand(org.jivesoftware.smackx.packet.AdHocCommandData):void");
    }

    private void respondError(AdHocCommandData adHocCommandData, XMPPError.Condition condition) {
        respondError(adHocCommandData, new XMPPError(condition));
    }

    private void respondError(AdHocCommandData adHocCommandData, XMPPError.Condition condition, AdHocCommand.SpecificErrorCondition specificErrorCondition) {
        XMPPError xMPPError = new XMPPError(condition);
        xMPPError.addExtension(new AdHocCommandData.SpecificError(specificErrorCondition));
        respondError(adHocCommandData, xMPPError);
    }

    private void respondError(AdHocCommandData adHocCommandData, XMPPError xMPPError) {
        adHocCommandData.setType(IQ.Type.ERROR);
        adHocCommandData.setError(xMPPError);
        this.connection.sendPacket(adHocCommandData);
    }

    private LocalCommand newInstanceOfCmd(String str, String str2) throws XMPPException {
        AdHocCommandInfo adHocCommandInfo = this.commands.get(str);
        try {
            LocalCommand commandInstance = adHocCommandInfo.getCommandInstance();
            commandInstance.setSessionID(str2);
            commandInstance.setName(adHocCommandInfo.getName());
            commandInstance.setNode(adHocCommandInfo.getNode());
            return commandInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new XMPPException(new XMPPError(XMPPError.Condition.interna_server_error));
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new XMPPException(new XMPPError(XMPPError.Condition.interna_server_error));
        }
    }

    /* access modifiers changed from: private */
    public Collection<AdHocCommandInfo> getRegisteredCommands() {
        return this.commands.values();
    }

    private static class AdHocCommandInfo {
        private LocalCommandFactory factory;
        private String name;
        private String node;
        private String ownerJID;

        public AdHocCommandInfo(String str, String str2, String str3, LocalCommandFactory localCommandFactory) {
            this.node = str;
            this.name = str2;
            this.ownerJID = str3;
            this.factory = localCommandFactory;
        }

        public LocalCommand getCommandInstance() throws InstantiationException, IllegalAccessException {
            return this.factory.getInstance();
        }

        public String getName() {
            return this.name;
        }

        public String getNode() {
            return this.node;
        }

        public String getOwnerJID() {
            return this.ownerJID;
        }
    }
}
