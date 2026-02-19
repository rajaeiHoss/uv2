package org.jivesoftware.smack.packet;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.util.StringUtils;

public abstract class Packet {
    protected static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
    private static String DEFAULT_XML_NS = null;
    public static final String ID_NOT_AVAILABLE = "ID_NOT_AVAILABLE";
    public static final DateFormat XEP_0082_UTC_FORMAT;
    private static long id = 0;
    private static String prefix = (StringUtils.randomString(5) + "-");
    private XMPPError error = null;
    private String from = null;
    private final List<PacketExtension> packetExtensions = new CopyOnWriteArrayList();
    private String packetID = null;
    private final Map<String, Object> properties = new HashMap();
    private String to = null;
    private String xmlns = DEFAULT_XML_NS;

    public abstract String toXML();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        XEP_0082_UTC_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static synchronized String nextID() {
        String sb;
        synchronized (Packet.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(prefix);
            long j = id;
            id = 1 + j;
            sb2.append(Long.toString(j));
            sb = sb2.toString();
        }
        return sb;
    }

    public static void setDefaultXmlns(String str) {
        DEFAULT_XML_NS = str;
    }

    public String getPacketID() {
        if (ID_NOT_AVAILABLE.equals(this.packetID)) {
            return null;
        }
        if (this.packetID == null) {
            this.packetID = nextID();
        }
        return this.packetID;
    }

