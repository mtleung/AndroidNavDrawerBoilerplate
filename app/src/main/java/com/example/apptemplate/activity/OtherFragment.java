package com.example.apptemplate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptemplate.R;

/**
 * Created by Marco on 10/11/2015.
 */
public class OtherFragment extends Fragment {
    LayoutInflater inflater;

    public static OtherFragment newInstance() {
        OtherFragment f = new OtherFragment();
        /*
        // Load arguments for fragment initialisation here
        Bundle args = new Bundle();
        args.putInt(key, value);
        f.setArguments(args);
        */
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;

        // Read in args from bundle
        // int value = (int) getArguments().getSerializable(key);

        // Inflate layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_other, container, false);

        return rootView;
    }
}
