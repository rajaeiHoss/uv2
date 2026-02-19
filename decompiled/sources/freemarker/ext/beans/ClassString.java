package freemarker.ext.beans;

import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class ClassString {
    private static final Class BIGDECIMAL_CLASS;
    private static final int INDETERMINATE = 2;
    private static final int LESS_SPECIFIC = 1;
    private static final int MORE_SPECIFIC = 0;
    private static final Class NUMBER_CLASS;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Number;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$math$BigDecimal;
    private final Class[] classes;

    static {
        Class cls = class$java$math$BigDecimal;
        if (cls == null) {
            cls = class$("java.math.BigDecimal");
            class$java$math$BigDecimal = cls;
        }
        BIGDECIMAL_CLASS = cls;
        Class cls2 = class$java$lang$Number;
        if (cls2 == null) {
            cls2 = class$("java.lang.Number");
            class$java$lang$Number = cls2;
        }
        NUMBER_CLASS = cls2;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    ClassString(Object[] objArr) {
        int length = objArr.length;
        this.classes = new Class[length];
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            this.classes[i] = obj == null ? MethodUtilities.OBJECT_CLASS : obj.getClass();
        }
    }

    /* access modifiers changed from: package-private */
    public Class[] getClasses() {
        return this.classes;
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            Class[] clsArr = this.classes;
            if (i >= clsArr.length) {
                return i2;
            }
            i2 ^= clsArr[i].hashCode();
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClassString)) {
            return false;
        }
        ClassString classString = (ClassString) obj;
        if (classString.classes.length != this.classes.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Class[] clsArr = this.classes;
            if (i >= clsArr.length) {
                return true;
            }
            if (classString.classes[i] != clsArr[i]) {
                return false;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public Object getMostSpecific(List list, boolean z) {
        LinkedList applicables = getApplicables(list, z);
        if (applicables.isEmpty()) {
            return OverloadedMethodsSubset.NO_SUCH_METHOD;
        }
        if (applicables.size() == 1) {
            return applicables.getFirst();
        }
        LinkedList linkedList = new LinkedList();
        Iterator it = applicables.iterator();
        while (it.hasNext()) {
            Member member = (Member) it.next();
            Class[] parameterTypes = MethodUtilities.getParameterTypes(member);
            boolean z2 = false;
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                int moreSpecific = moreSpecific(parameterTypes, MethodUtilities.getParameterTypes((Member) it2.next()), z);
                if (moreSpecific == 0) {
                    it2.remove();
                } else if (moreSpecific == 1) {
                    z2 = true;
                }
            }
            if (!z2) {
                linkedList.addLast(member);
            }
        }
        if (linkedList.size() > 1) {
            return OverloadedMethodsSubset.AMBIGUOUS_METHOD;
        }
        return linkedList.getFirst();
    }

    private static int moreSpecific(Class[] clsArr, Class[] clsArr2, boolean z) {
        int length = clsArr.length;
        int length2 = clsArr2.length;
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < length; i++) {
            Class cls = getClass(clsArr, length, i, z);
            Class cls2 = getClass(clsArr2, length2, i, z);
            if (cls != cls2) {
                z2 = z2 || MethodUtilities.isMoreSpecific(cls, cls2);
                z3 = z3 || MethodUtilities.isMoreSpecific(cls2, cls);
            }
        }
        if (z2) {
            if (z3) {
                return 2;
            }
            return 0;
        } else if (z3) {
            return 1;
        } else {
            return 2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Class getClass(java.lang.Class[] r0, int r1, int r2, boolean r3) {
        /*
            if (r3 == 0) goto L_0x000d
            int r1 = r1 + -1
            if (r2 < r1) goto L_0x000d
            r0 = r0[r1]
            java.lang.Class r0 = r0.getComponentType()
            goto L_0x000f
        L_0x000d:
            r0 = r0[r2]
        L_0x000f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.ClassString.getClass(java.lang.Class[], int, int, boolean):java.lang.Class");
    }

    /* access modifiers changed from: package-private */
    public LinkedList getApplicables(List list, boolean z) {
        LinkedList linkedList = new LinkedList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Member member = (Member) it.next();
            if (isApplicable(member, z)) {
                linkedList.add(member);
            }
        }
        return linkedList;
    }

    private boolean isApplicable(Member member, boolean z) {
        Class[] parameterTypes = MethodUtilities.getParameterTypes(member);
        int length = this.classes.length;
        int length2 = parameterTypes.length - (z ? 1 : 0);
        if (z) {
            if (length < length2) {
                return false;
            }
        } else if (length != length2) {
            return false;
        }
        for (int i = 0; i < length2; i++) {
            if (!isMethodInvocationConvertible(parameterTypes[i], this.classes[i])) {
                return false;
            }
        }
        if (!z) {
            return true;
        }
        Class<?> componentType = parameterTypes[length2].getComponentType();
        while (length2 < length) {
            if (!isMethodInvocationConvertible(componentType, this.classes[length2])) {
                return false;
            }
            length2++;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:119:0x015c, code lost:
        if (r9 == r0) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007a, code lost:
        if (r9 == r0) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
        if (r9 == r0) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00d8, code lost:
        if (r9 == r0) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0119, code lost:
        if (r9 == r0) goto L_0x011b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean isMethodInvocationConvertible(java.lang.Class r8, java.lang.Class r9) {
        /*
            boolean r0 = r8.isAssignableFrom(r9)
            r1 = 1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r8.isPrimitive()
            r2 = 0
            if (r0 == 0) goto L_0x016e
            java.lang.Class r0 = java.lang.Boolean.TYPE
            if (r8 != r0) goto L_0x0024
            java.lang.Class r8 = class$java$lang$Boolean
            if (r8 != 0) goto L_0x001f
            java.lang.String r8 = "java.lang.Boolean"
            java.lang.Class r8 = class$(r8)
            class$java$lang$Boolean = r8
        L_0x001f:
            if (r9 != r8) goto L_0x0022
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.String r3 = "java.lang.Float"
            java.lang.String r4 = "java.lang.Long"
            java.lang.String r5 = "java.lang.Integer"
            java.lang.String r6 = "java.lang.Short"
            java.lang.String r7 = "java.lang.Byte"
            if (r8 != r0) goto L_0x007d
            java.lang.Class r0 = class$java$lang$Double
            if (r0 != 0) goto L_0x003e
            java.lang.String r0 = "java.lang.Double"
            java.lang.Class r0 = class$(r0)
            class$java$lang$Double = r0
        L_0x003e:
            if (r9 == r0) goto L_0x007c
            java.lang.Class r0 = class$java$lang$Float
            if (r0 != 0) goto L_0x004a
            java.lang.Class r0 = class$(r3)
            class$java$lang$Float = r0
        L_0x004a:
            if (r9 == r0) goto L_0x007c
            java.lang.Class r0 = class$java$lang$Long
            if (r0 != 0) goto L_0x0056
            java.lang.Class r0 = class$(r4)
            class$java$lang$Long = r0
        L_0x0056:
            if (r9 == r0) goto L_0x007c
            java.lang.Class r0 = class$java$lang$Integer
            if (r0 != 0) goto L_0x0062
            java.lang.Class r0 = class$(r5)
            class$java$lang$Integer = r0
        L_0x0062:
            if (r9 == r0) goto L_0x007c
            java.lang.Class r0 = class$java$lang$Short
            if (r0 != 0) goto L_0x006e
            java.lang.Class r0 = class$(r6)
            class$java$lang$Short = r0
        L_0x006e:
            if (r9 == r0) goto L_0x007c
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x007a
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x007a:
            if (r9 != r0) goto L_0x007d
        L_0x007c:
            return r1
        L_0x007d:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r8 != r0) goto L_0x00a6
            java.lang.Class r0 = class$java$lang$Integer
            if (r0 != 0) goto L_0x008b
            java.lang.Class r0 = class$(r5)
            class$java$lang$Integer = r0
        L_0x008b:
            if (r9 == r0) goto L_0x00a5
            java.lang.Class r0 = class$java$lang$Short
            if (r0 != 0) goto L_0x0097
            java.lang.Class r0 = class$(r6)
            class$java$lang$Short = r0
        L_0x0097:
            if (r9 == r0) goto L_0x00a5
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x00a3
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x00a3:
            if (r9 != r0) goto L_0x00a6
        L_0x00a5:
            return r1
        L_0x00a6:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r8 != r0) goto L_0x00db
            java.lang.Class r0 = class$java$lang$Long
            if (r0 != 0) goto L_0x00b4
            java.lang.Class r0 = class$(r4)
            class$java$lang$Long = r0
        L_0x00b4:
            if (r9 == r0) goto L_0x00da
            java.lang.Class r0 = class$java$lang$Integer
            if (r0 != 0) goto L_0x00c0
            java.lang.Class r0 = class$(r5)
            class$java$lang$Integer = r0
        L_0x00c0:
            if (r9 == r0) goto L_0x00da
            java.lang.Class r0 = class$java$lang$Short
            if (r0 != 0) goto L_0x00cc
            java.lang.Class r0 = class$(r6)
            class$java$lang$Short = r0
        L_0x00cc:
            if (r9 == r0) goto L_0x00da
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x00d8
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x00d8:
            if (r9 != r0) goto L_0x00db
        L_0x00da:
            return r1
        L_0x00db:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r8 != r0) goto L_0x011c
            java.lang.Class r0 = class$java$lang$Float
            if (r0 != 0) goto L_0x00e9
            java.lang.Class r0 = class$(r3)
            class$java$lang$Float = r0
        L_0x00e9:
            if (r9 == r0) goto L_0x011b
            java.lang.Class r0 = class$java$lang$Long
            if (r0 != 0) goto L_0x00f5
            java.lang.Class r0 = class$(r4)
            class$java$lang$Long = r0
        L_0x00f5:
            if (r9 == r0) goto L_0x011b
            java.lang.Class r0 = class$java$lang$Integer
            if (r0 != 0) goto L_0x0101
            java.lang.Class r0 = class$(r5)
            class$java$lang$Integer = r0
        L_0x0101:
            if (r9 == r0) goto L_0x011b
            java.lang.Class r0 = class$java$lang$Short
            if (r0 != 0) goto L_0x010d
            java.lang.Class r0 = class$(r6)
            class$java$lang$Short = r0
        L_0x010d:
            if (r9 == r0) goto L_0x011b
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x0119
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x0119:
            if (r9 != r0) goto L_0x011c
        L_0x011b:
            return r1
        L_0x011c:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r8 != r0) goto L_0x0131
            java.lang.Class r8 = class$java$lang$Character
            if (r8 != 0) goto L_0x012c
            java.lang.String r8 = "java.lang.Character"
            java.lang.Class r8 = class$(r8)
            class$java$lang$Character = r8
        L_0x012c:
            if (r9 != r8) goto L_0x012f
            goto L_0x0130
        L_0x012f:
            r1 = 0
        L_0x0130:
            return r1
        L_0x0131:
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r8 != r0) goto L_0x0142
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x013f
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x013f:
            if (r9 != r0) goto L_0x0142
            return r1
        L_0x0142:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r8 != r0) goto L_0x015f
            java.lang.Class r0 = class$java$lang$Short
            if (r0 != 0) goto L_0x0150
            java.lang.Class r0 = class$(r6)
            class$java$lang$Short = r0
        L_0x0150:
            if (r9 == r0) goto L_0x015e
            java.lang.Class r0 = class$java$lang$Byte
            if (r0 != 0) goto L_0x015c
            java.lang.Class r0 = class$(r7)
            class$java$lang$Byte = r0
        L_0x015c:
            if (r9 != r0) goto L_0x015f
        L_0x015e:
            return r1
        L_0x015f:
            java.lang.Class r0 = BIGDECIMAL_CLASS
            boolean r9 = r0.isAssignableFrom(r9)
            if (r9 == 0) goto L_0x016e
            boolean r8 = isNumerical(r8)
            if (r8 == 0) goto L_0x016e
            return r1
        L_0x016e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.ClassString.isMethodInvocationConvertible(java.lang.Class, java.lang.Class):boolean");
    }

    private static boolean isNumerical(Class cls) {
        return NUMBER_CLASS.isAssignableFrom(cls) || !(!cls.isPrimitive() || cls == Boolean.TYPE || cls == Character.TYPE);
    }
}
