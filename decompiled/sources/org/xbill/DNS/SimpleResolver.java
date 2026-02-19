package org.xbill.DNS;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.UByte;

public class SimpleResolver implements Resolver {
    public static final int DEFAULT_EDNS_PAYLOADSIZE = 1280;
    public static final int DEFAULT_PORT = 53;
    private static final short DEFAULT_UDPSIZE = 512;
    private static String defaultResolver = "localhost";
    private static int uniqueID;
    private InetSocketAddress address;
    private boolean ignoreTruncation;
    private InetSocketAddress localAddress;
    private OPTRecord queryOPT;
    private long timeoutValue;
    private TSIG tsig;
    private boolean useTCP;

    public SimpleResolver(String str) throws UnknownHostException {
        InetAddress inetAddress;
        this.timeoutValue = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        if (str == null && (str = ResolverConfig.getCurrentConfig().server()) == null) {
            str = defaultResolver;
        }
        if (str.equals("0")) {
            inetAddress = InetAddress.getLocalHost();
        } else {
            inetAddress = InetAddress.getByName(str);
        }
        this.address = new InetSocketAddress(inetAddress, 53);
    }

    public SimpleResolver() throws UnknownHostException {
        this((String) null);
    }

    /* access modifiers changed from: package-private */
    public InetSocketAddress getAddress() {
        return this.address;
    }

    public static void setDefaultResolver(String str) {
        defaultResolver = str;
    }

    public void setPort(int i) {
        this.address = new InetSocketAddress(this.address.getAddress(), i);
    }

    public void setAddress(InetSocketAddress inetSocketAddress) {
        this.address = inetSocketAddress;
    }

    public void setAddress(InetAddress inetAddress) {
        this.address = new InetSocketAddress(inetAddress, this.address.getPort());
    }

    public void setLocalAddress(InetSocketAddress inetSocketAddress) {
        this.localAddress = inetSocketAddress;
    }

    public void setLocalAddress(InetAddress inetAddress) {
        this.localAddress = new InetSocketAddress(inetAddress, 0);
    }

    public void setTCP(boolean z) {
        this.useTCP = z;
    }

    public void setIgnoreTruncation(boolean z) {
        this.ignoreTruncation = z;
    }

    public void setEDNS(int i, int i2, int i3, List list) {
        if (i == 0 || i == -1) {
            this.queryOPT = new OPTRecord(i2 == 0 ? DEFAULT_EDNS_PAYLOADSIZE : i2, 0, i, i3, list);
            return;
        }
        throw new IllegalArgumentException("invalid EDNS level - must be 0 or -1");
    }

    public void setEDNS(int i) {
        setEDNS(i, 0, 0, (List) null);
    }

    public void setTSIGKey(TSIG tsig2) {
        this.tsig = tsig2;
    }

    /* access modifiers changed from: package-private */
    public TSIG getTSIGKey() {
        return this.tsig;
    }

    public void setTimeout(int i, int i2) {
        this.timeoutValue = (((long) i) * 1000) + ((long) i2);
    }

    public void setTimeout(int i) {
        setTimeout(i, 0);
    }

    /* access modifiers changed from: package-private */
    public long getTimeout() {
        return this.timeoutValue;
    }

    private Message parseMessage(byte[] bArr) throws WireParseException {
        try {
            return new Message(bArr);
        } catch (IOException e) {
            e = e;
            if (Options.check("verbose")) {
                e.printStackTrace();
            }
            if (!(e instanceof WireParseException)) {
                e = new WireParseException("Error parsing message");
            }
            throw ((WireParseException) e);
        }
    }

    private void verifyTSIG(Message message, Message message2, byte[] bArr, TSIG tsig2) {
        if (tsig2 != null) {
            int verify = tsig2.verify(message2, bArr, message.getTSIG());
            if (Options.check("verbose")) {
                PrintStream printStream = System.err;
                printStream.println("TSIG verify: " + Rcode.string(verify));
            }
        }
    }

