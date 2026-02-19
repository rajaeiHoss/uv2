package org.jivesoftware.smack;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.qpid.management.common.sasl.Constants;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smack.sasl.SASLAnonymous;
import org.jivesoftware.smack.sasl.SASLCramMD5Mechanism;
import org.jivesoftware.smack.sasl.SASLDigestMD5Mechanism;
import org.jivesoftware.smack.sasl.SASLExternalMechanism;
import org.jivesoftware.smack.sasl.SASLGSSAPIMechanism;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.sasl.SASLPlainMechanism;

public class SASLAuthentication implements UserAuthentication {
    private static Map<String, Class> implementedMechanisms = new HashMap();
    private static List<String> mechanismsPreferences = new ArrayList();
    private Connection connection;
    private SASLMechanism currentMechanism = null;
    private String errorCondition;
    private boolean resourceBinded;
    private boolean saslFailed;
    private boolean saslNegotiated;
    private Collection<String> serverMechanisms = new ArrayList();
    private boolean sessionSupported;

    static {
        registerSASLMechanism("EXTERNAL", SASLExternalMechanism.class);
        registerSASLMechanism("GSSAPI", SASLGSSAPIMechanism.class);
        registerSASLMechanism("DIGEST-MD5", SASLDigestMD5Mechanism.class);
        registerSASLMechanism(Constants.MECH_CRAMMD5, SASLCramMD5Mechanism.class);
        registerSASLMechanism(Constants.MECH_PLAIN, SASLPlainMechanism.class);
        registerSASLMechanism("ANONYMOUS", SASLAnonymous.class);
        supportSASLMechanism("DIGEST-MD5", 0);
        supportSASLMechanism(Constants.MECH_PLAIN, 1);
        supportSASLMechanism("ANONYMOUS", 2);
    }

    public static void registerSASLMechanism(String str, Class cls) {
        implementedMechanisms.put(str, cls);
    }

    public static void unregisterSASLMechanism(String str) {
        implementedMechanisms.remove(str);
        mechanismsPreferences.remove(str);
    }

    public static void supportSASLMechanism(String str) {
        mechanismsPreferences.add(0, str);
    }

    public static void supportSASLMechanism(String str, int i) {
        mechanismsPreferences.add(i, str);
    }

    public static void unsupportSASLMechanism(String str) {
        mechanismsPreferences.remove(str);
    }

    public static List<Class> getRegisterSASLMechanisms() {
        ArrayList arrayList = new ArrayList();
        for (String str : mechanismsPreferences) {
            arrayList.add(implementedMechanisms.get(str));
        }
        return arrayList;
    }

    SASLAuthentication(Connection connection2) {
        this.connection = connection2;
        init();
    }

    public boolean hasAnonymousAuthentication() {
        return this.serverMechanisms.contains("ANONYMOUS");
    }

    public boolean hasNonAnonymousAuthentication() {
        return !this.serverMechanisms.isEmpty() && (this.serverMechanisms.size() != 1 || !hasAnonymousAuthentication());
    }

