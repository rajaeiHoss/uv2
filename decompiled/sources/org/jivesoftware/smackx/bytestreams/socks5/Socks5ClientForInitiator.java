package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.packet.SyncPacketSend;

class Socks5ClientForInitiator extends Socks5Client {
    private Connection connection;
    private String sessionID;
    private String target;

    public Socks5ClientForInitiator(Bytestream.StreamHost streamHost, String str, Connection connection2, String str2, String str3) {
        super(streamHost, str);
        this.connection = connection2;
        this.sessionID = str2;
        this.target = str3;
    }

    public Socket getSocket(int i) throws IOException, XMPPException, InterruptedException, TimeoutException {
        Socket socket;
        if (this.streamHost.getJID().equals(this.connection.getUser())) {
            socket = Socks5Proxy.getSocks5Proxy().getSocket(this.digest);
            if (socket == null) {
                throw new XMPPException("target is not connected to SOCKS5 proxy");
            }
        } else {
            socket = super.getSocket(i);
            try {
                activate();
            } catch (XMPPException e) {
                socket.close();
                throw new XMPPException("activating SOCKS5 Bytestream failed", (Throwable) e);
            }
        }
        return socket;
    }

    private void activate() throws XMPPException {
        SyncPacketSend.getReply(this.connection, createStreamHostActivation());
    }

    private Bytestream createStreamHostActivation() {
        Bytestream bytestream = new Bytestream(this.sessionID);
        bytestream.setMode((Bytestream.Mode) null);
        bytestream.setType(IQ.Type.SET);
        bytestream.setTo(this.streamHost.getJID());
        bytestream.setToActivate(this.target);
        return bytestream;
    }
}
