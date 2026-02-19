package freemarker.template.utility;

public class UnsupportedNumberClassException extends RuntimeException {
    private final Class fClass;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnsupportedNumberClassException(java.lang.Class r3) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Unsupported number class: "
            r0.append(r1)
            java.lang.String r1 = r3.getName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r2.fClass = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.UnsupportedNumberClassException.<init>(java.lang.Class):void");
    }

    public Class getUnsupportedClass() {
        return this.fClass;
    }
}
