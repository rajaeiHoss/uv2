package org.xbill.DNS.tests;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.TSIG;
import org.xbill.DNS.ZoneTransferIn;

public class Xfrin {
    private static void usage(String str) {
        PrintStream printStream = System.out;
        printStream.println("Error: " + str);
        System.out.println("usage: xfrin [-i serial] [-k keyname/secret] [-s server] [-p port] [-f] zone");
        System.exit(1);
    }

    public static void main(String[] strArr) throws Exception {
        String str;
        ZoneTransferIn zoneTransferIn;
        String str2 = null;
        int i = -1;
        TSIG tsig = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 53;
        while (i2 < strArr.length) {
            if (!strArr[i2].equals("-i")) {
                if (!strArr[i2].equals("-k")) {
                    if (!strArr[i2].equals("-s")) {
                        if (!strArr[i2].equals("-p")) {
                            if (!strArr[i2].equals("-f")) {
                                if (!strArr[i2].startsWith("-")) {
                                    break;
                                }
                                usage("invalid option");
                            } else {
                                z = true;
                            }
                        } else {
                            i2++;
                            int parseInt = Integer.parseInt(strArr[i2]);
                            if (parseInt < 0 || parseInt > 65535) {
                                usage("invalid port");
                            }
                            i3 = parseInt;
                        }
                    } else {
                        i2++;
                        str2 = strArr[i2];
                    }
                } else {
                    i2++;
                    String str3 = strArr[i2];
                    int indexOf = str3.indexOf(47);
                    if (indexOf < 0) {
                        usage("invalid key");
                    }
                    tsig = new TSIG(str3.substring(0, indexOf), str3.substring(indexOf + 1));
                }
            } else {
                i2++;
                i = Integer.parseInt(strArr[i2]);
                if (i < 0) {
                    usage("invalid serial number");
                }
            }
            i2++;
        }
        if (i2 >= strArr.length) {
            usage("no zone name specified");
        }
        Name fromString = Name.fromString(strArr[i2]);
        if (str2 == null) {
            Lookup lookup = new Lookup(fromString, 2);
            Record[] run = lookup.run();
            if (run == null) {
                System.out.println("failed to look up NS record: " + lookup.getErrorString());
                System.exit(1);
            }
            String rdataToString = run[0].rdataToString();
            System.out.println("sending to server '" + rdataToString + "'");
            str = rdataToString;
        } else {
            str = str2;
        }
        if (i >= 0) {
            zoneTransferIn = ZoneTransferIn.newIXFR(fromString, (long) i, z, str, i3, tsig);
        } else {
            zoneTransferIn = ZoneTransferIn.newAXFR(fromString, str, i3, tsig);
        }
        List<Object> run2 = zoneTransferIn.run();
        if (zoneTransferIn.isAXFR()) {
            if (i >= 0) {
                System.out.println("AXFR-like IXFR response");
            } else {
                System.out.println("AXFR response");
            }
            for (Object println : run2) {
                System.out.println(println);
            }
        } else if (zoneTransferIn.isIXFR()) {
            System.out.println("IXFR response");
            Iterator it = run2.iterator();
            while (it.hasNext()) {
                ZoneTransferIn.Delta delta = (ZoneTransferIn.Delta) it.next();
                System.out.println("delta from " + delta.start + " to " + delta.end);
                System.out.println("deletes");
                for (Object println2 : delta.deletes) {
                    System.out.println(println2);
                }
                System.out.println("adds");
                for (Object println3 : delta.adds) {
                    System.out.println(println3);
                }
            }
        } else if (zoneTransferIn.isCurrent()) {
            System.out.println("up to date");
        }
    }
}
