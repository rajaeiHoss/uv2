package org.jivesoftware.smackx.filetransfer;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.StreamInitiation;

public class FileTransferNegotiator {
    public static boolean IBB_ONLY = false;
    private static final String[] NAMESPACE = {"http://jabber.org/protocol/si/profile/file-transfer", "http://jabber.org/protocol/si"};
    protected static final String STREAM_DATA_FIELD_NAME = "stream-method";
    private static final String STREAM_INIT_PREFIX = "jsi_";
    private static final Random randomGenerator = new Random();
    private static final Map<Connection, FileTransferNegotiator> transferObject = new ConcurrentHashMap();
    private final StreamNegotiator byteStreamTransferManager;
    private final Connection connection;
    private final StreamNegotiator inbandTransferManager;

    public static FileTransferNegotiator getInstanceFor(Connection connection2) {
        if (connection2 == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        } else if (!connection2.isConnected()) {
            return null;
        } else {
            Map<Connection, FileTransferNegotiator> map = transferObject;
            if (map.containsKey(connection2)) {
                return map.get(connection2);
            }
            FileTransferNegotiator fileTransferNegotiator = new FileTransferNegotiator(connection2);
            setServiceEnabled(connection2, true);
            map.put(connection2, fileTransferNegotiator);
            return fileTransferNegotiator;
        }
    }

