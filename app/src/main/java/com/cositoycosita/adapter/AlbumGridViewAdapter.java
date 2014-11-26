package com.cositoycosita.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cositoycosita.MainActivity;
import com.cositoycosita.MyApplication;
import com.cositoycosita.R;
import com.cositoycosita.fragment.ImageGalleryFragment;
import com.cositoycosita.fragment.MapFragment;
import com.cositoycosita.model.AlbumItem;
import com.cositoycosita.model.ImageItem;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class AlbumGridViewAdapter extends ArrayAdapter<AlbumItem> {
    List<AlbumItem> mItems = Collections.emptyList();
    private final static String TAG = AlbumGridViewAdapter.class.getSimpleName();
    Context mContext;
    private int mView;

    public AlbumGridViewAdapter(Context context, int view) {
        super(context, view);
        this.mContext = context;
        mView = view;
    }

    public void setItems(List<AlbumItem> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        final AlbumItem item = mItems.get(position);
        if (v == null) {
            v = View.inflate(mContext, mView, null);
        }
        TextView AlbumCaption = (TextView) v.findViewById(R.id.text);
        AlbumCaption.setText(item.getCaption());

        ParseImageView itemImage = (ParseImageView) v.findViewById(R.id.image);
        ParseFile photoFile = item.getImage();
        if (photoFile != null) {
            itemImage.setParseFile(photoFile);
            itemImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                    Log.i("TAG", "Image uploaded: " + item.getCaption());
                }
            });
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AlbumItem> mItems2 = new ArrayList<AlbumItem>();
                for(AlbumItem item : mItems) {
                    if(item.getCaption().equals(item.getCaption())) {
                        mItems2.add(item);
                    }
                }
                setItems(mItems2);
            }
        });
        return v;
    }

}