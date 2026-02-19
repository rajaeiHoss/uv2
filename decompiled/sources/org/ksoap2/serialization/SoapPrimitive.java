package org.ksoap2.serialization;

public class SoapPrimitive extends AttributeContainer {
    String name;
    String namespace;
    String value;

    public SoapPrimitive(String str, String str2, String str3) {
        this.namespace = str;
        this.name = str2;
        this.value = str3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r0 = r4.value;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r0 = r4.namespace;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.ksoap2.serialization.SoapPrimitive
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            org.ksoap2.serialization.SoapPrimitive r5 = (org.ksoap2.serialization.SoapPrimitive) r5
            java.lang.String r0 = r4.name
            java.lang.String r2 = r5.name
            boolean r0 = r0.equals(r2)
            r2 = 1
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = r4.namespace
            if (r0 != 0) goto L_0x001c
            java.lang.String r0 = r5.namespace
            if (r0 != 0) goto L_0x0037
            goto L_0x0024
        L_0x001c:
            java.lang.String r3 = r5.namespace
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0037
        L_0x0024:
            java.lang.String r0 = r4.value
            if (r0 != 0) goto L_0x002d
            java.lang.String r0 = r5.value
            if (r0 != 0) goto L_0x0037
            goto L_0x0035
        L_0x002d:
            java.lang.String r3 = r5.value
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0037
        L_0x0035:
            r0 = 1
            goto L_0x0038
        L_0x0037:
            r0 = 0
        L_0x0038:
            if (r0 == 0) goto L_0x0041
            boolean r5 = r4.attributesAreEqual(r5)
            if (r5 == 0) goto L_0x0041
            r1 = 1
        L_0x0041:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.serialization.SoapPrimitive.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = this.name.hashCode();
        String str = this.namespace;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return this.value;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getName() {
        return this.name;
    }
}
