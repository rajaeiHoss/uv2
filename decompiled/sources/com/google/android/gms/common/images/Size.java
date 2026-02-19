package com.google.android.gms.common.images;

import org.jivesoftware.smackx.GroupChatInvitation;

public final class Size {
    private final int zzalt;
    private final int zzalu;

    public Size(int i, int i2) {
        this.zzalt = i;
        this.zzalu = i2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str != null) {
            int indexOf = str.indexOf(42);
            if (indexOf < 0) {
                indexOf = str.indexOf(120);
            }
            if (indexOf >= 0) {
                try {
                    return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                    throw zzgm(str);
                }
            } else {
                throw zzgm(str);
            }
        } else {
            throw new IllegalArgumentException("string must not be null");
        }
    }

    private static NumberFormatException zzgm(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 16);
        sb.append("Invalid Size: \"");
        sb.append(str);
        sb.append("\"");
        throw new NumberFormatException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.zzalt == size.zzalt && this.zzalu == size.zzalu;
        }
        return false;
    }

    public final int getHeight() {
        return this.zzalu;
    }

    public final int getWidth() {
        return this.zzalt;
    }

    public final int hashCode() {
        int i = this.zzalu;
        int i2 = this.zzalt;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public final String toString() {
        int i = this.zzalt;
        int i2 = this.zzalu;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append(GroupChatInvitation.ELEMENT_NAME);
        sb.append(i2);
        return sb.toString();
    }
}
