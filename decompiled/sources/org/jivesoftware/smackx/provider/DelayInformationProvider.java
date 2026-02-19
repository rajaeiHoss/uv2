package org.jivesoftware.smackx.provider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.packet.DelayInformation;

public class DelayInformationProvider implements PacketExtensionProvider {
    private static final SimpleDateFormat XEP_0082_UTC_FORMAT_WITHOUT_MILLIS;
    private static final SimpleDateFormat XEP_0091_UTC_FALLBACK_FORMAT;
    private static Map<String, DateFormat> formats;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMd'T'HH:mm:ss");
        XEP_0091_UTC_FALLBACK_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        XEP_0082_UTC_FORMAT_WITHOUT_MILLIS = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        HashMap hashMap = new HashMap();
        formats = hashMap;
        hashMap.put("^\\d+T\\d+:\\d+:\\d+$", DelayInformation.XEP_0091_UTC_FORMAT);
        formats.put("^\\d+-\\d+-\\d+T\\d+:\\d+:\\d+\\.\\d+Z$", Packet.XEP_0082_UTC_FORMAT);
        formats.put("^\\d+-\\d+-\\d+T\\d+:\\d+:\\d+Z$", simpleDateFormat2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: java.util.Date} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2 = r1.parse(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r1 = formats.get(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.packet.PacketExtension parseExtension(org.xmlpull.v1.XmlPullParser r6) throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "stamp"
            java.lang.String r0 = r6.getAttributeValue(r0, r1)
            java.util.Map<java.lang.String, java.text.DateFormat> r1 = formats
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0012:
            boolean r2 = r1.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r4 = r0.matches(r2)
            if (r4 == 0) goto L_0x0012
            java.util.Map<java.lang.String, java.text.DateFormat> r1 = formats     // Catch:{ ParseException -> 0x003e }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ ParseException -> 0x003e }
            java.text.DateFormat r1 = (java.text.DateFormat) r1     // Catch:{ ParseException -> 0x003e }
            monitor-enter(r1)     // Catch:{ ParseException -> 0x003c }
            java.util.Date r2 = r1.parse(r0)     // Catch:{ all -> 0x0036 }
            monitor-exit(r1)     // Catch:{ all -> 0x0034 }
            goto L_0x0040
        L_0x0034:
            r4 = move-exception
            goto L_0x0038
        L_0x0036:
            r4 = move-exception
            r2 = r3
        L_0x0038:
            monitor-exit(r1)     // Catch:{ all -> 0x0034 }
            throw r4     // Catch:{ ParseException -> 0x003a }
        L_0x003a:
            goto L_0x0040
        L_0x003c:
            r2 = r3
            goto L_0x0040
        L_0x003e:
            r1 = r3
            r2 = r1
        L_0x0040:
            java.text.DateFormat r4 = org.jivesoftware.smackx.packet.DelayInformation.XEP_0091_UTC_FORMAT
            if (r1 != r4) goto L_0x0059
            java.lang.String r1 = "T"
            java.lang.String[] r1 = r0.split(r1)
            r4 = 0
            r1 = r1[r4]
            int r1 = r1.length()
            r4 = 8
            if (r1 >= r4) goto L_0x0059
            java.util.Date r2 = r5.handleDateWithMissingLeadingZeros(r0)
        L_0x0059:
            if (r2 != 0) goto L_0x0060
            java.util.Date r2 = new java.util.Date
            r2.<init>()
        L_0x0060:
            org.jivesoftware.smackx.packet.DelayInformation r0 = new org.jivesoftware.smackx.packet.DelayInformation
            r0.<init>(r2)
            java.lang.String r1 = ""
            java.lang.String r2 = "from"
            java.lang.String r1 = r6.getAttributeValue(r1, r2)
            r0.setFrom(r1)
            java.lang.String r6 = r6.nextText()
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            r3 = r6
        L_0x007e:
            r0.setReason(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.provider.DelayInformationProvider.parseExtension(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.packet.PacketExtension");
    }

    private Date handleDateWithMissingLeadingZeros(String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        List<Calendar> filterDatesBefore = filterDatesBefore(gregorianCalendar, parseXEP91Date(str, DelayInformation.XEP_0091_UTC_FORMAT), parseXEP91Date(str, XEP_0091_UTC_FALLBACK_FORMAT));
        if (!filterDatesBefore.isEmpty()) {
            return determineNearestDate(gregorianCalendar, filterDatesBefore).getTime();
        }
        return null;
    }

    private Calendar parseXEP91Date(String str, DateFormat dateFormat) {
        Calendar calendar;
        try {
            synchronized (dateFormat) {
                dateFormat.parse(str);
                calendar = dateFormat.getCalendar();
            }
            return calendar;
        } catch (ParseException unused) {
            return null;
        }
    }

    private List<Calendar> filterDatesBefore(Calendar calendar, Calendar... calendarArr) {
        ArrayList arrayList = new ArrayList();
        for (Calendar calendar2 : calendarArr) {
            if (calendar2 != null && calendar2.before(calendar)) {
                arrayList.add(calendar2);
            }
        }
        return arrayList;
    }

    private Calendar determineNearestDate(final Calendar calendar, List<Calendar> list) {
        Collections.sort(list, new Comparator<Calendar>() {
            public int compare(Calendar calendar, Calendar calendar2) {
                return new Long(calendar.getTimeInMillis() - calendar.getTimeInMillis()).compareTo(new Long(calendar.getTimeInMillis() - calendar2.getTimeInMillis()));
            }
        });
        return list.get(0);
    }
}
