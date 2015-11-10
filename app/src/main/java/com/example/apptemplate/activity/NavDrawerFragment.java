package com.example.apptemplate.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptemplate.R;
import com.example.apptemplate.adapter.NavDrawerAdapter;
import com.example.apptemplate.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 10/11/2015.
 */
public class NavDrawerFragment extends Fragment {

    private Context mContext;
    // Header item
    private TextView hUsername;
    private TextView hUserSubtitle;
    private ImageView hUserPic;


    private List<NavDrawerItem> drawerList;

    // List items
    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavDrawerAdapter mAdapter;
    private View containerView;

    private FragmentDrawerListener drawerListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        // Init drawer list
        initDrawerItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        // Header
        hUsername = (TextView) layout.findViewById(R.id.user_name);
        hUserSubtitle = (TextView) layout.findViewById(R.id.user_subtitle);
        hUserPic = (ImageView) layout.findViewById(R.id.user_pic);

        // Set up recycler view
        mAdapter = new NavDrawerAdapter(drawerList);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.nav_drawer_list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Listen for touch events on recycler view
        mRecyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getActivity(), mRecyclerView, new TouchListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Log.d("DrawerFragment", "onClick");
                        drawerListener.onDrawerItemSelected(view, position);
                        mDrawerLayout.closeDrawer(containerView);
                        mAdapter.clearSelection();
                    }

                    @Override
                    public void onShowPress(int index) {
                        Log.d("DrawerFragment", "onShowPress");
                        mAdapter.toggleSelection(index);
                    }

                    @Override
                    public void onHidePress() {
                        Log.d("DrawerFragment", "onHidePress");
                        mAdapter.clearSelection();
                    }
                })
        );
        return layout;
    }

    private void initDrawerItems() {
        // Add in drawer items
        drawerList = new ArrayList<>();
        drawerList.add(new NavDrawerItem(mContext, R.string.drawer_item_home, R.drawable.ic_action_person));
        drawerList.add(new NavDrawerItem(mContext, R.string.drawer_item_other, R.drawable.ic_action_person));
        drawerList.add(new NavDrawerItem());
    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }

    public static interface TouchListener {
        void onClick(View view, int position);
        void onShowPress(int index);
        void onHidePress();
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private TouchListener touchListener;

        public RecyclerTouchListener(Context context,
                                     final RecyclerView recyclerView, final TouchListener touchListener) {
            this.touchListener = touchListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapConfirmed(MotionEvent me) {
                    View child = recyclerView.findChildViewUnder(me.getX(), me.getY());
                    if (child != null && touchListener != null) {
                        touchListener.onClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                    touchListener.onHidePress();
                    return true;
                }

                @Override
                public boolean onDown(MotionEvent me) {
                    View child = recyclerView.findChildViewUnder(me.getX(), me.getY());
                    if (child != null && touchListener != null) {
                        int idx = recyclerView.getChildAdapterPosition(child);
                        touchListener.onShowPress(idx);
                    }
                    return false;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent me) {
            View child = rv.findChildViewUnder(me.getX(), me.getY());
            if(child != null && touchListener != null && gestureDetector.onTouchEvent(me)) {
                touchListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            if (me.getAction() == MotionEvent.ACTION_UP) {
                touchListener.onHidePress();
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent me) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {
        }
    }

    // Inits navigation drawer
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // creates call to onPrepareOptionsMenu()
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                // creates call to onPrepareOptionsMenu()
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset/2);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
