package com.cositoycosita.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cositoycosita.R;

/**
 * Displays terms and conditions.
 * Created by benjamin on 10/7/14.
 */
public class AlbumFragment extends Fragment {

    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.album_fragment, container, false);


        return mView;
    }

}