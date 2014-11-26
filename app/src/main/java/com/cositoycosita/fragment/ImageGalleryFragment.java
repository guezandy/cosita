package com.cositoycosita.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cositoycosita.MyApplication;
import com.cositoycosita.R;
import com.cositoycosita.adapter.AlbumGridViewAdapter;
import com.cositoycosita.model.AlbumItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**

 */
public class ImageGalleryFragment extends Fragment {
    private final static String TAG = AlbumFragment.class.getSimpleName();
    private View mView;
    private static SharedPreferences preferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.gallery_fragment, container, false);

        //Log.i(TAG, "Inside Album Fragment " + MyApplication.getAlbum());
        final GridView itemGrid = (GridView) mView.findViewById(R.id.galleryGridView);
        final AlbumGridViewAdapter gridAdapter = new AlbumGridViewAdapter(getActivity().getApplicationContext(), R.layout.row_grid);

        ParseQuery<AlbumItem> query = ParseQuery.getQuery("AlbumItem");
        query.whereEqualTo("album_title", MyApplication.getAlbum());
        query.findInBackground(new FindCallback<AlbumItem>() {
            @Override
            public void done(List<AlbumItem> covers, ParseException e) {
                if (e != null) {
                    // There was an error
                    Log.i(TAG, "No items in album item table");
                } else {
                    gridAdapter.setItems(covers);
                    itemGrid.setAdapter(gridAdapter);
                }
            }
        });

        Log.i(TAG, "Returning View");
        return mView;
    }

    public Activity getCurrentActivity() {
        return this.getActivity();
    }

}