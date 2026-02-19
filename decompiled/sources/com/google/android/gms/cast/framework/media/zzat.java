package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.R;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;

public final class zzat extends ArrayAdapter<MediaTrack> implements View.OnClickListener {
    private final Context mContext;
    private int zzfgn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzat(Context context, List<MediaTrack> list, int i) {
        super(context, R.layout.cast_tracks_chooser_dialog_row_layout, list == null ? new ArrayList<>() : list);
        this.zzfgn = -1;
        this.mContext = context;
        this.zzfgn = i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        zzav zzav;
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.cast_tracks_chooser_dialog_row_layout, viewGroup, false);
            zzav = new zzav(this, (TextView) view.findViewById(R.id.text), (RadioButton) view.findViewById(R.id.radio));
            view.setTag(zzav);
        } else {
            zzav = (zzav) view.getTag();
        }
        if (zzav == null) {
            return null;
        }
        zzav.zzfgp.setTag(Integer.valueOf(i));
        zzav.zzfgp.setChecked(this.zzfgn == i);
        view.setOnClickListener(this);
        MediaTrack mediaTrack = (MediaTrack) getItem(i);
        String name = mediaTrack.getName();
        if (TextUtils.isEmpty(name)) {
            if (mediaTrack.getSubtype() == 2) {
                name = this.mContext.getString(R.string.cast_tracks_chooser_dialog_closed_captions);
            } else {
                if (!TextUtils.isEmpty(mediaTrack.getLanguage())) {
                    String displayLanguage = MediaUtils.getTrackLanguage(mediaTrack).getDisplayLanguage();
                    if (!TextUtils.isEmpty(displayLanguage)) {
                        name = displayLanguage;
                    }
                }
                if (TextUtils.isEmpty(name)) {
                    name = this.mContext.getString(R.string.cast_tracks_chooser_dialog_default_track_name, new Object[]{Integer.valueOf(i + 1)});
                }
            }
        }
        zzav.zzfgo.setText(name);
        return view;
    }

    public final void onClick(View view) {
        this.zzfgn = ((Integer) ((zzav) view.getTag()).zzfgp.getTag()).intValue();
        notifyDataSetChanged();
    }

    public final MediaTrack zzafs() {
        int i = this.zzfgn;
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (MediaTrack) getItem(this.zzfgn);
    }
}
