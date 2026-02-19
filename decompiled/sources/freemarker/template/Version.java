package freemarker.template;

import freemarker.template.utility.StringUtil;
import java.io.Serializable;
import java.util.Date;

public final class Version implements Serializable {
    private final Date buildDate;
    private final String extraInfo;
    private final Boolean gaeCompliant;
    private Integer hashCode;
    private int intValue;
    private final int major;
    private final int micro;
    private final int minor;
    private String stringValue;

    public static int intValueFor(int i, int i2, int i3) {
        return (i * 1000000) + (i2 * 1000) + i3;
    }

    public Version(String str) {
        this(str, (Boolean) null, (Date) null);
    }

    public Version(String str, Boolean bool, Date date) {
        String str2;
        char charAt;
        String trim = str.trim();
        int[] iArr = new int[3];
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (true) {
            if (i >= trim.length()) {
                str2 = null;
                break;
            }
            char charAt2 = trim.charAt(i);
            if (charAt2 < '0' || charAt2 > '9') {
                if (charAt2 != '.') {
                    str2 = trim.substring(i);
                    break;
                } else if (i2 == 2) {
                    str2 = trim.substring(i);
                    break;
                } else {
                    i2++;
                }
            } else {
                iArr[i2] = (iArr[i2] * 10) + (charAt2 - '0');
                z = true;
            }
            i++;
        }
        if (z) {
            if (str2 != null && ((charAt = str2.charAt(0)) == '.' || charAt == '-' || charAt == '_')) {
                str2 = str2.substring(1);
            }
            this.extraInfo = str2;
            this.major = iArr[0];
            this.minor = iArr[1];
            this.micro = iArr[2];
            calculateIntValue();
            this.stringValue = trim;
            this.gaeCompliant = bool;
            this.buildDate = date;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A version number string ");
        stringBuffer.append(StringUtil.jQuote(trim));
        stringBuffer.append(" must start with a number.");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public Version(int i, int i2, int i3) {
        this(i, i2, i3, (String) null, (Boolean) null, (Date) null);
    }

    public Version(int i, int i2, int i3, String str, Boolean bool, Date date) {
        this.major = i;
        this.minor = i2;
        this.micro = i3;
        this.extraInfo = str;
        this.gaeCompliant = bool;
        this.buildDate = date;
        calculateIntValue();
    }

    private void calculateIntValue() {
        this.intValue = intValueFor(this.major, this.minor, this.micro);
    }

    public synchronized String toString() {
        if (this.stringValue == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.major);
            stringBuffer.append(".");
            stringBuffer.append(this.minor);
            stringBuffer.append(".");
            stringBuffer.append(this.micro);
            this.stringValue = stringBuffer.toString();
            if (this.extraInfo != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(this.stringValue);
                stringBuffer2.append("-");
                stringBuffer2.append(this.extraInfo);
                this.stringValue = stringBuffer2.toString();
            }
        }
        return this.stringValue;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getMicro() {
        return this.micro;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public Boolean isGAECompliant() {
        return this.gaeCompliant;
    }

    public Date getBuildDate() {
        return this.buildDate;
    }

    public int intValue() {
        return this.intValue;
    }

    public int hashCode() {
        if (this.hashCode == null) {
            Date date = this.buildDate;
            int i = 0;
            int hashCode2 = ((date == null ? 0 : date.hashCode()) + 31) * 31;
            String str = this.extraInfo;
            int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Boolean bool = this.gaeCompliant;
            int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
            Integer num = this.hashCode;
            int hashCode5 = (((hashCode4 + (num == null ? 0 : num.hashCode())) * 31) + this.intValue) * 31;
            String str2 = this.stringValue;
            if (str2 != null) {
                i = str2.hashCode();
            }
            this.hashCode = new Integer(hashCode5 + i);
        }
        return this.hashCode.intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Version version = (Version) obj;
        if (this.intValue != version.intValue || version.hashCode() != hashCode()) {
            return false;
        }
        Date date = this.buildDate;
        if (date == null) {
            if (version.buildDate != null) {
                return false;
            }
        } else if (!date.equals(version.buildDate)) {
            return false;
        }
        String str = this.extraInfo;
        if (str == null) {
            if (version.extraInfo != null) {
                return false;
            }
        } else if (!str.equals(version.extraInfo)) {
            return false;
        }
        Boolean bool = this.gaeCompliant;
        if (bool == null) {
            if (version.gaeCompliant != null) {
                return false;
            }
        } else if (!bool.equals(version.gaeCompliant)) {
            return false;
        }
        return true;
    }
}
