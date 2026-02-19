package org.jivesoftware.smackx.provider;

import org.jivesoftware.smack.provider.IQProvider;

public class StreamInitiationProvider implements IQProvider {
    /* JADX WARNING: type inference failed for: r0v24, types: [org.jivesoftware.smack.packet.PacketExtension] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.packet.IQ parseIQ(org.xmlpull.v1.XmlPullParser r19) throws java.lang.Exception {
        /*
            r18 = this;
            r1 = r19
            java.lang.String r0 = ""
            java.lang.String r2 = "id"
            java.lang.String r2 = r1.getAttributeValue(r0, r2)
            java.lang.String r0 = ""
            java.lang.String r3 = "mime-type"
            java.lang.String r3 = r1.getAttributeValue(r0, r3)
            org.jivesoftware.smackx.packet.StreamInitiation r4 = new org.jivesoftware.smackx.packet.StreamInitiation
            r4.<init>()
            org.jivesoftware.smackx.provider.DataFormProvider r5 = new org.jivesoftware.smackx.provider.DataFormProvider
            r5.<init>()
            r0 = 0
            r7 = 0
            r9 = r7
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r7 = 0
            r8 = 0
        L_0x0026:
            if (r7 != 0) goto L_0x00ef
            int r0 = r19.next()
            java.lang.String r15 = r19.getName()
            java.lang.String r6 = r19.getNamespace()
            r16 = r7
            r7 = 2
            if (r0 != r7) goto L_0x0095
            java.lang.String r0 = "file"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x0063
            java.lang.String r0 = ""
            java.lang.String r6 = "name"
            java.lang.String r12 = r1.getAttributeValue(r0, r6)
            java.lang.String r0 = ""
            java.lang.String r6 = "size"
            java.lang.String r10 = r1.getAttributeValue(r0, r6)
            java.lang.String r0 = ""
            java.lang.String r6 = "hash"
            java.lang.String r13 = r1.getAttributeValue(r0, r6)
            java.lang.String r0 = ""
            java.lang.String r6 = "date"
            java.lang.String r11 = r1.getAttributeValue(r0, r6)
            goto L_0x00eb
        L_0x0063:
            java.lang.String r0 = "desc"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x0071
            java.lang.String r14 = r19.nextText()
            goto L_0x00eb
        L_0x0071:
            java.lang.String r0 = "range"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x007d
            r7 = r16
            r8 = 1
            goto L_0x0026
        L_0x007d:
            java.lang.String r0 = "x"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00eb
            java.lang.String r0 = "jabber:x:data"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00eb
            org.jivesoftware.smack.packet.PacketExtension r0 = r5.parseExtension(r1)
            r9 = r0
            org.jivesoftware.smackx.packet.DataForm r9 = (org.jivesoftware.smackx.packet.DataForm) r9
            goto L_0x00eb
        L_0x0095:
            r6 = 3
            if (r0 != r6) goto L_0x00eb
            java.lang.String r0 = "si"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00a2
            r7 = 1
            goto L_0x0026
        L_0x00a2:
            java.lang.String r0 = "file"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00eb
            r6 = 0
            if (r10 == 0) goto L_0x00c2
            java.lang.String r0 = r10.trim()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x00c2
            long r6 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x00bd }
            goto L_0x00c2
        L_0x00bd:
            r0 = move-exception
            r15 = r0
            r15.printStackTrace()
        L_0x00c2:
            java.util.Date r15 = new java.util.Date
            r15.<init>()
            if (r11 == 0) goto L_0x00d7
            java.text.DateFormat r17 = org.jivesoftware.smack.packet.Packet.XEP_0082_UTC_FORMAT     // Catch:{ ParseException -> 0x00d7 }
            monitor-enter(r17)     // Catch:{ ParseException -> 0x00d7 }
            java.text.DateFormat r0 = org.jivesoftware.smack.packet.Packet.XEP_0082_UTC_FORMAT     // Catch:{ all -> 0x00d4 }
            java.util.Date r15 = r0.parse(r11)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r17)     // Catch:{ all -> 0x00d4 }
            goto L_0x00d7
        L_0x00d4:
            r0 = move-exception
            monitor-exit(r17)     // Catch:{ all -> 0x00d4 }
            throw r0     // Catch:{ ParseException -> 0x00d7 }
        L_0x00d7:
            org.jivesoftware.smackx.packet.StreamInitiation$File r0 = new org.jivesoftware.smackx.packet.StreamInitiation$File
            r0.<init>(r12, r6)
            r0.setHash(r13)
            r0.setDate(r15)
            r0.setDesc(r14)
            r0.setRanged(r8)
            r4.setFile(r0)
        L_0x00eb:
            r7 = r16
            goto L_0x0026
        L_0x00ef:
            r4.setSesssionID(r2)
            r4.setMimeType(r3)
            r4.setFeatureNegotiationForm(r9)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.provider.StreamInitiationProvider.parseIQ(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.packet.IQ");
    }
}
