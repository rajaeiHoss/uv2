package org.jivesoftware.smackx.workgroup.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class ModelUtil {
    private ModelUtil() {
    }

    public static final boolean areEqual(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static final boolean areBooleansEqual(Boolean bool, Boolean bool2) {
        return (bool == Boolean.TRUE && bool2 == Boolean.TRUE) || !(bool == Boolean.TRUE || bool2 == Boolean.TRUE);
    }

    public static final boolean areDifferent(Object obj, Object obj2) {
        return !areEqual(obj, obj2);
    }

    public static final boolean areBooleansDifferent(Boolean bool, Boolean bool2) {
        return !areBooleansEqual(bool, bool2);
    }

    public static final boolean hasNonNullElement(Object[] objArr) {
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final String concat(String[] strArr) {
        return concat(strArr, " ");
    }

    public static final String concat(String[] strArr, String str) {
        if (strArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            if (str2 != null) {
                sb.append(str2);
                sb.append(str);
            }
        }
        int length = sb.length();
        if (length > 0) {
            sb.setLength(length - 1);
        }
        return sb.toString();
    }

    public static final boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    public static final String nullifyIfEmpty(String str) {
        if (hasLength(str)) {
            return str;
        }
        return null;
    }

    public static final String nullifyingToString(Object obj) {
        if (obj != null) {
            return nullifyIfEmpty(obj.toString());
        }
        return null;
    }

    public static boolean hasStringChanged(String str, String str2) {
        if (str == null && str2 == null) {
            return false;
        }
        if ((str != null || str2 == null) && (str == null || str2 != null)) {
            return !str.equals(str2);
        }
        return true;
    }

    public static String getTimeFromLong(long j) {
        new Date();
        long j2 = j / 86400000;
        long j3 = j % 86400000;
        long j4 = j3 / 3600000;
        long j5 = j3 % 3600000;
        long j6 = j5 / 60000;
        long j7 = j5 % 60000;
        long j8 = j7 / 1000;
        long j9 = j7 % 1000;
        StringBuilder sb = new StringBuilder();
        if (j4 > 0) {
            sb.append(j4 + " " + "h" + ", ");
        }
        if (j6 > 0) {
            sb.append(j6 + " " + "min" + ", ");
        }
        sb.append(j8 + " " + "sec");
        return sb.toString();
    }

    public static List iteratorAsList(Iterator it) {
        ArrayList arrayList = new ArrayList(10);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static Iterator reverseListIterator(ListIterator listIterator) {
        return new ReverseListIterator(listIterator);
    }
}
