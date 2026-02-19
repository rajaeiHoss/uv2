package org.jivesoftware.smackx.bytestreams.socks5;

import com.google.android.gms.search.SearchAuth;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.Cache;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;

public class Socks5BytestreamRequest implements BytestreamRequest {
    private static final long BLACKLIST_LIFETIME = 7200000;
    private static final Cache<String, Integer> ADDRESS_BLACKLIST = new Cache<>(100, BLACKLIST_LIFETIME);
    private static final int BLACKLIST_MAX_SIZE = 100;
    private static int CONNECTION_FAILURE_THRESHOLD = 2;
    private Bytestream bytestreamRequest;
    private Socks5BytestreamManager manager;
    private int minimumConnectTimeout = 2000;
    private int totalConnectTimeout = SearchAuth.StatusCodes.AUTH_DISABLED;

    public static int getConnectFailureThreshold() {
        return CONNECTION_FAILURE_THRESHOLD;
    }

    public static void setConnectFailureThreshold(int i) {
        CONNECTION_FAILURE_THRESHOLD = i;
    }

    protected Socks5BytestreamRequest(Socks5BytestreamManager socks5BytestreamManager, Bytestream bytestream) {
        this.manager = socks5BytestreamManager;
        this.bytestreamRequest = bytestream;
    }

    public int getTotalConnectTimeout() {
        int i = this.totalConnectTimeout;
        return i <= 0 ? SearchAuth.StatusCodes.AUTH_DISABLED : i;
    }

    public void setTotalConnectTimeout(int i) {
        this.totalConnectTimeout = i;
    }

    public int getMinimumConnectTimeout() {
        int i = this.minimumConnectTimeout;
        if (i <= 0) {
            return 2000;
        }
        return i;
    }

    public void setMinimumConnectTimeout(int i) {
        this.minimumConnectTimeout = i;
    }

    public String getFrom() {
        return this.bytestreamRequest.getFrom();
    }

    public String getSessionID() {
        return this.bytestreamRequest.getSessionID();
    }

    public Socks5BytestreamSession accept() throws XMPPException, InterruptedException {
        Bytestream.StreamHost streamHost;
        Socket socket;
        Collection<Bytestream.StreamHost> streamHosts = this.bytestreamRequest.getStreamHosts();
        if (streamHosts.size() == 0) {
            cancelRequest();
        }
        String createDigest = Socks5Utils.createDigest(this.bytestreamRequest.getSessionID(), this.bytestreamRequest.getFrom(), this.manager.getConnection().getUser());
        int max = Math.max(getTotalConnectTimeout() / streamHosts.size(), getMinimumConnectTimeout());
        Iterator<Bytestream.StreamHost> it = streamHosts.iterator();
        while (true) {
            streamHost = null;
            if (!it.hasNext()) {
                socket = null;
                break;
            }
            streamHost = it.next();
            String str = streamHost.getAddress() + ":" + streamHost.getPort();
            int connectionFailures = getConnectionFailures(str);
            int i = CONNECTION_FAILURE_THRESHOLD;
            if (i <= 0 || connectionFailures < i) {
                try {
                    socket = new Socks5Client(streamHost, createDigest).getSocket(max);
                    break;
                } catch (TimeoutException unused) {
                    incrementConnectionFailures(str);
                } catch (IOException unused2) {
                    incrementConnectionFailures(str);
                } catch (XMPPException unused3) {
                    incrementConnectionFailures(str);
                }
            }
        }
        if (streamHost == null || socket == null) {
            cancelRequest();
        }
        this.manager.getConnection().sendPacket(createUsedHostResponse(streamHost));
        return new Socks5BytestreamSession(socket, streamHost.getJID().equals(this.bytestreamRequest.getFrom()));
    }

    public void reject() {
        this.manager.replyRejectPacket(this.bytestreamRequest);
    }

    private void cancelRequest() throws XMPPException {
        XMPPError xMPPError = new XMPPError(XMPPError.Condition.item_not_found, "Could not establish socket with any provided host");
        this.manager.getConnection().sendPacket(IQ.createErrorResponse(this.bytestreamRequest, xMPPError));
        throw new XMPPException("Could not establish socket with any provided host", xMPPError);
    }

    private Bytestream createUsedHostResponse(Bytestream.StreamHost streamHost) {
        Bytestream bytestream = new Bytestream(this.bytestreamRequest.getSessionID());
        bytestream.setTo(this.bytestreamRequest.getFrom());
        bytestream.setType(IQ.Type.RESULT);
        bytestream.setPacketID(this.bytestreamRequest.getPacketID());
        bytestream.setUsedHost(streamHost.getJID());
        return bytestream;
    }

    private void incrementConnectionFailures(String str) {
        Cache<String, Integer> cache = ADDRESS_BLACKLIST;
        Integer num = cache.get(str);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        cache.put(str, Integer.valueOf(i));
    }

    private int getConnectionFailures(String str) {
        Integer num = ADDRESS_BLACKLIST.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
