package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OptInTemplateClassResolver implements TemplateClassResolver {
    private final Set allowedClasses;
    private final Set trustedTemplateNames;
    private final List trustedTemplatePrefixes;

    public OptInTemplateClassResolver(Set set, List list) {
        this.allowedClasses = set == null ? Collections.EMPTY_SET : set;
        if (list != null) {
            this.trustedTemplateNames = new HashSet();
            this.trustedTemplatePrefixes = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                str = str.startsWith("/") ? str.substring(1) : str;
                if (str.endsWith("*")) {
                    this.trustedTemplatePrefixes.add(str.substring(0, str.length() - 1));
                } else {
                    this.trustedTemplateNames.add(str);
                }
            }
            return;
        }
        this.trustedTemplateNames = Collections.EMPTY_SET;
        this.trustedTemplatePrefixes = Collections.EMPTY_LIST;
    }

    public Class resolve(String str, Environment environment, Template template) throws TemplateException {
        String safeGetTemplateName = safeGetTemplateName(template);
        if (safeGetTemplateName != null && (this.trustedTemplateNames.contains(safeGetTemplateName) || hasMatchingPrefix(safeGetTemplateName))) {
            return TemplateClassResolver.SAFER_RESOLVER.resolve(str, environment, template);
        }
        if (this.allowedClasses.contains(str)) {
            try {
                return ClassUtil.forName(str);
            } catch (ClassNotFoundException e) {
                throw new _MiscTemplateException((Throwable) e, environment);
            }
        } else {
            throw new _MiscTemplateException(environment, new Object[]{"Instantiating ", str, " is not allowed in the template for security reasons. (If you run into this problem when using ?new in a template, you may want to check the \"", Configurable.NEW_BUILTIN_CLASS_RESOLVER_KEY, "\" setting in the FreeMarker configuration.)"});
        }
    }

    /* access modifiers changed from: protected */
    public String safeGetTemplateName(Template template) {
        String name;
        if (template == null || (name = template.getName()) == null) {
            return null;
        }
        String replace = name.indexOf(37) != -1 ? StringUtil.replace(StringUtil.replace(StringUtil.replace(StringUtil.replace(StringUtil.replace(StringUtil.replace(name, "%2e", ".", false, false), "%2E", ".", false, false), "%2f", "/", false, false), "%2F", "/", false, false), "%5c", "\\", false, false), "%5C", "\\", false, false) : name;
        int indexOf = replace.indexOf("..");
        if (indexOf != -1) {
            int i = indexOf - 1;
            char charAt = i >= 0 ? replace.charAt(i) : 65535;
            int i2 = indexOf + 2;
            char charAt2 = i2 < replace.length() ? replace.charAt(i2) : 65535;
            if ((charAt == 65535 || charAt == '/' || charAt == '\\') && (charAt2 == 65535 || charAt2 == '/' || charAt2 == '\\')) {
                return null;
            }
        }
        return name.startsWith("/") ? name.substring(1) : name;
    }

    private boolean hasMatchingPrefix(String str) {
        for (int i = 0; i < this.trustedTemplatePrefixes.size(); i++) {
            if (str.startsWith((String) this.trustedTemplatePrefixes.get(i))) {
                return true;
            }
        }
        return false;
    }
}
