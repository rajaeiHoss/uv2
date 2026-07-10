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

    public static PlayFragmentSearch getInstance(String title, PlayActivity playActivity) {
        PlayFragmentSearch playFragmentSearch = new PlayFragmentSearch();
        playFragmentSearch.mTitle = title;
        playFragmentSearch.mActivity = playActivity;
        playFragmentSearch.restData();
        return playFragmentSearch;
    }

    private void restData() {
        this.mJsonObj = new JSONObject();
        String channelLabel = UiUtils.getString(R.string.channel);
        int channelIndex = 0;
        while (channelIndex < this.mActivity.mApp.mDevInfo.mChCounts) {
            int displayChannel = channelIndex + 1;
            this.mListChannelName.add(channelLabel + Integer.valueOf(displayChannel));
            this.mListChannelCase.add(Integer.valueOf(channelIndex));
            channelIndex = displayChannel;
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
        try {
            Integer channel = Integer.valueOf(this.mJsonObj.getInt("Channel"));
            Integer recordType = Integer.valueOf(this.mJsonObj.getInt("RecordType"));
            Integer diskType = Integer.valueOf(this.mJsonObj.getInt("DiskType"));
            String date = this.mJsonObj.getString("Date");
            String startTime = this.mJsonObj.getString("StartTime");
            String endTime = this.mJsonObj.getString("EndTime");
            int channelSelectionIndex = getCaseIndex(this.mListChannelCase, channel);
            if (channelSelectionIndex >= 0) {
                this.mTvChannel.setText(this.mListChannelName.get(channelSelectionIndex));
            }
            int recordTypeSelectionIndex = getCaseIndex(this.mListRecordTypeCase, recordType);
            if (recordTypeSelectionIndex >= 0) {
                this.mTvRecordType.setText(this.mListRecordTypeName.get(recordTypeSelectionIndex));
            }
            int diskTypeSelectionIndex = getCaseIndex(this.mListDiskTypeCase, diskType);
            if (diskTypeSelectionIndex >= 0) {
                this.mTvDiskType.setText(this.mListDiskTypeName.get(diskTypeSelectionIndex));
            }
            this.mTvDate.setText(date);
            this.mTvStartTime.setText(startTime);
            this.mTvEndTime.setText(endTime);
        } catch (Exception unused) {
        }
    }

    private int getCaseIndex(List<Integer> cases, Integer value) {
        for (int caseIndex = 0; caseIndex < cases.size(); caseIndex++) {
            if (value.equals(cases.get(caseIndex))) {
                return caseIndex;
            }
        }
        return -1;
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
        String selectedDate;
        try {
            selectedDate = this.mJsonObj.getString("Date");
        } catch (Exception unused) {
            selectedDate = "";
        }
        ((DateDialog.Builder) ((DateDialog.Builder) ((DateDialog.Builder) new DateDialog.Builder(getContext()).setTitle((CharSequence) getString(R.string.date_title))).setConfirm((CharSequence) getString(R.string.common_confirm))).setCancel((CharSequence) getString(R.string.common_cancel))).setDate(selectedDate).setListener(new DateDialog.OnListener() {
            public void onCancel(BaseDialog baseDialog) {
            }

            public void onSelected(BaseDialog baseDialog, int year, int month, int day) {
                try {
                    PlayFragmentSearch.this.mJsonObj.put("Date", String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day)}));
                } catch (Exception unused) {
                }
                PlayFragmentSearch.this.refreshUi();
            }
        }).show();
    }

    public void showTimeDialog(final boolean isStartTime) {
        String selectedTime;
        if (isStartTime) {
            try {
                selectedTime = this.mJsonObj.getString("StartTime");
            } catch (Exception unused) {
                selectedTime = "";
            }
        } else {
            try {
                selectedTime = this.mJsonObj.getString("EndTime");
            } catch (Exception unused2) {
                selectedTime = "";
            }
        }
        ((TimeDialog.Builder) ((TimeDialog.Builder) ((TimeDialog.Builder) new TimeDialog.Builder(getContext()).setTitle((CharSequence) getString(R.string.time_title))).setConfirm((CharSequence) getString(R.string.common_confirm))).setCancel((CharSequence) getString(R.string.common_cancel))).setTime(selectedTime).setListener(new TimeDialog.OnListener() {
            public void onCancel(BaseDialog baseDialog) {
            }

            public void onSelected(BaseDialog baseDialog, int hour, int minute, int second) {
                try {
                    String selectedTime = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)});
                    if (isStartTime) {
                        PlayFragmentSearch.this.mJsonObj.put("StartTime", selectedTime);
                    } else {
                        PlayFragmentSearch.this.mJsonObj.put("EndTime", selectedTime);
                    }
                } catch (Exception unused) {
                }
                PlayFragmentSearch.this.refreshUi();
            }
        }).show();
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("FragmentSelectForChannel")) {
            if (selectedIndexes.size() > 0) {
                updateDateForSelect("Channel", this.mListChannelCase, selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("FragmentSelectForRecordType")) {
            if (selectedIndexes.size() > 0) {
                updateDateForSelect("RecordType", this.mListRecordTypeCase, selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("FragmentSelectForDiskType") && selectedIndexes.size() > 0) {
            updateDateForSelect("DiskType", this.mListDiskTypeCase, selectedIndexes.get(0).intValue());
        }
    }

    public void updateDateForSelect(String jsonKey, ArrayList<Integer> cases, int selectedIndex) {
        if (selectedIndex < cases.size()) {
            try {
                this.mJsonObj.put(jsonKey, cases.get(selectedIndex));
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void startSearch() {
        if (this.mActivity.mDvrNet == null) {
            toastSf((int) R.string.connect_error);
        } else if (this.mActivity.mConnectRunnable != null) {
            toastSf((int) R.string.is_connecting);
        } else if (this.mSearchRunnable != null) {
            toastSf((int) R.string.is_searching);
        } else {
            int diskType = 0;
            int fileTypeMask = 65535;
            int channelBits = 65535;
            String searchStartTime = "";
            String searchEndTime = "";
            try {
                int channel = this.mJsonObj.getInt("Channel");
                int recordType = this.mJsonObj.getInt("RecordType");
                int selectedDiskType = this.mJsonObj.getInt("DiskType");
                String date = this.mJsonObj.getString("Date");
                String startTime = this.mJsonObj.getString("StartTime");
                String endTime = this.mJsonObj.getString("EndTime");
                diskType = selectedDiskType;
                fileTypeMask = recordType != 0 ? 1 << (recordType - 1) : 65535;
                channelBits = 1 << channel;
                searchStartTime = date.replace("-", "") + startTime.replace(":", "");
                searchEndTime = date.replace("-", "") + endTime.replace(":", "");
            } catch (Exception unused) {
            }
            this.mProgressBar.setVisibility(0);
            this.mSearchRunnable = new PlaySearchRunnable(this.mActivity, this, diskType, fileTypeMask, 1, channelBits, searchStartTime, searchEndTime);
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

        PlaySearchRunnable(PlayActivity playActivity, FragmentBase fragmentBase, int diskType, int fileTypeMask, int streamType, int channelBits, String searchStartTime, String searchEndTime) {
            this.mActivity = playActivity;
            this.mFragment = fragmentBase;
            this.mDisk = diskType;
            this.mFileType = fileTypeMask;
            this.mStream = streamType;
            this.mChannelBits = channelBits;
            this.mStartTime = searchStartTime;
            this.mEndTime = searchEndTime;
        }

        /* access modifiers changed from: private */
        public char[] getChars(byte[] data) {
            Charset charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
            byteBuffer.put(data);
            byteBuffer.flip();
            return charset.decode(byteBuffer).array();
        }

        public void run() {
            if (this.mActivity.mDvrNet != null) {
                RemoteFileInfo[] searchResults = this.mActivity.mDvrNet.SearchVideoFileList(this.mDisk, this.mFileType, this.mStream, this.mChannelBits, this.mStartTime, this.mEndTime);
                this.mFileInfo = searchResults;
                if (searchResults == null || searchResults.length == 0) {
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
                            PlayFragmentFile instance = PlayFragmentFile.getInstance(UiUtils.getString(R.string.file), PlaySearchRunnable.this.mActivity);
                            instance.mPopFragment = PlaySearchRunnable.this.mFragment;
                            if (instance.mFileList == null) {
                                instance.mFileList = new ArrayList();
                            } else {
                                instance.mFileList.clear();
                            }
                            for (int fileIndex = 0; fileIndex < PlaySearchRunnable.this.mFileInfo.length; fileIndex++) {
                                RemoteFileInfo remoteFileInfo = PlaySearchRunnable.this.mFileInfo[fileIndex];
                                PlayFileInfo playFileInfo = new PlayFileInfo();
                                playFileInfo.nDiskType = remoteFileInfo.nDiskType;
                                playFileInfo.FileTime = remoteFileInfo.FileTime;
                                playFileInfo.name = remoteFileInfo.name;
                                playFileInfo.nFileSize = remoteFileInfo.nFileSize;
                                playFileInfo.nChannel = remoteFileInfo.nChannel;
                                playFileInfo.nType = remoteFileInfo.nType;
                                String recordTypeName;
                                if (1 == ((remoteFileInfo.nType >> 0) & 1)) {
                                    recordTypeName = UiUtils.getString(R.string.normal_record);
                                } else {
                                    recordTypeName = 1 == ((remoteFileInfo.nType >> 1) & 1) ? UiUtils.getString(R.string.alarm_record) : "";
                                }
                                String formattedFileTime = playFileInfo.FileTime;
                                if (playFileInfo.FileTime.length() >= 29) {
                                    char[] fileTimeChars = PlaySearchRunnable.this.getChars(playFileInfo.FileTime.getBytes());
                                    formattedFileTime = String.format("%s %s:%s:%s - %s:%s:%s", new Object[]{String.format("%s-%s-%s", new Object[]{String.valueOf(fileTimeChars, 0, 4), String.valueOf(fileTimeChars, 4, 2), String.valueOf(fileTimeChars, 6, 2)}), String.valueOf(fileTimeChars, 8, 2), String.valueOf(fileTimeChars, 10, 2), String.valueOf(fileTimeChars, 12, 2), String.valueOf(fileTimeChars, 23, 2), String.valueOf(fileTimeChars, 25, 2), String.valueOf(fileTimeChars, 27, 2)});
                                }
                                playFileInfo.mTitle = String.format("%s%d %s", new Object[]{UiUtils.getString(R.string.channel), Integer.valueOf(remoteFileInfo.nChannel), recordTypeName});
                                playFileInfo.mSubTitle = formattedFileTime;
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
