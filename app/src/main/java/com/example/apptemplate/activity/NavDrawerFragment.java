package com.example.apptemplate.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.apptemplate.R;
import com.example.apptemplate.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 10/11/2015.
 */
public class NavDrawerFragment extends Fragment {

    private Context mContext;
    private List<NavDrawerItem> drawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        // Init drawer list
        initDrawerItems();
    }

    private void initDrawerItems() {
        // Add in drawer items
        drawerList = new ArrayList<>();
        drawerList.add(new NavDrawerItem(mContext, R.string.drawer_item_home, R.drawable.ic_action_person));
        drawerList.add(new NavDrawerItem(mContext, R.string.drawer_item_other, R.drawable.ic_action_person));
    }
}
