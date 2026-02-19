package org.xbill.DNS.tests;

import java.io.PrintStream;
import java.util.Iterator;
import org.xbill.DNS.Name;
import org.xbill.DNS.Zone;

public class Primary {
    private static void usage() {
        System.out.println("usage: primary [-t] [-a | -i] origin file");
        System.exit(1);
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length < 2) {
            usage();
        }
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (strArr.length - i > 2) {
            if (strArr[0].equals("-t")) {
                z3 = true;
            } else if (strArr[0].equals("-a")) {
                z = true;
            } else if (strArr[0].equals("-i")) {
                z2 = true;
            }
            i++;
        }
        Name fromString = Name.fromString(strArr[i], Name.root);
        String str = strArr[i + 1];
        long currentTimeMillis = System.currentTimeMillis();
        Zone zone = new Zone(fromString, str);
        long currentTimeMillis2 = System.currentTimeMillis();
        if (z) {
            Iterator AXFR = zone.AXFR();
            while (AXFR.hasNext()) {
                System.out.println(AXFR.next());
            }
        } else if (z2) {
            Iterator it = zone.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } else {
            System.out.println(zone);
        }
        if (z3) {
            PrintStream printStream = System.out;
            printStream.println("; Load time: " + (currentTimeMillis2 - currentTimeMillis) + " ms");
        }
    }
}
