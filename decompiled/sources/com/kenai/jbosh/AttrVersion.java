package com.kenai.jbosh;

final class AttrVersion extends AbstractAttr<String> implements Comparable {
    private static final AttrVersion DEFAULT;
    private final int major;
    private final int minor;

    static {
        try {
            DEFAULT = createFromString("1.8");
        } catch (BOSHException e) {
            throw new IllegalStateException(e);
        }
    }

    private AttrVersion(String str) throws BOSHException {
        super(str);
        int indexOf = str.indexOf(46);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            try {
                int parseInt = Integer.parseInt(substring);
                this.major = parseInt;
                if (parseInt >= 0) {
                    String substring2 = str.substring(indexOf + 1);
                    try {
                        int parseInt2 = Integer.parseInt(substring2);
                        this.minor = parseInt2;
                        if (parseInt2 < 0) {
                            throw new BOSHException("Minor version may not be < 0");
                        }
                    } catch (NumberFormatException e) {
                        throw new BOSHException("Could not parse ver attribute value (minor ver): " + substring2, e);
                    }
                } else {
                    throw new BOSHException("Major version may not be < 0");
                }
            } catch (NumberFormatException e2) {
                throw new BOSHException("Could not parse ver attribute value (major ver): " + substring, e2);
            }
        } else {
            throw new BOSHException("Illegal ver attribute value (not in major.minor form): " + str);
        }
    }

    static AttrVersion getSupportedVersion() {
        return DEFAULT;
    }

    static AttrVersion createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrVersion(str);
    }

    /* access modifiers changed from: package-private */
    public int getMajor() {
        return this.major;
    }

    /* access modifiers changed from: package-private */
    public int getMinor() {
        return this.minor;
    }

    public int compareTo(Object obj) {
        if (obj instanceof AttrVersion) {
            AttrVersion attrVersion = (AttrVersion) obj;
            int i = this.major;
            int i2 = attrVersion.major;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = this.minor;
            int i4 = attrVersion.minor;
            if (i3 < i4) {
                return -1;
            }
            if (i3 > i4) {
                return 1;
            }
        }
        return 0;
    }
}
