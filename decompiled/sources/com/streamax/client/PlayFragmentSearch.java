package com.streamax.client;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.dvr.net.RemoteFileInfo;
import com.hjq.base.BaseDialog;
import com.streamax.client.bean.PlayFileInfo;
import com.streamax.client.fragment.FragmentBase;
import com.streamax.client.ui.dialog.DateDialog;
import com.streamax.client.ui.dialog.TimeDialog;
import com.streamax.utils.AppProxy;
import com.streamax.utils.TimeUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Fragment for searching/filtering remote playback files.
public class PlayFragmentSearch extends FragmentBase implements View.OnClickListener {
    public PlayActivity mActivity;
    public Button mBtnSearch;
    public Handler mHandler = AppProxy.getHandler();
    public JSONObject mJsonObj;
    public ArrayList<Integer> mListChannelCase = new ArrayList<>();
    public ArrayList<String> mListChannelName = new ArrayList<>();
    public ArrayList<Integer> mListDiskTypeCase = new ArrayList<>();
    public ArrayList<String> mListDiskTypeName = new ArrayList<>();
    public ArrayList<Integer> mListRecordTypeCase = new ArrayList<>();
    public ArrayList<String> mListRecordTypeName = new ArrayList<>();
    public ProgressBar mProgressBar;
    public View mRootView;
    public PlaySearchRunnable mSearchRunnable = null;
    public String mTitle;
    public VsTextView mTvChannel;
    public VsTextView mTvDate;
    public VsTextView mTvDiskType;
    public VsTextView mTvEndTime;
    public VsTextView mTvRecordType;
    public VsTextView mTvStartTime;

    public static PlayFragmentSearch getInstance(String str, PlayActivity playActivity) {
        PlayFragmentSearch playFragmentSearch = new PlayFragmentSearch();
        playFragmentSearch.mTitle = str;
        playFragmentSearch.mActivity = playActivity;
        playFragmentSearch.restData();
        return playFragmentSearch;
    }

