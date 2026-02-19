package com.google.android.gms.cast.framework.media;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.R;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TracksChooserDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public Dialog mDialog;
    private long[] zzewl;
    private List<MediaTrack> zzfgi;
    private List<MediaTrack> zzfgj;

    public static TracksChooserDialogFragment newInstance(MediaInfo mediaInfo, long[] jArr) {
        List<MediaTrack> mediaTracks;
        if (mediaInfo == null || (mediaTracks = mediaInfo.getMediaTracks()) == null) {
            return null;
        }
        ArrayList<MediaTrack> zza = zza(mediaTracks, 2);
        ArrayList<MediaTrack> zza2 = zza(mediaTracks, 1);
        if (zza.size() <= 1 && zza2.isEmpty()) {
            return null;
        }
        TracksChooserDialogFragment tracksChooserDialogFragment = new TracksChooserDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("extra_tracks_type_audio", zza);
        bundle.putParcelableArrayList("extra_tracks_type_text", zza2);
        bundle.putLongArray("extra_active_track_ids", jArr);
        tracksChooserDialogFragment.setArguments(bundle);
        return tracksChooserDialogFragment;
    }

    private static int zza(List<MediaTrack> list, long[] jArr, int i) {
        if (!(jArr == null || list == null)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                for (long j : jArr) {
                    if (j == list.get(i2).getId()) {
                        return i2;
                    }
                }
            }
        }
        return i;
    }

    private static ArrayList<MediaTrack> zza(List<MediaTrack> list, int i) {
        ArrayList<MediaTrack> arrayList = new ArrayList<>();
        if (list != null) {
            for (MediaTrack next : list) {
                if (next.getType() == i) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zza(zzat zzat, zzat zzat2) {
        RemoteMediaClient remoteMediaClient;
        CastSession currentCastSession = CastContext.getSharedInstance(getContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && currentCastSession.isConnected() && (remoteMediaClient = currentCastSession.getRemoteMediaClient()) != null && remoteMediaClient.hasMediaSession()) {
            ArrayList arrayList = new ArrayList();
            MediaTrack zzafs = zzat.zzafs();
            if (!(zzafs == null || zzafs.getId() == -1)) {
                arrayList.add(Long.valueOf(zzafs.getId()));
            }
            MediaTrack zzafs2 = zzat2.zzafs();
            if (zzafs2 != null) {
                arrayList.add(Long.valueOf(zzafs2.getId()));
            }
            long[] activeTrackIds = remoteMediaClient.getMediaStatus().getActiveTrackIds();
            if (activeTrackIds != null && activeTrackIds.length > 0) {
                HashSet hashSet = new HashSet();
                for (MediaTrack id : this.zzfgj) {
                    hashSet.add(Long.valueOf(id.getId()));
                }
                for (MediaTrack id2 : this.zzfgi) {
                    hashSet.add(Long.valueOf(id2.getId()));
                }
                for (long j : activeTrackIds) {
                    if (!hashSet.contains(Long.valueOf(j))) {
                        arrayList.add(Long.valueOf(j));
                    }
                }
            }
            long[] jArr = new long[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                jArr[i] = ((Long) arrayList.get(i)).longValue();
            }
            Arrays.sort(jArr);
            remoteMediaClient.setActiveMediaTracks(jArr);
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                dialog.cancel();
                this.mDialog = null;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("extra_tracks_type_text");
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            ArrayList arrayList = new ArrayList(parcelableArrayList);
            this.zzfgi = arrayList;
            arrayList.add(0, new MediaTrack.Builder(-1, 1).setName(getActivity().getString(R.string.cast_tracks_chooser_dialog_none)).setSubtype(2).setContentId("").build());
        }
        this.zzfgj = getArguments().getParcelableArrayList("extra_tracks_type_audio");
        this.zzewl = getArguments().getLongArray("extra_active_track_ids");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int zza = zza(this.zzfgi, this.zzewl, 0);
        int zza2 = zza(this.zzfgj, this.zzewl, -1);
        zzat zzat = new zzat(getActivity(), this.zzfgi, zza);
        zzat zzat2 = new zzat(getActivity(), this.zzfgj, zza2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.cast_tracks_chooser_dialog_layout, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.text_list_view);
        ListView listView2 = (ListView) inflate.findViewById(R.id.audio_list_view);
        TabHost tabHost = (TabHost) inflate.findViewById(R.id.tab_host);
        tabHost.setup();
        if (zzat.getCount() == 0) {
            listView.setVisibility(4);
        } else {
            listView.setAdapter(zzat);
            TabHost.TabSpec newTabSpec = tabHost.newTabSpec("textTab");
            newTabSpec.setContent(R.id.text_list_view);
            newTabSpec.setIndicator(getActivity().getString(R.string.cast_tracks_chooser_dialog_subtitles));
            tabHost.addTab(newTabSpec);
        }
        if (zzat2.getCount() <= 1) {
            listView2.setVisibility(4);
        } else {
            listView2.setAdapter(zzat2);
            TabHost.TabSpec newTabSpec2 = tabHost.newTabSpec("audioTab");
            newTabSpec2.setContent(R.id.audio_list_view);
            newTabSpec2.setIndicator(getActivity().getString(R.string.cast_tracks_chooser_dialog_audio));
            tabHost.addTab(newTabSpec2);
        }
        builder.setView(inflate).setPositiveButton(getActivity().getString(R.string.cast_tracks_chooser_dialog_ok), new zzas(this, zzat, zzat2)).setNegativeButton(R.string.cast_tracks_chooser_dialog_cancel, new zzar(this));
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.cancel();
            this.mDialog = null;
        }
        AlertDialog create = builder.create();
        this.mDialog = create;
        return create;
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage((Message) null);
        }
        super.onDestroyView();
    }
}
