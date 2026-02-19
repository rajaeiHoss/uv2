package com.google.android.gms.internal;

import android.os.Environment;
import java.util.concurrent.Callable;

final class zznv implements Callable<Boolean> {
    zznv() {
    }

    public final Boolean call() throws Exception {
        return Boolean.valueOf("mounted".equals(Environment.getExternalStorageState()));
    }
}