    private void restData() {
        this.mJsonObj = new JSONObject();
        String string = UiUtils.getString(R.string.channel);
        int i = 0;
        while (i < this.mActivity.mApp.mDevInfo.mChCounts) {
            int i2 = i + 1;
            Integer valueOf = Integer.valueOf(i2);
            ArrayList<String> arrayList = this.mListChannelName;
            arrayList.add(string + valueOf);
            this.mListChannelCase.add(Integer.valueOf(i));
            i = i2;
        }
        this.mListRecordTypeName.addAll(getStrDatas(R.array.play_search_record_type_name));
        this.mListRecordTypeCase.addAll(getIntDatas(R.array.play_search_record_type_case));
        this.mListDiskTypeName.addAll(getStrDatas(R.array.play_search_disk_type_name));
        this.mListDiskTypeCase.addAll(getIntDatas(R.array.play_search_disk_type_case));
        try {
            if (this.mListChannelCase.size() > 0) {
                this.mJsonObj.put("Channel", this.mListChannelCase.get(0));
            }
            if (this.mListRecordTypeCase.size() > 0) {
                this.mJsonObj.put("RecordType", this.mListRecordTypeCase.get(0));
            }
            if (this.mListDiskTypeCase.size() > 0) {
                this.mJsonObj.put("DiskType", this.mListDiskTypeCase.get(0));
            }
            this.mJsonObj.put("Date", TimeUtils.getTime(new Date(), "yyyy-MM-dd"));
            this.mJsonObj.put("StartTime", "00:00:00");
            this.mJsonObj.put("EndTime", "23:59:59");
        } catch (Exception unused) {
        }
    }

    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.play_fragment_search, (ViewGroup) null);
        this.mRootView = inflate;
        this.mTvChannel = (VsTextView) inflate.findViewById(R.id.play_search_tv_channel);
        this.mTvRecordType = (VsTextView) this.mRootView.findViewById(R.id.play_search_tv_record_type);
        this.mTvDiskType = (VsTextView) this.mRootView.findViewById(R.id.play_search_tv_disk_type);
        this.mTvDate = (VsTextView) this.mRootView.findViewById(R.id.play_search_tv_date);
        this.mTvStartTime = (VsTextView) this.mRootView.findViewById(R.id.play_search_tv_start_time);
        this.mTvEndTime = (VsTextView) this.mRootView.findViewById(R.id.play_search_tv_end_time);
        ProgressBar progressBar = (ProgressBar) this.mRootView.findViewById(R.id.play_search_busy);
        this.mProgressBar = progressBar;
        progressBar.setVisibility(8);
        this.mBtnSearch = (Button) this.mRootView.findViewById(R.id.play_search_button_search);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        refreshUi();
    }

    public void refreshUi() {
        int i;
        try {
            Integer valueOf = Integer.valueOf(this.mJsonObj.getInt("Channel"));
            Integer valueOf2 = Integer.valueOf(this.mJsonObj.getInt("RecordType"));
            Integer valueOf3 = Integer.valueOf(this.mJsonObj.getInt("DiskType"));
            String string = this.mJsonObj.getString("Date");
            String string2 = this.mJsonObj.getString("StartTime");
            String string3 = this.mJsonObj.getString("EndTime");
            int i2 = 0;
            int i3 = 0;
            while (true) {
                i = -1;
                if (i3 >= this.mListChannelCase.size()) {
                    i3 = -1;
                    break;
                } else if (valueOf == this.mListChannelCase.get(i3)) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 >= 0) {
                this.mTvChannel.setText(this.mListChannelName.get(i3));
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.mListRecordTypeCase.size()) {
                    i4 = -1;
                    break;
                } else if (valueOf2 == this.mListRecordTypeCase.get(i4)) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 >= 0) {
                this.mTvRecordType.setText(this.mListRecordTypeName.get(i4));
            }
            while (true) {
                if (i2 >= this.mListDiskTypeCase.size()) {
                    break;
                } else if (valueOf3 == this.mListDiskTypeCase.get(i2)) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i >= 0) {
                this.mTvDiskType.setText(this.mListDiskTypeName.get(i));
            }
            this.mTvDate.setText(string);
            this.mTvStartTime.setText(string2);
            this.mTvEndTime.setText(string3);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mTvChannel.setOnClickListener(this);
        this.mTvRecordType.setOnClickListener(this);
        this.mTvDiskType.setOnClickListener(this);
        this.mTvDate.setOnClickListener(this);
        this.mTvStartTime.setOnClickListener(this);
        this.mTvEndTime.setOnClickListener(this);
        this.mBtnSearch.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.play_search_button_search) {
            switch (id) {
                case R.id.play_search_tv_channel /*2131362878*/:
                    pushFragmentForChannel();
                    return;
                case R.id.play_search_tv_date /*2131362879*/:
                    showDateDialog();
                    return;
                case R.id.play_search_tv_disk_type /*2131362880*/:
                    pushFragmentForDiskType();
                    return;
                case R.id.play_search_tv_end_time /*2131362881*/:
                    showTimeDialog(false);
                    return;
                case R.id.play_search_tv_record_type /*2131362882*/:
                    pushFragmentForRecordType();
                    return;
                case R.id.play_search_tv_start_time /*2131362883*/:
                    showTimeDialog(true);
                    return;
                default:
                    return;
            }
        } else {
            startSearch();
        }
    }

    public void pushFragmentForChannel() {
        pushSelectFragment(UiUtils.getString(R.string.channel), "FragmentSelectForChannel", 0, this.mListChannelName, (List<Integer>) null);
    }

    public void pushFragmentForRecordType() {
        pushSelectFragment(UiUtils.getString(R.string.config_record_RecordType), "FragmentSelectForRecordType", 0, this.mListRecordTypeName, (List<Integer>) null);
    }

    public void pushFragmentForDiskType() {
        pushSelectFragment(UiUtils.getString(R.string.disk_type), "FragmentSelectForDiskType", 0, this.mListDiskTypeName, (List<Integer>) null);
    }

    public void showDateDialog() {
        String str;
        try {
            str = this.mJsonObj.getString("Date");
        } catch (Exception unused) {
            str = "";
        }
        ((DateDialog.Builder) ((DateDialog.Builder) ((DateDialog.Builder) new DateDialog.Builder(getContext()).setTitle((CharSequence) getString(R.string.date_title))).setConfirm((CharSequence) getString(R.string.common_confirm))).setCancel((CharSequence) getString(R.string.common_cancel))).setDate(str).setListener(new DateDialog.OnListener() {
            public void onCancel(BaseDialog baseDialog) {
            }

            public void onSelected(BaseDialog baseDialog, int i, int i2, int i3) {
                try {
                    PlayFragmentSearch.this.mJsonObj.put("Date", String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
                } catch (Exception unused) {
                }
                PlayFragmentSearch.this.refreshUi();
            }
        }).show();
    }

    public void showTimeDialog(final boolean z) {
        String str;
        if (z) {
            try {
                str = this.mJsonObj.getString("StartTime");
            } catch (Exception unused) {
                str = "";
            }
        } else {
            try {
                str = this.mJsonObj.getString("EndTime");
            } catch (Exception unused2) {
                str = "";
            }
        }
        ((TimeDialog.Builder) ((TimeDialog.Builder) ((TimeDialog.Builder) new TimeDialog.Builder(getContext()).setTitle((CharSequence) getString(R.string.time_title))).setConfirm((CharSequence) getString(R.string.common_confirm))).setCancel((CharSequence) getString(R.string.common_cancel))).setTime(str).setListener(new TimeDialog.OnListener() {
            public void onCancel(BaseDialog baseDialog) {
            }

            public void onSelected(BaseDialog baseDialog, int i, int i2, int i3) {
                try {
                    String format = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
                    if (z) {
                        PlayFragmentSearch.this.mJsonObj.put("StartTime", format);
                    } else {
                        PlayFragmentSearch.this.mJsonObj.put("EndTime", format);
                    }
                } catch (Exception unused) {
                }
                PlayFragmentSearch.this.refreshUi();
            }
        }).show();
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("FragmentSelectForChannel")) {
            if (list.size() > 0) {
                updateDateForSelect("Channel", this.mListChannelCase, list.get(0).intValue());
            }
        } else if (str.equals("FragmentSelectForRecordType")) {
            if (list.size() > 0) {
                updateDateForSelect("RecordType", this.mListRecordTypeCase, list.get(0).intValue());
            }
        } else if (str.equals("FragmentSelectForDiskType") && list.size() > 0) {
            updateDateForSelect("DiskType", this.mListDiskTypeCase, list.get(0).intValue());
        }
    }

    public void updateDateForSelect(String str, ArrayList<Integer> arrayList, int i) {
        if (i < arrayList.size()) {
            try {
                this.mJsonObj.put(str, arrayList.get(i));
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void startSearch() {
        String str;
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        String str3;
        int i5;
        String str4;
        int intValue;
        if (this.mActivity.mDvrNet == null) {
            toastSf((int) R.string.connect_error);
        } else if (this.mActivity.mConnectRunnable != null) {
            toastSf((int) R.string.is_connecting);
        } else if (this.mSearchRunnable != null) {
            toastSf((int) R.string.is_searching);
        } else {
            i3 = 0;
            i2 = 65535;
            i = 65535;
            str2 = "";
            str = "";
            try {
                int channel = this.mJsonObj.getInt("Channel");
                int recordType = this.mJsonObj.getInt("RecordType");
                int diskType = this.mJsonObj.getInt("DiskType");
                String date = this.mJsonObj.getString("Date");
                String startTime = this.mJsonObj.getString("StartTime");
                String endTime = this.mJsonObj.getString("EndTime");
                i3 = diskType;
                i2 = recordType != 0 ? 1 << (recordType - 1) : 65535;
                i = 1 << channel;
                str2 = date.replace("-", "") + startTime.replace(":", "");
                str = date.replace("-", "") + endTime.replace(":", "");
            } catch (Exception unused) {
            }
            this.mProgressBar.setVisibility(0);
            this.mSearchRunnable = new PlaySearchRunnable(this.mActivity, this, i3, i2, 1, i, str2, str);
            new Thread(this.mSearchRunnable).start();
        }
    }

    public class PlaySearchRunnable implements Runnable {
        PlayActivity mActivity;
        int mChannelBits;
        int mDisk;
        String mEndTime;
        RemoteFileInfo[] mFileInfo = null;
        int mFileType;
        public FragmentBase mFragment;
        String mStartTime;
        int mStream;

        PlaySearchRunnable(PlayActivity playActivity, FragmentBase fragmentBase, int i, int i2, int i3, int i4, String str, String str2) {
            this.mActivity = playActivity;
            this.mFragment = fragmentBase;
            this.mDisk = i;
            this.mFileType = i2;
            this.mStream = i3;
            this.mChannelBits = i4;
            this.mStartTime = str;
            this.mEndTime = str2;
        }

        /* access modifiers changed from: private */
        public char[] getChars(byte[] bArr) {
            Charset forName = Charset.forName("UTF-8");
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
            allocate.put(bArr);
            allocate.flip();
            return forName.decode(allocate).array();
        }

        public void run() {
            if (this.mActivity.mDvrNet != null) {
                RemoteFileInfo[] SearchVideoFileList = this.mActivity.mDvrNet.SearchVideoFileList(this.mDisk, this.mFileType, this.mStream, this.mChannelBits, this.mStartTime, this.mEndTime);
                this.mFileInfo = SearchVideoFileList;
                if (SearchVideoFileList == null || SearchVideoFileList.length == 0) {
                    PlayFragmentSearch.this.mHandler.post(new Runnable() {
                        public void run() {
                            PlayFragmentSearch.this.mSearchRunnable = null;
                            PlayFragmentSearch.this.mProgressBar.setVisibility(8);
                            PlayFragmentSearch.this.toastSf((int) R.string.search_no_find_file);
                        }
                    });
                } else {
                    PlayFragmentSearch.this.mHandler.post(new Runnable() {
                        public void run() {
                            String str;
                            PlayFragmentFile instance = PlayFragmentFile.getInstance(UiUtils.getString(R.string.file), PlaySearchRunnable.this.mActivity);
                            instance.mPopFragment = PlaySearchRunnable.this.mFragment;
                            if (instance.mFileList == null) {
                                instance.mFileList = new ArrayList();
                            } else {
                                instance.mFileList.clear();
                            }
                            for (int i = 0; i < PlaySearchRunnable.this.mFileInfo.length; i++) {
                                PlayFileInfo playFileInfo = new PlayFileInfo();
                                playFileInfo.nDiskType = PlaySearchRunnable.this.mFileInfo[i].nDiskType;
                                playFileInfo.FileTime = PlaySearchRunnable.this.mFileInfo[i].FileTime;
                                playFileInfo.name = PlaySearchRunnable.this.mFileInfo[i].name;
                                playFileInfo.nFileSize = PlaySearchRunnable.this.mFileInfo[i].nFileSize;
                                playFileInfo.nChannel = PlaySearchRunnable.this.mFileInfo[i].nChannel;
                                playFileInfo.nType = PlaySearchRunnable.this.mFileInfo[i].nType;
                                if (1 == ((PlaySearchRunnable.this.mFileInfo[i].nType >> 0) & 1)) {
                                    str = UiUtils.getString(R.string.normal_record);
                                } else {
                                    str = 1 == ((PlaySearchRunnable.this.mFileInfo[i].nType >> 1) & 1) ? UiUtils.getString(R.string.alarm_record) : "";
                                }
                                String str2 = playFileInfo.FileTime;
                                if (playFileInfo.FileTime.length() >= 29) {
                                    char[] access$100 = PlaySearchRunnable.this.getChars(playFileInfo.FileTime.getBytes());
                                    str2 = String.format("%s %s:%s:%s - %s:%s:%s", new Object[]{String.format("%s-%s-%s", new Object[]{String.valueOf(access$100, 0, 4), String.valueOf(access$100, 4, 2), String.valueOf(access$100, 6, 2)}), String.valueOf(access$100, 8, 2), String.valueOf(access$100, 10, 2), String.valueOf(access$100, 12, 2), String.valueOf(access$100, 23, 2), String.valueOf(access$100, 25, 2), String.valueOf(access$100, 27, 2)});
                                }
                                playFileInfo.mTitle = String.format("%s%d %s", new Object[]{UiUtils.getString(R.string.channel), Integer.valueOf(PlaySearchRunnable.this.mFileInfo[i].nChannel), str});
                                playFileInfo.mSubTitle = str2;
                                instance.mFileList.add(playFileInfo);
                            }
                            PlayFragmentSearch.this.mProgressBar.setVisibility(8);
                            ((PlayActivity) PlayFragmentSearch.this.getActivity()).refreshPager(instance);
                            PlayFragmentSearch.this.mSearchRunnable = null;
                        }
                    });
                }
            }
        }
    }
}
