package org.jivesoftware.smackx.pubsub.provider;

import org.jivesoftware.smack.provider.PacketExtensionProvider;

public class SubscriptionProvider implements PacketExtensionProvider {
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0041 A[LOOP:0: B:9:0x0041->B:12:0x004b, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.packet.PacketExtension parseExtension(org.xmlpull.v1.XmlPullParser r11) throws java.lang.Exception {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r1 = "jid"
            java.lang.String r3 = r11.getAttributeValue(r0, r1)
            java.lang.String r1 = "node"
            java.lang.String r4 = r11.getAttributeValue(r0, r1)
            java.lang.String r1 = "subid"
            java.lang.String r5 = r11.getAttributeValue(r0, r1)
            java.lang.String r1 = "subscription"
            java.lang.String r1 = r11.getAttributeValue(r0, r1)
            int r2 = r11.next()
            r6 = 3
            r7 = 2
            r8 = 0
            if (r2 != r7) goto L_0x0050
            java.lang.String r2 = r11.getName()
            java.lang.String r9 = "subscribe-options"
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L_0x0050
            int r2 = r11.next()
            if (r2 != r7) goto L_0x0041
            java.lang.String r2 = r11.getName()
            java.lang.String r7 = "required"
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x0041
            r8 = 1
        L_0x0041:
            int r2 = r11.next()
            if (r2 == r6) goto L_0x004e
            java.lang.String r2 = r11.getName()
            if (r2 == r9) goto L_0x004e
            goto L_0x0041
        L_0x004e:
            r7 = r8
            goto L_0x0051
        L_0x0050:
            r7 = 0
        L_0x0051:
            int r2 = r11.getEventType()
            if (r2 == r6) goto L_0x005b
            r11.next()
            goto L_0x0051
        L_0x005b:
            org.jivesoftware.smackx.pubsub.Subscription r11 = new org.jivesoftware.smackx.pubsub.Subscription
            if (r1 != 0) goto L_0x0060
            goto L_0x0064
        L_0x0060:
            org.jivesoftware.smackx.pubsub.Subscription$State r0 = org.jivesoftware.smackx.pubsub.Subscription.State.valueOf(r1)
        L_0x0064:
            r6 = r0
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.pubsub.provider.SubscriptionProvider.parseExtension(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.packet.PacketExtension");
    }
}
