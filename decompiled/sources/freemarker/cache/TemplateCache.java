package freemarker.cache;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.utility.StringUtil;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class TemplateCache {
    private static final char ASTERISK = '*';
    private static final String ASTERISKSTR = "*";
    private static final String CURRENT_DIR_PATH = "/./";
    private static final String CURRENT_DIR_PATH_PREFIX = "./";
    private static final Method INIT_CAUSE = getInitCauseMethod();
    private static final String LOCALE_SEPARATOR = "_";
    private static final String PARENT_DIR_PATH = "/../";
    private static final String PARENT_DIR_PATH_PREFIX = "../";
    private static final char SLASH = '/';
    static /* synthetic */ Class class$java$lang$Throwable;
    private static final Logger logger = Logger.getLogger("freemarker.cache");
    private Configuration config;
    private long delay;
    private final boolean isStorageConcurrent;
    private boolean localizedLookup;
    private final CacheStorage storage;
    private final TemplateLoader templateLoader;

    public TemplateCache() {
        this(createDefaultTemplateLoader());
    }

    private static TemplateLoader createDefaultTemplateLoader() {
        try {
            return new FileTemplateLoader();
        } catch (Exception e) {
            logger.warn("Could not create a file template loader for current directory", e);
            return null;
        }
    }

    public TemplateCache(TemplateLoader templateLoader2) {
        this(templateLoader2, new SoftCacheStorage());
    }

    public TemplateCache(TemplateLoader templateLoader2, CacheStorage cacheStorage) {
        this.delay = 5000;
        boolean z = true;
        this.localizedLookup = true;
        this.templateLoader = templateLoader2;
        this.storage = cacheStorage;
        if (cacheStorage != null) {
            this.isStorageConcurrent = (!(cacheStorage instanceof ConcurrentCacheStorage) || !((ConcurrentCacheStorage) cacheStorage).isConcurrent()) ? false : z;
            return;
        }
        throw new IllegalArgumentException("storage == null");
    }

    public void setConfiguration(Configuration configuration) {
        this.config = configuration;
        clear();
    }

    public TemplateLoader getTemplateLoader() {
        return this.templateLoader;
    }

    public CacheStorage getCacheStorage() {
        return this.storage;
    }

    public Template getTemplate(String str, Locale locale, String str2, boolean z) throws IOException {
        TemplateLoader templateLoader2;
        if (str == null) {
            throw new IllegalArgumentException("Argument \"name\" can't be null");
        } else if (locale == null) {
            throw new IllegalArgumentException("Argument \"locale\" can't be null");
        } else if (str2 != null) {
            String normalizeName = normalizeName(str);
            if (normalizeName == null || (templateLoader2 = this.templateLoader) == null) {
                return null;
            }
            return getTemplate(templateLoader2, normalizeName, locale, str2, z);
        } else {
            throw new IllegalArgumentException("Argument \"encoding\" can't be null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0269, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x026b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x026d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007b, code lost:
        r3 = null;
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0184, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0185, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0188, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0189, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x018d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0269 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:97:0x01e1] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x027a A[SYNTHETIC, Splitter:B:146:0x027a] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0283 A[Catch:{ RuntimeException -> 0x027e, IOException -> 0x0273, all -> 0x026f, all -> 0x0287 }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0184 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:15:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x018d A[ExcHandler: RuntimeException (e java.lang.RuntimeException), Splitter:B:15:0x0041] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:38:0x0086=Splitter:B:38:0x0086, B:43:0x00a9=Splitter:B:43:0x00a9} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private freemarker.template.Template getTemplate(freemarker.cache.TemplateLoader r20, java.lang.String r21, java.util.Locale r22, java.lang.String r23, boolean r24) throws java.io.IOException {
        /*
            r19 = this;
            r8 = r19
            r9 = r20
            r0 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            freemarker.log.Logger r1 = logger
            boolean r2 = r1.isDebugEnabled()
            if (r2 == 0) goto L_0x0019
            java.lang.String r7 = r8.buildDebugName(r0, r4, r5, r6)
            goto L_0x001a
        L_0x0019:
            r7 = 0
        L_0x001a:
            freemarker.cache.TemplateCache$TemplateKey r10 = new freemarker.cache.TemplateCache$TemplateKey
            r10.<init>(r0, r4, r5, r6)
            boolean r11 = r8.isStorageConcurrent
            if (r11 == 0) goto L_0x002c
            freemarker.cache.CacheStorage r11 = r8.storage
            java.lang.Object r11 = r11.get(r10)
            freemarker.cache.TemplateCache$CachedTemplate r11 = (freemarker.cache.TemplateCache.CachedTemplate) r11
            goto L_0x0039
        L_0x002c:
            freemarker.cache.CacheStorage r11 = r8.storage
            monitor-enter(r11)
            freemarker.cache.CacheStorage r12 = r8.storage     // Catch:{ all -> 0x028e }
            java.lang.Object r12 = r12.get(r10)     // Catch:{ all -> 0x028e }
            freemarker.cache.TemplateCache$CachedTemplate r12 = (freemarker.cache.TemplateCache.CachedTemplate) r12     // Catch:{ all -> 0x028e }
            monitor-exit(r11)     // Catch:{ all -> 0x028e }
            r11 = r12
        L_0x0039:
            long r12 = java.lang.System.currentTimeMillis()
            r16 = 0
            if (r11 == 0) goto L_0x0191
            long r14 = r11.lastChecked     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            long r14 = r12 - r14
            long r3 = r8.delay     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            int r18 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r18 >= 0) goto L_0x00ac
            if (r2 == 0) goto L_0x0061
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r0.<init>()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r0.append(r7)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r2 = " cached copy not yet stale; using cached."
            r0.append(r2)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r1.debug(r0)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
        L_0x0061:
            java.lang.Object r0 = r11.templateOrException     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            boolean r1 = r0 instanceof freemarker.template.Template     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            if (r1 != 0) goto L_0x00a9
            if (r0 != 0) goto L_0x006a
            goto L_0x00a9
        L_0x006a:
            boolean r1 = r0 instanceof java.lang.RuntimeException     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            if (r1 != 0) goto L_0x007f
            boolean r1 = r0 instanceof java.io.IOException     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            if (r1 == 0) goto L_0x0085
            r1 = r0
            java.io.IOException r1 = (java.io.IOException) r1     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x007a, all -> 0x0184 }
            r8.throwLoadFailedException(r1)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x007a, all -> 0x0184 }
            r14 = 1
            goto L_0x0086
        L_0x007a:
            r0 = move-exception
            r3 = 0
            r14 = 1
            goto L_0x0278
        L_0x007f:
            r1 = r0
            java.lang.RuntimeException r1 = (java.lang.RuntimeException) r1     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r8.throwLoadFailedException(r1)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
        L_0x0085:
            r14 = 0
        L_0x0086:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            r2.<init>()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            java.lang.String r3 = "t is "
            r2.append(r3)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            java.lang.Class r0 = r0.getClass()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            java.lang.String r0 = r0.getName()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            r2.append(r0)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            java.lang.String r0 = r2.toString()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            r1.<init>(r0)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
            throw r1     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x00a5, all -> 0x0184 }
        L_0x00a5:
            r0 = move-exception
            r3 = 0
            goto L_0x0278
        L_0x00a9:
            freemarker.template.Template r0 = (freemarker.template.Template) r0     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            return r0
        L_0x00ac:
            freemarker.cache.TemplateCache$CachedTemplate r11 = r11.cloneCachedTemplate()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r11.lastChecked = r12     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r4 = r22
            java.lang.Object r3 = r8.findTemplateSource(r0, r4)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            if (r3 != 0) goto L_0x00da
            if (r2 == 0) goto L_0x00d0
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r0.<init>()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r0.append(r7)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r2 = " no source found."
            r0.append(r2)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r1.debug(r0)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
        L_0x00d0:
            r1 = 0
            r8.storeNegativeLookup(r10, r11, r1)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            if (r3 == 0) goto L_0x00d9
            r9.closeTemplateSource(r3)
        L_0x00d9:
            return r1
        L_0x00da:
            long r12 = r9.getLastModified(r3)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            long r14 = r11.lastModified     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            int r17 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r17 != 0) goto L_0x00e6
            r14 = 1
            goto L_0x00e7
        L_0x00e6:
            r14 = 0
        L_0x00e7:
            java.lang.Object r15 = r11.source     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            boolean r15 = r3.equals(r15)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            if (r14 == 0) goto L_0x011c
            if (r15 == 0) goto L_0x011c
            if (r2 == 0) goto L_0x010f
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r0.<init>()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r0.append(r7)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r2 = " using cached since "
            r0.append(r2)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r0.append(r3)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r2 = " didn't change."
            r0.append(r2)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r0 = r0.toString()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r1.debug(r0)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
        L_0x010f:
            r8.storeCached(r10, r11)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.Object r0 = r11.templateOrException     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            freemarker.template.Template r0 = (freemarker.template.Template) r0     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            if (r3 == 0) goto L_0x011b
            r9.closeTemplateSource(r3)
        L_0x011b:
            return r0
        L_0x011c:
            if (r2 == 0) goto L_0x014e
            if (r15 != 0) goto L_0x014e
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.<init>()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = "Updating source, info for cause: sourceEquals="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r15)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = ", newlyFoundSource="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.Object) r3)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = ", cachedTemplate.source="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.Object r6 = r11.source     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.Object) r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r5 = r5.toString()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r1.debug(r5)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
        L_0x014e:
            if (r2 == 0) goto L_0x0178
            if (r14 != 0) goto L_0x0178
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.<init>()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = "Updating source, info for cause: lastModifiedNotChanged="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r14)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = ", cache lastModified="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            long r14 = r11.lastModified     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r14)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r6 = " != file lastModified="
            r5.append(r6)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r5.append(r12)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            java.lang.String r5 = r5.toString()     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r1.debug(r5)     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
        L_0x0178:
            r11.source = r3     // Catch:{ RuntimeException -> 0x0181, IOException -> 0x017f }
            r13 = r12
            r12 = r11
            r11 = r3
            goto L_0x01ff
        L_0x017f:
            r0 = move-exception
            goto L_0x018a
        L_0x0181:
            r0 = move-exception
            goto L_0x0281
        L_0x0184:
            r0 = move-exception
            r3 = 0
            goto L_0x0288
        L_0x0188:
            r0 = move-exception
            r3 = 0
        L_0x018a:
            r14 = 0
            goto L_0x0278
        L_0x018d:
            r0 = move-exception
            r3 = 0
            goto L_0x0281
        L_0x0191:
            if (r2 == 0) goto L_0x01de
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r3.<init>()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = "Could not find template in cache, creating new one; id=["
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = r10.name     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.String) r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = "["
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.util.Locale r5 = r10.locale     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.Object) r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = ","
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = r10.encoding     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            boolean r5 = r10.parse     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            if (r5 == 0) goto L_0x01cd
            java.lang.String r5 = ",parsed] "
            goto L_0x01cf
        L_0x01cd:
            java.lang.String r5 = ",unparsed] "
        L_0x01cf:
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r5 = "]"
            r3.append(r5)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            java.lang.String r3 = r3.toString()     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
            r1.debug(r3)     // Catch:{ RuntimeException -> 0x018d, IOException -> 0x0188, all -> 0x0184 }
        L_0x01de:
            freemarker.cache.TemplateCache$CachedTemplate r3 = new freemarker.cache.TemplateCache$CachedTemplate     // Catch:{ RuntimeException -> 0x027e, IOException -> 0x0273, all -> 0x026f }
            r5 = 0
            r3.<init>()     // Catch:{ RuntimeException -> 0x026d, IOException -> 0x026b, all -> 0x0269 }
            r3.lastChecked = r12     // Catch:{ RuntimeException -> 0x0266, IOException -> 0x0263, all -> 0x0269 }
            java.lang.Object r6 = r8.findTemplateSource(r0, r4)     // Catch:{ RuntimeException -> 0x0266, IOException -> 0x0263, all -> 0x0269 }
            if (r6 != 0) goto L_0x01f5
            r8.storeNegativeLookup(r10, r3, r5)     // Catch:{ RuntimeException -> 0x025f, IOException -> 0x025a, all -> 0x0257 }
            if (r6 == 0) goto L_0x01f4
            r9.closeTemplateSource(r6)
        L_0x01f4:
            return r5
        L_0x01f5:
            r3.source = r6     // Catch:{ RuntimeException -> 0x025f, IOException -> 0x025a, all -> 0x0257 }
            r11 = -9223372036854775808
            r3.lastModified = r11     // Catch:{ RuntimeException -> 0x025f, IOException -> 0x025a, all -> 0x0257 }
            r12 = r3
            r11 = r6
            r13 = -9223372036854775808
        L_0x01ff:
            if (r2 == 0) goto L_0x0221
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r2.<init>()     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            java.lang.String r3 = "Compiling FreeMarker template "
            r2.append(r3)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r2.append(r7)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            java.lang.String r3 = " from "
            r2.append(r3)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            java.lang.String r3 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.Object) r11)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r2.append(r3)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            java.lang.String r2 = r2.toString()     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r1.debug(r2)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
        L_0x0221:
            java.lang.Object r15 = r12.source     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = r15
            freemarker.template.Template r0 = r1.loadTemplate(r2, r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r12.templateOrException = r0     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r1 = -9223372036854775808
            int r3 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0240
            long r13 = r9.getLastModified(r15)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
        L_0x0240:
            r12.lastModified = r13     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            r8.storeCached(r10, r12)     // Catch:{ RuntimeException -> 0x0253, IOException -> 0x024e, all -> 0x024b }
            if (r11 == 0) goto L_0x024a
            r9.closeTemplateSource(r11)
        L_0x024a:
            return r0
        L_0x024b:
            r0 = move-exception
            r3 = r11
            goto L_0x0288
        L_0x024e:
            r0 = move-exception
            r3 = r11
            r11 = r12
            goto L_0x018a
        L_0x0253:
            r0 = move-exception
            r3 = r11
            r11 = r12
            goto L_0x0281
        L_0x0257:
            r0 = move-exception
            r3 = r6
            goto L_0x0288
        L_0x025a:
            r0 = move-exception
            r11 = r3
            r3 = r6
            goto L_0x018a
        L_0x025f:
            r0 = move-exception
            r11 = r3
            r3 = r6
            goto L_0x0281
        L_0x0263:
            r0 = move-exception
            r11 = r3
            goto L_0x0275
        L_0x0266:
            r0 = move-exception
            r11 = r3
            goto L_0x0280
        L_0x0269:
            r0 = move-exception
            goto L_0x0271
        L_0x026b:
            r0 = move-exception
            goto L_0x0275
        L_0x026d:
            r0 = move-exception
            goto L_0x0280
        L_0x026f:
            r0 = move-exception
            r5 = 0
        L_0x0271:
            r3 = r5
            goto L_0x0288
        L_0x0273:
            r0 = move-exception
            r5 = 0
        L_0x0275:
            r3 = r5
            goto L_0x018a
        L_0x0278:
            if (r14 != 0) goto L_0x027d
            r8.storeNegativeLookup(r10, r11, r0)     // Catch:{ all -> 0x0287 }
        L_0x027d:
            throw r0     // Catch:{ all -> 0x0287 }
        L_0x027e:
            r0 = move-exception
            r5 = 0
        L_0x0280:
            r3 = r5
        L_0x0281:
            if (r11 == 0) goto L_0x0286
            r8.storeNegativeLookup(r10, r11, r0)     // Catch:{ all -> 0x0287 }
        L_0x0286:
            throw r0     // Catch:{ all -> 0x0287 }
        L_0x0287:
            r0 = move-exception
        L_0x0288:
            if (r3 == 0) goto L_0x028d
            r9.closeTemplateSource(r3)
        L_0x028d:
            throw r0
        L_0x028e:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x028e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.cache.TemplateCache.getTemplate(freemarker.cache.TemplateLoader, java.lang.String, java.util.Locale, java.lang.String, boolean):freemarker.template.Template");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static final Method getInitCauseMethod() {
        try {
            Class cls = class$java$lang$Throwable;
            if (cls == null) {
                cls = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls;
            }
            Class[] clsArr = new Class[1];
            Class cls2 = class$java$lang$Throwable;
            if (cls2 == null) {
                cls2 = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls2;
            }
            clsArr[0] = cls2;
            return cls.getMethod("initCause", clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private void throwLoadFailedException(Exception exc) throws IOException {
        IOException iOException;
        Method method = INIT_CAUSE;
        if (method != null) {
            iOException = new IOException("There was an error loading the template on an earlier attempt; it's attached as a cause");
            try {
                method.invoke(iOException, new Object[]{exc});
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new UndeclaredThrowableException(e2);
            }
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("There was an error loading the template on an earlier attempt: ");
            stringBuffer.append(exc.getClass().getName());
            stringBuffer.append(": ");
            stringBuffer.append(exc.getMessage());
            iOException = new IOException(stringBuffer.toString());
        }
        throw iOException;
    }

    private void storeNegativeLookup(TemplateKey templateKey, CachedTemplate cachedTemplate, Exception exc) {
        cachedTemplate.templateOrException = exc;
        cachedTemplate.source = null;
        cachedTemplate.lastModified = 0;
        storeCached(templateKey, cachedTemplate);
    }

    private void storeCached(TemplateKey templateKey, CachedTemplate cachedTemplate) {
        if (this.isStorageConcurrent) {
            this.storage.put(templateKey, cachedTemplate);
            return;
        }
        synchronized (this.storage) {
            this.storage.put(templateKey, cachedTemplate);
        }
    }

    private Template loadTemplate(TemplateLoader templateLoader2, String str, Locale locale, String str2, boolean z, Object obj) throws IOException {
        Template template;
        Reader reader = templateLoader2.getReader(obj, str2);
        if (z) {
            try {
                template = new Template(str, reader, this.config, str2);
            } catch (Template.WrongEncodingException e) {
                str2 = e.specifiedEncoding;
                reader = templateLoader2.getReader(obj, str2);
                template = new Template(str, reader, this.config, str2);
            } catch (Throwable th) {
                reader.close();
                throw th;
            }
            template.setLocale(locale);
        } else {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[4096];
            while (true) {
                int read = reader.read(cArr);
                if (read > 0) {
                    stringWriter.write(cArr, 0, read);
                } else if (read == -1) {
                    break;
                }
            }
            template = Template.getPlainTextTemplate(str, stringWriter.toString(), this.config);
            template.setLocale(locale);
        }
        template.setEncoding(str2);
        reader.close();
        return template;
    }

    public synchronized long getDelay() {
        return this.delay;
    }

    public synchronized void setDelay(long j) {
        this.delay = j;
    }

    public synchronized boolean getLocalizedLookup() {
        return this.localizedLookup;
    }

    public synchronized void setLocalizedLookup(boolean z) {
        this.localizedLookup = z;
    }

    public void clear() {
        synchronized (this.storage) {
            this.storage.clear();
            TemplateLoader templateLoader2 = this.templateLoader;
            if (templateLoader2 instanceof StatefulTemplateLoader) {
                ((StatefulTemplateLoader) templateLoader2).resetState();
            }
        }
    }

    public void removeTemplate(String str, Locale locale, String str2, boolean z) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Argument \"name\" can't be null");
        } else if (locale == null) {
            throw new IllegalArgumentException("Argument \"locale\" can't be null");
        } else if (str2 != null) {
            String normalizeName = normalizeName(str);
            if (normalizeName != null && this.templateLoader != null) {
                Logger logger2 = logger;
                String buildDebugName = logger2.isDebugEnabled() ? buildDebugName(normalizeName, locale, str2, z) : null;
                TemplateKey templateKey = new TemplateKey(normalizeName, locale, str2, z);
                if (this.isStorageConcurrent) {
                    this.storage.remove(templateKey);
                } else {
                    synchronized (this.storage) {
                        this.storage.remove(templateKey);
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(buildDebugName);
                stringBuffer.append(" was removed from the cache, if it was there");
                logger2.debug(stringBuffer.toString());
            }
        } else {
            throw new IllegalArgumentException("Argument \"encoding\" can't be null");
        }
    }

    private String buildDebugName(String str, Locale locale, String str2, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtil.jQuoteNoXSS(str));
        stringBuffer.append("[");
        stringBuffer.append(StringUtil.jQuoteNoXSS((Object) locale));
        stringBuffer.append(",");
        stringBuffer.append(str2);
        stringBuffer.append(z ? ",parsed] " : ",unparsed]");
        return stringBuffer.toString();
    }

    public static String getFullTemplatePath(Environment environment, String str, String str2) {
        String str3;
        if (environment.isClassicCompatible() || str2.indexOf("://") > 0) {
            return str2;
        }
        if (str2.length() <= 0 || str2.charAt(0) != '/') {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(str2);
            return stringBuffer.toString();
        }
        int indexOf = str.indexOf("://");
        if (indexOf > 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str.substring(0, indexOf + 2));
            stringBuffer2.append(str2);
            str3 = stringBuffer2.toString();
        } else {
            str3 = str2.substring(1);
        }
        return str3;
    }

    private Object findTemplateSource(String str, Locale locale) throws IOException {
        String str2;
        String str3;
        if (!this.localizedLookup) {
            return acquireTemplateSource(str);
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            str2 = str;
        } else {
            str2 = str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1) {
            str3 = "";
        } else {
            str3 = str.substring(lastIndexOf);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(LOCALE_SEPARATOR);
        stringBuffer.append(locale.toString());
        String stringBuffer2 = stringBuffer.toString();
        StringBuffer stringBuffer3 = new StringBuffer(str.length() + stringBuffer2.length());
        stringBuffer3.append(str2);
        while (true) {
            stringBuffer3.setLength(str2.length());
            stringBuffer3.append(stringBuffer2);
            stringBuffer3.append(str3);
            Object acquireTemplateSource = acquireTemplateSource(stringBuffer3.toString());
            if (acquireTemplateSource != null) {
                return acquireTemplateSource;
            }
            int lastIndexOf2 = stringBuffer2.lastIndexOf(95);
            if (lastIndexOf2 == -1) {
                return null;
            }
            stringBuffer2 = stringBuffer2.substring(0, lastIndexOf2);
        }
    }

    private Object acquireTemplateSource(String str) throws IOException {
        if (str.indexOf(42) == -1) {
            return this.templateLoader.findTemplateSource(str);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/");
        ArrayList arrayList = new ArrayList();
        int i = -1;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(ASTERISKSTR)) {
                if (i != -1) {
                    arrayList.remove(i);
                }
                i = arrayList.size();
            }
            arrayList.add(nextToken);
        }
        if (i == -1) {
            return this.templateLoader.findTemplateSource(str);
        }
        String concatPath = concatPath(arrayList, 0, i);
        String concatPath2 = concatPath(arrayList, i + 1, arrayList.size());
        if (concatPath2.endsWith("/")) {
            concatPath2 = concatPath2.substring(0, concatPath2.length() - 1);
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        stringBuffer.append(concatPath);
        int length = concatPath.length();
        boolean isDebugEnabled = logger.isDebugEnabled();
        while (true) {
            stringBuffer.append(concatPath2);
            String stringBuffer2 = stringBuffer.toString();
            if (isDebugEnabled) {
                Logger logger2 = logger;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Trying to find template source ");
                stringBuffer3.append(StringUtil.jQuoteNoXSS(stringBuffer2));
                logger2.debug(stringBuffer3.toString());
            }
            Object findTemplateSource = this.templateLoader.findTemplateSource(stringBuffer2);
            if (findTemplateSource != null) {
                return findTemplateSource;
            }
            if (length == 0) {
                return null;
            }
            length = concatPath.lastIndexOf(47, length - 2) + 1;
            stringBuffer.setLength(length);
        }
    }

    private String concatPath(List list, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer((i2 - i) * 16);
        while (i < i2) {
            stringBuffer.append(list.get(i));
            stringBuffer.append(SLASH);
            i++;
        }
        return stringBuffer.toString();
    }

    private static String normalizeName(String str) {
        if (str.indexOf(0) != -1) {
            return null;
        }
        while (true) {
            int indexOf = str.indexOf(PARENT_DIR_PATH);
            if (indexOf == 0) {
                return null;
            }
            if (indexOf != -1) {
                int lastIndexOf = str.lastIndexOf(47, indexOf - 1);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str.substring(0, lastIndexOf + 1));
                stringBuffer.append(str.substring(indexOf + 4));
                str = stringBuffer.toString();
            } else if (str.startsWith(PARENT_DIR_PATH_PREFIX)) {
                return null;
            } else {
                while (true) {
                    int indexOf2 = str.indexOf(CURRENT_DIR_PATH);
                    if (indexOf2 == -1) {
                        break;
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str.substring(0, indexOf2));
                    stringBuffer2.append(str.substring((indexOf2 + 3) - 1));
                    str = stringBuffer2.toString();
                }
                if (str.startsWith(CURRENT_DIR_PATH_PREFIX)) {
                    str = str.substring(2);
                }
                if (str.length() <= 1 || str.charAt(0) != '/') {
                    return str;
                }
                return str.substring(1);
            }
        }
    }

    private static final class TemplateKey {
        /* access modifiers changed from: private */
        public final String encoding;
        /* access modifiers changed from: private */
        public final Locale locale;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final boolean parse;

        TemplateKey(String str, Locale locale2, String str2, boolean z) {
            this.name = str;
            this.locale = locale2;
            this.encoding = str2;
            this.parse = z;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TemplateKey)) {
                return false;
            }
            TemplateKey templateKey = (TemplateKey) obj;
            if (this.parse != templateKey.parse || !this.name.equals(templateKey.name) || !this.locale.equals(templateKey.locale) || !this.encoding.equals(templateKey.encoding)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((this.name.hashCode() ^ this.locale.hashCode()) ^ this.encoding.hashCode()) ^ (this.parse ? Boolean.FALSE : Boolean.TRUE).hashCode();
        }
    }

    private static final class CachedTemplate implements Cloneable, Serializable {
        private static final long serialVersionUID = 1;
        long lastChecked;
        long lastModified;
        Object source;
        Object templateOrException;

        private CachedTemplate() {
        }

        public CachedTemplate cloneCachedTemplate() {
            try {
                return (CachedTemplate) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    }
}
