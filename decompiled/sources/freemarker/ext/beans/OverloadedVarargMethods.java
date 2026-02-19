package freemarker.ext.beans;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: OverloadedVarArgMethod */
class OverloadedVarargMethods extends OverloadedMethodsSubset {
    private static final Map canoncialArgPackers = new HashMap();
    private final Map argPackers = new HashMap();

    OverloadedVarargMethods() {
    }

    /* compiled from: OverloadedVarArgMethod */
    private static class ArgumentPacker {
        private final int argCount;
        private final Class varArgType;

        ArgumentPacker(Class[] clsArr) {
            int length = clsArr.length;
            this.argCount = length;
            this.varArgType = clsArr[length - 1].getComponentType();
        }

        /* access modifiers changed from: package-private */
        public Object[] packArgs(Object[] objArr, List list, BeansWrapper beansWrapper) throws TemplateModelException {
            int length = objArr.length;
            int i = this.argCount;
            int i2 = i - 1;
            if (objArr.length != i) {
                Object[] objArr2 = new Object[i];
                System.arraycopy(objArr, 0, objArr2, 0, i2);
                Object newInstance = Array.newInstance(this.varArgType, length - i2);
                for (int i3 = i2; i3 < length; i3++) {
                    Object unwrapInternal = beansWrapper.unwrapInternal((TemplateModel) list.get(i3), this.varArgType);
                    if (unwrapInternal == BeansWrapper.CAN_NOT_UNWRAP) {
                        return null;
                    }
                    Array.set(newInstance, i3 - i2, unwrapInternal);
                }
                objArr2[i2] = newInstance;
                return objArr2;
            }
            Object unwrapInternal2 = beansWrapper.unwrapInternal((TemplateModel) list.get(i2), this.varArgType);
            if (unwrapInternal2 == BeansWrapper.CAN_NOT_UNWRAP) {
                return null;
            }
            Object newInstance2 = Array.newInstance(this.varArgType, 1);
            Array.set(newInstance2, 0, unwrapInternal2);
            objArr[i2] = newInstance2;
            return objArr;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ArgumentPacker)) {
                return false;
            }
            ArgumentPacker argumentPacker = (ArgumentPacker) obj;
            if (this.argCount == argumentPacker.argCount && this.varArgType == argumentPacker.varArgType) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.argCount ^ this.varArgType.hashCode();
        }
    }

    /* access modifiers changed from: package-private */
    public void onAddSignature(Member member, Class[] clsArr) {
        ArgumentPacker argumentPacker = new ArgumentPacker(clsArr);
        Map map = canoncialArgPackers;
        synchronized (map) {
            ArgumentPacker argumentPacker2 = (ArgumentPacker) map.get(argumentPacker);
            if (argumentPacker2 == null) {
                map.put(argumentPacker, argumentPacker);
            } else {
                argumentPacker = argumentPacker2;
            }
        }
        this.argPackers.put(member, argumentPacker);
        componentizeLastType(clsArr);
    }

    /* access modifiers changed from: package-private */
    public void updateSignature(int i) {
        Class[] clsArr;
        Class[][] unwrappingArgTypesByArgCount = getUnwrappingArgTypesByArgCount();
        Class[] clsArr2 = unwrappingArgTypesByArgCount[i];
        int i2 = i;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            Class[] clsArr3 = unwrappingArgTypesByArgCount[i3];
            if (clsArr3 != null) {
                varArgUpdate(clsArr2, clsArr3);
                break;
            }
            i2 = i3;
        }
        int i4 = i + 1;
        if (i4 < unwrappingArgTypesByArgCount.length && (clsArr = unwrappingArgTypesByArgCount[i4]) != null) {
            varArgUpdate(clsArr2, clsArr);
        }
    }

    /* access modifiers changed from: package-private */
    public void afterSignatureAdded(int i) {
        Class[] clsArr;
        Class[][] unwrappingArgTypesByArgCount = getUnwrappingArgTypesByArgCount();
        Class[] clsArr2 = unwrappingArgTypesByArgCount[i];
        for (int i2 = i + 1; i2 < unwrappingArgTypesByArgCount.length; i2++) {
            Class[] clsArr3 = unwrappingArgTypesByArgCount[i2];
            if (clsArr3 != null) {
                varArgUpdate(clsArr3, clsArr2);
            }
        }
        if (i > 0 && (clsArr = unwrappingArgTypesByArgCount[i - 1]) != null) {
            varArgUpdate(clsArr, clsArr2);
        }
    }

    private static void varArgUpdate(Class[] clsArr, Class[] clsArr2) {
        int length = clsArr.length;
        int length2 = clsArr2.length;
        int min = Math.min(length2, length);
        for (int i = 0; i < min; i++) {
            clsArr[i] = MethodUtilities.getMostSpecificCommonType(clsArr[i], clsArr2[i]);
        }
        if (length > length2) {
            Class cls = clsArr2[length2 - 1];
            while (length2 < length) {
                clsArr[length2] = MethodUtilities.getMostSpecificCommonType(clsArr[length2], cls);
                length2++;
            }
        }
    }

    private static void componentizeLastType(Class[] clsArr) {
        int length = clsArr.length - 1;
        clsArr[length] = clsArr[length].getComponentType();
    }

    /* access modifiers changed from: package-private */
    public Object getMemberAndArguments(List list, BeansWrapper beansWrapper) throws TemplateModelException {
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        int size = list.size();
        Class[][] unwrappingArgTypesByArgCount = getUnwrappingArgTypesByArgCount();
        Object[] objArr = new Object[size];
        int min = Math.min(size + 1, unwrappingArgTypesByArgCount.length - 1);
        loop0:
        while (min >= 0) {
            Class[] clsArr = unwrappingArgTypesByArgCount[min];
            if (clsArr != null) {
                Iterator it = list.iterator();
                int i = 0;
                while (i < size) {
                    Object unwrapInternal = beansWrapper.unwrapInternal((TemplateModel) it.next(), i < min ? clsArr[i] : clsArr[min - 1]);
                    if (unwrapInternal != BeansWrapper.CAN_NOT_UNWRAP) {
                        if (unwrapInternal != objArr[i]) {
                            objArr[i] = unwrapInternal;
                        }
                        i++;
                    }
                }
                break loop0;
            } else if (min == 0) {
                return NO_SUCH_METHOD;
            }
            min--;
        }
        Object memberForArgs = getMemberForArgs(objArr, true);
        if (!(memberForArgs instanceof Member)) {
            return memberForArgs;
        }
        Member member = (Member) memberForArgs;
        Object[] packArgs = ((ArgumentPacker) this.argPackers.get(member)).packArgs(objArr, list, beansWrapper);
        if (packArgs == null) {
            return NO_SUCH_METHOD;
        }
        BeansWrapper.coerceBigDecimals(getSignature(member), packArgs);
        return new MemberAndArguments(member, packArgs);
    }
}
