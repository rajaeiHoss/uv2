package org.xbill.DNS;

import java.io.IOException;

public class GPOSRecord extends Record {
    private static final long serialVersionUID = -6349714958085750705L;
    private byte[] altitude;
    private byte[] latitude;
    private byte[] longitude;

    GPOSRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new GPOSRecord();
    }

    private void validate(double d, double d2) throws IllegalArgumentException {
        if (d < -90.0d || d > 90.0d) {
            throw new IllegalArgumentException("illegal longitude " + d);
        } else if (d2 < -180.0d || d2 > 180.0d) {
            throw new IllegalArgumentException("illegal latitude " + d2);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GPOSRecord(Name name, int i, long j, double d, double d2, double d3) {
        super(name, 27, i, j);
        double d4 = d;
        double d5 = d2;
        validate(d, d2);
        this.longitude = Double.toString(d).getBytes();
        this.latitude = Double.toString(d2).getBytes();
        this.altitude = Double.toString(d3).getBytes();
    }

    public GPOSRecord(Name name, int i, long j, String str, String str2, String str3) {
        super(name, 27, i, j);
        try {
            this.longitude = byteArrayFromString(str);
            this.latitude = byteArrayFromString(str2);
            validate(getLongitude(), getLatitude());
            this.altitude = byteArrayFromString(str3);
        } catch (TextParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.longitude = dNSInput.readCountedString();
        this.latitude = dNSInput.readCountedString();
        this.altitude = dNSInput.readCountedString();
        try {
            validate(getLongitude(), getLatitude());
        } catch (IllegalArgumentException e) {
            throw new WireParseException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        try {
            this.longitude = byteArrayFromString(tokenizer.getString());
            this.latitude = byteArrayFromString(tokenizer.getString());
            this.altitude = byteArrayFromString(tokenizer.getString());
            try {
                validate(getLongitude(), getLatitude());
            } catch (IllegalArgumentException e) {
                throw new WireParseException(e.getMessage());
            }
        } catch (TextParseException e2) {
            throw tokenizer.exception(e2.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(byteArrayToString(this.longitude, true));
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.latitude, true));
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.altitude, true));
        return stringBuffer.toString();
    }

    public String getLongitudeString() {
        return byteArrayToString(this.longitude, false);
    }

    public double getLongitude() {
        return Double.parseDouble(getLongitudeString());
    }

    public String getLatitudeString() {
        return byteArrayToString(this.latitude, false);
    }

    public double getLatitude() {
        return Double.parseDouble(getLatitudeString());
    }

    public String getAltitudeString() {
        return byteArrayToString(this.altitude, false);
    }

    public double getAltitude() {
        return Double.parseDouble(getAltitudeString());
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeCountedString(this.longitude);
        dNSOutput.writeCountedString(this.latitude);
        dNSOutput.writeCountedString(this.altitude);
    }
}