    public String authenticate(String str, String str2, CallbackHandler callbackHandler) throws XMPPException {
        String str3;
        Iterator<String> it = mechanismsPreferences.iterator();
        while (true) {
            if (!it.hasNext()) {
                str3 = null;
                break;
            }
            str3 = it.next();
            if (implementedMechanisms.containsKey(str3) && this.serverMechanisms.contains(str3)) {
                break;
            }
        }
        if (str3 != null) {
            try {
                SASLMechanism sASLMechanism = (SASLMechanism) implementedMechanisms.get(str3).getConstructor(new Class[]{SASLAuthentication.class}).newInstance(new Object[]{this});
                this.currentMechanism = sASLMechanism;
                sASLMechanism.authenticate(str, this.connection.getHost(), callbackHandler);
                synchronized (this) {
                    long currentTimeMillis = System.currentTimeMillis();
                    while (!this.saslNegotiated && !this.saslFailed && System.currentTimeMillis() < currentTimeMillis) {
                        try {
                            wait(Math.abs(System.currentTimeMillis() - currentTimeMillis));
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                if (!this.saslFailed) {
                    if (this.saslNegotiated) {
                        return bindResourceAndEstablishSession(str2);
                    }
                    throw new XMPPException("SASL authentication failed");
                } else if (this.errorCondition != null) {
                    throw new XMPPException("SASL authentication " + str3 + " failed: " + this.errorCondition);
                } else {
                    throw new XMPPException("SASL authentication failed using mechanism " + str3);
                }
            } catch (XMPPException e) {
                throw e;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new XMPPException(e2);
            }
        } else {
            throw new XMPPException("SASL Authentication failed. No known authentication mechanisims.");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|(3:17|18|19)|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x005d */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A[Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e A[Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String authenticate(java.lang.String r7, java.lang.String r8, java.lang.String r9) throws org.jivesoftware.smack.XMPPException {
        /*
            r6 = this;
            java.util.List<java.lang.String> r0 = mechanismsPreferences
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0023
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Map<java.lang.String, java.lang.Class> r2 = implementedMechanisms
            boolean r2 = r2.containsKey(r1)
            if (r2 == 0) goto L_0x0006
            java.util.Collection<java.lang.String> r2 = r6.serverMechanisms
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L_0x0006
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            if (r1 == 0) goto L_0x00c8
            java.util.Map<java.lang.String, java.lang.Class> r0 = implementedMechanisms     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.Class r0 = (java.lang.Class) r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.Class<org.jivesoftware.smack.SASLAuthentication> r4 = org.jivesoftware.smack.SASLAuthentication.class
            r5 = 0
            r3[r5] = r4     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r3)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2[r5] = r6     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.Object r0 = r0.newInstance(r2)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            org.jivesoftware.smack.sasl.SASLMechanism r0 = (org.jivesoftware.smack.sasl.SASLMechanism) r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r6.currentMechanism = r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            org.jivesoftware.smack.Connection r2 = r6.connection     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r2 = r2.getServiceName()     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r0.authenticate((java.lang.String) r7, (java.lang.String) r2, (java.lang.String) r8)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            monitor-enter(r6)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            boolean r0 = r6.saslNegotiated     // Catch:{ all -> 0x00b3 }
            if (r0 != 0) goto L_0x005d
            boolean r0 = r6.saslFailed     // Catch:{ all -> 0x00b3 }
            if (r0 != 0) goto L_0x005d
            r2 = 30000(0x7530, double:1.4822E-319)
            r6.wait(r2)     // Catch:{ InterruptedException -> 0x005d }
        L_0x005d:
            monitor-exit(r6)     // Catch:{ all -> 0x00b3 }
            boolean r0 = r6.saslFailed     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            if (r0 == 0) goto L_0x009e
            java.lang.String r0 = r6.errorCondition     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            if (r0 == 0) goto L_0x0087
            org.jivesoftware.smack.XMPPException r0 = new org.jivesoftware.smack.XMPPException     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2.<init>()     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r3 = "SASL authentication "
            r2.append(r3)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2.append(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r1 = " failed: "
            r2.append(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r1 = r6.errorCondition     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2.append(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r1 = r2.toString()     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r0.<init>((java.lang.String) r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            throw r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
        L_0x0087:
            org.jivesoftware.smack.XMPPException r0 = new org.jivesoftware.smack.XMPPException     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2.<init>()     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r3 = "SASL authentication failed using mechanism "
            r2.append(r3)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r2.append(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r1 = r2.toString()     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r0.<init>((java.lang.String) r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            throw r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
        L_0x009e:
            boolean r0 = r6.saslNegotiated     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            if (r0 == 0) goto L_0x00a7
            java.lang.String r7 = r6.bindResourceAndEstablishSession(r9)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            return r7
        L_0x00a7:
            org.jivesoftware.smack.NonSASLAuthentication r0 = new org.jivesoftware.smack.NonSASLAuthentication     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            org.jivesoftware.smack.Connection r1 = r6.connection     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            r0.<init>(r1)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            java.lang.String r7 = r0.authenticate((java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9)     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
            return r7
        L_0x00b3:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00b3 }
            throw r0     // Catch:{ XMPPException -> 0x00c6, Exception -> 0x00b6 }
        L_0x00b6:
            r0 = move-exception
            r0.printStackTrace()
            org.jivesoftware.smack.NonSASLAuthentication r0 = new org.jivesoftware.smack.NonSASLAuthentication
            org.jivesoftware.smack.Connection r1 = r6.connection
            r0.<init>(r1)
            java.lang.String r7 = r0.authenticate((java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9)
            return r7
        L_0x00c6:
            r7 = move-exception
            throw r7
        L_0x00c8:
            org.jivesoftware.smack.NonSASLAuthentication r0 = new org.jivesoftware.smack.NonSASLAuthentication
            org.jivesoftware.smack.Connection r1 = r6.connection
            r0.<init>(r1)
            java.lang.String r7 = r0.authenticate((java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.SASLAuthentication.authenticate(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(3:8|9|10)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001b */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0020 A[Catch:{ IOException -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045 A[Catch:{ IOException -> 0x005d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String authenticateAnonymously() throws org.jivesoftware.smack.XMPPException {
        /*
            r3 = this;
            org.jivesoftware.smack.sasl.SASLAnonymous r0 = new org.jivesoftware.smack.sasl.SASLAnonymous     // Catch:{ IOException -> 0x005d }
            r0.<init>(r3)     // Catch:{ IOException -> 0x005d }
            r3.currentMechanism = r0     // Catch:{ IOException -> 0x005d }
            java.lang.String r1 = ""
            r2 = 0
            r0.authenticate((java.lang.String) r2, (java.lang.String) r2, (java.lang.String) r1)     // Catch:{ IOException -> 0x005d }
            monitor-enter(r3)     // Catch:{ IOException -> 0x005d }
            boolean r0 = r3.saslNegotiated     // Catch:{ all -> 0x005a }
            if (r0 != 0) goto L_0x001b
            boolean r0 = r3.saslFailed     // Catch:{ all -> 0x005a }
            if (r0 != 0) goto L_0x001b
            r0 = 5000(0x1388, double:2.4703E-320)
            r3.wait(r0)     // Catch:{ InterruptedException -> 0x001b }
        L_0x001b:
            monitor-exit(r3)     // Catch:{ all -> 0x005a }
            boolean r0 = r3.saslFailed     // Catch:{ IOException -> 0x005d }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r3.errorCondition     // Catch:{ IOException -> 0x005d }
            if (r0 == 0) goto L_0x003d
            org.jivesoftware.smack.XMPPException r0 = new org.jivesoftware.smack.XMPPException     // Catch:{ IOException -> 0x005d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x005d }
            r1.<init>()     // Catch:{ IOException -> 0x005d }
            java.lang.String r2 = "SASL authentication failed: "
            r1.append(r2)     // Catch:{ IOException -> 0x005d }
            java.lang.String r2 = r3.errorCondition     // Catch:{ IOException -> 0x005d }
            r1.append(r2)     // Catch:{ IOException -> 0x005d }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x005d }
            r0.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x005d }
            throw r0     // Catch:{ IOException -> 0x005d }
        L_0x003d:
            org.jivesoftware.smack.XMPPException r0 = new org.jivesoftware.smack.XMPPException     // Catch:{ IOException -> 0x005d }
            java.lang.String r1 = "SASL authentication failed"
            r0.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x005d }
            throw r0     // Catch:{ IOException -> 0x005d }
        L_0x0045:
            boolean r0 = r3.saslNegotiated     // Catch:{ IOException -> 0x005d }
            if (r0 == 0) goto L_0x004e
            java.lang.String r0 = r3.bindResourceAndEstablishSession(r2)     // Catch:{ IOException -> 0x005d }
            return r0
        L_0x004e:
            org.jivesoftware.smack.NonSASLAuthentication r0 = new org.jivesoftware.smack.NonSASLAuthentication     // Catch:{ IOException -> 0x005d }
            org.jivesoftware.smack.Connection r1 = r3.connection     // Catch:{ IOException -> 0x005d }
            r0.<init>(r1)     // Catch:{ IOException -> 0x005d }
            java.lang.String r0 = r0.authenticateAnonymously()     // Catch:{ IOException -> 0x005d }
            return r0
        L_0x005a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x005a }
            throw r0     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            org.jivesoftware.smack.NonSASLAuthentication r0 = new org.jivesoftware.smack.NonSASLAuthentication
            org.jivesoftware.smack.Connection r1 = r3.connection
            r0.<init>(r1)
            java.lang.String r0 = r0.authenticateAnonymously()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.SASLAuthentication.authenticateAnonymously():java.lang.String");
    }

    private String bindResourceAndEstablishSession(String str) throws XMPPException {
        synchronized (this) {
            long currentTimeMillis = System.currentTimeMillis() + NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
            while (!this.resourceBinded && System.currentTimeMillis() < currentTimeMillis) {
                try {
                    wait(Math.abs(System.currentTimeMillis() - currentTimeMillis));
                } catch (InterruptedException unused) {
                }
            }
        }
        if (this.resourceBinded) {
            Bind bind = new Bind();
            bind.setResource(str);
            PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(bind.getPacketID()));
            this.connection.sendPacket(bind);
            Bind bind2 = (Bind) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
            createPacketCollector.cancel();
            if (bind2 == null) {
                throw new XMPPException("No response from the server.");
            } else if (bind2.getType() != IQ.Type.ERROR) {
                String jid = bind2.getJid();
                if (this.sessionSupported) {
                    Session session = new Session();
                    PacketCollector createPacketCollector2 = this.connection.createPacketCollector(new PacketIDFilter(session.getPacketID()));
                    this.connection.sendPacket(session);
                    IQ iq = (IQ) createPacketCollector2.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
                    createPacketCollector2.cancel();
                    if (iq == null) {
                        throw new XMPPException("No response from the server.");
                    } else if (iq.getType() != IQ.Type.ERROR) {
                        return jid;
                    } else {
                        throw new XMPPException(iq.getError());
                    }
                } else {
                    throw new XMPPException("Session establishment not offered by server");
                }
            } else {
                throw new XMPPException(bind2.getError());
            }
        } else {
            throw new XMPPException("Resource binding not offered by server");
        }
    }

    /* access modifiers changed from: package-private */
    public void setAvailableSASLMethods(Collection<String> collection) {
        this.serverMechanisms = collection;
    }

    public boolean isAuthenticated() {
        return this.saslNegotiated;
    }

    /* access modifiers changed from: package-private */
    public void challengeReceived(String str) throws IOException {
        this.currentMechanism.challengeReceived(str);
    }

    /* access modifiers changed from: package-private */
    public void authenticated() {
        synchronized (this) {
            this.saslNegotiated = true;
            notify();
        }
    }

    /* access modifiers changed from: package-private */
    public void authenticationFailed() {
        authenticationFailed((String) null);
    }

    /* access modifiers changed from: package-private */
    public void authenticationFailed(String str) {
        synchronized (this) {
            this.saslFailed = true;
            this.errorCondition = str;
            notify();
        }
    }

    /* access modifiers changed from: package-private */
    public void bindingRequired() {
        synchronized (this) {
            this.resourceBinded = true;
            notify();
        }
    }

    public void send(Packet packet) {
        this.connection.sendPacket(packet);
    }

    /* access modifiers changed from: package-private */
    public void sessionsSupported() {
        this.sessionSupported = true;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.saslNegotiated = false;
        this.saslFailed = false;
        this.resourceBinded = false;
        this.sessionSupported = false;
    }
}