    private void applyEDNS(Message message) {
        if (this.queryOPT != null && message.getOPT() == null) {
            message.addRecord(this.queryOPT, 3);
        }
    }

    private int maxUDPSize(Message message) {
        OPTRecord opt = message.getOPT();
        if (opt == null) {
            return 512;
        }
        return opt.getPayloadSize();
    }

    public Message send(Message message) throws IOException {
        byte[] bArr;
        Message parseMessage;
        Record question;
        if (Options.check("verbose")) {
            System.err.println("Sending to " + this.address.getAddress().getHostAddress() + ":" + this.address.getPort());
        }
        if (message.getHeader().getOpcode() == 0 && (question = message.getQuestion()) != null && question.getType() == 252) {
            return sendAXFR(message);
        }
        Message message2 = (Message) message.clone();
        applyEDNS(message2);
        TSIG tsig2 = this.tsig;
        if (tsig2 != null) {
            tsig2.apply(message2, (TSIGRecord) null);
        }
        byte[] wire = message2.toWire(65535);
        int maxUDPSize = maxUDPSize(message2);
        long currentTimeMillis = System.currentTimeMillis() + this.timeoutValue;
        boolean z = false;
        while (true) {
            boolean z2 = (this.useTCP || wire.length > maxUDPSize) ? true : z;
            if (z2) {
                bArr = TCPClient.sendrecv(this.localAddress, this.address, wire, currentTimeMillis);
            } else {
                bArr = UDPClient.sendrecv(this.localAddress, this.address, wire, maxUDPSize, currentTimeMillis);
            }
            if (bArr.length >= 12) {
                int i = ((bArr[0] & UByte.MAX_VALUE) << 8) + (bArr[1] & UByte.MAX_VALUE);
                int id = message2.getHeader().getID();
                if (i != id) {
                    String str = "invalid message id: expected " + id + "; got id " + i;
                    if (!z2) {
                        if (Options.check("verbose")) {
                            System.err.println(str);
                        }
                        z = z2;
                    } else {
                        throw new WireParseException(str);
                    }
                } else {
                    parseMessage = parseMessage(bArr);
                    verifyTSIG(message2, parseMessage, bArr, this.tsig);
                    if (z2 || this.ignoreTruncation || !parseMessage.getHeader().getFlag(6)) {
                        return parseMessage;
                    }
                    z = true;
                }
            } else {
                throw new WireParseException("invalid DNS header - too short");
            }
        }
    }

    public Object sendAsync(Message message, ResolverListener resolverListener) {
        Integer num;
        synchronized (this) {
            int i = uniqueID;
            uniqueID = i + 1;
            num = new Integer(i);
        }
        Record question = message.getQuestion();
        String str = getClass() + ": " + (question != null ? question.getName().toString() : "(none)");
        ResolveThread resolveThread = new ResolveThread(this, message, num, resolverListener);
        resolveThread.setName(str);
        resolveThread.setDaemon(true);
        resolveThread.start();
        return num;
    }

    private Message sendAXFR(Message message) throws IOException {
        ZoneTransferIn newAXFR = ZoneTransferIn.newAXFR(message.getQuestion().getName(), (SocketAddress) this.address, this.tsig);
        newAXFR.setTimeout((int) (getTimeout() / 1000));
        newAXFR.setLocalAddress(this.localAddress);
        try {
            newAXFR.run();
            List<Record> axfr = newAXFR.getAXFR();
            Message message2 = new Message(message.getHeader().getID());
            message2.getHeader().setFlag(5);
            message2.getHeader().setFlag(0);
            message2.addRecord(message.getQuestion(), 0);
            for (Record addRecord : axfr) {
                message2.addRecord(addRecord, 1);
            }
            return message2;
        } catch (ZoneTransferException e) {
            throw new WireParseException(e.getMessage());
        }
    }
}
