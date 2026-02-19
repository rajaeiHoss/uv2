package org.xbill.DNS;

import java.io.PrintStream;

public class Compression {
    private static final int MAX_POINTER = 16383;
    private static final int TABLE_SIZE = 17;
    private Entry[] table = new Entry[17];
    private boolean verbose = Options.check("verbosecompression");

    private static class Entry {
        Name name;
        Entry next;
        int pos;

        private Entry() {
        }
    }

    public void add(int i, Name name) {
        if (i <= MAX_POINTER) {
            int hashCode = (name.hashCode() & Integer.MAX_VALUE) % 17;
            Entry entry = new Entry();
            entry.name = name;
            entry.pos = i;
            entry.next = this.table[hashCode];
            this.table[hashCode] = entry;
            if (this.verbose) {
                PrintStream printStream = System.err;
                printStream.println("Adding " + name + " at " + i);
            }
        }
    }

    public int get(Name name) {
        int i = -1;
        for (Entry entry = this.table[(name.hashCode() & Integer.MAX_VALUE) % 17]; entry != null; entry = entry.next) {
            if (entry.name.equals(name)) {
                i = entry.pos;
            }
        }
        if (this.verbose) {
            PrintStream printStream = System.err;
            printStream.println("Looking for " + name + ", found " + i);
        }
        return i;
    }
}
