package com.streamax.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.streamax.client.LocalRecordFileList;
import com.streamax.utils.AppProxy;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Playback menu hub: routes to remote, event, local, and capture views.
public class PlaybackActivity extends Activity {
    public static final int PageCaptureActivity = 4;
    public static final int PageEventActivity = 2;
    public static final int PageListActivity = 0;
    public static final int PageLocalActivity = 3;
    public static final int PageRemoteActivity = 1;
    public MyApp mApp;
    public int mCurrentPage = 0;
    public List<Map<String, Object>> mData;
    public EventPlayback mEventPlayback;
    public Handler mHandler;
    public ImageViewer mImageViewer;
    public LocalRecordFileList mLocalFileList;
    public View mLocalFileSelect;
    public View.OnClickListener mOnClickListener;
    public PlaybackAdapter mPlaybackAdapter;
    public ListView mPlaybackList;
    public View mPlaybackView;
    public RemotePlayback mRemotePlayback;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = AppProxy.getHandler();
        this.mApp = (MyApp) getApplication();
        this.mPlaybackView = LayoutInflater.from(this).inflate(R.layout.playbackpage, (ViewGroup) null);
        this.mData = getData();
        this.mPlaybackList = (ListView) this.mPlaybackView.findViewById(R.id.playback_listview);
        PlaybackAdapter playbackAdapter = new PlaybackAdapter(this);
        this.mPlaybackAdapter = playbackAdapter;
        this.mPlaybackList.setAdapter(playbackAdapter);
        this.mImageViewer = (ImageViewer) LayoutInflater.from(this).inflate(R.layout.imageviewer, (ViewGroup) null);
        this.mEventPlayback = (EventPlayback) LayoutInflater.from(this).inflate(R.layout.eventplayback, (ViewGroup) null);
        this.mRemotePlayback = (RemotePlayback) LayoutInflater.from(this).inflate(R.layout.remoteplayback, (ViewGroup) null);
        this.mLocalFileSelect = LayoutInflater.from(this).inflate(R.layout.localfileselect, (ViewGroup) null);
        setContentView(this.mPlaybackView);
        this.mImageViewer.FindViews();
        this.mRemotePlayback.FindViews();
        this.mRemotePlayback.SetActivity(this);
        this.mEventPlayback.FindViews();
        this.mEventPlayback.SetActivity(this);
        LocalRecordFileList localRecordFileList = (LocalRecordFileList) this.mLocalFileSelect.findViewById(R.id.localrecordfilelist);
        this.mLocalFileList = localRecordFileList;
        localRecordFileList.setActivity(this);
        this.mPlaybackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    Log.e("mCurrentPage:", "PageRemoteActivity");
                    PlaybackActivity.this.mCurrentPage = 1;
                    PlaybackActivity.this.mRemotePlayback.setVisibility(0);
                    PlaybackActivity playbackActivity = PlaybackActivity.this;
                    playbackActivity.setContentView(playbackActivity.mRemotePlayback);
                } else if (i == 1) {
                    Log.e("mCurrentPage:", "PageEventActivity");
                    PlaybackActivity.this.mCurrentPage = 2;
                    PlaybackActivity.this.mEventPlayback.setVisibility(0);
                    PlaybackActivity playbackActivity2 = PlaybackActivity.this;
                    playbackActivity2.setContentView(playbackActivity2.mEventPlayback);
                } else if (i == 2) {
                    Log.e("mCurrentPage:", "PageLocalActivity");
                    if (PlaybackActivity.this.mCurrentPage != 3) {
                        PlaybackActivity.this.mLocalFileList.refreshFiles();
                    }
                    PlaybackActivity.this.mCurrentPage = 3;
                    PlaybackActivity.this.mLocalFileSelect.setVisibility(0);
                    PlaybackActivity playbackActivity3 = PlaybackActivity.this;
                    playbackActivity3.setContentView(playbackActivity3.mLocalFileSelect);
                    PlaybackActivity.this.mLocalFileList.refreshAdapter();
                } else if (i == 3) {
                    Log.e("mCurrentPage:", "PageCaptureActivity");
                    if (PlaybackActivity.this.mCurrentPage != 4) {
                        PlaybackActivity.this.mImageViewer.refreshAdapter();
                    }
                    PlaybackActivity.this.mCurrentPage = 4;
                    PlaybackActivity playbackActivity4 = PlaybackActivity.this;
                    playbackActivity4.setContentView(playbackActivity4.mImageViewer);
                }
            }
        });
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.event_playback_title_cancel /*2131362375*/:
                        PlaybackActivity.this.mCurrentPage = 0;
                        PlaybackActivity playbackActivity = PlaybackActivity.this;
                        playbackActivity.setContentView(playbackActivity.mPlaybackView);
                        PlaybackActivity.this.mPlaybackAdapter.notifyDataSetChanged();
                        return;
                    case R.id.imageviewer_title_button_deleteCapture /*2131362471*/:
                        PlaybackActivity.this.deleteCurPic();
                        return;
                    case R.id.localfile_title_confirm /*2131362548*/:
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_edit).setVisibility(0);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_playback).setVisibility(0);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_delete).setVisibility(8);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_confirm).setVisibility(8);
                        PlaybackActivity.this.mLocalFileList.setEditState(false);
                        return;
                    case R.id.localfile_title_delete /*2131362549*/:
                        if (FileUtils.getSelCount() == 0) {
                            new AlertDialog.Builder(PlaybackActivity.this).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.pleaseselectfile).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LogUtils.log(getClass(), "FileUtils.getSelCount() == 0");
                                }
                            }).show();
                            return;
                        } else {
                            new AlertDialog.Builder(PlaybackActivity.this).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.confirm_delete).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    PlaybackActivity.this.mLocalFileList.deleteSelectFile();
                                    PlaybackActivity.this.mLocalFileList.mAdapter.notifyDataSetChanged();
                                }
                            }).show();
                            return;
                        }
                    case R.id.localfile_title_edit /*2131362550*/:
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_edit).setVisibility(8);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_playback).setVisibility(8);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_delete).setVisibility(0);
                        PlaybackActivity.this.mLocalFileSelect.findViewById(R.id.localfile_title_confirm).setVisibility(0);
                        PlaybackActivity.this.mLocalFileList.setEditState(true);
                        return;
                    case R.id.localfile_title_playback /*2131362551*/:
                        PlaybackActivity.this.mCurrentPage = 0;
                        PlaybackActivity playbackActivity2 = PlaybackActivity.this;
                        playbackActivity2.setContentView(playbackActivity2.mPlaybackView);
                        PlaybackActivity.this.mPlaybackAdapter.notifyDataSetChanged();
                        return;
                    case R.id.preview_title_button_cancel /*2131362910*/:
                        PlaybackActivity.this.mCurrentPage = 0;
                        PlaybackActivity playbackActivity3 = PlaybackActivity.this;
                        playbackActivity3.setContentView(playbackActivity3.mPlaybackView);
                        PlaybackActivity.this.mPlaybackAdapter.notifyDataSetChanged();
                        return;
                    case R.id.remote_playback_title_cancel /*2131362969*/:
                        PlaybackActivity.this.mCurrentPage = 0;
                        PlaybackActivity playbackActivity4 = PlaybackActivity.this;
                        playbackActivity4.setContentView(playbackActivity4.mPlaybackView);
                        PlaybackActivity.this.mRemotePlayback.Exit();
                        PlaybackActivity.this.mPlaybackAdapter.notifyDataSetChanged();
                        return;
                    default:
                        return;
                }
            }
        };
        this.mEventPlayback.findViewById(R.id.event_playback_title_cancel).setOnClickListener(this.mOnClickListener);
        this.mImageViewer.findViewById(R.id.preview_title_button_cancel).setOnClickListener(this.mOnClickListener);
        this.mImageViewer.findViewById(R.id.imageviewer_title_button_deleteCapture).setOnClickListener(this.mOnClickListener);
        this.mRemotePlayback.findViewById(R.id.remote_playback_title_cancel).setOnClickListener(this.mOnClickListener);
        this.mLocalFileSelect.findViewById(R.id.localfile_title_playback).setOnClickListener(this.mOnClickListener);
        this.mLocalFileSelect.findViewById(R.id.localfile_title_edit).setOnClickListener(this.mOnClickListener);
        this.mLocalFileSelect.findViewById(R.id.localfile_title_delete).setOnClickListener(this.mOnClickListener);
        this.mLocalFileSelect.findViewById(R.id.localfile_title_confirm).setOnClickListener(this.mOnClickListener);
        this.mLocalFileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!PlaybackActivity.this.mLocalFileList.getEditState()) {
                    String str = ((LocalRecordFileList.ViewHolder) view.getTag()).path;
                    Intent intent = new Intent(PlaybackActivity.this, LocalPlaybackActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("path", str);
                    intent.putExtras(bundle);
                    PlaybackActivity.this.startActivity(intent);
                }
            }
        });
        if (this.mApp.mPushInfo != null) {
            startActivity(new Intent(this, EventPlaybackActivity.class));
        }
    }

    private List<Map<String, Object>> getData() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("image", Integer.valueOf(R.drawable.playback_remote));
        hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getString(R.string.remote_playback));
        hashMap.put(FirebaseAnalytics.Param.CONTENT, getString(R.string.remote_playback_content));
        arrayList.add(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("image", Integer.valueOf(R.drawable.playbackevent));
        hashMap2.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getString(R.string.eventplayback));
        hashMap2.put(FirebaseAnalytics.Param.CONTENT, getString(R.string.event_playback_content));
        arrayList.add(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("image", Integer.valueOf(R.drawable.playback_local));
        hashMap3.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getString(R.string.local_playback));
        hashMap3.put(FirebaseAnalytics.Param.CONTENT, getString(R.string.local_playback_content));
        arrayList.add(hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("image", Integer.valueOf(R.drawable.playback_image));
        hashMap4.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getString(R.string.image_playback));
        hashMap4.put(FirebaseAnalytics.Param.CONTENT, getString(R.string.image_playback_content));
        arrayList.add(hashMap4);
        return arrayList;
    }

    public class ViewHolder {
        public TextView ccontent;
        public ImageView img;
        public TextView title;

        public ViewHolder() {
        }
    }

    public class PlaybackAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public long getItemId(int i) {
            return (long) i;
        }

        public PlaybackAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return PlaybackActivity.this.mData.size();
        }

        public Object getItem(int i) {
            return PlaybackActivity.this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.playback_item, (ViewGroup) null);
                viewHolder.img = (ImageView) view2.findViewById(R.id.playback_item_image);
                viewHolder.title = (TextView) view2.findViewById(R.id.playback_item_title);
                viewHolder.ccontent = (TextView) view2.findViewById(R.id.playback_item_content);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.img.setBackgroundResource(((Integer) PlaybackActivity.this.mData.get(i).get("image")).intValue());
            viewHolder.title.setText((String) PlaybackActivity.this.mData.get(i).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
            viewHolder.ccontent.setText((String) PlaybackActivity.this.mData.get(i).get(FirebaseAnalytics.Param.CONTENT));
            return view2;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        int i = this.mCurrentPage;
        if (i == 3) {
            this.mLocalFileList.refreshFiles();
            this.mLocalFileList.refreshAdapter();
        } else if (i == 4) {
            this.mImageViewer.refreshAdapter();
        }
        super.onResume();
    }

    public void deleteCurPic() {
        String curPicPath = ImageUtils.getCurPicPath(this.mImageViewer.mPosition);
        if (!TextUtils.isEmpty(curPicPath)) {
            File file = new File(curPicPath);
            if (file.exists() && file.delete()) {
                this.mImageViewer.mPosition = 0;
                this.mImageViewer.mImageSwitcher.setImageDrawable((Drawable) null);
                ImageUtils.updateFileList();
                Collections.reverse(ImageUtils.fileList);
                ((BaseAdapter) this.mImageViewer.mGallery.getAdapter()).notifyDataSetChanged();
            }
        }
    }
}
