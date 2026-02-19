package freemarker.core;

import freemarker.log.Logger;
import freemarker.template.Template;
import freemarker.template.utility.StringUtil;
import kotlin.text.Typography;

public class _ErrorDescriptionBuilder {
    private static final Logger logger = Logger.getLogger("freemarker.runtime");
    private Expression blamed;
    private final String description;
    private final Object[] descriptionParts;
    private boolean showBlamer;
    private Template template;
    private Object tip;
    private Object[] tips;

    public _ErrorDescriptionBuilder(String str) {
        this.description = str;
        this.descriptionParts = null;
    }

    public _ErrorDescriptionBuilder(Object[] objArr) {
        this.descriptionParts = objArr;
        this.description = null;
    }

    public String toString() {
        return toString((TemplateElement) null);
    }

    public String toString(TemplateElement templateElement) {
        Expression expression;
        if (this.blamed == null && this.tips == null && this.tip == null && this.descriptionParts == null) {
            return this.description;
        }
        StringBuffer stringBuffer = new StringBuffer(200);
        if (!(templateElement == null || (expression = this.blamed) == null || !this.showBlamer)) {
            try {
                Blaming findBlaming = findBlaming(templateElement, expression, 0);
                if (findBlaming != null) {
                    stringBuffer.append("For ");
                    String nodeTypeSymbol = findBlaming.blamer.getNodeTypeSymbol();
                    char c = Typography.quote;
                    if (nodeTypeSymbol.indexOf(34) != -1) {
                        c = '`';
                    }
                    stringBuffer.append(c);
                    stringBuffer.append(nodeTypeSymbol);
                    stringBuffer.append(c);
                    stringBuffer.append(" ");
                    stringBuffer.append(findBlaming.roleOfblamed);
                    stringBuffer.append(": ");
                }
            } catch (Throwable th) {
                logger.error("Error when searching blamer for better error message.", th);
            }
        }
        String str = this.description;
        if (str != null) {
            stringBuffer.append(str);
        } else {
            appendParts(stringBuffer, this.descriptionParts);
        }
        String str2 = null;
        int i = 1;
        if (this.blamed != null) {
            int length = stringBuffer.length() - 1;
            while (length >= 0 && Character.isWhitespace(stringBuffer.charAt(length))) {
                stringBuffer.deleteCharAt(length);
                length--;
            }
            char charAt = stringBuffer.length() > 0 ? stringBuffer.charAt(stringBuffer.length() - 1) : 0;
            if (charAt != 0) {
                stringBuffer.append(10);
            }
            if (charAt != ':') {
                stringBuffer.append("The blamed expression:\n");
            }
            String[] splitToLines = splitToLines(this.blamed.toString());
            int i2 = 0;
            while (i2 < splitToLines.length) {
                stringBuffer.append(i2 == 0 ? "==> " : "\n    ");
                stringBuffer.append(splitToLines[i2]);
                i2++;
            }
            stringBuffer.append("  [");
            stringBuffer.append(this.blamed.getStartLocation());
            stringBuffer.append(']');
            if (containsSingleInterpolatoinLiteral(this.blamed, 0)) {
                str2 = "It has been noticed that you are using ${...} as the sole content of a quoted string. That does nothing but forcably converts the value inside ${...} to string (as it inserts it into the enclosing string). If that's not what you meant, just remove the quotation marks, ${ and }; you don't need them. If you indeed wanted to convert to string, use myExpression?string instead.";
            }
        }
        Object[] objArr = this.tips;
        int length2 = objArr != null ? objArr.length : 0;
        Object obj = this.tip;
        int i3 = length2 + (obj != null ? 1 : 0) + (str2 != null ? 1 : 0);
        if (objArr == null || i3 != objArr.length) {
            Object[] objArr2 = new Object[i3];
            if (obj != null) {
                objArr2[0] = obj;
            } else {
                i = 0;
            }
            if (objArr != null) {
                int i4 = 0;
                while (true) {
                    Object[] objArr3 = this.tips;
                    if (i4 >= objArr3.length) {
                        break;
                    }
                    objArr2[i] = objArr3[i4];
                    i4++;
                    i++;
                }
            }
            if (str2 != null) {
                objArr2[i] = str2;
            }
            objArr = objArr2;
        }
        if (objArr != null && objArr.length > 0) {
            stringBuffer.append("\n\n");
            for (int i5 = 0; i5 < objArr.length; i5++) {
                if (i5 != 0) {
                    stringBuffer.append(10);
                }
                stringBuffer.append("Tip: ");
                Object obj2 = objArr[i5];
                if (!(obj2 instanceof Object[])) {
                    stringBuffer.append(objArr[i5]);
                } else {
                    appendParts(stringBuffer, (Object[]) obj2);
                }
            }
        }
        return stringBuffer.toString();
    }