    public void setPacketID(String str) {
        this.packetID = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public XMPPError getError() {
        return this.error;
    }

    public void setError(XMPPError xMPPError) {
        this.error = xMPPError;
    }

    public synchronized Collection<PacketExtension> getExtensions() {
        if (this.packetExtensions == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(this.packetExtensions));
    }

    public PacketExtension getExtension(String str) {
        return getExtension((String) null, str);
    }

    public PacketExtension getExtension(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        for (PacketExtension next : this.packetExtensions) {
            if ((str == null || str.equals(next.getElementName())) && str2.equals(next.getNamespace())) {
                return next;
            }
        }
        return null;
    }

    public void addExtension(PacketExtension packetExtension) {
        this.packetExtensions.add(packetExtension);
    }

    public void removeExtension(PacketExtension packetExtension) {
        this.packetExtensions.remove(packetExtension);
    }

    public synchronized Object getProperty(String str) {
        Map<String, Object> map = this.properties;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public synchronized void setProperty(String str, Object obj) {
        if (obj instanceof Serializable) {
            this.properties.put(str, obj);
        } else {
            throw new IllegalArgumentException("Value must be serialiazble");
        }
    }

    public synchronized void deleteProperty(String str) {
        Map<String, Object> map = this.properties;
        if (map != null) {
            map.remove(str);
        }
    }

    public synchronized Collection<String> getPropertyNames() {
        if (this.properties == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(this.properties.keySet()));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:62|63|(2:65|66)|(2:69|70)|71|72) */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011c, code lost:
        if (r4 == null) goto L_0x011f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0100 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x0134 */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0117 A[SYNTHETIC, Splitter:B:54:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012a A[SYNTHETIC, Splitter:B:65:0x012a] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0131 A[SYNTHETIC, Splitter:B:69:0x0131] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getExtensionsXML() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0140 }
            r0.<init>()     // Catch:{ all -> 0x0140 }
            java.util.Collection r1 = r6.getExtensions()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0140 }
        L_0x000e:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x0022
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0140 }
            org.jivesoftware.smack.packet.PacketExtension r2 = (org.jivesoftware.smack.packet.PacketExtension) r2     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = r2.toXML()     // Catch:{ all -> 0x0140 }
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x000e
        L_0x0022:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r6.properties     // Catch:{ all -> 0x0140 }
            if (r1 == 0) goto L_0x013a
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0140 }
            if (r1 != 0) goto L_0x013a
            java.lang.String r1 = "<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">"
            r0.append(r1)     // Catch:{ all -> 0x0140 }
            java.util.Collection r1 = r6.getPropertyNames()     // Catch:{ all -> 0x0140 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0140 }
        L_0x0039:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x0135
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0140 }
            java.lang.Object r3 = r6.getProperty(r2)     // Catch:{ all -> 0x0140 }
            java.lang.String r4 = "<property>"
            r0.append(r4)     // Catch:{ all -> 0x0140 }
            java.lang.String r4 = "<name>"
            r0.append(r4)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = org.jivesoftware.smack.util.StringUtils.escapeForXML(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</name>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "<value type=\""
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            boolean r2 = r3 instanceof java.lang.Integer     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x0077
            java.lang.String r2 = "integer\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r3)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x0077:
            boolean r2 = r3 instanceof java.lang.Long     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x008a
            java.lang.String r2 = "long\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r3)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x008a:
            boolean r2 = r3 instanceof java.lang.Float     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x009d
            java.lang.String r2 = "float\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r3)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x009d:
            boolean r2 = r3 instanceof java.lang.Double     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x00b0
            java.lang.String r2 = "double\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r3)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x00b0:
            boolean r2 = r3 instanceof java.lang.Boolean     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x00c2
            java.lang.String r2 = "boolean\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            r0.append(r3)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x00c2:
            boolean r2 = r3 instanceof java.lang.String     // Catch:{ all -> 0x0140 }
            if (r2 == 0) goto L_0x00da
            java.lang.String r2 = "string\">"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = org.jivesoftware.smack.util.StringUtils.escapeForXML(r3)     // Catch:{ all -> 0x0140 }
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x011f
        L_0x00da:
            r2 = 0
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x010e, all -> 0x010b }
            r4.<init>()     // Catch:{ Exception -> 0x010e, all -> 0x010b }
            java.io.ObjectOutputStream r5 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0108, all -> 0x0106 }
            r5.writeObject(r3)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r2 = "java-object\">"
            r0.append(r2)     // Catch:{ Exception -> 0x0104 }
            byte[] r2 = r4.toByteArray()     // Catch:{ Exception -> 0x0104 }
            java.lang.String r2 = org.jivesoftware.smack.util.StringUtils.encodeBase64((byte[]) r2)     // Catch:{ Exception -> 0x0104 }
            r0.append(r2)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r2 = "</value>"
            r0.append(r2)     // Catch:{ Exception -> 0x0104 }
            r5.close()     // Catch:{ Exception -> 0x0100 }
        L_0x0100:
            r4.close()     // Catch:{ Exception -> 0x011f }
            goto L_0x011f
        L_0x0104:
            r2 = move-exception
            goto L_0x0112
        L_0x0106:
            r0 = move-exception
            goto L_0x0128
        L_0x0108:
            r3 = move-exception
            r5 = r2
            goto L_0x0111
        L_0x010b:
            r0 = move-exception
            r4 = r2
            goto L_0x0128
        L_0x010e:
            r3 = move-exception
            r4 = r2
            r5 = r4
        L_0x0111:
            r2 = r3
        L_0x0112:
            r2.printStackTrace()     // Catch:{ all -> 0x0126 }
            if (r5 == 0) goto L_0x011c
            r5.close()     // Catch:{ Exception -> 0x011b }
            goto L_0x011c
        L_0x011b:
        L_0x011c:
            if (r4 == 0) goto L_0x011f
            goto L_0x0100
        L_0x011f:
            java.lang.String r2 = "</property>"
            r0.append(r2)     // Catch:{ all -> 0x0140 }
            goto L_0x0039
        L_0x0126:
            r0 = move-exception
            r2 = r5
        L_0x0128:
            if (r2 == 0) goto L_0x012f
            r2.close()     // Catch:{ Exception -> 0x012e }
            goto L_0x012f
        L_0x012e:
        L_0x012f:
            if (r4 == 0) goto L_0x0134
            r4.close()     // Catch:{ Exception -> 0x0134 }
        L_0x0134:
            throw r0     // Catch:{ all -> 0x0140 }
        L_0x0135:
            java.lang.String r1 = "</properties>"
            r0.append(r1)     // Catch:{ all -> 0x0140 }
        L_0x013a:
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0140 }
            monitor-exit(r6)
            return r0
        L_0x0140:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.packet.Packet.getExtensionsXML():java.lang.String");
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Packet packet = (Packet) obj;
        XMPPError xMPPError = this.error;
        if (xMPPError == null ? packet.error != null : !xMPPError.equals(packet.error)) {
            return false;
        }
        String str = this.from;
        if (str == null ? packet.from != null : !str.equals(packet.from)) {
            return false;
        }
        if (!this.packetExtensions.equals(packet.packetExtensions)) {
            return false;
        }
        String str2 = this.packetID;
        if (str2 == null ? packet.packetID != null : !str2.equals(packet.packetID)) {
            return false;
        }
        Map<String, Object> map = this.properties;
        if (map == null ? packet.properties != null : !map.equals(packet.properties)) {
            return false;
        }
        String str3 = this.to;
        if (str3 == null ? packet.to != null : !str3.equals(packet.to)) {
            return false;
        }
        String str4 = this.xmlns;
        String str5 = packet.xmlns;
        if (str4 != null) {
            if (!str4.equals(str5)) {
                return false;
            }
            return true;
        } else if (str5 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.xmlns;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.packetID;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.to;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.from;
        int hashCode4 = (((((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.packetExtensions.hashCode()) * 31) + this.properties.hashCode()) * 31;
        XMPPError xMPPError = this.error;
        if (xMPPError != null) {
            i = xMPPError.hashCode();
        }
        return hashCode4 + i;
    }
}
