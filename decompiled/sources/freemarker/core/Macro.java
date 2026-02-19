package freemarker.core;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.utility.Collections12;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

public final class Macro extends TemplateElement implements TemplateModel {
    static final Macro DO_NOTHING_MACRO = new Macro(".pass", Collections.EMPTY_LIST, Collections12.EMPTY_MAP, TextBlock.EMPTY_BLOCK);
    final int TYPE_FUNCTION = 1;
    final int TYPE_MACRO = 0;
    private String catchAllParamName;
    boolean isFunction;
    /* access modifiers changed from: private */
    public final String name;
    /* access modifiers changed from: private */
    public Map paramDefaults;
    /* access modifiers changed from: private */
    public final String[] paramNames;

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return false;
    }

    Macro(String str, List list, Map map, TemplateElement templateElement) {
        this.name = str;
        this.paramNames = (String[]) list.toArray(new String[list.size()]);
        this.paramDefaults = map;
        this.nestedBlock = templateElement;
    }

    public String getCatchAll() {
        return this.catchAllParamName;
    }

    public void setCatchAll(String str) {
        this.catchAllParamName = str;
    }

    public String[] getArgumentNames() {
        return (String[]) this.paramNames.clone();
    }

    /* access modifiers changed from: package-private */
    public String[] getArgumentNamesInternal() {
        return this.paramNames;
    }

    /* access modifiers changed from: package-private */
    public boolean hasArgNamed(String str) {
        return this.paramDefaults.containsKey(str);
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) {
        environment.visitMacroDef(this);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.name);
        stringBuffer.append(this.isFunction ? '(' : ' ');
        int length = this.paramNames.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                if (this.isFunction) {
                    stringBuffer.append(", ");
                } else {
                    stringBuffer.append(' ');
                }
            }
            String str = this.paramNames[i];
            stringBuffer.append(str);
            Map map = this.paramDefaults;
            if (!(map == null || map.get(str) == null)) {
                stringBuffer.append('=');
                Expression expression = (Expression) this.paramDefaults.get(str);
                if (this.isFunction) {
                    stringBuffer.append(expression.getCanonicalForm());
                } else {
                    MessageUtil.appendExpressionAsUntearable(stringBuffer, expression);
                }
            }
        }
        if (this.catchAllParamName != null) {
            if (length != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.catchAllParamName);
            stringBuffer.append("...");
        }
        if (this.isFunction) {
            stringBuffer.append(')');
        }
        if (z) {
            stringBuffer.append(Typography.greater);
            if (this.nestedBlock != null) {
                stringBuffer.append(this.nestedBlock.getCanonicalForm());
            }
            stringBuffer.append("</");
            stringBuffer.append(getNodeTypeSymbol());
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.isFunction ? "#function" : "#macro";
    }

    public boolean isFunction() {
        return this.isFunction;
    }

    class Context implements LocalContext {
        TemplateElement body;
        Environment.Namespace bodyNamespace;
        List bodyParameterNames;
        Environment.Namespace localVars;
        ArrayList prevLocalContextStack;
        Context prevMacroContext;

        Context(Environment environment, TemplateElement templateElement, List list) {
            environment.getClass();
            this.localVars = new Environment.Namespace();
            this.prevMacroContext = environment.getCurrentMacroContext();
            this.bodyNamespace = environment.getCurrentNamespace();
            this.prevLocalContextStack = environment.getLocalContextStack();
            this.body = templateElement;
            this.bodyParameterNames = list;
        }

        /* access modifiers changed from: package-private */
        public Macro getMacro() {
            return Macro.this;
        }

        /* access modifiers changed from: package-private */
        public void runMacro(Environment environment) throws TemplateException, IOException {
            sanityCheck(environment);
            if (Macro.this.nestedBlock != null) {
                environment.visit(Macro.this.nestedBlock);
            }
        }

        /* access modifiers changed from: package-private */
        public void sanityCheck(Environment environment) throws TemplateException {
            InvalidReferenceException invalidReferenceException;
            Expression expression;
            boolean z;
            do {
                invalidReferenceException = null;
                expression = null;
                boolean z2 = false;
                z = false;
                for (int i = 0; i < Macro.this.paramNames.length; i++) {
                    String str = Macro.this.paramNames[i];
                    if (this.localVars.get(str) == null) {
                        Expression expression2 = (Expression) Macro.this.paramDefaults.get(str);
                        if (expression2 != null) {
                            try {
                                TemplateModel eval = expression2.eval(environment);
                                if (eval != null) {
                                    this.localVars.put(str, (Object) eval);
                                    z2 = true;
                                } else if (!z) {
                                    expression = expression2;
                                    z = true;
                                }
                            } catch (InvalidReferenceException e) {
                                if (!z) {
                                    invalidReferenceException = e;
                                }
                            }
                        } else if (!environment.isClassicCompatible()) {
                            boolean containsKey = this.localVars.containsKey(str);
                            Object[] objArr = new Object[8];
                            objArr[0] = "When calling macro ";
                            objArr[1] = new _DelayedJQuote(Macro.this.name);
                            objArr[2] = ", required parameter ";
                            objArr[3] = new _DelayedJQuote(str);
                            objArr[4] = " (parameter #";
                            objArr[5] = new Integer(i + 1);
                            objArr[6] = ") was ";
                            objArr[7] = containsKey ? "specified, but had null/missing value." : "not specified.";
                            throw new _MiscTemplateException(environment, new _ErrorDescriptionBuilder(objArr).tip(containsKey ? new Object[]{"If the parameter value expression on the caller side is known to be legally null/missing, you may want to specify a default value for it with the \"!\" operator, like paramValue!defaultValue."} : new Object[]{"If the omission was deliberate, you may consider making the parameter optional in the macro by specifying a default value for it, like ", "<#macro macroName paramName=defaultExpr>", ")"}));
                        }
                    }
                }
                if (!z2) {
                    break;
                }
            } while (z);
            if (!z) {
                return;
            }
            if (invalidReferenceException != null) {
                throw invalidReferenceException;
            } else if (!environment.isClassicCompatible()) {
                throw InvalidReferenceException.getInstance(expression, environment);
            }
        }

        public TemplateModel getLocalVariable(String str) throws TemplateModelException {
            return this.localVars.get(str);
        }

        /* access modifiers changed from: package-private */
        public Environment.Namespace getLocals() {
            return this.localVars;
        }

        /* access modifiers changed from: package-private */
        public void setLocalVar(String str, TemplateModel templateModel) {
            this.localVars.put(str, (Object) templateModel);
        }

        public Collection getLocalVariableNames() throws TemplateModelException {
            HashSet hashSet = new HashSet();
            TemplateModelIterator it = this.localVars.keys().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().toString());
            }
            return hashSet;
        }
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return (this.paramNames.length * 2) + 1 + 1 + 1;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.name;
        }
        String[] strArr = this.paramNames;
        int length = (strArr.length * 2) + 1;
        if (i < length) {
            String str = strArr[(i - 1) / 2];
            if (i % 2 != 0) {
                return str;
            }
            return this.paramDefaults.get(str);
        } else if (i == length) {
            return this.catchAllParamName;
        } else {
            if (i == length + 1) {
                return new Integer(this.isFunction ? 1 : 0);
            }
            throw new IndexOutOfBoundsException();
        }
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.ASSIGNMENT_TARGET;
        }
        int length = (this.paramNames.length * 2) + 1;
        if (i < length) {
            if (i % 2 != 0) {
                return ParameterRole.PARAMETER_NAME;
            }
            return ParameterRole.PARAMETER_DEFAULT;
        } else if (i == length) {
            return ParameterRole.CATCH_ALL_PARAMETER_NAME;
        } else {
            if (i == length + 1) {
                return ParameterRole.AST_NODE_SUBTYPE;
            }
            throw new IndexOutOfBoundsException();
        }
    }
}
