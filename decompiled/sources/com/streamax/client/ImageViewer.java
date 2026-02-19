package com.streamax.client;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;
import com.zycs.UView.R;
import java.util.Collections;

public class ImageViewer extends LinearLayout {
    public BaseAdapter mAdapter;
    public Context mContext;
    public Gallery mGallery;
    public ImageSwitcher mImageSwitcher;
    public View mImageViewer;
    public int mPosition;

    public ImageViewer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public ImageViewer(Context context) {
        super(context);
        this.mContext = context;
        FindViews();
    }

    public void FindViews() {
        this.mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageviewer_switcher);
        this.mGallery = (Gallery) findViewById(R.id.imageviewer_gallery);
        this.mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(ImageViewer.this.mContext);
                imageView.setBackgroundColor(0);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                return imageView;
            }
        });
        this.mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
        this.mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        this.mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ImageViewer.this.mPosition = i;
                ImageViewer.this.mImageSwitcher.setImageDrawable(new BitmapDrawable(ImageUtils.getAt(i)));
            }
        });
    }

    public void refreshAdapter() {
        ImageUtils.updateFileList();
        Collections.reverse(ImageUtils.fileList);
        BaseAdapter baseAdapter = this.mAdapter;
        if (baseAdapter == null) {
            ImageAdapter imageAdapter = new ImageAdapter(this.mContext);
            this.mAdapter = imageAdapter;
            this.mGallery.setAdapter(imageAdapter);
            return;
        }
        baseAdapter.notifyDataSetChanged();
    }

    public class ImageAdapter extends BaseAdapter {
        public Context mContext;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public ImageAdapter(Context context) {
            this.mContext = context;
        }

        public int getCount() {
            return ImageUtils.getCount();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null) {
                imageView = new ImageView(this.mContext);
            } else {
                imageView = (ImageView) view;
            }
            imageView.setImageBitmap(ImageUtils.getAt(i));
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new Gallery.LayoutParams((int) (((double) ImageViewer.this.mGallery.getHeight()) * 1.22d), ImageViewer.this.mGallery.getHeight()));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
    }
}