    public static void setServiceEnabled(Connection connection2, boolean z) {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(connection2);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(NAMESPACE));
        arrayList.add(InBandBytestreamManager.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Socks5BytestreamManager.NAMESPACE);
        }
        for (String str : arrayList) {
            if (!z) {
                instanceFor.removeFeature(str);
            } else if (!instanceFor.includesFeature(str)) {
                instanceFor.addFeature(str);
            }
        }
    }

    public static boolean isServiceEnabled(Connection connection2) {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(connection2);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(NAMESPACE));
        arrayList.add(InBandBytestreamManager.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Socks5BytestreamManager.NAMESPACE);
        }
        for (String includesFeature : arrayList) {
            if (!instanceFor.includesFeature(includesFeature)) {
                return false;
            }
        }
        return true;
    }

    public static IQ createIQ(String str, String str2, String str3, IQ.Type type) {
        IQ iq = new IQ() {
            public String getChildElementXML() {
                return null;
            }
        };
        iq.setPacketID(str);
        iq.setTo(str2);
        iq.setFrom(str3);
        iq.setType(type);
        return iq;
    }

    public static Collection<String> getSupportedProtocols() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(InBandBytestreamManager.NAMESPACE);
        if (!IBB_ONLY) {
            arrayList.add(Socks5BytestreamManager.NAMESPACE);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private FileTransferNegotiator(Connection connection2) {
        configureConnection(connection2);
        this.connection = connection2;
        this.byteStreamTransferManager = new Socks5TransferNegotiator(connection2);
        this.inbandTransferManager = new IBBTransferNegotiator(connection2);
    }

    private void configureConnection(final Connection connection2) {
        connection2.addConnectionListener(new ConnectionListener() {
            public void reconnectingIn(int i) {
            }

            public void reconnectionFailed(Exception exc) {
            }

            public void reconnectionSuccessful() {
            }

            public void connectionClosed() {
                FileTransferNegotiator.this.cleanup(connection2);
            }

            public void connectionClosedOnError(Exception exc) {
                FileTransferNegotiator.this.cleanup(connection2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void cleanup(Connection connection2) {
        if (transferObject.remove(connection2) != null) {
            this.inbandTransferManager.cleanup();
        }
    }

    public StreamNegotiator selectStreamNegotiator(FileTransferRequest fileTransferRequest) throws XMPPException {
        StreamInitiation streamInitiation = fileTransferRequest.getStreamInitiation();
        FormField streamMethodField = getStreamMethodField(streamInitiation.getFeatureNegotiationForm());
        if (streamMethodField != null) {
            try {
                return getNegotiator(streamMethodField);
            } catch (XMPPException e) {
                IQ createIQ = createIQ(streamInitiation.getPacketID(), streamInitiation.getFrom(), streamInitiation.getTo(), IQ.Type.ERROR);
                createIQ.setError(e.getXMPPError());
                this.connection.sendPacket(createIQ);
                throw e;
            }
        } else {
            XMPPError xMPPError = new XMPPError(XMPPError.Condition.bad_request, "No stream methods contained in packet.");
            IQ createIQ2 = createIQ(streamInitiation.getPacketID(), streamInitiation.getFrom(), streamInitiation.getTo(), IQ.Type.ERROR);
            createIQ2.setError(xMPPError);
            this.connection.sendPacket(createIQ2);
            throw new XMPPException("No stream methods contained in packet.", xMPPError);
        }
    }

    private FormField getStreamMethodField(DataForm dataForm) {
        FormField formField;
        Iterator<FormField> fields = dataForm.getFields();
        do {
            formField = null;
            if (!fields.hasNext()) {
                break;
            }
            formField = fields.next();
        } while (!formField.getVariable().equals(STREAM_DATA_FIELD_NAME));
        return formField;
    }

    private StreamNegotiator getNegotiator(FormField formField) throws XMPPException {
        Iterator<FormField.Option> options = formField.getOptions();
        boolean z = false;
        boolean z2 = false;
        while (options.hasNext()) {
            String value = options.next().getValue();
            if (value.equals(Socks5BytestreamManager.NAMESPACE) && !IBB_ONLY) {
                z = true;
            } else if (value.equals(InBandBytestreamManager.NAMESPACE)) {
                z2 = true;
            }
        }
        if (!z && !z2) {
            XMPPError xMPPError = new XMPPError(XMPPError.Condition.bad_request, "No acceptable transfer mechanism");
            throw new XMPPException(xMPPError.getMessage(), xMPPError);
        } else if (z && z2 && formField.getType().equals(FormField.TYPE_LIST_MULTI)) {
            return new FaultTolerantNegotiator(this.connection, this.byteStreamTransferManager, this.inbandTransferManager);
        } else {
            if (z) {
                return this.byteStreamTransferManager;
            }
            return this.inbandTransferManager;
        }
    }

    public void rejectStream(StreamInitiation streamInitiation) {
        XMPPError xMPPError = new XMPPError(XMPPError.Condition.forbidden, "Offer Declined");
        IQ createIQ = createIQ(streamInitiation.getPacketID(), streamInitiation.getFrom(), streamInitiation.getTo(), IQ.Type.ERROR);
        createIQ.setError(xMPPError);
        this.connection.sendPacket(createIQ);
    }

    public String getNextStreamID() {
        return STREAM_INIT_PREFIX + Math.abs(randomGenerator.nextLong());
    }

    public StreamNegotiator negotiateOutgoingTransfer(String str, String str2, String str3, long j, String str4, int i) throws XMPPException {
        StreamInitiation streamInitiation = new StreamInitiation();
        streamInitiation.setSesssionID(str2);
        streamInitiation.setMimeType(URLConnection.guessContentTypeFromName(str3));
        StreamInitiation.File file = new StreamInitiation.File(str3, j);
        file.setDesc(str4);
        streamInitiation.setFile(file);
        streamInitiation.setFeatureNegotiationForm(createDefaultInitiationForm());
        streamInitiation.setFrom(this.connection.getUser());
        streamInitiation.setTo(str);
        streamInitiation.setType(IQ.Type.SET);
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(streamInitiation.getPacketID()));
        this.connection.sendPacket(streamInitiation);
        Packet nextResult = createPacketCollector.nextResult((long) i);
        createPacketCollector.cancel();
        if (!(nextResult instanceof IQ)) {
            return null;
        }
        IQ iq = (IQ) nextResult;
        if (iq.getType().equals(IQ.Type.RESULT)) {
            return getOutgoingNegotiator(getStreamMethodField(((StreamInitiation) nextResult).getFeatureNegotiationForm()));
        }
        if (iq.getType().equals(IQ.Type.ERROR)) {
            throw new XMPPException(iq.getError());
        }
        throw new XMPPException("File transfer response unreadable");
    }

    private StreamNegotiator getOutgoingNegotiator(FormField formField) throws XMPPException {
        Iterator<String> values = formField.getValues();
        boolean z = false;
        boolean z2 = false;
        while (values.hasNext()) {
            String next = values.next();
            if (next.equals(Socks5BytestreamManager.NAMESPACE) && !IBB_ONLY) {
                z = true;
            } else if (next.equals(InBandBytestreamManager.NAMESPACE)) {
                z2 = true;
            }
        }
        if (!z && !z2) {
            XMPPError xMPPError = new XMPPError(XMPPError.Condition.bad_request, "No acceptable transfer mechanism");
            throw new XMPPException(xMPPError.getMessage(), xMPPError);
        } else if (z && z2) {
            return new FaultTolerantNegotiator(this.connection, this.byteStreamTransferManager, this.inbandTransferManager);
        } else {
            if (z) {
                return this.byteStreamTransferManager;
            }
            return this.inbandTransferManager;
        }
    }

    private DataForm createDefaultInitiationForm() {
        DataForm dataForm = new DataForm(Form.TYPE_FORM);
        FormField formField = new FormField(STREAM_DATA_FIELD_NAME);
        formField.setType(FormField.TYPE_LIST_MULTI);
        if (!IBB_ONLY) {
            formField.addOption(new FormField.Option(Socks5BytestreamManager.NAMESPACE));
        }
        formField.addOption(new FormField.Option(InBandBytestreamManager.NAMESPACE));
        dataForm.addField(formField);
        return dataForm;
    }
}
