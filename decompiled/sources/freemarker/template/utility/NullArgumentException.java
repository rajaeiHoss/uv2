package freemarker.template.utility;

public class NullArgumentException extends IllegalArgumentException {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NullArgumentException(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "The \""
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = "\" argument can't be null"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.NullArgumentException.<init>(java.lang.String):void");
    }

    public static void check(String str, Object obj) {
        if (obj == null) {
            throw new NullArgumentException(str);
        }
    }
}
