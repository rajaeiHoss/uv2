package org.xbill.DNS;

import java.io.IOException;
import java.util.ArrayList;

public class Generator {
    private long current;
    public final int dclass;
    public long end;
    public final String namePattern;
    public final Name origin;
    public final String rdataPattern;
    public long start;
    public long step;
    public final long ttl;
    public final int type;

    public static boolean supportedType(int i) {
        Type.check(i);
        return i == 12 || i == 5 || i == 39 || i == 1 || i == 28 || i == 2;
    }

    public Generator(long j, long j2, long j3, String str, int i, int i2, long j4, String str2, Name name) {
        long j5 = j;
        long j6 = j2;
        long j7 = j3;
        if (j5 < 0 || j6 < 0 || j5 > j6 || j7 <= 0) {
            throw new IllegalArgumentException("invalid range specification");
        } else if (supportedType(i)) {
            DClass.check(i2);
            this.start = j5;
            this.end = j6;
            this.step = j7;
            this.namePattern = str;
            this.type = i;
            this.dclass = i2;
            this.ttl = j4;
            this.rdataPattern = str2;
            this.origin = name;
            this.current = j5;
        } else {
            throw new IllegalArgumentException("unsupported type");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0092, code lost:
        if (r3 == false) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0094, code lost:
        r11 = -r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0095, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0097, code lost:
        if (r5 != ',') goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0099, code lost:
        r3 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009c, code lost:
        if (r3 >= r0.length) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009e, code lost:
        r5 = (char) (r0[r3] & kotlin.UByte.MAX_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a3, code lost:
        if (r5 == ',') goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a5, code lost:
        if (r5 != '}') goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a8, code lost:
        if (r5 < '0') goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00aa, code lost:
        if (r5 > '9') goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ac, code lost:
        r5 = (char) (r5 - '0');
        r16 = (r16 * 10) + ((long) r5);
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00bd, code lost:
        throw new org.xbill.DNS.TextParseException("invalid width");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00be, code lost:
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00bf, code lost:
        if (r5 != ',') goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c1, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c6, code lost:
        if (r8 == r0.length) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c8, code lost:
        r3 = (char) (r0[r8] & kotlin.UByte.MAX_VALUE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cf, code lost:
        if (r3 != 'o') goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d1, code lost:
        r3 = false;
        r5 = 1;
        r13 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00d8, code lost:
        if (r3 != 'x') goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00da, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00db, code lost:
        r5 = 1;
        r13 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00e1, code lost:
        if (r3 != 'X') goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00e3, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e7, code lost:
        if (r3 != 'd') goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00ef, code lost:
        throw new org.xbill.DNS.TextParseException("invalid base");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f5, code lost:
        throw new org.xbill.DNS.TextParseException("invalid base");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00f6, code lost:
        r3 = false;
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00f8, code lost:
        r8 = r8 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00fa, code lost:
        if (r8 == r0.length) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00fe, code lost:
        if (r0[r8] != 125) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0100, code lost:
        r5 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x010a, code lost:
        throw new org.xbill.DNS.TextParseException("invalid modifiers");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String substitute(java.lang.String r19, long r20) throws java.io.IOException {
        /*
            r18 = this;
            byte[] r0 = r19.getBytes()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r3 = 0
            r4 = 0
        L_0x000b:
            int r5 = r0.length
            if (r3 >= r5) goto L_0x016b
            byte r5 = r0[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            char r5 = (char) r5
            if (r4 == 0) goto L_0x001c
            r1.append(r5)
            r2 = 1
            r4 = 0
            goto L_0x0168
        L_0x001c:
            r7 = 92
            if (r5 != r7) goto L_0x0031
            int r4 = r3 + 1
            int r5 = r0.length
            if (r4 == r5) goto L_0x0029
            r2 = 1
            r4 = 1
            goto L_0x0168
        L_0x0029:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            java.lang.String r1 = "invalid escape character"
            r0.<init>(r1)
            throw r0
        L_0x0031:
            r7 = 36
            if (r5 != r7) goto L_0x0164
            int r8 = r3 + 1
            int r9 = r0.length
            if (r8 >= r9) goto L_0x0049
            byte r9 = r0[r8]
            if (r9 != r7) goto L_0x0049
            byte r3 = r0[r8]
            r3 = r3 & 255(0xff, float:3.57E-43)
            char r3 = (char) r3
            r1.append(r3)
        L_0x0046:
            r3 = r8
            goto L_0x0167
        L_0x0049:
            int r7 = r0.length
            r13 = 10
            r2 = 48
            if (r8 >= r7) goto L_0x010b
            byte r7 = r0[r8]
            r11 = 123(0x7b, float:1.72E-43)
            if (r7 != r11) goto L_0x010b
            int r3 = r8 + 1
            int r7 = r0.length
            if (r3 >= r7) goto L_0x0064
            byte r7 = r0[r3]
            r11 = 45
            if (r7 != r11) goto L_0x0064
            r8 = r3
            r3 = 1
            goto L_0x0065
        L_0x0064:
            r3 = 0
        L_0x0065:
            r11 = 0
        L_0x0067:
            int r7 = r8 + 1
            int r9 = r0.length
            r10 = 57
            r15 = 125(0x7d, float:1.75E-43)
            r6 = 44
            if (r7 >= r9) goto L_0x0092
            byte r5 = r0[r7]
            r5 = r5 & 255(0xff, float:3.57E-43)
            char r5 = (char) r5
            if (r5 == r6) goto L_0x0091
            if (r5 != r15) goto L_0x007c
            goto L_0x0091
        L_0x007c:
            if (r5 < r2) goto L_0x0089
            if (r5 > r10) goto L_0x0089
            int r5 = r5 + -48
            char r5 = (char) r5
            long r11 = r11 * r13
            long r8 = (long) r5
            long r11 = r11 + r8
            r8 = r7
            goto L_0x0067
        L_0x0089:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            java.lang.String r1 = "invalid offset"
            r0.<init>(r1)
            throw r0
        L_0x0091:
            r8 = r7
        L_0x0092:
            if (r3 == 0) goto L_0x0095
            long r11 = -r11
        L_0x0095:
            r16 = 0
            if (r5 != r6) goto L_0x00bf
        L_0x0099:
            int r3 = r8 + 1
            int r7 = r0.length
            if (r3 >= r7) goto L_0x00bf
            byte r5 = r0[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            char r5 = (char) r5
            if (r5 == r6) goto L_0x00be
            if (r5 != r15) goto L_0x00a8
            goto L_0x00be
        L_0x00a8:
            if (r5 < r2) goto L_0x00b6
            if (r5 > r10) goto L_0x00b6
            int r5 = r5 + -48
            char r5 = (char) r5
            long r16 = r16 * r13
            long r7 = (long) r5
            long r16 = r16 + r7
            r8 = r3
            goto L_0x0099
        L_0x00b6:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            java.lang.String r1 = "invalid width"
            r0.<init>(r1)
            throw r0
        L_0x00be:
            r8 = r3
        L_0x00bf:
            if (r5 != r6) goto L_0x00f6
            int r8 = r8 + 1
            int r3 = r0.length
            java.lang.String r5 = "invalid base"
            if (r8 == r3) goto L_0x00f0
            byte r3 = r0[r8]
            r3 = r3 & 255(0xff, float:3.57E-43)
            char r3 = (char) r3
            r6 = 111(0x6f, float:1.56E-43)
            if (r3 != r6) goto L_0x00d6
            r3 = 0
            r5 = 1
            r13 = 8
            goto L_0x00f8
        L_0x00d6:
            r6 = 120(0x78, float:1.68E-43)
            if (r3 != r6) goto L_0x00df
            r3 = 0
        L_0x00db:
            r5 = 1
            r13 = 16
            goto L_0x00f8
        L_0x00df:
            r6 = 88
            if (r3 != r6) goto L_0x00e5
            r3 = 1
            goto L_0x00db
        L_0x00e5:
            r6 = 100
            if (r3 != r6) goto L_0x00ea
            goto L_0x00f6
        L_0x00ea:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            r0.<init>(r5)
            throw r0
        L_0x00f0:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            r0.<init>(r5)
            throw r0
        L_0x00f6:
            r3 = 0
            r5 = 1
        L_0x00f8:
            int r8 = r8 + r5
            int r5 = r0.length
            if (r8 == r5) goto L_0x0103
            byte r5 = r0[r8]
            if (r5 != r15) goto L_0x0103
            r5 = r16
            goto L_0x0111
        L_0x0103:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            java.lang.String r1 = "invalid modifiers"
            r0.<init>(r1)
            throw r0
        L_0x010b:
            r8 = r3
            r3 = 0
            r5 = 0
            r11 = 0
        L_0x0111:
            long r9 = r20 + r11
            r11 = 0
            int r7 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r7 < 0) goto L_0x015c
            r11 = 8
            int r7 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x0124
            java.lang.String r7 = java.lang.Long.toOctalString(r9)
            goto L_0x0133
        L_0x0124:
            r11 = 16
            int r7 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x012f
            java.lang.String r7 = java.lang.Long.toHexString(r9)
            goto L_0x0133
        L_0x012f:
            java.lang.String r7 = java.lang.Long.toString(r9)
        L_0x0133:
            if (r3 == 0) goto L_0x0139
            java.lang.String r7 = r7.toUpperCase()
        L_0x0139:
            r9 = 0
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x0157
            int r3 = r7.length()
            long r9 = (long) r3
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0157
            int r3 = (int) r5
            int r5 = r7.length()
            int r3 = r3 - r5
        L_0x014e:
            int r5 = r3 + -1
            if (r3 <= 0) goto L_0x0157
            r1.append(r2)
            r3 = r5
            goto L_0x014e
        L_0x0157:
            r1.append(r7)
            goto L_0x0046
        L_0x015c:
            org.xbill.DNS.TextParseException r0 = new org.xbill.DNS.TextParseException
            java.lang.String r1 = "invalid offset expansion"
            r0.<init>(r1)
            throw r0
        L_0x0164:
            r1.append(r5)
        L_0x0167:
            r2 = 1
        L_0x0168:
            int r3 = r3 + r2
            goto L_0x000b
        L_0x016b:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Generator.substitute(java.lang.String, long):java.lang.String");
    }

    public Record nextRecord() throws IOException {
        long j = this.current;
        if (j > this.end) {
            return null;
        }
        Name fromString = Name.fromString(substitute(this.namePattern, j), this.origin);
        String substitute = substitute(this.rdataPattern, this.current);
        this.current += this.step;
        return Record.fromString(fromString, this.type, this.dclass, this.ttl, substitute, this.origin);
    }

    public Record[] expand() throws IOException {
        ArrayList arrayList = new ArrayList();
        long j = this.start;
        while (j < this.end) {
            arrayList.add(Record.fromString(Name.fromString(substitute(this.namePattern, this.current), this.origin), this.type, this.dclass, this.ttl, substitute(this.rdataPattern, this.current), this.origin));
            j += this.step;
        }
        return (Record[]) arrayList.toArray(new Record[arrayList.size()]);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("$GENERATE ");
        stringBuffer.append(this.start + "-" + this.end);
        if (this.step > 1) {
            stringBuffer.append("/" + this.step);
        }
        stringBuffer.append(" ");
        stringBuffer.append(this.namePattern + " ");
        stringBuffer.append(this.ttl + " ");
        if (this.dclass != 1 || !Options.check("noPrintIN")) {
            stringBuffer.append(DClass.string(this.dclass) + " ");
        }
        stringBuffer.append(Type.string(this.type) + " ");
        stringBuffer.append(this.rdataPattern + " ");
        return stringBuffer.toString();
    }
}
