package org.jivesoftware.smackx.filetransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.IQTypeFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.packet.StreamInitiation;

public class FileTransferManager {
    private Connection connection;
    private final FileTransferNegotiator fileTransferNegotiator;
    private List<FileTransferListener> listeners;

    public FileTransferManager(Connection connection2) {
        this.connection = connection2;
        this.fileTransferNegotiator = FileTransferNegotiator.getInstanceFor(connection2);
    }

    public void addFileTransferListener(FileTransferListener fileTransferListener) {
        if (this.listeners == null) {
            initListeners();
        }
        synchronized (this.listeners) {
            this.listeners.add(fileTransferListener);
        }
    }

    private void initListeners() {
        this.listeners = new ArrayList();
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                FileTransferManager.this.fireNewRequest((StreamInitiation) packet);
            }
        }, new AndFilter(new PacketTypeFilter(StreamInitiation.class), new IQTypeFilter(IQ.Type.SET)));
    }

    /* access modifiers changed from: protected */
    public void fireNewRequest(StreamInitiation streamInitiation) {
        int size;
        FileTransferListener[] fileTransferListenerArr;
        synchronized (this.listeners) {
            size = this.listeners.size();
            fileTransferListenerArr = new FileTransferListener[size];
            this.listeners.toArray(fileTransferListenerArr);
        }
        FileTransferRequest fileTransferRequest = new FileTransferRequest(this, streamInitiation);
        for (int i = 0; i < size; i++) {
            fileTransferListenerArr[i].fileTransferRequest(fileTransferRequest);
        }
    }

    public void removeFileTransferListener(FileTransferListener fileTransferListener) {
        List<FileTransferListener> list = this.listeners;
        if (list != null) {
            synchronized (list) {
                this.listeners.remove(fileTransferListener);
            }
        }
    }

    public OutgoingFileTransfer createOutgoingFileTransfer(String str) {
        return new OutgoingFileTransfer(this.connection.getUser(), str, this.fileTransferNegotiator.getNextStreamID(), this.fileTransferNegotiator);
    }

    /* access modifiers changed from: protected */
    public IncomingFileTransfer createIncomingFileTransfer(FileTransferRequest fileTransferRequest) {
        Objects.requireNonNull(fileTransferRequest, "RecieveRequest cannot be null");
        IncomingFileTransfer incomingFileTransfer = new IncomingFileTransfer(fileTransferRequest, this.fileTransferNegotiator);
        incomingFileTransfer.setFileInfo(fileTransferRequest.getFileName(), fileTransferRequest.getFileSize());
        return incomingFileTransfer;
    }

    /* access modifiers changed from: protected */
    public void rejectIncomingFileTransfer(FileTransferRequest fileTransferRequest) {
        StreamInitiation streamInitiation = fileTransferRequest.getStreamInitiation();
        IQ createIQ = FileTransferNegotiator.createIQ(streamInitiation.getPacketID(), streamInitiation.getFrom(), streamInitiation.getTo(), IQ.Type.ERROR);
        createIQ.setError(new XMPPError(XMPPError.Condition.forbidden));
        this.connection.sendPacket(createIQ);
    }
}
