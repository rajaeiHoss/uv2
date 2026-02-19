package org.xbill.DNS;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.xbill.DNS.Tokenizer;

public class LOCRecord extends Record {
    private static final long serialVersionUID = 9058224788126750409L;
    private static NumberFormat w2;
    private static NumberFormat w3;
    private long altitude;
    private long hPrecision;
    private long latitude;
    private long longitude;
    private long size;
    private long vPrecision;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        w2 = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(2);
        DecimalFormat decimalFormat2 = new DecimalFormat();
        w3 = decimalFormat2;
        decimalFormat2.setMinimumIntegerDigits(3);
    }

    LOCRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new LOCRecord();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LOCRecord(Name name, int i, long j, double d, double d2, double d3, double d4, double d5, double d6) {
        super(name, 29, i, j);
        this.latitude = (long) ((d * 3600.0d * 1000.0d) + 2.147483648E9d);
        this.longitude = (long) ((3600.0d * d2 * 1000.0d) + 2.147483648E9d);
        this.altitude = (long) ((d3 + 100000.0d) * 100.0d);
        this.size = (long) (d4 * 100.0d);
        this.hPrecision = (long) (d5 * 100.0d);
        this.vPrecision = (long) (d6 * 100.0d);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        if (dNSInput.readU8() == 0) {
            this.size = parseLOCformat(dNSInput.readU8());
            this.hPrecision = parseLOCformat(dNSInput.readU8());
            this.vPrecision = parseLOCformat(dNSInput.readU8());
            this.latitude = dNSInput.readU32();
            this.longitude = dNSInput.readU32();
            this.altitude = dNSInput.readU32();
            return;
        }
        throw new WireParseException("Invalid LOC version");
    }

    private double parseFixedPoint(String str) {
        if (str.matches("^-?\\d+$")) {
            return (double) Integer.parseInt(str);
        }
        if (str.matches("^-?\\d+\\.\\d*$")) {
            String[] split = str.split("\\.");
            double parseInt = (double) Integer.parseInt(split[0]);
            double parseInt2 = (double) Integer.parseInt(split[1]);
            if (parseInt < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                parseInt2 *= -1.0d;
            }
            return parseInt + (parseInt2 / Math.pow(10.0d, (double) split[1].length()));
        }
        throw new NumberFormatException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long parsePosition(org.xbill.DNS.Tokenizer r18, java.lang.String r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r18
            r1 = r19
            java.lang.String r2 = "latitude"
            boolean r2 = r1.equals(r2)
            int r3 = r18.getUInt16()
            java.lang.String r4 = "Invalid LOC "
            r5 = 180(0xb4, float:2.52E-43)
            if (r3 > r5) goto L_0x00ed
            r5 = 90
            if (r3 <= r5) goto L_0x001a
            if (r2 != 0) goto L_0x00ed
        L_0x001a:
            java.lang.String r5 = r18.getString()
            r6 = 0
            int r9 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x007c }
            if (r9 < 0) goto L_0x0061
            r10 = 59
            if (r9 > r10) goto L_0x0061
            java.lang.String r5 = r18.getString()     // Catch:{ NumberFormatException -> 0x005e }
            r10 = r17
            double r11 = r10.parseFixedPoint(r5)     // Catch:{ NumberFormatException -> 0x007f }
            int r13 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r13 < 0) goto L_0x0043
            r6 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r13 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r13 >= 0) goto L_0x0043
            java.lang.String r5 = r18.getString()     // Catch:{ NumberFormatException -> 0x005c }
            goto L_0x0080
        L_0x0043:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x005c }
            r6.<init>()     // Catch:{ NumberFormatException -> 0x005c }
            r6.append(r4)     // Catch:{ NumberFormatException -> 0x005c }
            r6.append(r1)     // Catch:{ NumberFormatException -> 0x005c }
            java.lang.String r7 = " seconds"
            r6.append(r7)     // Catch:{ NumberFormatException -> 0x005c }
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x005c }
            org.xbill.DNS.TextParseException r6 = r0.exception(r6)     // Catch:{ NumberFormatException -> 0x005c }
            throw r6     // Catch:{ NumberFormatException -> 0x005c }
        L_0x005c:
            r6 = r11
            goto L_0x007f
        L_0x005e:
            r10 = r17
            goto L_0x007f
        L_0x0061:
            r10 = r17
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x007f }
            r11.<init>()     // Catch:{ NumberFormatException -> 0x007f }
            r11.append(r4)     // Catch:{ NumberFormatException -> 0x007f }
            r11.append(r1)     // Catch:{ NumberFormatException -> 0x007f }
            java.lang.String r12 = " minutes"
            r11.append(r12)     // Catch:{ NumberFormatException -> 0x007f }
            java.lang.String r11 = r11.toString()     // Catch:{ NumberFormatException -> 0x007f }
            org.xbill.DNS.TextParseException r11 = r0.exception(r11)     // Catch:{ NumberFormatException -> 0x007f }
            throw r11     // Catch:{ NumberFormatException -> 0x007f }
        L_0x007c:
            r10 = r17
            r9 = 0
        L_0x007f:
            r11 = r6
        L_0x0080:
            int r6 = r5.length()
            r7 = 1
            if (r6 != r7) goto L_0x00d9
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            long r13 = (long) r9
            long r8 = (long) r3
            r15 = 60
            long r8 = r8 * r15
            long r13 = r13 + r8
            long r13 = r13 * r15
            double r8 = (double) r13
            double r11 = r11 + r8
            double r11 = r11 * r6
            long r6 = (long) r11
            r3 = 0
            char r3 = r5.charAt(r3)
            char r3 = java.lang.Character.toUpperCase(r3)
            if (r2 == 0) goto L_0x00a9
            r5 = 83
            if (r3 == r5) goto L_0x00af
        L_0x00a9:
            if (r2 != 0) goto L_0x00b1
            r5 = 87
            if (r3 != r5) goto L_0x00b1
        L_0x00af:
            long r6 = -r6
            goto L_0x00d2
        L_0x00b1:
            if (r2 == 0) goto L_0x00b7
            r5 = 78
            if (r3 != r5) goto L_0x00be
        L_0x00b7:
            if (r2 != 0) goto L_0x00d2
            r2 = 69
            if (r3 != r2) goto L_0x00be
            goto L_0x00d2
        L_0x00be:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            org.xbill.DNS.TextParseException r0 = r0.exception(r1)
            throw r0
        L_0x00d2:
            r0 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r6 = r6 + r0
            return r6
        L_0x00d9:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            org.xbill.DNS.TextParseException r0 = r0.exception(r1)
            throw r0
        L_0x00ed:
            r10 = r17
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = " degrees"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            org.xbill.DNS.TextParseException r0 = r0.exception(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.LOCRecord.parsePosition(org.xbill.DNS.Tokenizer, java.lang.String):long");
    }

    private long parseDouble(Tokenizer tokenizer, String str, boolean z, long j, long j2, long j3) throws IOException {
        Tokenizer.Token token = tokenizer.get();
        if (!token.isEOL()) {
            String str2 = token.value;
            if (str2.length() > 1 && str2.charAt(str2.length() - 1) == 'm') {
                str2 = str2.substring(0, str2.length() - 1);
            }
            try {
                long parseFixedPoint = (long) (parseFixedPoint(str2) * 100.0d);
                if (parseFixedPoint >= j && parseFixedPoint <= j2) {
                    return parseFixedPoint;
                }
                throw tokenizer.exception("Invalid LOC " + str);
            } catch (NumberFormatException unused) {
                throw tokenizer.exception("Invalid LOC " + str);
            }
        } else if (!z) {
            tokenizer.unget();
            return j3;
        } else {
            throw tokenizer.exception("Invalid LOC " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.latitude = parsePosition(tokenizer, "latitude");
        this.longitude = parsePosition(tokenizer, "longitude");
        this.altitude = parseDouble(tokenizer, "altitude", true, -10000000, 4284967295L, 0) + 10000000;
        Tokenizer tokenizer2 = tokenizer;
        this.size = parseDouble(tokenizer2, "size", false, 0, 9000000000L, 100);
        this.hPrecision = parseDouble(tokenizer2, "horizontal precision", false, 0, 9000000000L, 1000000);
        this.vPrecision = parseDouble(tokenizer2, "vertical precision", false, 0, 9000000000L, 1000);
    }

    private void renderFixedPoint(StringBuffer stringBuffer, NumberFormat numberFormat, long j, long j2) {
        stringBuffer.append(j / j2);
        long j3 = j % j2;
        if (j3 != 0) {
            stringBuffer.append(".");
            stringBuffer.append(numberFormat.format(j3));
        }
    }

    private String positionToString(long j, char c, char c2) {
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = j - 2147483648L;
        if (j2 < 0) {
            j2 = -j2;
            c = c2;
        }
        stringBuffer.append(j2 / 3600000);
        long j3 = j2 % 3600000;
        stringBuffer.append(" ");
        stringBuffer.append(j3 / 60000);
        stringBuffer.append(" ");
        StringBuffer stringBuffer2 = stringBuffer;
        renderFixedPoint(stringBuffer2, w3, j3 % 60000, 1000);
        stringBuffer.append(" ");
        stringBuffer.append(c);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(positionToString(this.latitude, 'N', 'S'));
        stringBuffer.append(" ");
        stringBuffer.append(positionToString(this.longitude, 'E', 'W'));
        stringBuffer.append(" ");
        StringBuffer stringBuffer2 = stringBuffer;
        renderFixedPoint(stringBuffer2, w2, this.altitude - 10000000, 100);
        stringBuffer.append("m ");
        renderFixedPoint(stringBuffer2, w2, this.size, 100);
        stringBuffer.append("m ");
        renderFixedPoint(stringBuffer2, w2, this.hPrecision, 100);
        stringBuffer.append("m ");
        renderFixedPoint(stringBuffer2, w2, this.vPrecision, 100);
        stringBuffer.append("m");
        return stringBuffer.toString();
    }

    public double getLatitude() {
        return ((double) (this.latitude - 2147483648L)) / 3600000.0d;
    }

    public double getLongitude() {
        return ((double) (this.longitude - 2147483648L)) / 3600000.0d;
    }

    public double getAltitude() {
        return ((double) (this.altitude - 10000000)) / 100.0d;
    }

    public double getSize() {
        return ((double) this.size) / 100.0d;
    }

    public double getHPrecision() {
        return ((double) this.hPrecision) / 100.0d;
    }

    public double getVPrecision() {
        return ((double) this.vPrecision) / 100.0d;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU8(0);
        dNSOutput.writeU8(toLOCformat(this.size));
        dNSOutput.writeU8(toLOCformat(this.hPrecision));
        dNSOutput.writeU8(toLOCformat(this.vPrecision));
        dNSOutput.writeU32(this.latitude);
        dNSOutput.writeU32(this.longitude);
        dNSOutput.writeU32(this.altitude);
    }

    private static long parseLOCformat(int i) throws WireParseException {
        long j = (long) (i >> 4);
        int i2 = i & 15;
        if (j > 9 || i2 > 9) {
            throw new WireParseException("Invalid LOC Encoding");
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return j;
            }
            j *= 10;
            i2 = i3;
        }
    }

    private int toLOCformat(long j) {
        byte b = 0;
        while (j > 9) {
            b = (byte) (b + 1);
            j /= 10;
        }
        return (int) ((j << 4) + ((long) b));
    }
}
