package org.xbill.DNS;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.xbill.DNS.TSIG;

public class ZoneTransferIn {
    private static final int AXFR = 6;
    private static final int END = 7;
    private static final int FIRSTDATA = 1;
    private static final int INITIALSOA = 0;
    private static final int IXFR_ADD = 5;
    private static final int IXFR_ADDSOA = 4;
    private static final int IXFR_DEL = 3;
    private static final int IXFR_DELSOA = 2;
    private SocketAddress address;
    private List axfr;
    private TCPClient client;
    private long current_serial;
    private int dclass;
    private long end_serial;
    private Record initialsoa;
    private List ixfr;
    private long ixfr_serial;
    private SocketAddress localAddress;
    private int qtype;
    private int rtype;
    private int state;
    private long timeout = 900000;
    private TSIG tsig;
    private TSIG.StreamVerifier verifier;
    private boolean want_fallback;
    private Name zname;

    public static class Delta {
        public List adds;
        public List deletes;
        public long end;
        public long start;

        private Delta() {
            this.adds = new ArrayList();
            this.deletes = new ArrayList();
        }
    }

    private ZoneTransferIn() {
    }

    private ZoneTransferIn(Name name, int i, long j, boolean z, SocketAddress socketAddress, TSIG tsig2) {
        this.address = socketAddress;
        this.tsig = tsig2;
        if (name.isAbsolute()) {
            this.zname = name;
        } else {
            try {
                this.zname = Name.concatenate(name, Name.root);
            } catch (NameTooLongException unused) {
                throw new IllegalArgumentException("ZoneTransferIn: name too long");
            }
        }
        this.qtype = i;
        this.dclass = 1;
        this.ixfr_serial = j;
        this.want_fallback = z;
        this.state = 0;
    }

    public static ZoneTransferIn newAXFR(Name name, SocketAddress socketAddress, TSIG tsig2) {
        return new ZoneTransferIn(name, 252, 0, false, socketAddress, tsig2);
    }

    public static ZoneTransferIn newAXFR(Name name, String str, int i, TSIG tsig2) throws UnknownHostException {
        if (i == 0) {
            i = 53;
        }
        return newAXFR(name, (SocketAddress) new InetSocketAddress(str, i), tsig2);
    }

    public static ZoneTransferIn newAXFR(Name name, String str, TSIG tsig2) throws UnknownHostException {
        return newAXFR(name, str, 0, tsig2);
    }

    public static ZoneTransferIn newIXFR(Name name, long j, boolean z, SocketAddress socketAddress, TSIG tsig2) {
        return new ZoneTransferIn(name, Type.IXFR, j, z, socketAddress, tsig2);
    }

    public static ZoneTransferIn newIXFR(Name name, long j, boolean z, String str, int i, TSIG tsig2) throws UnknownHostException {
        if (i == 0) {
            i = 53;
        }
        return newIXFR(name, j, z, (SocketAddress) new InetSocketAddress(str, i), tsig2);
    }

    public static ZoneTransferIn newIXFR(Name name, long j, boolean z, String str, TSIG tsig2) throws UnknownHostException {
        return newIXFR(name, j, z, str, 0, tsig2);
    }

    public Name getName() {
        return this.zname;
    }

    public int getType() {
        return this.qtype;
    }

    public void setTimeout(int i) {
        if (i >= 0) {
            this.timeout = ((long) i) * 1000;
            return;
        }
        throw new IllegalArgumentException("timeout cannot be negative");
    }

    public void setDClass(int i) {
        DClass.check(i);
        this.dclass = i;
    }

    public void setLocalAddress(SocketAddress socketAddress) {
        this.localAddress = socketAddress;
    }

    private void openConnection() throws IOException {
        TCPClient tCPClient = new TCPClient(System.currentTimeMillis() + this.timeout);
        this.client = tCPClient;
        SocketAddress socketAddress = this.localAddress;
        if (socketAddress != null) {
            tCPClient.bind(socketAddress);
        }
        this.client.connect(this.address);
    }

    private void sendQuery() throws IOException {
        Record newRecord = Record.newRecord(this.zname, this.qtype, this.dclass);
        Message message = new Message();
        message.getHeader().setOpcode(0);
        message.addRecord(newRecord, 0);
        if (this.qtype == 251) {
            message.addRecord(new SOARecord(this.zname, this.dclass, 0, Name.root, Name.root, this.ixfr_serial, 0, 0, 0, 0), 2);
        }
        TSIG tsig2 = this.tsig;
        if (tsig2 != null) {
            tsig2.apply(message, (TSIGRecord) null);
            this.verifier = new TSIG.StreamVerifier(this.tsig, message.getTSIG());
        }
        this.client.send(message.toWire(65535));
    }

    private long getSOASerial(Record record) {
        return ((SOARecord) record).getSerial();
    }

    private void logxfr(String str) {
        if (Options.check("verbose")) {
            PrintStream printStream = System.out;
            printStream.println(this.zname + ": " + str);
        }
    }

    private void fail(String str) throws ZoneTransferException {
        throw new ZoneTransferException(str);
    }

