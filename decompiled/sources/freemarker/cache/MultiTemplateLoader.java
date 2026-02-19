package freemarker.cache;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MultiTemplateLoader implements StatefulTemplateLoader {
    private final Map lastLoaderForName = Collections.synchronizedMap(new HashMap());
    private final TemplateLoader[] loaders;

    public MultiTemplateLoader(TemplateLoader[] templateLoaderArr) {
        this.loaders = (TemplateLoader[]) templateLoaderArr.clone();
    }

    public Object findTemplateSource(String str) throws IOException {
        Object findTemplateSource;
        TemplateLoader templateLoader = (TemplateLoader) this.lastLoaderForName.get(str);
        if (templateLoader != null && (findTemplateSource = templateLoader.findTemplateSource(str)) != null) {
            return new MultiSource(findTemplateSource, templateLoader);
        }
        int i = 0;
        while (true) {
            TemplateLoader[] templateLoaderArr = this.loaders;
            if (i < templateLoaderArr.length) {
                TemplateLoader templateLoader2 = templateLoaderArr[i];
                Object findTemplateSource2 = templateLoader2.findTemplateSource(str);
                if (findTemplateSource2 != null) {
                    this.lastLoaderForName.put(str, templateLoader2);
                    return new MultiSource(findTemplateSource2, templateLoader2);
                }
                i++;
            } else {
                this.lastLoaderForName.remove(str);
                return null;
            }
        }
    }

    public long getLastModified(Object obj) {
        return ((MultiSource) obj).getLastModified();
    }

    public Reader getReader(Object obj, String str) throws IOException {
        return ((MultiSource) obj).getReader(str);
    }

    public void closeTemplateSource(Object obj) throws IOException {
        ((MultiSource) obj).close();
    }

    public void resetState() {
        this.lastLoaderForName.clear();
        int i = 0;
        while (true) {
            TemplateLoader[] templateLoaderArr = this.loaders;
            if (i < templateLoaderArr.length) {
                TemplateLoader templateLoader = templateLoaderArr[i];
                if (templateLoader instanceof StatefulTemplateLoader) {
                    ((StatefulTemplateLoader) templateLoader).resetState();
                }
                i++;
            } else {
                return;
            }
        }
    }

    private static final class MultiSource {
        private final TemplateLoader loader;
        private final Object source;

        MultiSource(Object obj, TemplateLoader templateLoader) {
            this.source = obj;
            this.loader = templateLoader;
        }

        /* access modifiers changed from: package-private */
        public long getLastModified() {
            return this.loader.getLastModified(this.source);
        }

        /* access modifiers changed from: package-private */
        public Reader getReader(String str) throws IOException {
            return this.loader.getReader(this.source, str);
        }

        /* access modifiers changed from: package-private */
        public void close() throws IOException {
            this.loader.closeTemplateSource(this.source);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MultiSource)) {
                return false;
            }
            MultiSource multiSource = (MultiSource) obj;
            if (!multiSource.loader.equals(this.loader) || !multiSource.source.equals(this.source)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.loader.hashCode() + (this.source.hashCode() * 31);
        }

        public String toString() {
            return this.source.toString();
        }
    }
}
