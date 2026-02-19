package org.jivesoftware.smackx;

import java.util.Iterator;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.packet.XHTMLExtension;

public class XHTMLManager {
    private static final String namespace = "http://jabber.org/protocol/xhtml-im";

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                XHTMLManager.setServiceEnabled(connection, true);
            }
        });
    }

    public static Iterator getBodies(Message message) {
        XHTMLExtension xHTMLExtension = (XHTMLExtension) message.getExtension("html", namespace);
        if (xHTMLExtension != null) {
            return xHTMLExtension.getBodies();
        }
        return null;
    }

    public static void addBody(Message message, String str) {
        XHTMLExtension xHTMLExtension = (XHTMLExtension) message.getExtension("html", namespace);
        if (xHTMLExtension == null) {
            xHTMLExtension = new XHTMLExtension();
            message.addExtension(xHTMLExtension);
        }
        xHTMLExtension.addBody(str);
    }

    public static boolean isXHTMLMessage(Message message) {
        return message.getExtension("html", namespace) != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void setServiceEnabled(org.jivesoftware.smack.Connection r2, boolean r3) {
        /*
            java.lang.Class<org.jivesoftware.smackx.XHTMLManager> r0 = org.jivesoftware.smackx.XHTMLManager.class
            monitor-enter(r0)
            boolean r1 = isServiceEnabled(r2)     // Catch:{ all -> 0x0022 }
            if (r1 != r3) goto L_0x000b
            monitor-exit(r0)
            return
        L_0x000b:
            if (r3 == 0) goto L_0x0017
            org.jivesoftware.smackx.ServiceDiscoveryManager r2 = org.jivesoftware.smackx.ServiceDiscoveryManager.getInstanceFor(r2)     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "http://jabber.org/protocol/xhtml-im"
            r2.addFeature(r3)     // Catch:{ all -> 0x0022 }
            goto L_0x0020
        L_0x0017:
            org.jivesoftware.smackx.ServiceDiscoveryManager r2 = org.jivesoftware.smackx.ServiceDiscoveryManager.getInstanceFor(r2)     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "http://jabber.org/protocol/xhtml-im"
            r2.removeFeature(r3)     // Catch:{ all -> 0x0022 }
        L_0x0020:
            monitor-exit(r0)
            return
        L_0x0022:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.XHTMLManager.setServiceEnabled(org.jivesoftware.smack.Connection, boolean):void");
    }

    public static boolean isServiceEnabled(Connection connection) {
        return ServiceDiscoveryManager.getInstanceFor(connection).includesFeature(namespace);
    }

    public static boolean isServiceEnabled(Connection connection, String str) {
        try {
            return ServiceDiscoveryManager.getInstanceFor(connection).discoverInfo(str).containsFeature(namespace);
        } catch (XMPPException e) {
            e.printStackTrace();
            return false;
        }
    }
}
