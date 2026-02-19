package com.streamax.client;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dvr.avstream.AVStream;
import com.dvr.avstream.AuTrack;
import com.dvr.avstream.FilePlaybackInterface;
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Playback activity for locally stored files.
public class LocalPlaybackActivity extends Activity implements FilePlaybackInterface {
    public AVStream mAVStream;
    public AuTrack mAudioTrack;
    public View mBottomBar;
    public Button mBtnExit;
    public Context mContext = this;
    public int mHeight;
    public ImageView mImageCapture;
    public ImageView mImagePlay;
    public ImageView mImageSound;
    public ImageView mImageSpeedDown;
    public ImageView mImageSpeedUp;
    public ImageView mImageStop;
    public LayoutInflater mInflater;
    View.OnClickListener mOnclickListener;
    public String mPlaybackFileName = null;
    public String mPlaybackTime;
    public PopupWindow mPopupCapture;
    public SeekBar mSeekBar;
    public int mSpeed = 1;
    public View mTopBar;
    public boolean mTracking = false;
    public UpdateText mUpdateText;
    public VideoGroup mVideoGroup;
    public int mWidth;
    public boolean mbMute = false;
    public boolean mbPause = false;
    public boolean mbPlay = false;
    List<Map<String, Object>> mbmpList;
    public long mhFileDecoder;
    public View mpopViewer;
    public TextView mtvSpeed;
    public TextView mtvTime;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.localplayback);
        this.mInflater = LayoutInflater.from(this);
        FindViews();
        this.mPlaybackFileName = getIntent().getExtras().getString("path");
        Play();
    }

    public void FindViews() {
        this.mTopBar = findViewById(R.id.local_playback_title);
        this.mBottomBar = findViewById(R.id.local_playback_controlbar);
        this.mImagePlay = (ImageView) findViewById(R.id.local_playback_controlbar_play);
        this.mImageStop = (ImageView) findViewById(R.id.local_playback_controlbar_stop);
        this.mImageSpeedUp = (ImageView) findViewById(R.id.local_playback_controlbar_fast);
        this.mImageSpeedDown = (ImageView) findViewById(R.id.local_playback_controlbar_slow);
        this.mImageCapture = (ImageView) findViewById(R.id.local_playback_controlbar_capture);
        this.mImageSound = (ImageView) findViewById(R.id.local_playback_controlbar_sound);
        this.mVideoGroup = (VideoGroup) findViewById(R.id.local_playback_videogroup);
        Button button = (Button) findViewById(R.id.local_playback_title_cancel);
        this.mBtnExit = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocalPlaybackActivity.this.Exit();
                LocalPlaybackActivity.this.finish();
            }
        });
        VideoGroup videoGroup = this.mVideoGroup;
        if (videoGroup != null) {
            videoGroup.LoadViews();
            this.mVideoGroup.SetInitMode(1);
            this.mVideoGroup.getVideoView(0).setBackgroundColor(-11184811);
            this.mVideoGroup.getVideoView(0).setMax(true);
        }
        this.mSeekBar = (SeekBar) findViewById(R.id.local_playback_progressbar);
        this.mtvTime = (TextView) findViewById(R.id.local_playback_progress_text);
        this.mtvSpeed = (TextView) findViewById(R.id.local_playback_playrate_text);
        this.mVideoGroup.getVideoView(0).setOsdState(false);
        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View view) {
                int i = 0;
                switch (view.getId()) {
                    case R.id.local_playback_controlbar_capture /*2131362534*/:
                        if (LocalPlaybackActivity.this.mbPlay) {
                            if (!Environment.getExternalStorageState().equals("mounted")) {
                                Toast.makeText(LocalPlaybackActivity.this, LocalPlaybackActivity.this.mContext.getString(R.string.ExternalStorageerror), 0).show();
                                return;
                            }
                            MediaPlayer.create(LocalPlaybackActivity.this.mContext, R.raw.capture).start();
                            LocalPlaybackActivity localPlaybackActivity = LocalPlaybackActivity.this;
                            localPlaybackActivity.popImageViewer(localPlaybackActivity.CaptureImage());
                            return;
                        }
                        return;
                    case R.id.local_playback_controlbar_fast /*2131362535*/:
                        int i2 = LocalPlaybackActivity.this.mSpeed;
                        if (i2 == -2) {
                            LocalPlaybackActivity.this.mSpeed = 1;
                        } else if (i2 == 1) {
                            LocalPlaybackActivity.this.mSpeed = 2;
                        }
                        LocalPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(LocalPlaybackActivity.this.mSpeed)}));
                        LocalPlaybackActivity.this.mAVStream.AVFileSetSpeed(LocalPlaybackActivity.this.mhFileDecoder, LocalPlaybackActivity.this.mSpeed);
                        return;
                    case R.id.local_playback_controlbar_play /*2131362536*/:
                        ImageView imageView = (ImageView) LocalPlaybackActivity.this.findViewById(R.id.local_playback_controlbar_play);
                        if (!LocalPlaybackActivity.this.mbPlay) {
                            imageView.setImageResource(R.drawable.pause);
                            LocalPlaybackActivity.this.Play();
                            return;
                        }
                        LocalPlaybackActivity localPlaybackActivity2 = LocalPlaybackActivity.this;
                        localPlaybackActivity2.mbPause = !localPlaybackActivity2.mbPause;
                        if (LocalPlaybackActivity.this.mbPause) {
                            imageView.setImageResource(R.drawable.play);
                            LocalPlaybackActivity.this.mSpeed = 1;
                            LocalPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(LocalPlaybackActivity.this.mSpeed)}));
                            LocalPlaybackActivity.this.mAVStream.AVFileSetSpeed(LocalPlaybackActivity.this.mhFileDecoder, LocalPlaybackActivity.this.mSpeed);
                            i = 1;
                        } else {
                            imageView.setImageResource(R.drawable.pause);
                        }
                        if (LocalPlaybackActivity.this.mAVStream != null && LocalPlaybackActivity.this.mhFileDecoder != 0) {
                            LocalPlaybackActivity.this.mAVStream.AVFilePause(LocalPlaybackActivity.this.mhFileDecoder, i);
                            return;
                        }
                        return;
                    case R.id.local_playback_controlbar_slow /*2131362537*/:
                        int i3 = LocalPlaybackActivity.this.mSpeed;
                        if (i3 == 1) {
                            LocalPlaybackActivity.this.mSpeed = -2;
                        } else if (i3 == 2) {
                            LocalPlaybackActivity.this.mSpeed = 1;
                        }
                        LocalPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(LocalPlaybackActivity.this.mSpeed)}));
                        LocalPlaybackActivity.this.mAVStream.AVFileSetSpeed(LocalPlaybackActivity.this.mhFileDecoder, LocalPlaybackActivity.this.mSpeed);
                        return;
                    case R.id.local_playback_controlbar_sound /*2131362538*/:
                        if (LocalPlaybackActivity.this.mbMute) {
                            LocalPlaybackActivity.this.mbMute = false;
                            LocalPlaybackActivity.this.mImageSound.setImageResource(R.drawable.soundopen);
                        } else {
                            LocalPlaybackActivity.this.mbMute = true;
                            LocalPlaybackActivity.this.mImageSound.setImageResource(R.drawable.soundclose);
                            i = 1;
                        }
                        if (LocalPlaybackActivity.this.mAVStream != null) {
                            LocalPlaybackActivity.this.mAVStream.AVFileSetMute(LocalPlaybackActivity.this.mhFileDecoder, i);
                            return;
                        }
                        return;
                    case R.id.local_playback_controlbar_stop /*2131362539*/:
                        LocalPlaybackActivity.this.StopPlay();
                        return;
                    default:
                        return;
                }
            }
        };
        this.mOnclickListener = clickListener;
        this.mImagePlay.setOnClickListener(clickListener);
        this.mImageStop.setOnClickListener(this.mOnclickListener);
        this.mImageSpeedUp.setOnClickListener(this.mOnclickListener);
        this.mImageSpeedDown.setOnClickListener(this.mOnclickListener);
        this.mImageCapture.setOnClickListener(this.mOnclickListener);
        this.mImageSound.setOnClickListener(this.mOnclickListener);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                LocalPlaybackActivity.this.mTracking = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                LocalPlaybackActivity localPlaybackActivity = LocalPlaybackActivity.this;
                localPlaybackActivity.Seek(localPlaybackActivity.mSeekBar.getMax(), LocalPlaybackActivity.this.mSeekBar.getProgress());
                LocalPlaybackActivity.this.mTracking = false;
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        SetConfiguration(configuration);
        super.onConfigurationChanged(configuration);
    }

    public List<Map<String, Object>> CaptureImage() {
        String str = Environment.getExternalStorageDirectory() + "/streaming/capture/";
        Calendar instance = Calendar.getInstance();
        String format = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        ArrayList arrayList = new ArrayList();
        if (this.mAVStream != null) {
            HashMap hashMap = new HashMap();
            String str2 = str + format + (Integer.toString(0) + ".bmp");
            hashMap.put("channel", 0);
            hashMap.put("path", str2);
            long j = this.mhFileDecoder;
            if (j == 0) {
                return null;
            }
            if (this.mAVStream.AVCaptureImage(j, str2.getBytes()) == 0) {
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    public void popImageViewer(List<Map<String, Object>> list) {
        List<Map<String, Object>> list2 = list;
        if (list.size() != 0) {
            this.mbmpList = list2;
            int size = list.size();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inTempStorage = new byte[829440];
            this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, (ViewGroup) null);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            int i = 17;
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(Color.argb(200, 40, 40, 40));
            int i2 = 0;
            linearLayout.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.width = MyApp.getScreenWidth();
            layoutParams.height = (MyApp.getScreenWidth() * 3) / 4;
            int i3 = 0;
            while (true) {
                double d = (double) i3;
                double d2 = (double) size;
                if (d < Math.sqrt(d2)) {
                    LinearLayout linearLayout2 = new LinearLayout(this.mContext);
                    linearLayout2.setOrientation(i2);
                    linearLayout2.setGravity(i);
                    linearLayout2.setPadding(i2, i2, i2, i2);
                    int i4 = 0;
                    while (true) {
                        double d3 = (double) i4;
                        if (d3 >= Math.sqrt(d2)) {
                            break;
                        }
                        String obj = list2.get((int) ((Math.sqrt(d2) * d) + d3)).get("path").toString();
                        ImageView imageView = new ImageView(this.mContext);
                        imageView.setImageBitmap(BitmapFactory.decodeFile(obj, options));
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        linearLayout2.addView(imageView, layoutParams);
                        i4++;
                    }
                    linearLayout.addView(linearLayout2, layoutParams);
                    i3++;
                    i = 17;
                    i2 = 0;
                } else {
                    ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
                    this.mPopupCapture = null;
                    PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -1, -1, true);
                    this.mPopupCapture = popupWindow;
                    popupWindow.setOutsideTouchable(false);
                    this.mPopupCapture.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
                    this.mPopupCapture.showAtLocation(findViewById(R.id.localplayback), 17, 0, 0);
                    this.mPopupCapture.update();
                    this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            LocalPlaybackActivity.this.mPopupCapture.dismiss();
                        }
                    });
                    this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            for (int i = 0; i < LocalPlaybackActivity.this.mbmpList.size(); i++) {
                                String obj = LocalPlaybackActivity.this.mbmpList.get(i).get("path").toString();
                                if (obj != null) {
                                    new File(obj).delete();
                                }
                            }
                            LocalPlaybackActivity.this.mPopupCapture.dismiss();
                        }
                    });
                    return;
                }
            }
        }
    }

    public void OpenFiles(String str) {
        AVStream aVStream = this.mAVStream;
        if (aVStream == null) {
            this.mAVStream = new AVStream();
            AuTrack auTrack = new AuTrack();
            this.mAudioTrack = auTrack;
            auTrack.mPlayer.play();
            this.mAudioTrack.SetMute(this.mbMute);
            this.mAudioTrack.SwitchChannels(0);
            this.mAVStream.SetFilePlaybackInterface(this);
            this.mAVStream.SetAudioInterface(this.mAudioTrack);
            this.mhFileDecoder = this.mAVStream.AVOpenFileDecoder(str.getBytes());
        } else if (this.mhFileDecoder != 0) {
            aVStream.StopPlay();
            this.mAVStream.AVCloseDecoder(this.mhFileDecoder);
            this.mAVStream = null;
            this.mAudioTrack = null;
            this.mhFileDecoder = 0;
        }
    }

    public void Play() {
        if (this.mPlaybackFileName != null) {
            StopPlay();
            OpenFiles(this.mPlaybackFileName);
            this.mAVStream.AVStartFilePlay(this.mhFileDecoder);
            this.mSpeed = 1;
            this.mAVStream.AVFileSetSpeed(this.mhFileDecoder, 1);
            this.mAVStream.AVFileSetMute(this.mhFileDecoder, this.mbMute ? 1 : 0);
            this.mAudioTrack.mPlayer.play();
            this.mbPlay = true;
            this.mbPause = false;
            this.mAVStream.AVFilePause(this.mhFileDecoder, 0);
            ((ImageView) findViewById(R.id.local_playback_controlbar_play)).setImageResource(R.drawable.pause);
        }
    }

    public void StopPlay() {
        if (this.mPlaybackFileName != null) {
            long j = this.mhFileDecoder;
            if (j != 0) {
                this.mAVStream.AVStopFilePlay(j);
                this.mAVStream.AVCloseFileDecoder(this.mhFileDecoder);
                this.mAVStream = null;
                this.mhFileDecoder = 0;
                this.mSpeed = 1;
                this.mAudioTrack.mPlayer.Stop();
                this.mAudioTrack = null;
            }
        }
    }

    public void Seek(int i, int i2) {
        AVStream aVStream = this.mAVStream;
        if (aVStream != null) {
            long j = this.mhFileDecoder;
            if (j != 0) {
                aVStream.AVFileSeekPos(j, (i2 * 100) / i);
            }
        }
    }

    public class UpdateText implements Runnable {
        public UpdateText() {
        }

        public void run() {
            LocalPlaybackActivity.this.mtvTime.setText(LocalPlaybackActivity.this.mPlaybackTime);
        }
    }

    public void SetConfiguration(Configuration configuration) {
        int i = 0;
        if (configuration.orientation == 2) {
            i = 8;
        } else {
            int i2 = configuration.orientation;
        }
        findViewById(R.id.local_playback_title).setVisibility(i);
        findViewById(R.id.local_playback_progress).setVisibility(i);
        findViewById(R.id.local_playback_controlbar).setVisibility(i);
    }

    public void FilePlaybackCallback(int i, int i2, byte[] bArr, int i3, int i4, int i5, int i6, final int i7) {
        VideoView videoView = this.mVideoGroup.getVideoView(0);
        if (videoView != null) {
            videoView.writeIn(bArr, i4, i5);
        }
        if (i6 == 1) {
            this.mSeekBar.post(new Runnable() {
                public void run() {
                    LocalPlaybackActivity.this.mSeekBar.setProgress(0);
                    LocalPlaybackActivity.this.mSeekBar.setSecondaryProgress(0);
                }
            });
            this.mSeekBar.setMax(i7);
            this.mUpdateText = null;
            this.mUpdateText = new UpdateText();
        }
        if (i6 == 2) {
            if (!this.mTracking) {
                this.mSeekBar.post(new Runnable() {
                    public void run() {
                        LocalPlaybackActivity.this.mSeekBar.setProgress(i7);
                        LocalPlaybackActivity.this.mSeekBar.setSecondaryProgress(i7);
                    }
                });
            }
            this.mVideoGroup.getVideoView(0).writeIn(bArr, i4, i5);
            this.mPlaybackTime = String.format("%02d:%02d", new Object[]{Integer.valueOf(i7 / 60), Integer.valueOf(i7 % 60)});
            this.mtvTime.post(this.mUpdateText);
        }
        if (i6 == 3) {
            this.mSeekBar.post(new Runnable() {
                public void run() {
                    LocalPlaybackActivity.this.mSeekBar.setProgress(0);
                    LocalPlaybackActivity.this.mSeekBar.setSecondaryProgress(0);
                    LocalPlaybackActivity.this.mbPlay = false;
                    LocalPlaybackActivity.this.mbPause = false;
                    LocalPlaybackActivity.this.mImagePlay.setImageResource(R.drawable.play);
                    LocalPlaybackActivity.this.mtvTime.setText("00:00");
                    LocalPlaybackActivity.this.mtvSpeed.setText("1X");
                    LocalPlaybackActivity.this.mVideoGroup.ClearViews();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Exit();
        super.onDestroy();
    }

    public void Exit() {
        if (this.mPlaybackFileName != null) {
            StopPlay();
        }
    }
}
