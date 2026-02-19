package org.xbill.DNS;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import kotlin.UByte;

public final class Address {
    public static final int IPv4 = 1;
    public static final int IPv6 = 2;

    private Address() {
    }

    private static byte[] parseV4(String str) {
        byte[] bArr = new byte[4];
        int length = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt < '0' || charAt > '9') {
                if (charAt != '.' || i == 3 || i2 == 0) {
                    return null;
                }
                bArr[i] = (byte) i3;
                i++;
                i2 = 0;
                i3 = 0;
            } else if (i2 == 3) {
                return null;
            } else {
                if (i2 > 0 && i3 == 0) {
                    return null;
                }
                i2++;
                i3 = (i3 * 10) + (charAt - '0');
                if (i3 > 255) {
                    return null;
                }
            }
        }
        if (i != 3 || i2 == 0) {
            return null;
        }
        bArr[i] = (byte) i3;
        return bArr;
    }

    private static byte[] parseV6(String str) {
        int i;
        byte[] byteArray;
        byte[] bArr = new byte[16];
        int i2 = -1;
        String[] split = str.split(":", -1);
        int length = split.length - 1;
        if (split[0].length() != 0) {
            i = 0;
        } else if (length + 0 <= 0 || split[1].length() != 0) {
            return null;
        } else {
            i = 1;
        }
        if (split[length].length() == 0) {
            if (length - i <= 0 || split[length - 1].length() != 0) {
                return null;
            }
            length--;
        }
        if ((length - i) + 1 > 8) {
            return null;
        }
        int i3 = 0;
        while (true) {
            if (i > length) {
                break;
            }
            if (split[i].length() == 0) {
                if (i2 >= 0) {
                    return null;
                }
                i2 = i3;
            } else if (split[i].indexOf(46) < 0) {
                int i4 = 0;
                while (i4 < split[i].length()) {
                    try {
                        if (Character.digit(split[i].charAt(i4), 16) < 0) {
                            return null;
                        }
                        i4++;
                    } catch (NumberFormatException unused) {
                    }
                }
                int parseInt = Integer.parseInt(split[i], 16);
                if (parseInt > 65535) {
                    break;
                } else if (parseInt < 0) {
                    break;
                } else {
                    int i5 = i3 + 1;
                    bArr[i3] = (byte) (parseInt >>> 8);
                    i3 = i5 + 1;
                    bArr[i5] = (byte) (parseInt & 255);
                }
            } else if (i < length || i > 6 || (byteArray = toByteArray(split[i], 1)) == null) {
                return null;
            } else {
                int i6 = 0;
                while (i6 < 4) {
                    bArr[i3] = byteArray[i6];
                    i6++;
                    i3++;
                }
            }
            i++;
        }
        if (i3 < 16 && i2 < 0) {
            return null;
        }
        if (i2 >= 0) {
            int i7 = (16 - i3) + i2;
            System.arraycopy(bArr, i2, bArr, i7, i3 - i2);
            while (i2 < i7) {
                bArr[i2] = 0;
                i2++;
            }
        }
        return bArr;
    }

    public static int[] toArray(String str, int i) {
        byte[] byteArray = toByteArray(str, i);
        if (byteArray == null) {
            return null;
        }
        int[] iArr = new int[byteArray.length];
        for (int i2 = 0; i2 < byteArray.length; i2++) {
            iArr[i2] = byteArray[i2] & UByte.MAX_VALUE;
        }
        return iArr;
    }

    public static int[] toArray(String str) {
        return toArray(str, 1);
    }

    public static byte[] toByteArray(String str, int i) {
        if (i == 1) {
            return parseV4(str);
        }
        if (i == 2) {
            return parseV6(str);
        }
        throw new IllegalArgumentException("unknown address family");
    }

    public static boolean isDottedQuad(String str) {
        return toByteArray(str, 1) != null;
    }

    public static String toDottedQuad(byte[] bArr) {
        return (bArr[0] & UByte.MAX_VALUE) + "." + (bArr[1] & UByte.MAX_VALUE) + "." + (bArr[2] & UByte.MAX_VALUE) + "." + (bArr[3] & UByte.MAX_VALUE);
    }

    public static String toDottedQuad(int[] iArr) {
        return iArr[0] + "." + iArr[1] + "." + iArr[2] + "." + iArr[3];
    }

    private static Record[] lookupHostName(String str) throws UnknownHostException {
        try {
            Record[] run = new Lookup(str).run();
            if (run != null) {
                return run;
            }
            throw new UnknownHostException("unknown host");
        } catch (TextParseException unused) {
            throw new UnknownHostException("invalid name");
        }
    }

    private static InetAddress addrFromRecord(String str, Record record) throws UnknownHostException {
        return InetAddress.getByAddress(str, ((ARecord) record).getAddress().getAddress());
    }

    public static InetAddress getByName(String str) throws UnknownHostException {
        try {
            return getByAddress(str);
        } catch (UnknownHostException unused) {
            return addrFromRecord(str, lookupHostName(str)[0]);
        }
    }

    public static InetAddress[] getAllByName(String str) throws UnknownHostException {
        try {
            return new InetAddress[]{getByAddress(str)};
        } catch (UnknownHostException unused) {
            Record[] lookupHostName = lookupHostName(str);
            InetAddress[] inetAddressArr = new InetAddress[lookupHostName.length];
            for (int i = 0; i < lookupHostName.length; i++) {
                inetAddressArr[i] = addrFromRecord(str, lookupHostName[i]);
            }
            return inetAddressArr;
        }
    }

    public static InetAddress getByAddress(String str) throws UnknownHostException {
        byte[] byteArray = toByteArray(str, 1);
        if (byteArray != null) {
            return InetAddress.getByAddress(byteArray);
        }
        byte[] byteArray2 = toByteArray(str, 2);
        if (byteArray2 != null) {
            return InetAddress.getByAddress(byteArray2);
        }
        throw new UnknownHostException("Invalid address: " + str);
    }

    public static InetAddress getByAddress(String str, int i) throws UnknownHostException {
        if (i == 1 || i == 2) {
            byte[] byteArray = toByteArray(str, i);
            if (byteArray != null) {
                return InetAddress.getByAddress(byteArray);
            }
            throw new UnknownHostException("Invalid address: " + str);
        }
        throw new IllegalArgumentException("unknown address family");
    }

    public static String getHostName(InetAddress inetAddress) throws UnknownHostException {
        Record[] run = new Lookup(ReverseMap.fromAddress(inetAddress), 12).run();
        if (run != null) {
            return ((PTRRecord) run[0]).getTarget().toString();
        }
        throw new UnknownHostException("unknown address");
    }

    public static int familyOf(InetAddress inetAddress) {
        if (inetAddress instanceof Inet4Address) {
            return 1;
        }
        if (inetAddress instanceof Inet6Address) {
            return 2;
        }
        throw new IllegalArgumentException("unknown address family");
    }

    public static int addressLength(int i) {
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 16;
        }
        throw new IllegalArgumentException("unknown address family");
    }
}
