package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.packet.DelayInformation;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.Header;
import org.jivesoftware.smackx.packet.HeadersExtension;
import org.jivesoftware.smackx.pubsub.listener.ItemDeleteListener;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.listener.NodeConfigListener;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.packet.SyncPacketSend;
import org.jivesoftware.smackx.pubsub.util.NodeUtils;

public abstract class Node {
    protected Connection con;
    protected ConcurrentHashMap<NodeConfigListener, PacketListener> configEventToListenerMap = new ConcurrentHashMap<>();
    protected String id;
    protected ConcurrentHashMap<ItemDeleteListener, PacketListener> itemDeleteToListenerMap = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<ItemEventListener, PacketListener> itemEventToListenerMap = new ConcurrentHashMap<>();
    protected String to;

    protected Node(Connection connection, String str) {
        this.con = connection;
        this.id = str;
    }

    /* access modifiers changed from: package-private */
    public void setTo(String str) {
        this.to = str;
    }

    public String getId() {
        return this.id;
    }

    public ConfigureForm getNodeConfiguration() throws XMPPException {
        return NodeUtils.getFormFromPacket(sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.CONFIGURE_OWNER, getId()), PubSubNamespace.OWNER), PubSubElementType.CONFIGURE_OWNER);
    }

    public void sendConfigurationForm(Form form) throws XMPPException {
        SyncPacketSend.getReply(this.con, createPubsubPacket(IQ.Type.SET, new FormNode(FormNodeType.CONFIGURE_OWNER, getId(), form), PubSubNamespace.OWNER));
    }

    public DiscoverInfo discoverInfo() throws XMPPException {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setTo(this.to);
        discoverInfo.setNode(getId());
        return (DiscoverInfo) SyncPacketSend.getReply(this.con, discoverInfo);
    }

    public List<Subscription> getSubscriptions() throws XMPPException {
        return ((SubscriptionsExtension) ((PubSub) sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.SUBSCRIPTIONS, getId()))).getExtension(PubSubElementType.SUBSCRIPTIONS)).getSubscriptions();
    }

    public List<Subscription> getAllSubscriptions() throws XMPPException {
        return ((SubscriptionsExtension) ((PubSub) sendPubsubPacket(IQ.Type.GET, new NodeExtension(PubSubElementType.SUBSCRIPTIONS_OWNER, getId()), PubSubNamespace.OWNER)).getExtension(PubSubElementType.SUBSCRIPTIONS_OWNER)).getSubscriptions();
    }

    public Subscription subscribe(String str) throws XMPPException {
        return (Subscription) ((PubSub) sendPubsubPacket(IQ.Type.SET, new SubscribeExtension(str, getId()))).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public Subscription subscribe(String str, SubscribeForm subscribeForm) throws XMPPException {
        PubSub createPubsubPacket = createPubsubPacket(IQ.Type.SET, new SubscribeExtension(str, getId()));
        createPubsubPacket.addExtension(new FormNode(FormNodeType.OPTIONS, subscribeForm));
        return (Subscription) ((PubSub) PubSubManager.sendPubsubPacket(this.con, str, IQ.Type.SET, createPubsubPacket)).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public void unsubscribe(String str) throws XMPPException {
        unsubscribe(str, (String) null);
    }

    public void unsubscribe(String str, String str2) throws XMPPException {
        sendPubsubPacket(IQ.Type.SET, new UnsubscribeExtension(str, getId(), str2));
    }

    public SubscribeForm getSubscriptionOptions(String str) throws XMPPException {
        return getSubscriptionOptions(str, (String) null);
    }

    public SubscribeForm getSubscriptionOptions(String str, String str2) throws XMPPException {
        return new SubscribeForm(((FormNode) ((PubSub) sendPubsubPacket(IQ.Type.GET, new OptionsExtension(str, getId(), str2))).getExtension(PubSubElementType.OPTIONS)).getForm());
    }

    public void addItemEventListener(ItemEventListener itemEventListener) {
        ItemEventTranslator itemEventTranslator = new ItemEventTranslator(itemEventListener);
        this.itemEventToListenerMap.put(itemEventListener, itemEventTranslator);
        this.con.addPacketListener(itemEventTranslator, new EventContentFilter(EventElementType.items.toString(), "item"));
    }

    public void removeItemEventListener(ItemEventListener itemEventListener) {
        PacketListener remove = this.itemEventToListenerMap.remove(itemEventListener);
        if (remove != null) {
            this.con.removePacketListener(remove);
        }
    }

    public void addConfigurationListener(NodeConfigListener nodeConfigListener) {
        NodeConfigTranslator nodeConfigTranslator = new NodeConfigTranslator(nodeConfigListener);
        this.configEventToListenerMap.put(nodeConfigListener, nodeConfigTranslator);
        this.con.addPacketListener(nodeConfigTranslator, new EventContentFilter(EventElementType.configuration.toString()));
    }

    public void removeConfigurationListener(NodeConfigListener nodeConfigListener) {
        PacketListener remove = this.configEventToListenerMap.remove(nodeConfigListener);
        if (remove != null) {
            this.con.removePacketListener(remove);
        }
    }

    public void addItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        ItemDeleteTranslator itemDeleteTranslator = new ItemDeleteTranslator(itemDeleteListener);
        this.itemDeleteToListenerMap.put(itemDeleteListener, itemDeleteTranslator);
        this.con.addPacketListener(itemDeleteTranslator, new OrFilter(new EventContentFilter(EventElementType.items.toString(), "retract"), new EventContentFilter(EventElementType.purge.toString())));
    }

    public void removeItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        PacketListener remove = this.itemDeleteToListenerMap.remove(itemDeleteListener);
        if (remove != null) {
            this.con.removePacketListener(remove);
        }
    }

    public String toString() {
        return super.toString() + " " + getClass().getName() + " id: " + this.id;
    }

    /* access modifiers changed from: protected */
    public PubSub createPubsubPacket(IQ.Type type, PacketExtension packetExtension) {
        return createPubsubPacket(type, packetExtension, (PubSubNamespace) null);
    }

    /* access modifiers changed from: protected */
    public PubSub createPubsubPacket(IQ.Type type, PacketExtension packetExtension, PubSubNamespace pubSubNamespace) {
        return PubSubManager.createPubsubPacket(this.to, type, packetExtension, pubSubNamespace);
    }

    /* access modifiers changed from: protected */
    public Packet sendPubsubPacket(IQ.Type type, NodeExtension nodeExtension) throws XMPPException {
        return PubSubManager.sendPubsubPacket(this.con, this.to, type, (PacketExtension) nodeExtension);
    }

    /* access modifiers changed from: protected */
    public Packet sendPubsubPacket(IQ.Type type, NodeExtension nodeExtension, PubSubNamespace pubSubNamespace) throws XMPPException {
        return PubSubManager.sendPubsubPacket(this.con, this.to, type, (PacketExtension) nodeExtension, pubSubNamespace);
    }

    /* access modifiers changed from: private */
    public static List<String> getSubscriptionIds(Packet packet) {
        HeadersExtension headersExtension = (HeadersExtension) packet.getExtension("headers", HeadersExtension.NAMESPACE);
        if (headersExtension == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(headersExtension.getHeaders().size());
        for (Header value : headersExtension.getHeaders()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    public class ItemEventTranslator implements PacketListener {
        private ItemEventListener listener;

        public ItemEventTranslator(ItemEventListener itemEventListener) {
            this.listener = itemEventListener;
        }

        public void processPacket(Packet packet) {
            ItemsExtension itemsExtension = (ItemsExtension) ((EventElement) packet.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
            DelayInformation delayInformation = (DelayInformation) packet.getExtension("delay", "urn:xmpp:delay");
            if (delayInformation == null) {
                delayInformation = (DelayInformation) packet.getExtension(GroupChatInvitation.ELEMENT_NAME, "jabber:x:delay");
            }
            this.listener.handlePublishedItems(new ItemPublishEvent(itemsExtension.getNode(), itemsExtension.getItems(), Node.getSubscriptionIds(packet), delayInformation == null ? null : delayInformation.getStamp()));
        }
    }

    public class ItemDeleteTranslator implements PacketListener {
        private ItemDeleteListener listener;

        public ItemDeleteTranslator(ItemDeleteListener itemDeleteListener) {
            this.listener = itemDeleteListener;
        }

        public void processPacket(Packet packet) {
            EventElement eventElement = (EventElement) packet.getExtension("event", PubSubNamespace.EVENT.getXmlns());
            if (eventElement.getExtensions().get(0).getElementName().equals(PubSubElementType.PURGE_EVENT.getElementName())) {
                this.listener.handlePurge();
                return;
            }
            ItemsExtension itemsExtension = (ItemsExtension) eventElement.getEvent();
            List<? extends PacketExtension> items = itemsExtension.getItems();
            Iterator<? extends PacketExtension> it = items.iterator();
            ArrayList arrayList = new ArrayList(items.size());
            while (it.hasNext()) {
                arrayList.add(((RetractItem) it.next()).getId());
            }
            this.listener.handleDeletedItems(new ItemDeleteEvent(itemsExtension.getNode(), arrayList, Node.getSubscriptionIds(packet)));
        }
    }

    public class NodeConfigTranslator implements PacketListener {
        private NodeConfigListener listener;

        public NodeConfigTranslator(NodeConfigListener nodeConfigListener) {
            this.listener = nodeConfigListener;
        }

        public void processPacket(Packet packet) {
            this.listener.handleNodeConfiguration((ConfigurationEvent) ((EventElement) packet.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent());
        }
    }

    class EventContentFilter implements PacketFilter {
        private String firstElement;
        private String secondElement;

        EventContentFilter(String str) {
            this.firstElement = str;
        }

        EventContentFilter(String str, String str2) {
            this.firstElement = str;
            this.secondElement = str2;
        }

        public boolean accept(Packet packet) {
            EventElement eventElement;
            NodeExtension event;
            if (!(packet instanceof Message) || (eventElement = (EventElement) packet.getExtension("event", PubSubNamespace.EVENT.getXmlns())) == null || (event = eventElement.getEvent()) == null || !event.getElementName().equals(this.firstElement) || !event.getNode().equals(Node.this.getId())) {
                return false;
            }
            if (this.secondElement == null) {
                return true;
            }
            if (event instanceof EmbeddedPacketExtension) {
                List<PacketExtension> extensions = ((EmbeddedPacketExtension) event).getExtensions();
                if (extensions.size() <= 0 || !extensions.get(0).getElementName().equals(this.secondElement)) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }
}
