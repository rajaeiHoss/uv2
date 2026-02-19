package freemarker.ext.beans;

import freemarker.template.TemplateModelException;
import freemarker.template.utility.ClassUtil;
import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.List;

final class OverloadedMethods {
    private final OverloadedMethodsSubset fixArgMethods = new OverloadedFixArgMethods();
    private OverloadedMethodsSubset varargMethods;
    private final BeansWrapper wrapper;

    OverloadedMethods(BeansWrapper beansWrapper) {
        this.wrapper = beansWrapper;
    }

    /* access modifiers changed from: package-private */
    public BeansWrapper getWrapper() {
        return this.wrapper;
    }

    /* access modifiers changed from: package-private */
    public void addMember(Member member) {
        this.fixArgMethods.addMember(member);
        if (MethodUtilities.isVarArgs(member)) {
            if (this.varargMethods == null) {
                this.varargMethods = new OverloadedVarargMethods();
            }
            this.varargMethods.addMember(member);
        }
    }

    /* access modifiers changed from: package-private */
    public MemberAndArguments getMemberAndArguments(List list) throws TemplateModelException {
        Object memberAndArguments = this.fixArgMethods.getMemberAndArguments(list, this.wrapper);
        if (memberAndArguments == OverloadedMethodsSubset.NO_SUCH_METHOD) {
            OverloadedMethodsSubset overloadedMethodsSubset = this.varargMethods;
            if (overloadedMethodsSubset != null) {
                memberAndArguments = overloadedMethodsSubset.getMemberAndArguments(list, this.wrapper);
            }
            if (memberAndArguments == OverloadedMethodsSubset.NO_SUCH_METHOD) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No compatible overloaded variation was found for the signature deducated from the actual parameter values:\n");
                stringBuffer.append(getDeducedCallSignature(list));
                stringBuffer.append("\nThe available overloaded variations are:\n");
                stringBuffer.append(memberListToString());
                throw new TemplateModelException(stringBuffer.toString());
            }
        }
        if (memberAndArguments != OverloadedMethodsSubset.AMBIGUOUS_METHOD) {
            return (MemberAndArguments) memberAndArguments;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Multiple compatible overloaded variation was found for the signature deducated from the actual parameter values:\n");
        stringBuffer2.append(getDeducedCallSignature(list));
        stringBuffer2.append("\nThe available overloaded variations are (including non-matching):\n");
        stringBuffer2.append(memberListToString());
        throw new TemplateModelException(stringBuffer2.toString());
    }

    private String memberListToString() {
        Iterator members = this.fixArgMethods.getMembers();
        OverloadedMethodsSubset overloadedMethodsSubset = this.varargMethods;
        Iterator members2 = overloadedMethodsSubset != null ? overloadedMethodsSubset.getMembers() : null;
        if (!(members.hasNext() || (members2 != null && members2.hasNext()))) {
            return "No members";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (members.hasNext()) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(",\n");
            }
            stringBuffer.append("    ");
            stringBuffer.append(methodOrConstructorToString((Member) members.next()));
        }
        if (members2 != null) {
            while (members2.hasNext()) {
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(",\n");
                }
                stringBuffer.append(methodOrConstructorToString((Member) members2.next()));
            }
        }
        return stringBuffer.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.reflect.Member} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.reflect.Member} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getDeducedCallSignature(java.util.List r4) {
        /*
            r3 = this;
            freemarker.ext.beans.OverloadedMethodsSubset r0 = r3.fixArgMethods
            java.util.Iterator r0 = r0.getMembers()
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0015
            java.lang.Object r0 = r0.next()
            r2 = r0
            java.lang.reflect.Member r2 = (java.lang.reflect.Member) r2
            goto L_0x002e
        L_0x0015:
            freemarker.ext.beans.OverloadedMethodsSubset r0 = r3.varargMethods
            if (r0 == 0) goto L_0x001e
            java.util.Iterator r0 = r0.getMembers()
            goto L_0x001f
        L_0x001e:
            r0 = r2
        L_0x001f:
            if (r0 == 0) goto L_0x002e
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002e
            java.lang.Object r0 = r0.next()
            r2 = r0
            java.lang.reflect.Member r2 = (java.lang.reflect.Member) r2
        L_0x002e:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            if (r2 == 0) goto L_0x004c
            boolean r1 = r2 instanceof java.lang.reflect.Constructor
            if (r1 == 0) goto L_0x003f
            java.lang.String r1 = "constructor "
            r0.append(r1)
            goto L_0x0044
        L_0x003f:
            java.lang.String r1 = "method "
            r0.append(r1)
        L_0x0044:
            java.lang.String r1 = r2.getName()
            r0.append(r1)
            goto L_0x0051
        L_0x004c:
            java.lang.String r1 = "???"
            r0.append(r1)
        L_0x0051:
            r1 = 40
            r0.append(r1)
            r1 = 0
        L_0x0057:
            int r2 = r4.size()
            if (r1 >= r2) goto L_0x0074
            if (r1 == 0) goto L_0x0064
            java.lang.String r2 = ", "
            r0.append(r2)
        L_0x0064:
            java.lang.Object r2 = r4.get(r1)
            freemarker.template.TemplateModel r2 = (freemarker.template.TemplateModel) r2
            java.lang.String r2 = freemarker.template.utility.ClassUtil.getFTLTypeDescription(r2)
            r0.append(r2)
            int r1 = r1 + 1
            goto L_0x0057
        L_0x0074:
            r4 = 41
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.OverloadedMethods.getDeducedCallSignature(java.util.List):java.lang.String");
    }

    private String methodOrConstructorToString(Member member) {
        StringBuffer stringBuffer = new StringBuffer();
        String shortClassName = ClassUtil.getShortClassName(member.getDeclaringClass());
        if (shortClassName != null) {
            stringBuffer.append(shortClassName);
            stringBuffer.append('.');
        }
        stringBuffer.append(member.getName());
        stringBuffer.append('(');
        Class[] parameterTypes = MethodUtilities.getParameterTypes(member);
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(ClassUtil.getShortClassName(parameterTypes[i]));
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }
}
