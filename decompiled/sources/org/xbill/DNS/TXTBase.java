package org.xbill.DNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xbill.DNS.Tokenizer;

abstract class TXTBase extends Record {
    private static final long serialVersionUID = -4319510507246305931L;
    protected List strings;

    protected TXTBase() {
    }

    protected TXTBase(Name name, int i, int i2, long j) {
        super(name, i, i2, j);
    }

    protected TXTBase(Name name, int i, int i2, long j, List list) {
        super(name, i, i2, j);
        if (list != null) {
            this.strings = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    this.strings.add(byteArrayFromString((String) it.next()));
                } catch (TextParseException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
            return;
        }
        throw new IllegalArgumentException("strings must not be null");
    }

    protected TXTBase(Name name, int i, int i2, long j, String str) {
        this(name, i, i2, j, Collections.singletonList(str));
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.strings = new ArrayList(2);
        while (dNSInput.remaining() > 0) {
            this.strings.add(dNSInput.readCountedString());
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.strings = new ArrayList(2);
        while (true) {
            Tokenizer.Token token = tokenizer.get();
            if (!token.isString()) {
                tokenizer.unget();
                return;
            }
            try {
                this.strings.add(byteArrayFromString(token.value));
            } catch (TextParseException e) {
                throw tokenizer.exception(e.getMessage());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = this.strings.iterator();
        while (it.hasNext()) {
            stringBuffer.append(byteArrayToString((byte[]) it.next(), true));
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public List getStrings() {
        ArrayList arrayList = new ArrayList(this.strings.size());
        for (int i = 0; i < this.strings.size(); i++) {
            arrayList.add(byteArrayToString((byte[]) this.strings.get(i), false));
        }
        return arrayList;
    }

    public List getStringsAsByteArrays() {
        return this.strings;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        for (int i = 0; i < this.strings.size(); i++) {
            dNSOutput.writeCountedString((byte[]) this.strings.get(i));
        }
    }
}
