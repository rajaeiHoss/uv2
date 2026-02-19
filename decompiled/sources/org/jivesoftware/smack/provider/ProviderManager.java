package org.jivesoftware.smack.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.packet.IQ;

public class ProviderManager {
    private static ProviderManager instance;
    private Map<String, Object> extensionProviders = new ConcurrentHashMap();
    private Map<String, Object> iqProviders = new ConcurrentHashMap();

    public static synchronized ProviderManager getInstance() {
        ProviderManager providerManager;
        synchronized (ProviderManager.class) {
            if (instance == null) {
                instance = new ProviderManager();
            }
            providerManager = instance;
        }
        return providerManager;
    }

    public static synchronized void setInstance(ProviderManager providerManager) {
        synchronized (ProviderManager.class) {
            if (instance == null) {
                instance = providerManager;
            } else {
                throw new IllegalStateException("ProviderManager singleton already set");
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:48:0x0108 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize() {
        /*
            r10 = this;
            java.lang.ClassLoader[] r0 = r10.getClassLoaders()     // Catch:{ Exception -> 0x010d }
            int r1 = r0.length     // Catch:{ Exception -> 0x010d }
            r2 = 0
        L_0x0006:
            if (r2 >= r1) goto L_0x0111
            r3 = r0[r2]     // Catch:{ Exception -> 0x010d }
            java.lang.String r4 = "META-INF/smack.providers"
            java.util.Enumeration r3 = r3.getResources(r4)     // Catch:{ Exception -> 0x010d }
        L_0x0010:
            boolean r4 = r3.hasMoreElements()     // Catch:{ Exception -> 0x010d }
            if (r4 == 0) goto L_0x0109
            java.lang.Object r4 = r3.nextElement()     // Catch:{ Exception -> 0x010d }
            java.net.URL r4 = (java.net.URL) r4     // Catch:{ Exception -> 0x010d }
            r5 = 0
            java.io.InputStream r5 = r4.openStream()     // Catch:{ all -> 0x0104 }
            org.xmlpull.v1.XmlPullParserFactory r4 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ all -> 0x0104 }
            org.xmlpull.v1.XmlPullParser r4 = r4.newPullParser()     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = "http://xmlpull.org/v1/doc/features.html#process-namespaces"
            r7 = 1
            r4.setFeature(r6, r7)     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = "UTF-8"
            r4.setInput(r5, r6)     // Catch:{ all -> 0x0104 }
            int r6 = r4.getEventType()     // Catch:{ all -> 0x0104 }
        L_0x0038:
            r8 = 2
            if (r6 != r8) goto L_0x00f9
            java.lang.String r6 = r4.getName()     // Catch:{ all -> 0x0104 }
            java.lang.String r8 = "iqProvider"
            boolean r6 = r6.equals(r8)     // Catch:{ all -> 0x0104 }
            if (r6 == 0) goto L_0x009b
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = r4.nextText()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r8 = r4.nextText()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r9 = r4.nextText()     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = r10.getProviderKey(r6, r8)     // Catch:{ all -> 0x0104 }
            java.util.Map<java.lang.String, java.lang.Object> r8 = r10.iqProviders     // Catch:{ all -> 0x0104 }
            boolean r8 = r8.containsKey(r6)     // Catch:{ all -> 0x0104 }
            if (r8 != 0) goto L_0x00f9
            java.lang.Class r8 = java.lang.Class.forName(r9)     // Catch:{ ClassNotFoundException -> 0x0096 }
            java.lang.Class<org.jivesoftware.smack.provider.IQProvider> r9 = org.jivesoftware.smack.provider.IQProvider.class
            boolean r9 = r9.isAssignableFrom(r8)     // Catch:{ ClassNotFoundException -> 0x0096 }
            if (r9 == 0) goto L_0x0088
            java.util.Map<java.lang.String, java.lang.Object> r9 = r10.iqProviders     // Catch:{ ClassNotFoundException -> 0x0096 }
            java.lang.Object r8 = r8.newInstance()     // Catch:{ ClassNotFoundException -> 0x0096 }
            r9.put(r6, r8)     // Catch:{ ClassNotFoundException -> 0x0096 }
            goto L_0x00f9
        L_0x0088:
            java.lang.Class<org.jivesoftware.smack.packet.IQ> r9 = org.jivesoftware.smack.packet.IQ.class
            boolean r9 = r9.isAssignableFrom(r8)     // Catch:{ ClassNotFoundException -> 0x0096 }
            if (r9 == 0) goto L_0x00f9
            java.util.Map<java.lang.String, java.lang.Object> r9 = r10.iqProviders     // Catch:{ ClassNotFoundException -> 0x0096 }
            r9.put(r6, r8)     // Catch:{ ClassNotFoundException -> 0x0096 }
            goto L_0x00f9
        L_0x0096:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0104 }
            goto L_0x00f9
        L_0x009b:
            java.lang.String r6 = r4.getName()     // Catch:{ all -> 0x0104 }
            java.lang.String r8 = "extensionProvider"
            boolean r6 = r6.equals(r8)     // Catch:{ all -> 0x0104 }
            if (r6 == 0) goto L_0x00f9
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = r4.nextText()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r8 = r4.nextText()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            r4.next()     // Catch:{ all -> 0x0104 }
            java.lang.String r9 = r4.nextText()     // Catch:{ all -> 0x0104 }
            java.lang.String r6 = r10.getProviderKey(r6, r8)     // Catch:{ all -> 0x0104 }
            java.util.Map<java.lang.String, java.lang.Object> r8 = r10.extensionProviders     // Catch:{ all -> 0x0104 }
            boolean r8 = r8.containsKey(r6)     // Catch:{ all -> 0x0104 }
            if (r8 != 0) goto L_0x00f9
            java.lang.Class r8 = java.lang.Class.forName(r9)     // Catch:{ ClassNotFoundException -> 0x00f5 }
            java.lang.Class<org.jivesoftware.smack.provider.PacketExtensionProvider> r9 = org.jivesoftware.smack.provider.PacketExtensionProvider.class
            boolean r9 = r9.isAssignableFrom(r8)     // Catch:{ ClassNotFoundException -> 0x00f5 }
            if (r9 == 0) goto L_0x00e7
            java.util.Map<java.lang.String, java.lang.Object> r9 = r10.extensionProviders     // Catch:{ ClassNotFoundException -> 0x00f5 }
            java.lang.Object r8 = r8.newInstance()     // Catch:{ ClassNotFoundException -> 0x00f5 }
            r9.put(r6, r8)     // Catch:{ ClassNotFoundException -> 0x00f5 }
            goto L_0x00f9
        L_0x00e7:
            java.lang.Class<org.jivesoftware.smack.packet.PacketExtension> r9 = org.jivesoftware.smack.packet.PacketExtension.class
            boolean r9 = r9.isAssignableFrom(r8)     // Catch:{ ClassNotFoundException -> 0x00f5 }
            if (r9 == 0) goto L_0x00f9
            java.util.Map<java.lang.String, java.lang.Object> r9 = r10.extensionProviders     // Catch:{ ClassNotFoundException -> 0x00f5 }
            r9.put(r6, r8)     // Catch:{ ClassNotFoundException -> 0x00f5 }
            goto L_0x00f9
        L_0x00f5:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0104 }
        L_0x00f9:
            int r6 = r4.next()     // Catch:{ all -> 0x0104 }
            if (r6 != r7) goto L_0x0038
            r5.close()     // Catch:{ Exception -> 0x0010 }
            goto L_0x0010
        L_0x0104:
            r0 = move-exception
            r5.close()     // Catch:{ Exception -> 0x0108 }
        L_0x0108:
            throw r0     // Catch:{ Exception -> 0x010d }
        L_0x0109:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x010d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0111:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.provider.ProviderManager.initialize():void");
    }

    public Object getIQProvider(String str, String str2) {
        return this.iqProviders.get(getProviderKey(str, str2));
    }

    public Collection<Object> getIQProviders() {
        return Collections.unmodifiableCollection(this.iqProviders.values());
    }

    public void addIQProvider(String str, String str2, Object obj) {
        if ((obj instanceof IQProvider) || ((obj instanceof Class) && IQ.class.isAssignableFrom((Class) obj))) {
            this.iqProviders.put(getProviderKey(str, str2), obj);
            return;
        }
        throw new IllegalArgumentException("Provider must be an IQProvider or a Class instance.");
    }

    public void removeIQProvider(String str, String str2) {
        this.iqProviders.remove(getProviderKey(str, str2));
    }

    public Object getExtensionProvider(String str, String str2) {
        return this.extensionProviders.get(getProviderKey(str, str2));
    }

    public void addExtensionProvider(String str, String str2, Object obj) {
        if ((obj instanceof PacketExtensionProvider) || (obj instanceof Class)) {
            this.extensionProviders.put(getProviderKey(str, str2), obj);
            return;
        }
        throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
    }

    public void removeExtensionProvider(String str, String str2) {
        this.extensionProviders.remove(getProviderKey(str, str2));
    }

    public Collection<Object> getExtensionProviders() {
        return Collections.unmodifiableCollection(this.extensionProviders.values());
    }

    private String getProviderKey(String str, String str2) {
        return "<" + str + "/><" + str2 + "/>";
    }

    private ClassLoader[] getClassLoaders() {
        ClassLoader[] classLoaderArr = {ProviderManager.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            ClassLoader classLoader = classLoaderArr[i];
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    private ProviderManager() {
        initialize();
    }
}
