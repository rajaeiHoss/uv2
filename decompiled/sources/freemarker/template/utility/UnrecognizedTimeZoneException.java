package freemarker.template.utility;

public class UnrecognizedTimeZoneException extends Exception {
    private final String timeZoneName;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnrecognizedTimeZoneException(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Unrecognized time zone: "
            r0.append(r1)
            java.lang.String r1 = freemarker.template.utility.StringUtil.jQuote((java.lang.String) r3)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r2.timeZoneName = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.UnrecognizedTimeZoneException.<init>(java.lang.String):void");
    }

    public String getTimeZoneName() {
        return this.timeZoneName;
    }
}
