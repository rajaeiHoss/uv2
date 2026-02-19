package com.google.android.gms.internal;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.List;

public final class zzbbw extends UIController {
    private final TextView mView;
    private final List<String> zzfhz;

    public zzbbw(TextView textView, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.zzfhz = arrayList;
        this.mView = textView;
        arrayList.addAll(list);
    }

    public final void onMediaStatusUpdated() {
        MediaQueueItem preloadedItem;
        MediaInfo media;
        MediaMetadata metadata;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (preloadedItem = remoteMediaClient.getPreloadedItem()) != null && (media = preloadedItem.getMedia()) != null && (metadata = media.getMetadata()) != null) {
            for (String next : this.zzfhz) {
                if (metadata.containsKey(next)) {
                    this.mView.setText(metadata.getString(next));
                    return;
                }
            }
            this.mView.setText("");
        }
    }
}
