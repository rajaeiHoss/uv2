package org.jivesoftware.smackx.pubsub;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.packet.SyncPacketSend;
import org.jivesoftware.smackx.pubsub.util.NodeUtils;

public class PubSubManager {
    private Connection con;
    private Map<String, Node> nodeMap = new ConcurrentHashMap();
    private String to;

    public PubSubManager(Connection connection) {
        this.con = connection;
    }

    public PubSubManager(Connection connection, String str) {
        this.con = connection;
        this.to = str;
    }

    public LeafNode createNode() throws XMPPException {
        LeafNode leafNode = new LeafNode(this.con, ((NodeExtension) ((PubSub) sendPubsubPacket(IQ.Type.SET, new NodeExtension(PubSubElementType.CREATE))).getExtension("create", PubSubNamespace.BASIC.getXmlns())).getNode());
        leafNode.setTo(this.to);
        this.nodeMap.put(leafNode.getId(), leafNode);
        return leafNode;
    }

    public LeafNode createNode(String str) throws XMPPException {
        return (LeafNode) createNode(str, (Form) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smackx.pubsub.Node createNode(java.lang.String r5, org.jivesoftware.smackx.Form r6) throws org.jivesoftware.smack.XMPPException {
        /*
            r4 = this;
            java.lang.String r0 = r4.to
            org.jivesoftware.smack.packet.IQ$Type r1 = org.jivesoftware.smack.packet.IQ.Type.SET
            org.jivesoftware.smackx.pubsub.NodeExtension r2 = new org.jivesoftware.smackx.pubsub.NodeExtension
            org.jivesoftware.smackx.pubsub.PubSubElementType r3 = org.jivesoftware.smackx.pubsub.PubSubElementType.CREATE
            r2.<init>(r3, r5)
            org.jivesoftware.smackx.pubsub.packet.PubSub r0 = createPubsubPacket(r0, r1, r2)
            if (r6 == 0) goto L_0x003c
            org.jivesoftware.smackx.pubsub.FormNode r1 = new org.jivesoftware.smackx.pubsub.FormNode
            org.jivesoftware.smackx.pubsub.FormNodeType r2 = org.jivesoftware.smackx.pubsub.FormNodeType.CONFIGURE
            r1.<init>(r2, r6)
            r0.addExtension(r1)
            org.jivesoftware.smackx.pubsub.ConfigureNodeFields r1 = org.jivesoftware.smackx.pubsub.ConfigureNodeFields.node_type
            java.lang.String r1 = r1.getFieldName()
            org.jivesoftware.smackx.FormField r6 = r6.getField(r1)
            if (r6 == 0) goto L_0x003c
            java.util.Iterator r6 = r6.getValues()
            java.lang.Object r6 = r6.next()
            java.lang.String r6 = (java.lang.String) r6
            org.jivesoftware.smackx.pubsub.NodeType r1 = org.jivesoftware.smackx.pubsub.NodeType.leaf
            java.lang.String r1 = r1.toString()
            boolean r6 = r6.equals(r1)
            goto L_0x003d
        L_0x003c:
            r6 = 1
        L_0x003d:
            org.jivesoftware.smack.Connection r1 = r4.con
            java.lang.String r2 = r4.to
            org.jivesoftware.smack.packet.IQ$Type r3 = org.jivesoftware.smack.packet.IQ.Type.SET
            sendPubsubPacket((org.jivesoftware.smack.Connection) r1, (java.lang.String) r2, (org.jivesoftware.smack.packet.IQ.Type) r3, (org.jivesoftware.smackx.pubsub.packet.PubSub) r0)
            if (r6 == 0) goto L_0x0050
            org.jivesoftware.smackx.pubsub.LeafNode r6 = new org.jivesoftware.smackx.pubsub.LeafNode
            org.jivesoftware.smack.Connection r0 = r4.con
            r6.<init>(r0, r5)
            goto L_0x0057
        L_0x0050:
            org.jivesoftware.smackx.pubsub.CollectionNode r6 = new org.jivesoftware.smackx.pubsub.CollectionNode
            org.jivesoftware.smack.Connection r0 = r4.con
            r6.<init>(r0, r5)
        L_0x0057:
            java.lang.String r5 = r4.to
            r6.setTo(r5)
            java.util.Map<java.lang.String, org.jivesoftware.smackx.pubsub.Node> r5 = r4.nodeMap
            java.lang.String r0 = r6.getId()
            r5.put(r0, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.pubsub.PubSubManager.createNode(java.lang.String, org.jivesoftware.smackx.Form):org.jivesoftware.smackx.pubsub.Node");
    }

    public Node getNode(String str) throws XMPPException {
        Node node = this.nodeMap.get(str);
        if (node == null) {
            DiscoverInfo discoverInfo = new DiscoverInfo();
            discoverInfo.setTo(this.to);
            discoverInfo.setNode(str);
            if (((DiscoverInfo) SyncPacketSend.getReply(this.con, discoverInfo)).getIdentities().next().getType().equals(NodeType.leaf.toString())) {
                node = new LeafNode(this.con, str);
            } else {
                node = new CollectionNode(this.con, str);
            }
            node.setTo(this.to);
            this.nodeMap.put(str, node);
        }
        return node;
    }

    public DiscoverItems discoverNodes(String str) throws XMPPException {
        DiscoverItems discoverItems = new DiscoverItems();
        if (str != null) {
            discoverItems.setNode(str);
        }
        discoverItems.setTo(this.to);
        return (DiscoverItems) SyncPacketSend.getReply(this.con, discoverItems);
    }

    public List<Subscription> getSubscriptions() throws XMPPException {
        return ((SubscriptionsExtension) sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.SUBSCRIPTIONS)).getExtension(PubSubElementType.SUBSCRIPTIONS.getElementName(), PubSubElementType.SUBSCRIPTIONS.getNamespace().getXmlns())).getSubscriptions();
    }

    public List<Affiliation> getAffiliations() throws XMPPException {
        return ((AffiliationsExtension) ((PubSub) sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.AFFILIATIONS))).getExtension(PubSubElementType.AFFILIATIONS)).getAffiliations();
    }

    public void deleteNode(String str) throws XMPPException {
        sendPubsubPacket(IQ.Type.SET, new NodeExtension(PubSubElementType.DELETE, str), PubSubElementType.DELETE.getNamespace());
        this.nodeMap.remove(str);
    }

    public ConfigureForm getDefaultConfiguration() throws XMPPException {
        return NodeUtils.getFormFromPacket((PubSub) sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.DEFAULT), PubSubElementType.DEFAULT.getNamespace()), PubSubElementType.DEFAULT);
    }

    public DiscoverInfo getSupportedFeatures() throws XMPPException {
        return ServiceDiscoveryManager.getInstanceFor(this.con).discoverInfo(this.to);
    }

    private Packet sendPubsubPacket(IQ.Type type, PacketExtension packetExtension, PubSubNamespace pubSubNamespace) throws XMPPException {
        return sendPubsubPacket(this.con, this.to, type, packetExtension, pubSubNamespace);
    }

    private Packet sendPubsubPacket(IQ.Type type, PacketExtension packetExtension) throws XMPPException {
        return sendPubsubPacket(type, packetExtension, (PubSubNamespace) null);
    }

    static PubSub createPubsubPacket(String str, IQ.Type type, PacketExtension packetExtension) {
        return createPubsubPacket(str, type, packetExtension, (PubSubNamespace) null);
    }

    static PubSub createPubsubPacket(String str, IQ.Type type, PacketExtension packetExtension, PubSubNamespace pubSubNamespace) {
        PubSub pubSub = new PubSub();
        pubSub.setTo(str);
        pubSub.setType(type);
        if (pubSubNamespace != null) {
            pubSub.setPubSubNamespace(pubSubNamespace);
        }
        pubSub.addExtension(packetExtension);
        return pubSub;
    }

    static Packet sendPubsubPacket(Connection connection, String str, IQ.Type type, PacketExtension packetExtension) throws XMPPException {
        return sendPubsubPacket(connection, str, type, packetExtension, (PubSubNamespace) null);
    }

    static Packet sendPubsubPacket(Connection connection, String str, IQ.Type type, PacketExtension packetExtension, PubSubNamespace pubSubNamespace) throws XMPPException {
        return SyncPacketSend.getReply(connection, createPubsubPacket(str, type, packetExtension, pubSubNamespace));
    }

    static Packet sendPubsubPacket(Connection connection, String str, IQ.Type type, PubSub pubSub) throws XMPPException {
        return sendPubsubPacket(connection, str, type, pubSub, (PubSubNamespace) null);
    }

    static Packet sendPubsubPacket(Connection connection, String str, IQ.Type type, PubSub pubSub, PubSubNamespace pubSubNamespace) throws XMPPException {
        return SyncPacketSend.getReply(connection, pubSub);
    }
}
