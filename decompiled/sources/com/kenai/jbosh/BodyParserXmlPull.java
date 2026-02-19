package com.kenai.jbosh;

import java.lang.ref.SoftReference;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

final class BodyParserXmlPull implements BodyParser {
    private static final Logger LOG = Logger.getLogger(BodyParserXmlPull.class.getName());
    private static final ThreadLocal<SoftReference<XmlPullParser>> XPP_PARSER = new ThreadLocal<SoftReference<XmlPullParser>>() {
        /* access modifiers changed from: protected */
        public SoftReference<XmlPullParser> initialValue() {
            return new SoftReference<XmlPullParser>(null);
        }
    };

    BodyParserXmlPull() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        r3 = r1.getPrefix();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        if (r3 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r3 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r5 = r1.getNamespace();
        r6 = r1.getName();
        r7 = new com.kenai.jbosh.QName(r5, r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r2.isLoggable(java.util.logging.Level.FINEST) == false) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        r2.finest("Start element: ");
        r2.finest("    prefix: " + r3);
        r2.finest("    URI: " + r5);
        r2.finest("    local: " + r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009c, code lost:
        r2 = com.kenai.jbosh.AbstractBody.getBodyQName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a4, code lost:
        if (r2.equalsQName(r7) == false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a6, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ab, code lost:
        if (r2 >= r1.getAttributeCount()) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ad, code lost:
        r3 = r1.getAttributeNamespace(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b5, code lost:
        if (r3.length() != 0) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b7, code lost:
        r3 = r1.getNamespace((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bc, code lost:
        r5 = r1.getAttributePrefix(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c0, code lost:
        if (r5 != null) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c2, code lost:
        r5 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c3, code lost:
        r6 = r1.getAttributeName(r2);
        r7 = r1.getAttributeValue(r2);
        r5 = com.kenai.jbosh.BodyQName.createWithPrefix(r3, r6, r5);
        r8 = LOG;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d7, code lost:
        if (r8.isLoggable(java.util.logging.Level.FINEST) == false) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d9, code lost:
        r8.finest("        Attribute: {" + r3 + com.streamax.config.constant.Constants.JsonSstringSuffix + r6 + " = '" + r7 + "'");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0103, code lost:
        r0.addBodyAttributeValue(r5, r7);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0144, code lost:
        throw new java.lang.IllegalStateException("Root element was not '" + r2.getLocalPart() + "' in the '" + r2.getNamespaceURI() + "' namespace.  (Was '" + r6 + "' in '" + r5 + "')");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r2 = LOG;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        if (r2.isLoggable(java.util.logging.Level.FINEST) == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        r2.finest("Start tag: " + r1.getName());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.kenai.jbosh.BodyParserResults parse(java.lang.String r12) throws com.kenai.jbosh.BOSHException {
        /*
            r11 = this;
            com.kenai.jbosh.BodyParserResults r0 = new com.kenai.jbosh.BodyParserResults
            r0.<init>()
            org.xmlpull.v1.XmlPullParser r1 = getXmlPullParser()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.io.StringReader r2 = new java.io.StringReader     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r2.<init>(r12)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.setInput(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            int r2 = r1.getEventType()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x0015:
            r3 = 1
            if (r2 == r3) goto L_0x014b
            r3 = 2
            if (r2 != r3) goto L_0x0145
            java.util.logging.Logger r2 = LOG     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.util.logging.Level r3 = java.util.logging.Level.FINEST     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            boolean r3 = r2.isLoggable(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r3 == 0) goto L_0x003d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r4 = "Start tag: "
            r3.append(r4)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r4 = r1.getName()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.append(r4)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r3.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r2.finest(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x003d:
            java.lang.String r3 = r1.getPrefix()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r4 = ""
            if (r3 != 0) goto L_0x0046
            r3 = r4
        L_0x0046:
            java.lang.String r5 = r1.getNamespace()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r6 = r1.getName()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            com.kenai.jbosh.QName r7 = new com.kenai.jbosh.QName     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r7.<init>(r5, r6, r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.util.logging.Level r8 = java.util.logging.Level.FINEST     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            boolean r8 = r2.isLoggable(r8)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r8 == 0) goto L_0x009c
            java.lang.String r8 = "Start element: "
            r2.finest(r8)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r8.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r9 = "    prefix: "
            r8.append(r9)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r8.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r8.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r2.finest(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r8 = "    URI: "
            r3.append(r8)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.append(r5)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r3.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r2.finest(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r8 = "    local: "
            r3.append(r8)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r3.append(r6)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r3.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r2.finest(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x009c:
            com.kenai.jbosh.BodyQName r2 = com.kenai.jbosh.AbstractBody.getBodyQName()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            boolean r3 = r2.equalsQName(r7)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r3 == 0) goto L_0x0109
            r2 = 0
        L_0x00a7:
            int r3 = r1.getAttributeCount()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r2 >= r3) goto L_0x014b
            java.lang.String r3 = r1.getAttributeNamespace(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            int r5 = r3.length()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r5 != 0) goto L_0x00bc
            r3 = 0
            java.lang.String r3 = r1.getNamespace(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x00bc:
            java.lang.String r5 = r1.getAttributePrefix(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r5 != 0) goto L_0x00c3
            r5 = r4
        L_0x00c3:
            java.lang.String r6 = r1.getAttributeName(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r7 = r1.getAttributeValue(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            com.kenai.jbosh.BodyQName r5 = com.kenai.jbosh.BodyQName.createWithPrefix(r3, r6, r5)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.util.logging.Logger r8 = LOG     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.util.logging.Level r9 = java.util.logging.Level.FINEST     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            boolean r9 = r8.isLoggable(r9)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            if (r9 == 0) goto L_0x0103
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r9.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r10 = "        Attribute: {"
            r9.append(r10)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r9.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = "}"
            r9.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r9.append(r6)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = " = '"
            r9.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r9.append(r7)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = "'"
            r9.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r9.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r8.finest(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x0103:
            r0.addBodyAttributeValue(r5, r7)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            int r2 = r2 + 1
            goto L_0x00a7
        L_0x0109:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.<init>()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = "Root element was not '"
            r1.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = r2.getLocalPart()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r3 = "' in the '"
            r1.append(r3)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r2 = r2.getNamespaceURI()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.append(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r2 = "' namespace.  (Was '"
            r1.append(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.append(r6)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r2 = "' in '"
            r1.append(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r1.append(r5)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r2 = "')"
            r1.append(r2)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            java.lang.String r1 = r1.toString()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            r0.<init>(r1)     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            throw r0     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
        L_0x0145:
            int r2 = r1.next()     // Catch:{ RuntimeException -> 0x0150, XmlPullParserException -> 0x014e, IOException -> 0x014c }
            goto L_0x0015
        L_0x014b:
            return r0
        L_0x014c:
            r0 = move-exception
            goto L_0x0151
        L_0x014e:
            r0 = move-exception
            goto L_0x0151
        L_0x0150:
            r0 = move-exception
        L_0x0151:
            com.kenai.jbosh.BOSHException r1 = new com.kenai.jbosh.BOSHException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Could not parse body:\n"
            r2.append(r3)
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            r1.<init>(r12, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jbosh.BodyParserXmlPull.parse(java.lang.String):com.kenai.jbosh.BodyParserResults");
    }

    private static XmlPullParser getXmlPullParser() {
        ThreadLocal<SoftReference<XmlPullParser>> threadLocal = XPP_PARSER;
        XmlPullParser xmlPullParser = (XmlPullParser) threadLocal.get().get();
        if (xmlPullParser != null) {
            return xmlPullParser;
        }
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            newInstance.setValidating(false);
            XmlPullParser newPullParser = newInstance.newPullParser();
            threadLocal.set(new SoftReference<>(newPullParser));
            return newPullParser;
        } catch (Exception e) {
            throw new IllegalStateException("Could not create XmlPull parser", e);
        }
    }
}
