package org.jivesoftware.smack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.packet.CapsExtension;
import org.jivesoftware.smackx.workgroup.packet.SessionID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class PacketReader {
    /* access modifiers changed from: private */
    public XMPPConnection connection;
    private String connectionID = null;
    private Semaphore connectionSemaphore;
    private boolean done;
    private ExecutorService listenerExecutor;
    private XmlPullParser parser;
    private Thread readerThread;

    protected PacketReader(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.done = false;
        this.connectionID = null;
        Thread reader = new Thread() {
            public void run() {
                PacketReader.this.parsePackets(this);
            }
        };
        this.readerThread = reader;
        reader.setName("Smack Packet Reader (" + this.connection.connectionCounterValue + ")");
        this.readerThread.setDaemon(true);
        this.listenerExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "Smack Listener Processor (" + PacketReader.this.connection.connectionCounterValue + ")");
                thread.setDaemon(true);
                return thread;
            }
        });
        resetParser();
    }

    public void startup() throws XMPPException {
        this.connectionSemaphore = new Semaphore(1);
        this.readerThread.start();
        try {
            this.connectionSemaphore.acquire();
            this.connectionSemaphore.tryAcquire((long) (SmackConfiguration.getPacketReplyTimeout() * 3), TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        String str = this.connectionID;
        if (str != null) {
            this.connection.connectionID = str;
            return;
        }
        throw new XMPPException("Connection failed. No response from server.");
    }

    public void shutdown() {
        if (!this.done) {
            for (ConnectionListener connectionClosed : this.connection.getConnectionListeners()) {
                try {
                    connectionClosed.connectionClosed();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.done = true;
        this.listenerExecutor.shutdown();
    }

    /* access modifiers changed from: package-private */
    public void cleanup() {
        this.connection.recvListeners.clear();
        this.connection.collectors.clear();
    }

    /* access modifiers changed from: package-private */
    public void notifyConnectionError(Exception exc) {
        this.done = true;
        this.connection.shutdown(new Presence(Presence.Type.unavailable));
        exc.printStackTrace();
        for (ConnectionListener connectionClosedOnError : this.connection.getConnectionListeners()) {
            try {
                connectionClosedOnError.connectionClosedOnError(exc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void notifyReconnection() {
        for (ConnectionListener reconnectionSuccessful : this.connection.getConnectionListeners()) {
            try {
                reconnectionSuccessful.reconnectionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetParser() {
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            this.parser = newPullParser;
            newPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            this.parser.setInput(this.connection.reader);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000b A[SYNTHETIC, Splitter:B:4:0x000b] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01c1 A[Catch:{ Exception -> 0x01e7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parsePackets(java.lang.Thread r6) {
        /*
            r5 = this;
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            int r0 = r0.getEventType()     // Catch:{ Exception -> 0x01e7 }
        L_0x0006:
            r1 = 2
            java.lang.String r2 = "stream"
            if (r0 != r1) goto L_0x01c1
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "message"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x0024
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.packet.Packet r0 = org.jivesoftware.smack.util.PacketParserUtils.parseMessage(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r0)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x0024:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "iq"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x003f
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.XMPPConnection r1 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.packet.IQ r0 = org.jivesoftware.smack.util.PacketParserUtils.parseIQ(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r0)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x003f:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "presence"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x0058
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.packet.Presence r0 = org.jivesoftware.smack.util.PacketParserUtils.parsePresence(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r0)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x0058:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            r1 = 0
            if (r0 == 0) goto L_0x00c6
            java.lang.String r0 = "jabber:client"
            org.xmlpull.v1.XmlPullParser r2 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r2.getNamespace(r1)     // Catch:{ Exception -> 0x01e7 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x01d5
            r0 = 0
        L_0x0074:
            org.xmlpull.v1.XmlPullParser r1 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            int r1 = r1.getAttributeCount()     // Catch:{ Exception -> 0x01e7 }
            if (r0 >= r1) goto L_0x01d5
            org.xmlpull.v1.XmlPullParser r1 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r1.getAttributeName(r0)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "id"
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r1 == 0) goto L_0x00a8
            org.xmlpull.v1.XmlPullParser r1 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r1.getAttributeValue(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.connectionID = r1     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "1.0"
            org.xmlpull.v1.XmlPullParser r2 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r3 = ""
            java.lang.String r4 = "version"
            java.lang.String r2 = r2.getAttributeValue(r3, r4)     // Catch:{ Exception -> 0x01e7 }
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r1 != 0) goto L_0x00c3
            r5.releaseConnectionIDLock()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x00c3
        L_0x00a8:
            org.xmlpull.v1.XmlPullParser r1 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r1.getAttributeName(r0)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "from"
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r1 == 0) goto L_0x00c3
            org.jivesoftware.smack.XMPPConnection r1 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.ConnectionConfiguration r1 = r1.config     // Catch:{ Exception -> 0x01e7 }
            org.xmlpull.v1.XmlPullParser r2 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = r2.getAttributeValue(r0)     // Catch:{ Exception -> 0x01e7 }
            r1.setServiceName(r2)     // Catch:{ Exception -> 0x01e7 }
        L_0x00c3:
            int r0 = r0 + 1
            goto L_0x0074
        L_0x00c6:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "error"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r0 != 0) goto L_0x01b5
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "features"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x00e9
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            r5.parseFeatures(r0)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x00e9:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "proceed"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x0101
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            r0.proceedTLSReceived()     // Catch:{ Exception -> 0x01e7 }
            r5.resetParser()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x0101:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "failure"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x0148
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getNamespace(r1)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "urn:ietf:params:xml:ns:xmpp-tls"
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x01e7 }
            if (r1 != 0) goto L_0x0140
            java.lang.String r1 = "http://jabber.org/protocol/compress"
            boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x012c
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            r0.streamCompressionDenied()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x012c:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.sasl.SASLMechanism$Failure r0 = org.jivesoftware.smack.util.PacketParserUtils.parseSASLFailure(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r0)     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.SASLAuthentication r0 = r0.getSASLAuthentication()     // Catch:{ Exception -> 0x01e7 }
            r0.authenticationFailed()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x0140:
            java.lang.Exception r6 = new java.lang.Exception     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "TLS negotiation has failed"
            r6.<init>(r0)     // Catch:{ Exception -> 0x01e7 }
            throw r6     // Catch:{ Exception -> 0x01e7 }
        L_0x0148:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "challenge"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x016e
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.nextText()     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.sasl.SASLMechanism$Challenge r1 = new org.jivesoftware.smack.sasl.SASLMechanism$Challenge     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r1)     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.XMPPConnection r1 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.SASLAuthentication r1 = r1.getSASLAuthentication()     // Catch:{ Exception -> 0x01e7 }
            r1.challengeReceived(r0)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x016e:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "success"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x019e
            org.jivesoftware.smack.sasl.SASLMechanism$Success r0 = new org.jivesoftware.smack.sasl.SASLMechanism$Success     // Catch:{ Exception -> 0x01e7 }
            org.xmlpull.v1.XmlPullParser r1 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r1.nextText()     // Catch:{ Exception -> 0x01e7 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x01e7 }
            r5.processPacket(r0)     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.PacketWriter r0 = r0.packetWriter     // Catch:{ Exception -> 0x01e7 }
            r0.openStream()     // Catch:{ Exception -> 0x01e7 }
            r5.resetParser()     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.SASLAuthentication r0 = r0.getSASLAuthentication()     // Catch:{ Exception -> 0x01e7 }
            r0.authenticated()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x019e:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "compressed"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x01d5
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            r0.startStreamCompression()     // Catch:{ Exception -> 0x01e7 }
            r5.resetParser()     // Catch:{ Exception -> 0x01e7 }
            goto L_0x01d5
        L_0x01b5:
            org.jivesoftware.smack.XMPPException r6 = new org.jivesoftware.smack.XMPPException     // Catch:{ Exception -> 0x01e7 }
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            org.jivesoftware.smack.packet.StreamError r0 = org.jivesoftware.smack.util.PacketParserUtils.parseStreamError(r0)     // Catch:{ Exception -> 0x01e7 }
            r6.<init>((org.jivesoftware.smack.packet.StreamError) r0)     // Catch:{ Exception -> 0x01e7 }
            throw r6     // Catch:{ Exception -> 0x01e7 }
        L_0x01c1:
            r1 = 3
            if (r0 != r1) goto L_0x01d5
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x01e7 }
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r0 == 0) goto L_0x01d5
            org.jivesoftware.smack.XMPPConnection r0 = r5.connection     // Catch:{ Exception -> 0x01e7 }
            r0.disconnect()     // Catch:{ Exception -> 0x01e7 }
        L_0x01d5:
            org.xmlpull.v1.XmlPullParser r0 = r5.parser     // Catch:{ Exception -> 0x01e7 }
            int r0 = r0.next()     // Catch:{ Exception -> 0x01e7 }
            boolean r1 = r5.done     // Catch:{ Exception -> 0x01e7 }
            if (r1 != 0) goto L_0x01ef
            r1 = 1
            if (r0 == r1) goto L_0x01ef
            java.lang.Thread r1 = r5.readerThread     // Catch:{ Exception -> 0x01e7 }
            if (r6 == r1) goto L_0x0006
            goto L_0x01ef
        L_0x01e7:
            r6 = move-exception
            boolean r0 = r5.done
            if (r0 != 0) goto L_0x01ef
            r5.notifyConnectionError(r6)
        L_0x01ef:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.PacketReader.parsePackets(java.lang.Thread):void");
    }

    private void releaseConnectionIDLock() {
        this.connectionSemaphore.release();
    }

    private void processPacket(Packet packet) {
        if (packet != null) {
            for (PacketCollector processPacket : this.connection.getPacketCollectors()) {
                processPacket.processPacket(packet);
            }
            this.listenerExecutor.submit(new ListenerNotification(packet));
        }
    }

    private void parseFeatures(XmlPullParser xmlPullParser) throws Exception {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("starttls")) {
                    z2 = true;
                } else if (xmlPullParser.getName().equals("mechanisms")) {
                    this.connection.getSASLAuthentication().setAvailableSASLMethods(PacketParserUtils.parseMechanisms(xmlPullParser));
                } else if (xmlPullParser.getName().equals("bind")) {
                    this.connection.getSASLAuthentication().bindingRequired();
                } else if (xmlPullParser.getName().equals("ver")) {
                    this.connection.getConfiguration().setRosterVersioningAvailable(true);
                } else if (xmlPullParser.getName().equals(CapsExtension.NODE_NAME)) {
                    String attributeValue = xmlPullParser.getAttributeValue((String) null, "node");
                    String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "ver");
                    this.connection.getConfiguration().setCapsNode(attributeValue + "#" + attributeValue2);
                } else if (xmlPullParser.getName().equals(SessionID.ELEMENT_NAME)) {
                    this.connection.getSASLAuthentication().sessionsSupported();
                } else if (xmlPullParser.getName().equals("compression")) {
                    this.connection.setAvailableCompressionMethods(PacketParserUtils.parseCompressionMethods(xmlPullParser));
                } else if (xmlPullParser.getName().equals("register")) {
                    this.connection.getAccountManager().setSupportsAccountCreation(true);
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("starttls")) {
                    this.connection.startTLSReceived(z3);
                } else if (xmlPullParser.getName().equals("required") && z2) {
                    z3 = true;
                } else if (xmlPullParser.getName().equals("features")) {
                    z = true;
                }
            }
        }
        if (!this.connection.isSecureConnection() && !z2 && this.connection.getConfiguration().getSecurityMode() == ConnectionConfiguration.SecurityMode.required) {
            throw new XMPPException("Server does not support security (TLS), but security required by connection configuration.", new XMPPError(XMPPError.Condition.forbidden));
        } else if (!z2 || this.connection.getConfiguration().getSecurityMode() == ConnectionConfiguration.SecurityMode.disabled) {
            releaseConnectionIDLock();
        }
    }

    private class ListenerNotification implements Runnable {
        private Packet packet;

        public ListenerNotification(Packet packet2) {
            this.packet = packet2;
        }

        public void run() {
            for (Connection.ListenerWrapper notifyListener : PacketReader.this.connection.recvListeners.values()) {
                notifyListener.notifyListener(this.packet);
            }
        }
    }
}
