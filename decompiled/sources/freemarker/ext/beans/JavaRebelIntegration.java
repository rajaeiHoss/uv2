package freemarker.ext.beans;

import java.lang.ref.WeakReference;
import org.zeroturnaround.javarebel.ClassEventListener;
import org.zeroturnaround.javarebel.ReloaderFactory;

class JavaRebelIntegration {
    JavaRebelIntegration() {
    }

    static void testAvailability() {
        ReloaderFactory.getInstance();
    }

    static void registerWrapper(BeansWrapper beansWrapper) {
        ReloaderFactory.getInstance().addClassReloadListener(new BeansWrapperCacheInvalidator(beansWrapper));
    }

    private static class BeansWrapperCacheInvalidator implements ClassEventListener {
        private final WeakReference ref;

        BeansWrapperCacheInvalidator(BeansWrapper beansWrapper) {
            this.ref = new WeakReference(beansWrapper);
        }

        public void onClassEvent(int i, Class cls) {
            BeansWrapper beansWrapper = (BeansWrapper) this.ref.get();
            if (beansWrapper == null) {
                ReloaderFactory.getInstance().removeClassReloadListener(this);
            } else if (i == 1) {
                beansWrapper.removeFromClassIntrospectionCache(cls);
            }
        }
    }
}
