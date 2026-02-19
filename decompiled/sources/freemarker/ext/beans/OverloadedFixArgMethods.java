package freemarker.ext.beans;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class OverloadedFixArgMethods extends OverloadedMethodsSubset {
    /* access modifiers changed from: package-private */
    public void afterSignatureAdded(int i) {
    }

    /* access modifiers changed from: package-private */
    public void onAddSignature(Member member, Class[] clsArr) {
    }

    /* access modifiers changed from: package-private */
    public void updateSignature(int i) {
    }

    OverloadedFixArgMethods() {
    }

    /* access modifiers changed from: package-private */
    public Object getMemberAndArguments(List list, BeansWrapper beansWrapper) throws TemplateModelException {
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        int size = list.size();
        Class[][] unwrappingArgTypesByArgCount = getUnwrappingArgTypesByArgCount();
        if (unwrappingArgTypesByArgCount.length <= size) {
            return NO_SUCH_METHOD;
        }
        Class[] clsArr = unwrappingArgTypesByArgCount[size];
        if (clsArr == null) {
            return NO_SUCH_METHOD;
        }
        Object[] objArr = new Object[size];
        Iterator it = list.iterator();
        for (int i = 0; i < size; i++) {
            Object unwrapInternal = beansWrapper.unwrapInternal((TemplateModel) it.next(), clsArr[i]);
            if (unwrapInternal == BeansWrapper.CAN_NOT_UNWRAP) {
                return NO_SUCH_METHOD;
            }
            objArr[i] = unwrapInternal;
        }
        Object memberForArgs = getMemberForArgs(objArr, false);
        if (!(memberForArgs instanceof Member)) {
            return memberForArgs;
        }
        Member member = (Member) memberForArgs;
        BeansWrapper.coerceBigDecimals(getSignature(member), objArr);
        return new MemberAndArguments(member, objArr);
    }
}
