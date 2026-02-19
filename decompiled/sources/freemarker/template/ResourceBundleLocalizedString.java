package freemarker.template;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleLocalizedString extends LocalizedString {
    private String resourceBundleLookupKey;
    private String resourceKey;

    public ResourceBundleLocalizedString(String str, String str2) {
        this.resourceBundleLookupKey = str;
        this.resourceKey = str2;
    }

    public String getLocalizedString(Locale locale) throws TemplateModelException {
        try {
            return ResourceBundle.getBundle(this.resourceBundleLookupKey, locale).getString(this.resourceKey);
        } catch (MissingResourceException e) {
            throw new TemplateModelException("missing resource", (Exception) e);
        }
    }
}
