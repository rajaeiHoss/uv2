package org.xbill.DNS;

import java.util.HashMap;

class Mnemonic {
    static final int CASE_LOWER = 3;
    static final int CASE_SENSITIVE = 1;
    static final int CASE_UPPER = 2;
    private static Integer[] cachedInts = new Integer[64];
    private String description;
    private int max = Integer.MAX_VALUE;
    private boolean numericok;
    private String prefix;
    private HashMap strings = new HashMap();
    private HashMap values = new HashMap();
    private int wordcase;

    static {
        for (int i = 0; i < cachedInts.length; i++) {
            cachedInts[i] = new Integer(i);
        }
    }

    public Mnemonic(String str, int i) {
        this.description = str;
        this.wordcase = i;
    }

    public void setMaximum(int i) {
        this.max = i;
    }

    public void setPrefix(String str) {
        this.prefix = sanitize(str);
    }

    public void setNumericAllowed(boolean z) {
        this.numericok = z;
    }

    public static Integer toInteger(int i) {
        if (i >= 0) {
            Integer[] numArr = cachedInts;
            if (i < numArr.length) {
                return numArr[i];
            }
        }
        return new Integer(i);
    }

    public void check(int i) {
        if (i < 0 || i > this.max) {
            throw new IllegalArgumentException(this.description + " " + i + "is out of range");
        }
    }

    private String sanitize(String str) {
        int i = this.wordcase;
        if (i == 2) {
            return str.toUpperCase();
        }
        return i == 3 ? str.toLowerCase() : str;
    }

    private int parseNumeric(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0 || parseInt > this.max) {
                return -1;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public void add(int i, String str) {
        check(i);
        Integer integer = toInteger(i);
        String sanitize = sanitize(str);
        this.strings.put(sanitize, integer);
        this.values.put(integer, sanitize);
    }

    public void addAlias(int i, String str) {
        check(i);
        Integer integer = toInteger(i);
        this.strings.put(sanitize(str), integer);
    }

    public void addAll(Mnemonic mnemonic) {
        if (this.wordcase == mnemonic.wordcase) {
            this.strings.putAll(mnemonic.strings);
            this.values.putAll(mnemonic.values);
            return;
        }
        throw new IllegalArgumentException(mnemonic.description + ": wordcases do not match");
    }

    public String getText(int i) {
        check(i);
        String str = (String) this.values.get(toInteger(i));
        if (str != null) {
            return str;
        }
        String num = Integer.toString(i);
        if (this.prefix == null) {
            return num;
        }
        return this.prefix + num;
    }

    public int getValue(String str) {
        int parseNumeric;
        String sanitize = sanitize(str);
        Integer num = (Integer) this.strings.get(sanitize);
        if (num != null) {
            return num.intValue();
        }
        String str2 = this.prefix;
        if (str2 != null && sanitize.startsWith(str2) && (parseNumeric = parseNumeric(sanitize.substring(this.prefix.length()))) >= 0) {
            return parseNumeric;
        }
        if (this.numericok) {
            return parseNumeric(sanitize);
        }
        return -1;
    }
}
