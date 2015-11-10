package com.example.apptemplate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptemplate.R;

import java.util.ArrayList;

/**
 * Created by Marco on 10/11/2015.
 */
public class HomeFragment extends Fragment {

    LayoutInflater inflater;

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }
}