    private void fallback() throws ZoneTransferException {
        if (!this.want_fallback) {
            fail("server doesn't support IXFR");
        }
        logxfr("falling back to AXFR");
        this.qtype = 252;
        this.state = 0;
    }

    private void parseRR(Record record) throws ZoneTransferException {
        int type = record.getType();
        switch (this.state) {
            case 0:
                if (type != 6) {
                    fail("missing initial SOA");
                }
                this.initialsoa = record;
                long sOASerial = getSOASerial(record);
                this.end_serial = sOASerial;
                if (this.qtype != 251 || Serial.compare(sOASerial, this.ixfr_serial) > 0) {
                    this.state = 1;
                    return;
                }
                logxfr("up to date");
                this.state = 7;
                return;
            case 1:
                if (this.qtype == 251 && type == 6 && getSOASerial(record) == this.ixfr_serial) {
                    this.rtype = Type.IXFR;
                    this.ixfr = new ArrayList();
                    logxfr("got incremental response");
                    this.state = 2;
                } else {
                    this.rtype = 252;
                    ArrayList arrayList = new ArrayList();
                    this.axfr = arrayList;
                    arrayList.add(this.initialsoa);
                    logxfr("got nonincremental response");
                    this.state = 6;
                }
                parseRR(record);
                return;
            case 2:
                Delta delta = new Delta();
                this.ixfr.add(delta);
                delta.start = getSOASerial(record);
                delta.deletes.add(record);
                this.state = 3;
                return;
            case 3:
                if (type == 6) {
                    this.current_serial = getSOASerial(record);
                    this.state = 4;
                    parseRR(record);
                    return;
                }
                List list = this.ixfr;
                ((Delta) list.get(list.size() - 1)).deletes.add(record);
                return;
            case 4:
                List list2 = this.ixfr;
                Delta delta2 = (Delta) list2.get(list2.size() - 1);
                delta2.end = getSOASerial(record);
                delta2.adds.add(record);
                this.state = 5;
                return;
            case 5:
                if (type == 6) {
                    long sOASerial2 = getSOASerial(record);
                    if (sOASerial2 == this.end_serial) {
                        this.state = 7;
                        return;
                    } else if (sOASerial2 != this.current_serial) {
                        fail("IXFR out of sync: expected serial " + this.current_serial + " , got " + sOASerial2);
                    } else {
                        this.state = 2;
                        parseRR(record);
                        return;
                    }
                }
                List list3 = this.ixfr;
                ((Delta) list3.get(list3.size() - 1)).adds.add(record);
                return;
            case 6:
                if (type != 1 || record.getDClass() == this.dclass) {
                    this.axfr.add(record);
                    if (type == 6) {
                        this.state = 7;
                        return;
                    }
                    return;
                }
                return;
            case 7:
                fail("extra data");
                return;
            default:
                fail("invalid state");
                return;
        }
    }

    private void closeConnection() {
        try {
            TCPClient tCPClient = this.client;
            if (tCPClient != null) {
                tCPClient.cleanup();
            }
        } catch (IOException unused) {
        }
    }

    private Message parseMessage(byte[] bArr) throws WireParseException {
        try {
            return new Message(bArr);
        } catch (IOException e) {
            if (e instanceof WireParseException) {
                throw ((WireParseException) e);
            }
            throw new WireParseException("Error parsing message");
        }
    }

    private void doxfr() throws IOException, ZoneTransferException {
        sendQuery();
        while (this.state != 7) {
            byte[] recv = this.client.recv();
            Message parseMessage = parseMessage(recv);
            if (parseMessage.getHeader().getRcode() == 0 && this.verifier != null) {
                parseMessage.getTSIG();
                if (this.verifier.verify(parseMessage, recv) != 0) {
                    fail("TSIG failure");
                }
            }
            Record[] sectionArray = parseMessage.getSectionArray(1);
            if (this.state == 0) {
                int rcode = parseMessage.getRcode();
                if (rcode != 0) {
                    if (this.qtype == 251 && rcode == 4) {
                        fallback();
                        doxfr();
                        return;
                    }
                    fail(Rcode.string(rcode));
                }
                Record question = parseMessage.getQuestion();
                if (!(question == null || question.getType() == this.qtype)) {
                    fail("invalid question section");
                }
                if (sectionArray.length == 0 && this.qtype == 251) {
                    fallback();
                    doxfr();
                    return;
                }
            }
            for (Record parseRR : sectionArray) {
                parseRR(parseRR);
            }
            if (this.state == 7 && this.verifier != null && !parseMessage.isVerified()) {
                fail("last message must be signed");
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public List run() throws IOException, ZoneTransferException {
        try {
            openConnection();
            doxfr();
            closeConnection();
            List list = this.axfr;
            if (list != null) {
                return list;
            }
            return this.ixfr;
        } catch (Throwable th) {
            closeConnection();
            throw th;
        }
    }

    public boolean isAXFR() {
        return this.rtype == 252;
    }

    public List getAXFR() {
        return this.axfr;
    }

    public boolean isIXFR() {
        return this.rtype == 251;
    }

    public List getIXFR() {
        return this.ixfr;
    }

    public boolean isCurrent() {
        return this.axfr == null && this.ixfr == null;
    }
}
