package freemarker.ext.beans;

import freemarker.core._DelayedFTLTypeDescription;
import freemarker.core._TemplateModelException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.ClassUtil;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class SimpleMemberModel {
    private final Class[] argTypes;
    private final Member member;

    protected SimpleMemberModel(Member member2, Class[] clsArr) {
        this.member = member2;
        this.argTypes = clsArr;
    }

    /* access modifiers changed from: package-private */
    public Object[] unwrapArguments(List list, BeansWrapper beansWrapper) throws TemplateModelException {
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        boolean isVarArgs = MethodUtilities.isVarArgs(this.member);
        int length = this.argTypes.length;
        if (isVarArgs) {
            int i = length - 1;
            if (i > list.size()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Method ");
                stringBuffer.append(this.member);
                stringBuffer.append(" takes at least ");
                stringBuffer.append(i);
                stringBuffer.append(" arguments, ");
                stringBuffer.append(list.size());
                stringBuffer.append(" was given.");
                throw new TemplateModelException(stringBuffer.toString());
            }
        } else if (length != list.size()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Method ");
            stringBuffer2.append(this.member);
            stringBuffer2.append(" takes exactly ");
            stringBuffer2.append(length);
            stringBuffer2.append(" arguments, ");
            stringBuffer2.append(list.size());
            stringBuffer2.append(" was given.");
            throw new TemplateModelException(stringBuffer2.toString());
        }
        return unwrapArguments(list, this.argTypes, isVarArgs, beansWrapper);
    }

    static Object[] unwrapArguments(List list, Class[] clsArr, boolean z, BeansWrapper beansWrapper) throws TemplateModelException {
        Object obj;
        Object unwrapInternal;
        if (list == null) {
            return null;
        }
        int length = clsArr.length;
        int size = list.size();
        Object[] objArr = new Object[length];
        Iterator it = list.iterator();
        int i = z ? length - 1 : length;
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            Class cls = clsArr[i3];
            TemplateModel templateModel = (TemplateModel) it.next();
            Object unwrapInternal2 = beansWrapper.unwrapInternal(templateModel, cls);
            if (unwrapInternal2 == BeansWrapper.CAN_NOT_UNWRAP) {
                throw createArgumentTypeMismarchException(i3, templateModel, cls);
            } else if (unwrapInternal2 != null || !cls.isPrimitive()) {
                objArr[i3] = unwrapInternal2;
                i3++;
            } else {
                throw createNullToPrimitiveArgumentException(i3, cls);
            }
        }
        if (z) {
            Class cls2 = clsArr[length - 1];
            Class<?> componentType = cls2.getComponentType();
            if (!it.hasNext()) {
                objArr[i3] = Array.newInstance(componentType, 0);
            } else {
                TemplateModel templateModel2 = (TemplateModel) it.next();
                int i4 = size - i3;
                if (i4 != 1 || (unwrapInternal = beansWrapper.unwrapInternal(templateModel2, cls2)) == BeansWrapper.CAN_NOT_UNWRAP) {
                    Object newInstance = Array.newInstance(componentType, i4);
                    while (i2 < i4) {
                        if (i2 == 0) {
                            obj = templateModel2;
                        } else {
                            obj = it.next();
                        }
                        TemplateModel templateModel3 = (TemplateModel) obj;
                        Object unwrapInternal3 = beansWrapper.unwrapInternal(templateModel3, componentType);
                        if (unwrapInternal3 == BeansWrapper.CAN_NOT_UNWRAP) {
                            throw createArgumentTypeMismarchException(i3 + i2, templateModel3, componentType);
                        } else if (unwrapInternal3 != null || !componentType.isPrimitive()) {
                            Array.set(newInstance, i2, unwrapInternal3);
                            i2++;
                        } else {
                            throw createNullToPrimitiveArgumentException(i3 + i2, componentType);
                        }
                    }
                    objArr[i3] = newInstance;
                } else {
                    objArr[i3] = unwrapInternal;
                }
            }
        }
        return objArr;
    }

    private static TemplateModelException createArgumentTypeMismarchException(int i, TemplateModel templateModel, Class cls) {
        return new _TemplateModelException(new Object[]{"Argument type mismatch; can't convert (unwrap) argument #", new Integer(i + 1), " value of type ", new _DelayedFTLTypeDescription(templateModel), " to ", ClassUtil.getShortClassName(cls), "."});
    }

    private static TemplateModelException createNullToPrimitiveArgumentException(int i, Class cls) {
        return new _TemplateModelException(new Object[]{"Argument type mismatch; argument #", new Integer(i + 1), " is null, which can't be converted to primitive type ", cls.getName(), "."});
    }

    /* access modifiers changed from: protected */
    public Member getMember() {
        return this.member;
    }
}
