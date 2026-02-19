package org.jivesoftware.smack.util;

import com.mdvr.BlackBox.GPSInfo;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import kotlin.jvm.internal.ByteCompanionObject;
import org.xbill.DNS.Flags;

public class Base64 {
    public static final int DECODE = 0;
    public static final int DONT_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "UTF-8";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte[] _ORDERED_ALPHABET = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, GPSInfo.GPS_STATUS_VALID, 66, 67, 68, GPSInfo.GPS_DIRECTIONLON_E, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, GPSInfo.GPS_DIRECTIONLAT_S, 84, 85, GPSInfo.GPS_STATUS_INVALID, GPSInfo.GPS_DIRECTIONlON_W, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private static final byte[] _ORDERED_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, Flags.CD, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, EQUALS_SIGN, 62, 63, -9, -9, -9, -9};
    private static final byte[] _STANDARD_ALPHABET = {GPSInfo.GPS_STATUS_VALID, 66, 67, 68, GPSInfo.GPS_DIRECTIONLON_E, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, GPSInfo.GPS_DIRECTIONLAT_S, 84, 85, GPSInfo.GPS_STATUS_INVALID, GPSInfo.GPS_DIRECTIONlON_W, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Flags.CD, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {GPSInfo.GPS_STATUS_VALID, 66, 67, 68, GPSInfo.GPS_DIRECTIONLON_E, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, GPSInfo.GPS_DIRECTIONLAT_S, 84, 85, GPSInfo.GPS_STATUS_INVALID, GPSInfo.GPS_DIRECTIONlON_W, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, EQUALS_SIGN, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Flags.CD, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};

    /* access modifiers changed from: private */
    public static final byte[] getAlphabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    /* access modifiers changed from: private */
    public static final byte[] getDecodabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    public static final void main(String[] strArr) {
        if (strArr.length < 3) {
            usage("Not enough arguments.");
            return;
        }
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        if (str.equals("-e")) {
            encodeFileToFile(str2, str3);
        } else if (str.equals("-d")) {
            decodeFileToFile(str2, str3);
        } else {
            usage("Unknown flag: " + str);
        }
    }

    private static final void usage(String str) {
        System.err.println(str);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, byte[] bArr2, int i, int i2) {
        encode3to4(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] alphabet = getAlphabet(i4);
        int i5 = 0;
        int i6 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        int i7 = i6 | i5;
        if (i2 == 1) {
            bArr2[i3] = alphabet[i7 >>> 18];
            bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
            bArr2[i3 + 2] = EQUALS_SIGN;
            bArr2[i3 + 3] = EQUALS_SIGN;
            return bArr2;
        } else if (i2 == 2) {
            bArr2[i3] = alphabet[i7 >>> 18];
            bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
            bArr2[i3 + 2] = alphabet[(i7 >>> 6) & 63];
            bArr2[i3 + 3] = EQUALS_SIGN;
            return bArr2;
        } else if (i2 != 3) {
            return bArr2;
        } else {
            bArr2[i3] = alphabet[i7 >>> 18];
            bArr2[i3 + 1] = alphabet[(i7 >>> 12) & 63];
            bArr2[i3 + 2] = alphabet[(i7 >>> 6) & 63];
            bArr2[i3 + 3] = alphabet[i7 & 63];
            return bArr2;
        }
    }

    public static String encodeObject(Serializable serializable) {
        return encodeObject(serializable, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:11|60|61|62|63|64|65|66|67|68) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:46|47|48|49|50|51|52|53|54|55|56) */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:17|18|19|20|21|22|23|24|25|26|27|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004d, code lost:
        return new java.lang.String(r2.toByteArray());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x002f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0032 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0035 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0038 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x0070 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0073 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0076 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x007f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:64:0x0082 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:66:0x0085 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeObject(java.io.Serializable r4, int r5) {
        /*
            r0 = r5 & 2
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0065, all -> 0x0060 }
            r2.<init>()     // Catch:{ IOException -> 0x0065, all -> 0x0060 }
            org.jivesoftware.smack.util.Base64$OutputStream r3 = new org.jivesoftware.smack.util.Base64$OutputStream     // Catch:{ IOException -> 0x005b, all -> 0x0057 }
            r5 = r5 | 1
            r3.<init>(r2, r5)     // Catch:{ IOException -> 0x005b, all -> 0x0057 }
            r5 = 2
            if (r0 != r5) goto L_0x0023
            java.util.zip.GZIPOutputStream r5 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x0053, all -> 0x0050 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x0053, all -> 0x0050 }
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.<init>(r5)     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            goto L_0x0029
        L_0x001d:
            r4 = move-exception
            goto L_0x007c
        L_0x0020:
            r4 = move-exception
            r0 = r1
            goto L_0x006a
        L_0x0023:
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0053, all -> 0x0050 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0053, all -> 0x0050 }
            r5 = r1
        L_0x0029:
            r0.writeObject(r4)     // Catch:{ IOException -> 0x004e }
            r0.close()     // Catch:{ Exception -> 0x002f }
        L_0x002f:
            r5.close()     // Catch:{ Exception -> 0x0032 }
        L_0x0032:
            r3.close()     // Catch:{ Exception -> 0x0035 }
        L_0x0035:
            r2.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            byte[] r5 = r2.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            java.lang.String r0 = "UTF-8"
            r4.<init>(r5, r0)     // Catch:{ UnsupportedEncodingException -> 0x0044 }
            return r4
        L_0x0044:
            java.lang.String r4 = new java.lang.String
            byte[] r5 = r2.toByteArray()
            r4.<init>(r5)
            return r4
        L_0x004e:
            r4 = move-exception
            goto L_0x006a
        L_0x0050:
            r4 = move-exception
            r5 = r1
            goto L_0x007c
        L_0x0053:
            r4 = move-exception
            r5 = r1
            r0 = r5
            goto L_0x006a
        L_0x0057:
            r4 = move-exception
            r5 = r1
            r3 = r5
            goto L_0x007c
        L_0x005b:
            r4 = move-exception
            r5 = r1
            r0 = r5
            r3 = r0
            goto L_0x006a
        L_0x0060:
            r4 = move-exception
            r5 = r1
            r2 = r5
            r3 = r2
            goto L_0x007c
        L_0x0065:
            r4 = move-exception
            r5 = r1
            r0 = r5
            r2 = r0
            r3 = r2
        L_0x006a:
            r4.printStackTrace()     // Catch:{ all -> 0x007a }
            r0.close()     // Catch:{ Exception -> 0x0070 }
        L_0x0070:
            r5.close()     // Catch:{ Exception -> 0x0073 }
        L_0x0073:
            r3.close()     // Catch:{ Exception -> 0x0076 }
        L_0x0076:
            r2.close()     // Catch:{ Exception -> 0x0079 }
        L_0x0079:
            return r1
        L_0x007a:
            r4 = move-exception
            r1 = r0
        L_0x007c:
            r1.close()     // Catch:{ Exception -> 0x007f }
        L_0x007f:
            r5.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r3.close()     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            r2.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.encodeObject(java.io.Serializable, int):java.lang.String");
    }

    public static String encodeBytes(byte[] bArr) {
        return encodeBytes(bArr, 0, bArr.length, 0);
    }

    public static String encodeBytes(byte[] bArr, int i) {
        return encodeBytes(bArr, 0, bArr.length, i);
    }

    public static String encodeBytes(byte[] bArr, int i, int i2) {
        return encodeBytes(bArr, i, i2, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: org.jivesoftware.smack.util.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: org.jivesoftware.smack.util.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.util.zip.GZIPOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: org.jivesoftware.smack.util.Base64$OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: org.jivesoftware.smack.util.Base64$OutputStream} */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:10|11|12|13|14|15|16|17|18|19|20) */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:26|27|37|38|39|40|41|42|43|44|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:47|48|49|50|51|52|53|54|55) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0060 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0063 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x006f */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeBytes(byte[] r18, int r19, int r20, int r21) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21 & 8
            r3 = r21 & 2
            java.lang.String r8 = "UTF-8"
            r4 = 1
            r5 = 2
            if (r3 != r5) goto L_0x0073
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0056, all -> 0x0052 }
            r3.<init>()     // Catch:{ IOException -> 0x0056, all -> 0x0052 }
            org.jivesoftware.smack.util.Base64$OutputStream r5 = new org.jivesoftware.smack.util.Base64$OutputStream     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            r4 = r21 | 1
            r5.<init>(r3, r4)     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r9 = r18
            r4.write(r9, r0, r1)     // Catch:{ IOException -> 0x0045 }
            r4.close()     // Catch:{ IOException -> 0x0045 }
            r4.close()     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            r5.close()     // Catch:{ Exception -> 0x002e }
        L_0x002e:
            r3.close()     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x003b }
            byte[] r1 = r3.toByteArray()     // Catch:{ UnsupportedEncodingException -> 0x003b }
            r0.<init>(r1, r8)     // Catch:{ UnsupportedEncodingException -> 0x003b }
            return r0
        L_0x003b:
            java.lang.String r0 = new java.lang.String
            byte[] r1 = r3.toByteArray()
            r0.<init>(r1)
            return r0
        L_0x0045:
            r0 = move-exception
            goto L_0x005a
        L_0x0047:
            r0 = move-exception
            goto L_0x0069
        L_0x0049:
            r0 = move-exception
            r4 = r2
            goto L_0x005a
        L_0x004c:
            r0 = move-exception
            r5 = r2
            goto L_0x0069
        L_0x004f:
            r0 = move-exception
            r4 = r2
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r3 = r2
            r5 = r3
            goto L_0x0069
        L_0x0056:
            r0 = move-exception
            r3 = r2
            r4 = r3
        L_0x0059:
            r5 = r4
        L_0x005a:
            r0.printStackTrace()     // Catch:{ all -> 0x0067 }
            r4.close()     // Catch:{ Exception -> 0x0060 }
        L_0x0060:
            r5.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r3.close()     // Catch:{ Exception -> 0x0066 }
        L_0x0066:
            return r2
        L_0x0067:
            r0 = move-exception
            r2 = r4
        L_0x0069:
            r2.close()     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r5.close()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            r3.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0072:
            throw r0
        L_0x0073:
            r9 = r18
            if (r2 != 0) goto L_0x0079
            r11 = 1
            goto L_0x007a
        L_0x0079:
            r11 = 0
        L_0x007a:
            int r2 = r1 * 4
            int r2 = r2 / 3
            int r3 = r1 % 3
            r12 = 4
            if (r3 <= 0) goto L_0x0085
            r3 = 4
            goto L_0x0086
        L_0x0085:
            r3 = 0
        L_0x0086:
            int r3 = r3 + r2
            r13 = 76
            if (r11 == 0) goto L_0x008d
            int r2 = r2 / r13
            goto L_0x008e
        L_0x008d:
            r2 = 0
        L_0x008e:
            int r3 = r3 + r2
            byte[] r14 = new byte[r3]
            int r15 = r1 + -2
            r7 = 0
            r16 = 0
            r17 = 0
        L_0x0098:
            if (r7 >= r15) goto L_0x00c0
            int r3 = r7 + r0
            r4 = 3
            r2 = r18
            r5 = r14
            r6 = r16
            r10 = r7
            r7 = r21
            encode3to4(r2, r3, r4, r5, r6, r7)
            int r2 = r17 + 4
            if (r11 == 0) goto L_0x00b9
            if (r2 != r13) goto L_0x00b9
            int r2 = r16 + 4
            r3 = 10
            r14[r2] = r3
            int r16 = r16 + 1
            r17 = 0
            goto L_0x00bb
        L_0x00b9:
            r17 = r2
        L_0x00bb:
            int r7 = r10 + 3
            int r16 = r16 + 4
            goto L_0x0098
        L_0x00c0:
            r10 = r7
            if (r10 >= r1) goto L_0x00d5
            int r2 = r10 + r0
            int r3 = r1 - r10
            r0 = r18
            r1 = r2
            r2 = r3
            r3 = r14
            r4 = r16
            r5 = r21
            encode3to4(r0, r1, r2, r3, r4, r5)
            int r16 = r16 + 4
        L_0x00d5:
            r0 = r16
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x00de }
            r2 = 0
            r1.<init>(r14, r2, r0, r8)     // Catch:{ UnsupportedEncodingException -> 0x00df }
            return r1
        L_0x00de:
            r2 = 0
        L_0x00df:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r14, r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.encodeBytes(byte[], int, int, int):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static int decode4to3(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte[] decodabet = getDecodabet(i3);
        int i4 = i + 2;
        if (bArr[i4] == 61) {
            bArr2[i2] = (byte) ((((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18)) >>> 16);
            return 1;
        }
        int i5 = i + 3;
        if (bArr[i5] == 61) {
            int i6 = (decodabet[bArr[i + 1]] & 255) << 12;
            int i7 = ((decodabet[bArr[i4]] & 255) << 6) | i6 | ((decodabet[bArr[i]] & 255) << 18);
            bArr2[i2] = (byte) (i7 >>> 16);
            bArr2[i2 + 1] = (byte) (i7 >>> 8);
            return 2;
        }
        try {
            int b = ((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i4]] & 255) << 6) | (decodabet[bArr[i5]] & 255);
            bArr2[i2] = (byte) (b >> 16);
            bArr2[i2 + 1] = (byte) (b >> 8);
            bArr2[i2 + 2] = (byte) b;
            return 3;
        } catch (Exception unused) {
            PrintStream printStream = System.out;
            printStream.println("" + bArr[i] + ": " + decodabet[bArr[i]]);
            PrintStream printStream2 = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i8 = i + 1;
            sb.append(bArr[i8]);
            sb.append(": ");
            sb.append(decodabet[bArr[i8]]);
            printStream2.println(sb.toString());
            PrintStream printStream3 = System.out;
            printStream3.println("" + bArr[i4] + ": " + decodabet[bArr[i4]]);
            PrintStream printStream4 = System.out;
            printStream4.println("" + bArr[i5] + ": " + decodabet[bArr[i5]]);
            return -1;
        }
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        byte[] decodabet = getDecodabet(i3);
        byte[] bArr2 = new byte[((i2 * 3) / 4)];
        byte[] bArr3 = new byte[4];
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i + i2) {
            byte b = (byte) (bArr[i4] & ByteCompanionObject.MAX_VALUE);
            byte b2 = decodabet[b];
            if (b2 >= -5) {
                if (b2 >= -1) {
                    int i7 = i5 + 1;
                    bArr3[i5] = b;
                    if (i7 > 3) {
                        i6 += decode4to3(bArr3, 0, bArr2, i6, i3);
                        if (b == 61) {
                            break;
                        }
                        i5 = 0;
                    } else {
                        i5 = i7;
                    }
                }
                i4++;
            } else {
                PrintStream printStream = System.err;
                printStream.println("Bad Base64 input character at " + i4 + ": " + bArr[i4] + "(decimal)");
                return null;
            }
        }
        byte[] bArr4 = new byte[i6];
        System.arraycopy(bArr2, 0, bArr4, 0, i6);
        return bArr4;
    }

    public static byte[] decode(String str) {
        return decode(str, 0);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|(8:14|15|16|17|(3:18|19|(1:21)(1:57))|22|23|24)|25|26|(2:27|28)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:29|36|43|44|45|46|47|48|49) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x004f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0052 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x006c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x006f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(java.lang.String r5, int r6) {
        /*
            java.lang.String r0 = "UTF-8"
            byte[] r5 = r5.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x0007 }
            goto L_0x000b
        L_0x0007:
            byte[] r5 = r5.getBytes()
        L_0x000b:
            int r0 = r5.length
            r1 = 0
            byte[] r5 = decode(r5, r1, r0, r6)
            if (r5 == 0) goto L_0x0079
            int r6 = r5.length
            r0 = 4
            if (r6 < r0) goto L_0x0079
            byte r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r0 = 1
            byte r0 = r5[r0]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto L_0x0079
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0073, all -> 0x0066 }
            r2.<init>()     // Catch:{ IOException -> 0x0073, all -> 0x0066 }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0062, all -> 0x005d }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0062, all -> 0x005d }
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x005b, all -> 0x0058 }
        L_0x003e:
            int r0 = r4.read(r6)     // Catch:{ IOException -> 0x0064, all -> 0x0056 }
            if (r0 < 0) goto L_0x0048
            r2.write(r6, r1, r0)     // Catch:{ IOException -> 0x0064, all -> 0x0056 }
            goto L_0x003e
        L_0x0048:
            byte[] r5 = r2.toByteArray()     // Catch:{ IOException -> 0x0064, all -> 0x0056 }
            r2.close()     // Catch:{ Exception -> 0x004f }
        L_0x004f:
            r4.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            r3.close()     // Catch:{ Exception -> 0x0079 }
            goto L_0x0079
        L_0x0056:
            r5 = move-exception
            goto L_0x0060
        L_0x0058:
            r5 = move-exception
            r4 = r0
            goto L_0x0060
        L_0x005b:
            r4 = r0
            goto L_0x0064
        L_0x005d:
            r5 = move-exception
            r3 = r0
            r4 = r3
        L_0x0060:
            r0 = r2
            goto L_0x0069
        L_0x0062:
            r3 = r0
            r4 = r3
        L_0x0064:
            r0 = r2
            goto L_0x0075
        L_0x0066:
            r5 = move-exception
            r3 = r0
            r4 = r3
        L_0x0069:
            r0.close()     // Catch:{ Exception -> 0x006c }
        L_0x006c:
            r4.close()     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            r3.close()     // Catch:{ Exception -> 0x0072 }
        L_0x0072:
            throw r5
        L_0x0073:
            r3 = r0
            r4 = r3
        L_0x0075:
            r0.close()     // Catch:{ Exception -> 0x004f }
            goto L_0x004f
        L_0x0079:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.decode(java.lang.String, int):byte[]");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: java.io.ObjectInputStream} */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.io.ObjectInputStream] */
    /* JADX WARNING: type inference failed for: r4v9 */
    /* JADX WARNING: type inference failed for: r4v14 */
    /* JADX WARNING: type inference failed for: r4v17 */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|(3:1|2|(4:3|4|5|6))|7|8|9|10|28) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0041 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0016 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object decodeToObject(java.lang.String r4) {
        /*
            byte[] r4 = decode(r4)
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0035, ClassNotFoundException -> 0x002e, all -> 0x0029 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0035, ClassNotFoundException -> 0x002e, all -> 0x0029 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0026, ClassNotFoundException -> 0x0023, all -> 0x001e }
            r4.<init>(r1)     // Catch:{ IOException -> 0x0026, ClassNotFoundException -> 0x0023, all -> 0x001e }
            java.lang.Object r0 = r4.readObject()     // Catch:{ IOException -> 0x001c, ClassNotFoundException -> 0x001a }
        L_0x0013:
            r1.close()     // Catch:{ Exception -> 0x0016 }
        L_0x0016:
            r4.close()     // Catch:{ Exception -> 0x003c }
            goto L_0x003c
        L_0x001a:
            r2 = move-exception
            goto L_0x0031
        L_0x001c:
            r2 = move-exception
            goto L_0x0038
        L_0x001e:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L_0x003e
        L_0x0023:
            r2 = move-exception
            r4 = r0
            goto L_0x0031
        L_0x0026:
            r2 = move-exception
            r4 = r0
            goto L_0x0038
        L_0x0029:
            r4 = move-exception
            r1 = r0
            r0 = r4
            r4 = r1
            goto L_0x003e
        L_0x002e:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x0031:
            r2.printStackTrace()     // Catch:{ all -> 0x003d }
            goto L_0x0013
        L_0x0035:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L_0x0038:
            r2.printStackTrace()     // Catch:{ all -> 0x003d }
            goto L_0x0013
        L_0x003c:
            return r0
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            r1.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            r4.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.decodeToObject(java.lang.String):java.lang.Object");
    }

    public static boolean encodeToFile(byte[] bArr, String str) {
        try (OutputStream outputStream = new OutputStream(new FileOutputStream(str), 1)) {
            outputStream.write(bArr);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean decodeToFile(java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            org.jivesoftware.smack.util.Base64$OutputStream r2 = new org.jivesoftware.smack.util.Base64$OutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r3.<init>(r5)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            r2.<init>(r3, r0)     // Catch:{ IOException -> 0x0024, all -> 0x001f }
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r2.write(r4)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
            r0 = 1
            r2.close()     // Catch:{ Exception -> 0x0027 }
            goto L_0x0027
        L_0x001a:
            r4 = move-exception
            r1 = r2
            goto L_0x0020
        L_0x001d:
            r1 = r2
            goto L_0x0024
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            throw r4
        L_0x0024:
            r1.close()     // Catch:{ Exception -> 0x0027 }
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.decodeToFile(java.lang.String, java.lang.String):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.jivesoftware.smack.util.Base64$InputStream} */
    /* JADX WARNING: type inference failed for: r0v0, types: [byte[], org.jivesoftware.smack.util.Base64$InputStream] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decodeFromFile(java.lang.String r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0067 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0067 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0067 }
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0034
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ IOException -> 0x0067 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0067 }
            r3.<init>()     // Catch:{ IOException -> 0x0067 }
            java.lang.String r4 = "File is too big for this convenience method ("
            r3.append(r4)     // Catch:{ IOException -> 0x0067 }
            long r4 = r1.length()     // Catch:{ IOException -> 0x0067 }
            r3.append(r4)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r1 = " bytes)."
            r3.append(r1)     // Catch:{ IOException -> 0x0067 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x0067 }
            r2.println(r1)     // Catch:{ IOException -> 0x0067 }
            r0.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            return r0
        L_0x0034:
            long r2 = r1.length()     // Catch:{ IOException -> 0x0067 }
            int r3 = (int) r2     // Catch:{ IOException -> 0x0067 }
            byte[] r2 = new byte[r3]     // Catch:{ IOException -> 0x0067 }
            org.jivesoftware.smack.util.Base64$InputStream r3 = new org.jivesoftware.smack.util.Base64$InputStream     // Catch:{ IOException -> 0x0067 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0067 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0067 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0067 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0067 }
            r1 = 0
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x0067 }
            r4 = 0
        L_0x004c:
            r5 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.read(r2, r4, r5)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            if (r5 < 0) goto L_0x0056
            int r4 = r4 + r5
            goto L_0x004c
        L_0x0056:
            byte[] r0 = new byte[r4]     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            java.lang.System.arraycopy(r2, r1, r0, r1, r4)     // Catch:{ IOException -> 0x0062, all -> 0x005f }
            r3.close()     // Catch:{ Exception -> 0x0082 }
            goto L_0x0082
        L_0x005f:
            r7 = move-exception
            r0 = r3
            goto L_0x0083
        L_0x0062:
            r1 = r0
            r0 = r3
            goto L_0x0068
        L_0x0065:
            r7 = move-exception
            goto L_0x0083
        L_0x0067:
            r1 = r0
        L_0x0068:
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ all -> 0x0065 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
            r3.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = "Error decoding from file "
            r3.append(r4)     // Catch:{ all -> 0x0065 }
            r3.append(r7)     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x0065 }
            r2.println(r7)     // Catch:{ all -> 0x0065 }
            r0.close()     // Catch:{ Exception -> 0x0081 }
        L_0x0081:
            r0 = r1
        L_0x0082:
            return r0
        L_0x0083:
            r0.close()     // Catch:{ Exception -> 0x0086 }
        L_0x0086:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.decodeFromFile(java.lang.String):byte[]");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:17|18|(3:19|20|29)) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        java.lang.System.err.println("Error encoding from file " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0046 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeFromFile(java.lang.String r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r1.<init>(r7)     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            double r2 = (double) r2     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r4 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            double r2 = r2 * r4
            int r2 = (int) r2     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r3 = 40
            int r2 = java.lang.Math.max(r2, r3)     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            org.jivesoftware.smack.util.Base64$InputStream r3 = new org.jivesoftware.smack.util.Base64$InputStream     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r1 = 1
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x0045, all -> 0x0043 }
            r1 = 0
            r4 = 0
        L_0x002d:
            r5 = 4096(0x1000, float:5.74E-42)
            int r5 = r3.read(r2, r4, r5)     // Catch:{ IOException -> 0x0046 }
            if (r5 < 0) goto L_0x0037
            int r4 = r4 + r5
            goto L_0x002d
        L_0x0037:
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x0046 }
            java.lang.String r6 = "UTF-8"
            r5.<init>(r2, r1, r4, r6)     // Catch:{ IOException -> 0x0046 }
            r3.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0041:
            r0 = r5
            goto L_0x005f
        L_0x0043:
            r7 = move-exception
            goto L_0x0062
        L_0x0045:
            r3 = r0
        L_0x0046:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r2.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "Error encoding from file "
            r2.append(r4)     // Catch:{ all -> 0x0060 }
            r2.append(r7)     // Catch:{ all -> 0x0060 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0060 }
            r1.println(r7)     // Catch:{ all -> 0x0060 }
            r3.close()     // Catch:{ Exception -> 0x005f }
        L_0x005f:
            return r0
        L_0x0060:
            r7 = move-exception
            r0 = r3
        L_0x0062:
            r0.close()     // Catch:{ Exception -> 0x0065 }
        L_0x0065:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.encodeFromFile(java.lang.String):java.lang.String");
    }

    public static void encodeFileToFile(String str, String str2) {
        String encodeFromFile = encodeFromFile(str);
        if (encodeFromFile == null) {
            return;
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2))) {
            bufferedOutputStream.write(encodeFromFile.getBytes("US-ASCII"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeFileToFile(String str, String str2) {
        byte[] decodeFromFile = decodeFromFile(str);
        if (decodeFromFile == null) {
            return;
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2))) {
            bufferedOutputStream.write(decodeFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class InputStream extends FilterInputStream {
        private byte[] alphabet;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public InputStream(java.io.InputStream inputStream) {
            this(inputStream, 0);
        }

        public InputStream(java.io.InputStream inputStream, int i) {
            super(inputStream);
            boolean z = true;
            this.breakLines = (i & 8) != 8;
            z = (i & 1) != 1 ? false : z;
            this.encode = z;
            int i2 = z ? 4 : 3;
            this.bufferLength = i2;
            this.buffer = new byte[i2];
            this.position = -1;
            this.lineLength = 0;
            this.options = i;
            this.alphabet = Base64.getAlphabet(i);
            this.decodabet = Base64.getDecodabet(i);
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[LOOP:1: B:19:0x003b->B:25:0x0051, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0057 A[EDGE_INSN: B:58:0x0057->B:26:0x0057 ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read() throws java.io.IOException {
            /*
                r10 = this;
                int r0 = r10.position
                r1 = -1
                r2 = 0
                if (r0 >= 0) goto L_0x0071
                boolean r0 = r10.encode
                r3 = 4
                if (r0 == 0) goto L_0x0038
                r0 = 3
                byte[] r4 = new byte[r0]
                r5 = 0
                r6 = 0
            L_0x0010:
                if (r5 >= r0) goto L_0x0027
                java.io.InputStream r7 = r10.in     // Catch:{ IOException -> 0x0020 }
                int r7 = r7.read()     // Catch:{ IOException -> 0x0020 }
                if (r7 < 0) goto L_0x0023
                byte r7 = (byte) r7     // Catch:{ IOException -> 0x0020 }
                r4[r5] = r7     // Catch:{ IOException -> 0x0020 }
                int r6 = r6 + 1
                goto L_0x0023
            L_0x0020:
                r7 = move-exception
                if (r5 == 0) goto L_0x0026
            L_0x0023:
                int r5 = r5 + 1
                goto L_0x0010
            L_0x0026:
                throw r7
            L_0x0027:
                if (r6 <= 0) goto L_0x0037
                r5 = 0
                byte[] r7 = r10.buffer
                r8 = 0
                int r9 = r10.options
                byte[] unused = org.jivesoftware.smack.util.Base64.encode3to4(r4, r5, r6, r7, r8, r9)
                r10.position = r2
                r10.numSigBytes = r3
                goto L_0x0071
            L_0x0037:
                return r1
            L_0x0038:
                byte[] r0 = new byte[r3]
                r4 = 0
            L_0x003b:
                if (r4 >= r3) goto L_0x0057
            L_0x003d:
                java.io.InputStream r5 = r10.in
                int r5 = r5.read()
                if (r5 < 0) goto L_0x004e
                byte[] r6 = r10.decodabet
                r7 = r5 & 127(0x7f, float:1.78E-43)
                byte r6 = r6[r7]
                r7 = -5
                if (r6 <= r7) goto L_0x003d
            L_0x004e:
                if (r5 >= 0) goto L_0x0051
                goto L_0x0057
            L_0x0051:
                byte r5 = (byte) r5
                r0[r4] = r5
                int r4 = r4 + 1
                goto L_0x003b
            L_0x0057:
                if (r4 != r3) goto L_0x0066
                byte[] r3 = r10.buffer
                int r4 = r10.options
                int r0 = org.jivesoftware.smack.util.Base64.decode4to3(r0, r2, r3, r2, r4)
                r10.numSigBytes = r0
                r10.position = r2
                goto L_0x0071
            L_0x0066:
                if (r4 != 0) goto L_0x0069
                return r1
            L_0x0069:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Improperly padded Base64 input."
                r0.<init>(r1)
                throw r0
            L_0x0071:
                int r0 = r10.position
                if (r0 < 0) goto L_0x00a4
                int r3 = r10.numSigBytes
                if (r0 < r3) goto L_0x007a
                return r1
            L_0x007a:
                boolean r3 = r10.encode
                if (r3 == 0) goto L_0x008d
                boolean r3 = r10.breakLines
                if (r3 == 0) goto L_0x008d
                int r3 = r10.lineLength
                r4 = 76
                if (r3 < r4) goto L_0x008d
                r10.lineLength = r2
                r0 = 10
                return r0
            L_0x008d:
                int r2 = r10.lineLength
                int r2 = r2 + 1
                r10.lineLength = r2
                byte[] r2 = r10.buffer
                int r3 = r0 + 1
                r10.position = r3
                byte r0 = r2[r0]
                int r2 = r10.bufferLength
                if (r3 < r2) goto L_0x00a1
                r10.position = r1
            L_0x00a1:
                r0 = r0 & 255(0xff, float:3.57E-43)
                return r0
            L_0x00a4:
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Error in Base64 code reading stream."
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Base64.InputStream.read():int");
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                }
            }
            return i3;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        private byte[] alphabet;
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i) {
            super(outputStream);
            boolean z = true;
            this.breakLines = (i & 8) != 8;
            z = (i & 1) != 1 ? false : z;
            this.encode = z;
            int i2 = z ? 3 : 4;
            this.bufferLength = i2;
            this.buffer = new byte[i2];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = i;
            this.alphabet = Base64.getAlphabet(i);
            this.decodabet = Base64.getDecodabet(i);
        }

        public void write(int i) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(i);
            } else if (this.encode) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) i;
                if (i3 >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    int i4 = this.lineLength + 4;
                    this.lineLength = i4;
                    if (this.breakLines && i4 >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else {
                byte[] bArr2 = this.decodabet;
                int i5 = i & 127;
                if (bArr2[i5] > -5) {
                    byte[] bArr3 = this.buffer;
                    int i6 = this.position;
                    int i7 = i6 + 1;
                    this.position = i7;
                    bArr3[i6] = (byte) i;
                    if (i7 >= this.bufferLength) {
                        this.out.write(this.b4, 0, Base64.decode4to3(bArr3, 0, this.b4, 0, this.options));
                        this.position = 0;
                    }
                } else if (bArr2[i5] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }
}
