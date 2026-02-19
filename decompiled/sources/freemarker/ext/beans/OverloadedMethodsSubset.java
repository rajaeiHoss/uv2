package freemarker.ext.beans;

import freemarker.template.TemplateModelException;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract class OverloadedMethodsSubset {
    static final Object AMBIGUOUS_METHOD = new Object();
    static final Object[] EMPTY_ARGS = new Object[0];
    static final Object NO_SUCH_METHOD = new Object();
    private final List members = new LinkedList();
    private final Map selectorCache = new HashMap();
    private final Map signatures = new HashMap();
    private Class[][] unwrappingArgTypesByArgCount;

    /* access modifiers changed from: package-private */
    public abstract void afterSignatureAdded(int i);

    /* access modifiers changed from: package-private */
    public abstract Object getMemberAndArguments(List list, BeansWrapper beansWrapper) throws TemplateModelException;

    /* access modifiers changed from: package-private */
    public abstract void onAddSignature(Member member, Class[] clsArr);

    /* access modifiers changed from: package-private */
    public abstract void updateSignature(int i);

    OverloadedMethodsSubset() {
    }

    /* access modifiers changed from: package-private */
    public void addMember(Member member) {
        this.members.add(member);
        Class[] parameterTypes = MethodUtilities.getParameterTypes(member);
        int length = parameterTypes.length;
        this.signatures.put(member, parameterTypes.clone());
        onAddSignature(member, parameterTypes);
        Class[][] clsArr = this.unwrappingArgTypesByArgCount;
        if (clsArr == null) {
            Class[][] clsArr2 = new Class[(length + 1)][];
            this.unwrappingArgTypesByArgCount = clsArr2;
            clsArr2[length] = parameterTypes;
        } else {
            if (clsArr.length <= length) {
                Class[][] clsArr3 = new Class[(length + 1)][];
                System.arraycopy(clsArr, 0, clsArr3, 0, clsArr.length);
                this.unwrappingArgTypesByArgCount = clsArr3;
                clsArr3[length] = parameterTypes;
            } else {
                Class[] clsArr4 = clsArr[length];
                if (clsArr4 == null) {
                    clsArr[length] = parameterTypes;
                } else {
                    for (int i = 0; i < clsArr4.length; i++) {
                        clsArr4[i] = MethodUtilities.getMostSpecificCommonType(clsArr4[i], parameterTypes[i]);
                    }
                }
            }
        }
        updateSignature(length);
        afterSignatureAdded(length);
    }

    /* access modifiers changed from: package-private */
    public Class[] getSignature(Member member) {
        return (Class[]) this.signatures.get(member);
    }

    /* access modifiers changed from: package-private */
    public Class[][] getUnwrappingArgTypesByArgCount() {
        return this.unwrappingArgTypesByArgCount;
    }

    /* access modifiers changed from: package-private */
    public Object getMemberForArgs(Object[] objArr, boolean z) {
        Object obj;
        ClassString classString = new ClassString(objArr);
        synchronized (this.selectorCache) {
            obj = this.selectorCache.get(classString);
            if (obj == null) {
                obj = classString.getMostSpecific(this.members, z);
                this.selectorCache.put(classString, obj);
            }
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Iterator getMembers() {
        return this.members.iterator();
    }
}
