package com.kenai.jbosh;

final class AttrAccept extends AbstractAttr<String> {
    private final String[] encodings;

    private AttrAccept(String str) {
        super(str);
        this.encodings = str.split("[\\s,]+");
    }

    static AttrAccept createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrAccept(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isAccepted(String str) {
        for (String equalsIgnoreCase : this.encodings) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
