package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.packet.StreamInitiation;

public class FaultTolerantNegotiator extends StreamNegotiator {
    private Connection connection;
    private PacketFilter primaryFilter;
    private StreamNegotiator primaryNegotiator;
    private PacketFilter secondaryFilter;
    private StreamNegotiator secondaryNegotiator;

    public void cleanup() {
    }

    public FaultTolerantNegotiator(Connection connection2, StreamNegotiator streamNegotiator, StreamNegotiator streamNegotiator2) {
        this.primaryNegotiator = streamNegotiator;
        this.secondaryNegotiator = streamNegotiator2;
        this.connection = connection2;
    }

    public PacketFilter getInitiationPacketFilter(String str, String str2) {
        if (this.primaryFilter == null || this.secondaryFilter == null) {
            this.primaryFilter = this.primaryNegotiator.getInitiationPacketFilter(str, str2);
            this.secondaryFilter = this.secondaryNegotiator.getInitiationPacketFilter(str, str2);
        }
        return new OrFilter(this.primaryFilter, this.secondaryFilter);
    }

    /* access modifiers changed from: package-private */
    public InputStream negotiateIncomingStream(Packet packet) throws XMPPException {
        throw new UnsupportedOperationException("Negotiation only handled by create incoming stream method.");
    }

    /* access modifiers changed from: package-private */
    public final Packet initiateIncomingStream(Connection connection2, StreamInitiation streamInitiation) {
        throw new UnsupportedOperationException("Initiation handled by createIncomingStream method");
    }

    public InputStream createIncomingStream(StreamInitiation streamInitiation) throws XMPPException {
        XMPPException xMPPException;
        PacketCollector createPacketCollector = this.connection.createPacketCollector(getInitiationPacketFilter(streamInitiation.getFrom(), streamInitiation.getSessionID()));
        this.connection.sendPacket(super.createInitiationAccept(streamInitiation, getNamespaces()));
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(Executors.newFixedThreadPool(2));
        ArrayList<Future> arrayList = new ArrayList<>();
        try {
            arrayList.add(executorCompletionService.submit(new NegotiatorService(createPacketCollector)));
            arrayList.add(executorCompletionService.submit(new NegotiatorService(createPacketCollector)));
            int i = 0;
            InputStream inputStream = null;
            xMPPException = null;
            while (inputStream == null && i < arrayList.size()) {
                i++;
                try {
                    Future poll = executorCompletionService.poll(10, TimeUnit.SECONDS);
                    if (poll != null) {
                        inputStream = (InputStream) poll.get();
                    }
                } catch (InterruptedException unused) {
                }
            }
            for (Future cancel : arrayList) {
                cancel.cancel(true);
            }
            createPacketCollector.cancel();
            if (inputStream != null) {
                return inputStream;
            }
            if (xMPPException != null) {
                throw xMPPException;
            }
            throw new XMPPException("File transfer negotiation failed.");
        } catch (ExecutionException e) {
            xMPPException = new XMPPException(e.getCause());
            for (Future cancel2 : arrayList) {
                cancel2.cancel(true);
            }
            createPacketCollector.cancel();
            throw xMPPException;
        } catch (Throwable th) {
            for (Future cancel2 : arrayList) {
                cancel2.cancel(true);
            }
            createPacketCollector.cancel();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public StreamNegotiator determineNegotiator(Packet packet) {
        return this.primaryFilter.accept(packet) ? this.primaryNegotiator : this.secondaryNegotiator;
    }

    public OutputStream createOutgoingStream(String str, String str2, String str3) throws XMPPException {
        try {
            return this.primaryNegotiator.createOutgoingStream(str, str2, str3);
        } catch (XMPPException unused) {
            return this.secondaryNegotiator.createOutgoingStream(str, str2, str3);
        }
    }

    public String[] getNamespaces() {
        String[] namespaces = this.primaryNegotiator.getNamespaces();
        String[] namespaces2 = this.secondaryNegotiator.getNamespaces();
        String[] strArr = new String[(namespaces.length + namespaces2.length)];
        System.arraycopy(namespaces, 0, strArr, 0, namespaces.length);
        System.arraycopy(namespaces2, 0, strArr, namespaces.length, namespaces2.length);
        return strArr;
    }

    private class NegotiatorService implements Callable<InputStream> {
        private PacketCollector collector;

        NegotiatorService(PacketCollector packetCollector) {
            this.collector = packetCollector;
        }

        public InputStream call() throws Exception {
            Packet nextResult = this.collector.nextResult((long) (SmackConfiguration.getPacketReplyTimeout() * 2));
            if (nextResult != null) {
                return FaultTolerantNegotiator.this.determineNegotiator(nextResult).negotiateIncomingStream(nextResult);
            }
            throw new XMPPException("No response from remote client");
        }
    }
}
