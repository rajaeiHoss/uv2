package org.jivesoftware.smackx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.Cache;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.packet.MultipleAddresses;

public class MultipleRecipientManager {
    private static Cache services = new Cache(100, 86400000);

    public static void send(Connection connection, Packet packet, List list, List list2, List list3) throws XMPPException {
        send(connection, packet, list, list2, list3, (String) null, (String) null, false);
    }

    public static void send(Connection connection, Packet packet, List list, List list2, List list3, String str, String str2, boolean z) throws XMPPException {
        String multipleRecipienServiceAddress = getMultipleRecipienServiceAddress(connection);
        if (multipleRecipienServiceAddress != null) {
            sendThroughService(connection, packet, list, list2, list3, str, str2, z, multipleRecipienServiceAddress);
        } else if (z || ((str != null && str.trim().length() > 0) || (str2 != null && str2.trim().length() > 0))) {
            throw new XMPPException("Extended Stanza Addressing not supported by server");
        } else {
            sendToIndividualRecipients(connection, packet, list, list2, list3);
        }
    }

    public static void reply(Connection connection, Message message, Message message2) throws XMPPException {
        MultipleRecipientInfo multipleRecipientInfo = getMultipleRecipientInfo(message);
        if (multipleRecipientInfo == null) {
            throw new XMPPException("Original message does not contain multiple recipient info");
        } else if (multipleRecipientInfo.shouldNotReply()) {
            throw new XMPPException("Original message should not be replied");
        } else if (multipleRecipientInfo.getReplyRoom() == null) {
            if (message.getThread() != null) {
                message2.setThread(message.getThread());
            }
            MultipleAddresses.Address replyAddress = multipleRecipientInfo.getReplyAddress();
            if (replyAddress == null || replyAddress.getJid() == null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (MultipleAddresses.Address jid : multipleRecipientInfo.getTOAddresses()) {
                    arrayList.add(jid.getJid());
                }
                for (MultipleAddresses.Address jid2 : multipleRecipientInfo.getCCAddresses()) {
                    arrayList2.add(jid2.getJid());
                }
                if (!arrayList.contains(message.getFrom()) && !arrayList2.contains(message.getFrom())) {
                    arrayList.add(message.getFrom());
                }
                String user = connection.getUser();
                if (!arrayList.remove(user) && !arrayList2.remove(user)) {
                    String parseBareAddress = StringUtils.parseBareAddress(user);
                    arrayList.remove(parseBareAddress);
                    arrayList2.remove(parseBareAddress);
                }
                String multipleRecipienServiceAddress = getMultipleRecipienServiceAddress(connection);
                if (multipleRecipienServiceAddress != null) {
                    sendThroughService(connection, message2, arrayList, arrayList2, (List) null, (String) null, (String) null, false, multipleRecipienServiceAddress);
                } else {
                    sendToIndividualRecipients(connection, message2, arrayList, arrayList2, (List) null);
                }
            } else {
                message2.setTo(replyAddress.getJid());
                connection.sendPacket(message2);
            }
        } else {
            throw new XMPPException("Reply should be sent through a room");
        }
    }

    public static MultipleRecipientInfo getMultipleRecipientInfo(Packet packet) {
        MultipleAddresses multipleAddresses = (MultipleAddresses) packet.getExtension("addresses", "http://jabber.org/protocol/address");
        if (multipleAddresses == null) {
            return null;
        }
        return new MultipleRecipientInfo(multipleAddresses);
    }