    private boolean containsSingleInterpolatoinLiteral(Expression expression, int i) {
        if (expression == null || i > 20) {
            return false;
        }
        if ((expression instanceof StringLiteral) && ((StringLiteral) expression).isSingleInterpolationLiteral()) {
            return true;
        }
        int parameterCount = expression.getParameterCount();
        for (int i2 = 0; i2 < parameterCount; i2++) {
            Object parameterValue = expression.getParameterValue(i2);
            if ((parameterValue instanceof Expression) && containsSingleInterpolatoinLiteral((Expression) parameterValue, i + 1)) {
                return true;
            }
        }
        return false;
    }

    private Blaming findBlaming(TemplateObject templateObject, Expression expression, int i) {
        Blaming findBlaming;
        if (i > 50) {
            return null;
        }
        int parameterCount = templateObject.getParameterCount();
        int i2 = 0;
        while (i2 < parameterCount) {
            Object parameterValue = templateObject.getParameterValue(i2);
            if (parameterValue == expression) {
                Blaming blaming = new Blaming();
                blaming.blamer = templateObject;
                blaming.roleOfblamed = templateObject.getParameterRole(i2);
                return blaming;
            } else if ((parameterValue instanceof TemplateObject) && (findBlaming = findBlaming((TemplateObject) parameterValue, expression, i + 1)) != null) {
                return findBlaming;
            } else {
                i2++;
            }
        }
        return null;
    }

    private void appendParts(StringBuffer stringBuffer, Object[] objArr) {
        Template template2 = this.template;
        if (template2 == null) {
            Expression expression = this.blamed;
            template2 = expression != null ? expression.getTemplate() : null;
        }
        for (int i = 0; i < objArr.length; i++) {
            Object[] objArr2 = objArr[i];
            if (objArr2 instanceof Object[]) {
                appendParts(stringBuffer, objArr2);
            } else {
                String obj = objArr[i].toString();
                if (template2 == null) {
                    stringBuffer.append(obj);
                } else if (obj.length() <= 4 || obj.charAt(0) != '<' || ((obj.charAt(1) != '#' && obj.charAt(1) != '@' && (obj.charAt(1) != '/' || (obj.charAt(2) != '#' && obj.charAt(2) != '@'))) || obj.charAt(obj.length() - 1) != '>')) {
                    stringBuffer.append(obj);
                } else if (template2.getActualTagSyntax() == 2) {
                    stringBuffer.append('[');
                    stringBuffer.append(obj.substring(1, obj.length() - 1));
                    stringBuffer.append(']');
                } else {
                    stringBuffer.append(obj);
                }
            }
        }
    }

    private String[] splitToLines(String str) {
        return StringUtil.split(StringUtil.replace(StringUtil.replace(str, "\r\n", "\n"), "\r", "\n"), 10);
    }

    public _ErrorDescriptionBuilder template(Template template2) {
        this.template = template2;
        return this;
    }

    public _ErrorDescriptionBuilder blame(Expression expression) {
        this.blamed = expression;
        return this;
    }

    public _ErrorDescriptionBuilder showBlamer(boolean z) {
        this.showBlamer = z;
        return this;
    }

    public _ErrorDescriptionBuilder tip(String str) {
        this.tip = str;
        return this;
    }

    public _ErrorDescriptionBuilder tip(Object[] objArr) {
        this.tip = objArr;
        return this;
    }

    public _ErrorDescriptionBuilder tips(Object[] objArr) {
        this.tips = objArr;
        return this;
    }

    public _ErrorDescriptionBuilder tips(String[][] strArr) {
        this.tips = strArr;
        return this;
    }

    private static class Blaming {
        TemplateObject blamer;
        ParameterRole roleOfblamed;

        private Blaming() {
        }
    }
}
