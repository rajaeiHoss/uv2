package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamRequest;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.packet.StreamInitiation;

public class IBBTransferNegotiator extends StreamNegotiator {
    private Connection connection;
    private InBandBytestreamManager manager;

    public void cleanup() {
    }

    protected IBBTransferNegotiator(Connection connection2) {
        this.connection = connection2;
        this.manager = InBandBytestreamManager.getByteStreamManager(connection2);
    }

    public OutputStream createOutgoingStream(String str, String str2, String str3) throws XMPPException {
        InBandBytestreamSession establishSession = this.manager.establishSession(str3, str);
        establishSession.setCloseBothStreamsEnabled(true);
        return establishSession.getOutputStream();
    }

    public InputStream createIncomingStream(StreamInitiation streamInitiation) throws XMPPException {
        this.manager.ignoreBytestreamRequestOnce(streamInitiation.getSessionID());
        return negotiateIncomingStream(initiateIncomingStream(this.connection, streamInitiation));
    }

    public PacketFilter getInitiationPacketFilter(String str, String str2) {
        this.manager.ignoreBytestreamRequestOnce(str2);
        return new AndFilter(new FromContainsFilter(str), new IBBOpenSidFilter(str2));
    }

    public String[] getNamespaces() {
        return new String[]{InBandBytestreamManager.NAMESPACE};
    }

    /* access modifiers changed from: package-private */
    public InputStream negotiateIncomingStream(Packet packet) throws XMPPException {
        InBandBytestreamSession accept = new ByteStreamRequest(this.manager, (Open) packet).accept();
        accept.setCloseBothStreamsEnabled(true);
        return accept.getInputStream();
    }

    private static class IBBOpenSidFilter extends PacketTypeFilter {
        private String sessionID;

        public IBBOpenSidFilter(String str) {
            super(Open.class);
            if (str != null) {
                this.sessionID = str;
                return;
            }
            throw new IllegalArgumentException("StreamID cannot be null");
        }

        public boolean accept(Packet packet) {
            if (!super.accept(packet)) {
                return false;
            }
            Open open = (Open) packet;
            if (!this.sessionID.equals(open.getSessionID()) || !IQ.Type.SET.equals(open.getType())) {
                return false;
            }
            return true;
        }
    }

    private static class ByteStreamRequest extends InBandBytestreamRequest {
        private ByteStreamRequest(InBandBytestreamManager inBandBytestreamManager, Open open) {
            super(inBandBytestreamManager, open);
        }
    }
}