    private static void sendToIndividualRecipients(Connection connection, Packet packet, List list, List list2, List list3) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                packet.setTo((String) it.next());
                connection.sendPacket(new PacketCopy(packet.toXML()));
            }
        }
        if (list2 != null) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                packet.setTo((String) it2.next());
                connection.sendPacket(new PacketCopy(packet.toXML()));
            }
        }
        if (list3 != null) {
            Iterator it3 = list3.iterator();
            while (it3.hasNext()) {
                packet.setTo((String) it3.next());
                connection.sendPacket(new PacketCopy(packet.toXML()));
            }
        }
    }

    private static void sendThroughService(Connection connection, Packet packet, List list, List list2, List list3, String str, String str2, boolean z, String str3) {
        Packet packet2 = packet;
        MultipleAddresses multipleAddresses = new MultipleAddresses();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                multipleAddresses.addAddress("to", (String) it.next(), (String) null, (String) null, false, (String) null);
            }
        }
        if (list2 != null) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                multipleAddresses.addAddress(MultipleAddresses.CC, (String) it2.next(), (String) null, (String) null, false, (String) null);
            }
        }
        if (list3 != null) {
            Iterator it3 = list3.iterator();
            while (it3.hasNext()) {
                multipleAddresses.addAddress(MultipleAddresses.BCC, (String) it3.next(), (String) null, (String) null, false, (String) null);
            }
        }
        if (z) {
            multipleAddresses.setNoReply();
        } else {
            if (str != null && str.trim().length() > 0) {
                multipleAddresses.addAddress(MultipleAddresses.REPLY_TO, str, (String) null, (String) null, false, (String) null);
            }
            if (str2 != null && str2.trim().length() > 0) {
                multipleAddresses.addAddress(MultipleAddresses.REPLY_ROOM, str2, (String) null, (String) null, false, (String) null);
            }
        }
        packet.setTo(str3);
        packet.addExtension(multipleAddresses);
        connection.sendPacket(packet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061 A[Catch:{ XMPPException -> 0x0069 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0064 A[Catch:{ XMPPException -> 0x0069 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getMultipleRecipienServiceAddress(org.jivesoftware.smack.Connection r7) {
        /*
            java.lang.String r0 = r7.getServiceName()
            org.jivesoftware.smack.util.Cache r1 = services
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 != 0) goto L_0x0072
            org.jivesoftware.smack.util.Cache r2 = services
            monitor-enter(r2)
            org.jivesoftware.smack.util.Cache r1 = services     // Catch:{ all -> 0x006f }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x006f }
            if (r1 != 0) goto L_0x006d
            org.jivesoftware.smackx.ServiceDiscoveryManager r3 = org.jivesoftware.smackx.ServiceDiscoveryManager.getInstanceFor(r7)     // Catch:{ XMPPException -> 0x0069 }
            org.jivesoftware.smackx.packet.DiscoverInfo r3 = r3.discoverInfo(r0)     // Catch:{ XMPPException -> 0x0069 }
            java.lang.String r4 = "http://jabber.org/protocol/address"
            boolean r3 = r3.containsFeature(r4)     // Catch:{ XMPPException -> 0x0069 }
            if (r3 == 0) goto L_0x002c
            goto L_0x005c
        L_0x002c:
            org.jivesoftware.smackx.ServiceDiscoveryManager r3 = org.jivesoftware.smackx.ServiceDiscoveryManager.getInstanceFor(r7)     // Catch:{ XMPPException -> 0x0069 }
            org.jivesoftware.smackx.packet.DiscoverItems r3 = r3.discoverItems(r0)     // Catch:{ XMPPException -> 0x0069 }
            java.util.Iterator r3 = r3.getItems()     // Catch:{ XMPPException -> 0x0069 }
        L_0x0038:
            boolean r4 = r3.hasNext()     // Catch:{ XMPPException -> 0x0069 }
            if (r4 == 0) goto L_0x005d
            java.lang.Object r4 = r3.next()     // Catch:{ XMPPException -> 0x0069 }
            org.jivesoftware.smackx.packet.DiscoverItems$Item r4 = (org.jivesoftware.smackx.packet.DiscoverItems.Item) r4     // Catch:{ XMPPException -> 0x0069 }
            org.jivesoftware.smackx.ServiceDiscoveryManager r5 = org.jivesoftware.smackx.ServiceDiscoveryManager.getInstanceFor(r7)     // Catch:{ XMPPException -> 0x0069 }
            java.lang.String r6 = r4.getEntityID()     // Catch:{ XMPPException -> 0x0069 }
            java.lang.String r4 = r4.getNode()     // Catch:{ XMPPException -> 0x0069 }
            org.jivesoftware.smackx.packet.DiscoverInfo r4 = r5.discoverInfo(r6, r4)     // Catch:{ XMPPException -> 0x0069 }
            java.lang.String r5 = "http://jabber.org/protocol/address"
            boolean r4 = r4.containsFeature(r5)     // Catch:{ XMPPException -> 0x0069 }
            if (r4 == 0) goto L_0x0038
        L_0x005c:
            r1 = r0
        L_0x005d:
            org.jivesoftware.smack.util.Cache r7 = services     // Catch:{ XMPPException -> 0x0069 }
            if (r1 != 0) goto L_0x0064
            java.lang.String r3 = ""
            goto L_0x0065
        L_0x0064:
            r3 = r1
        L_0x0065:
            r7.put(r0, r3)     // Catch:{ XMPPException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x006f }
        L_0x006d:
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            goto L_0x0072
        L_0x006f:
            r7 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            throw r7
        L_0x0072:
            java.lang.String r7 = ""
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x007b
            r1 = 0
        L_0x007b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.MultipleRecipientManager.getMultipleRecipienServiceAddress(org.jivesoftware.smack.Connection):java.lang.String");
    }

    private static class PacketCopy extends Packet {
        private String text;

        public PacketCopy(String str) {
            this.text = str;
        }

        public String toXML() {
            return this.text;
        }
    }
}
