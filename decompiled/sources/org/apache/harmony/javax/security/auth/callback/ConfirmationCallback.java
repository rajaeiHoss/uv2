package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;

public class ConfirmationCallback implements Callback, Serializable {
    public static final int CANCEL = 2;
    public static final int ERROR = 2;
    public static final int INFORMATION = 0;
    public static final int NO = 1;
    public static final int OK = 3;
    public static final int OK_CANCEL_OPTION = 2;
    public static final int UNSPECIFIED_OPTION = -1;
    public static final int WARNING = 1;
    public static final int YES = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int YES_NO_OPTION = 0;
    private static final long serialVersionUID = -9095656433782481624L;
    private int defaultOption;
    private int messageType;
    private int optionType = -1;
    private String[] options;
    private String prompt;
    private int selection;

    public ConfirmationCallback(int i, int i2, int i3) {
        if (i > 2 || i < 0) {
            throw new IllegalArgumentException("auth.16");
        }
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalArgumentException("auth.18");
                } else if (!(i3 == 3 || i3 == 2)) {
                    throw new IllegalArgumentException("auth.17");
                }
            } else if (!(i3 == 0 || i3 == 1 || i3 == 2)) {
                throw new IllegalArgumentException("auth.17");
            }
        } else if (!(i3 == 0 || i3 == 1)) {
            throw new IllegalArgumentException("auth.17");
        }
        this.messageType = i;
        this.optionType = i2;
        this.defaultOption = i3;
    }

    public ConfirmationCallback(int i, String[] strArr, int i2) {
        if (i > 2 || i < 0) {
            throw new IllegalArgumentException("auth.16");
        } else if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("auth.1A");
        } else {
            for (int i3 = 0; i3 < strArr.length; i3++) {
                if (strArr[i3] == null || strArr[i3].length() == 0) {
                    throw new IllegalArgumentException("auth.1A");
                }
            }
            if (i2 < 0 || i2 >= strArr.length) {
                throw new IllegalArgumentException("auth.17");
            }
            this.options = strArr;
            this.defaultOption = i2;
            this.messageType = i;
        }
    }

    public ConfirmationCallback(String str, int i, int i2, int i3) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        } else if (i > 2 || i < 0) {
            throw new IllegalArgumentException("auth.16");
        } else {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalArgumentException("auth.18");
                    } else if (!(i3 == 3 || i3 == 2)) {
                        throw new IllegalArgumentException("auth.17");
                    }
                } else if (!(i3 == 0 || i3 == 1 || i3 == 2)) {
                    throw new IllegalArgumentException("auth.17");
                }
            } else if (!(i3 == 0 || i3 == 1)) {
                throw new IllegalArgumentException("auth.17");
            }
            this.prompt = str;
            this.messageType = i;
            this.optionType = i2;
            this.defaultOption = i3;
        }
    }

    public ConfirmationCallback(String str, int i, String[] strArr, int i2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        } else if (i > 2 || i < 0) {
            throw new IllegalArgumentException("auth.16");
        } else if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("auth.1A");
        } else {
            for (int i3 = 0; i3 < strArr.length; i3++) {
                if (strArr[i3] == null || strArr[i3].length() == 0) {
                    throw new IllegalArgumentException("auth.1A");
                }
            }
            if (i2 < 0 || i2 >= strArr.length) {
                throw new IllegalArgumentException("auth.17");
            }
            this.options = strArr;
            this.defaultOption = i2;
            this.messageType = i;
            this.prompt = str;
        }
    }

    public String getPrompt() {
        return this.prompt;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getDefaultOption() {
        return this.defaultOption;
    }

    public String[] getOptions() {
        return this.options;
    }

    public int getOptionType() {
        return this.optionType;
    }

    public int getSelectedIndex() {
        return this.selection;
    }

    public void setSelectedIndex(int i) {
        String[] strArr = this.options;
        if (strArr == null) {
            int i2 = this.optionType;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (!(i2 != 2 || i == 3 || i == 2)) {
                        throw new IllegalArgumentException("auth.19");
                    }
                } else if (!(i == 0 || i == 1 || i == 2)) {
                    throw new IllegalArgumentException("auth.19");
                }
            } else if (!(i == 0 || i == 1)) {
                throw new IllegalArgumentException("auth.19");
            }
            this.selection = i;
        } else if (i < 0 || i > strArr.length) {
            throw new ArrayIndexOutOfBoundsException("auth.1B");
        } else {
            this.selection = i;
        }
    }
}
