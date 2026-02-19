package org.jivesoftware.smack.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import kotlin.UByte;
import kotlin.text.Typography;

public class StringUtils {
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] APOS_ENCODE = "&apos;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static MessageDigest digest = null;
    private static char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Random randGen = new Random();

    public static String parseName(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("@");
        if (lastIndexOf <= 0) {
            return "";
        }
        return str.substring(0, lastIndexOf);
    }

    public static String parseServer(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("@");
        int i = lastIndexOf + 1;
        if (i > str.length()) {
            return "";
        }
        int indexOf = str.indexOf("/");
        if (indexOf <= 0 || indexOf <= lastIndexOf) {
            return str.substring(i);
        }
        return str.substring(i, indexOf);
    }

    public static String parseResource(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("/");
        int i = indexOf + 1;
        return (i > str.length() || indexOf < 0) ? "" : str.substring(i);
    }

    public static String parseBareAddress(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("/");
        if (indexOf < 0) {
            return str;
        }
        if (indexOf == 0) {
            return "";
        }
        return str.substring(0, indexOf);
    }

    public static String escapeNode(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length() + 8);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                sb.append("\\22");
            } else if (charAt == '/') {
                sb.append("\\2f");
            } else if (charAt == ':') {
                sb.append("\\3a");
            } else if (charAt == '<') {
                sb.append("\\3c");
            } else if (charAt == '>') {
                sb.append("\\3e");
            } else if (charAt == '@') {
                sb.append("\\40");
            } else if (charAt == '\\') {
                sb.append("\\5c");
            } else if (charAt == '&') {
                sb.append("\\26");
            } else if (charAt == '\'') {
                sb.append("\\27");
            } else if (Character.isWhitespace(charAt)) {
                sb.append("\\20");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static String unescapeNode(String str) {
        int i;
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        int i2 = 0;
        int length = charArray.length;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '\\' && (i = i2 + 2) < length) {
                char c = charArray[i2 + 1];
                char c2 = charArray[i];
                if (c == '2') {
                    if (c2 == '0') {
                        sb.append(' ');
                    } else if (c2 == '2') {
                        sb.append(Typography.quote);
                    } else if (c2 == 'f') {
                        sb.append('/');
                    } else if (c2 == '6') {
                        sb.append(Typography.amp);
                    } else if (c2 == '7') {
                        sb.append('\'');
                    }
                } else if (c == '3') {
                    if (c2 == 'a') {
                        sb.append(':');
                    } else if (c2 == 'c') {
                        sb.append(Typography.less);
                    } else if (c2 == 'e') {
                        sb.append(Typography.greater);
                    }
                } else if (c == '4') {
                    if (c2 == '0') {
                        sb.append("@");
                    }
                } else if (c == '5' && c2 == 'c') {
                    sb.append("\\");
                }
                i2 = i;
                i2++;
            }
            sb.append(charAt);
            i2++;
        }
        return sb.toString();
    }

    public static String escapeForXML(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        StringBuilder sb = new StringBuilder((int) (((double) length) * 1.3d));
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char c = charArray[i];
            if (c <= '>') {
                if (c == '<') {
                    if (i > i2) {
                        sb.append(charArray, i2, i - i2);
                    }
                    i2 = i + 1;
                    sb.append(LT_ENCODE);
                } else if (c == '>') {
                    if (i > i2) {
                        sb.append(charArray, i2, i - i2);
                    }
                    i2 = i + 1;
                    sb.append(GT_ENCODE);
                } else if (c == '&') {
                    if (i > i2) {
                        sb.append(charArray, i2, i - i2);
                    }
                    int i3 = i + 5;
                    if (length <= i3 || charArray[i + 1] != '#' || !Character.isDigit(charArray[i + 2]) || !Character.isDigit(charArray[i + 3]) || !Character.isDigit(charArray[i + 4]) || charArray[i3] != ';') {
                        i2 = i + 1;
                        sb.append(AMP_ENCODE);
                    }
                } else if (c == '\"') {
                    if (i > i2) {
                        sb.append(charArray, i2, i - i2);
                    }
                    i2 = i + 1;
                    sb.append(QUOTE_ENCODE);
                } else if (c == '\'') {
                    if (i > i2) {
                        sb.append(charArray, i2, i - i2);
                    }
                    i2 = i + 1;
                    sb.append(APOS_ENCODE);
                }
            }
            i++;
        }
        if (i2 == 0) {
            return str;
        }
        if (i > i2) {
            sb.append(charArray, i2, i - i2);
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String hash(java.lang.String r3) {
        /*
            java.lang.Class<org.jivesoftware.smack.util.StringUtils> r0 = org.jivesoftware.smack.util.StringUtils.class
            monitor-enter(r0)
            java.security.MessageDigest r1 = digest     // Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x0017
            java.lang.String r1 = "SHA-1"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0010 }
            digest = r1     // Catch:{ NoSuchAlgorithmException -> 0x0010 }
            goto L_0x0017
        L_0x0010:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0035 }
            java.lang.String r2 = "Failed to load the SHA-1 MessageDigest. Jive will be unable to function normally."
            r1.println(r2)     // Catch:{ all -> 0x0035 }
        L_0x0017:
            java.security.MessageDigest r1 = digest     // Catch:{ UnsupportedEncodingException -> 0x0023 }
            java.lang.String r2 = "UTF-8"
            byte[] r3 = r3.getBytes(r2)     // Catch:{ UnsupportedEncodingException -> 0x0023 }
            r1.update(r3)     // Catch:{ UnsupportedEncodingException -> 0x0023 }
            goto L_0x0029
        L_0x0023:
            r3 = move-exception
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0035 }
            r1.println(r3)     // Catch:{ all -> 0x0035 }
        L_0x0029:
            java.security.MessageDigest r3 = digest     // Catch:{ all -> 0x0035 }
            byte[] r3 = r3.digest()     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = encodeHex(r3)     // Catch:{ all -> 0x0035 }
            monitor-exit(r0)
            return r3
        L_0x0035:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.StringUtils.hash(java.lang.String):java.lang.String");
    }

    public static String encodeHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int b2 = b & UByte.MAX_VALUE;
            if (b2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(b2, 16));
        }
        return sb.toString();
    }

    public static String encodeBase64(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bArr = null;
        }
        return encodeBase64(bArr);
    }

    public static String encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, 0, bArr.length, z);
    }

    public static String encodeBase64(byte[] bArr, int i, int i2, boolean z) {
        return Base64.encodeBytes(bArr, i, i2, z ? 0 : 8);
    }

    public static byte[] decodeBase64(String str) {
        return Base64.decode(str);
    }

    public static String randomString(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(cArr);
    }

    private StringUtils() {
    }
}
