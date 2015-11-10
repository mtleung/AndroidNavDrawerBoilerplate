package com.example.apptemplate.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptemplate.R;
import com.example.apptemplate.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Marco on 10/11/2015.
 * Based on http://www.android4devs.com/2014/12/how-to-make-material-design-navigation-drawer.html
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private List<NavDrawerItem> data = Collections.emptyList();
    private SparseBooleanArray selectedItems;

    public NavDrawerAdapter(List<NavDrawerItem> data) {
        this.data = data;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch(viewType) {
            case (NavDrawerItem.TYPE_ITEM):
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
                break;
            case (NavDrawerItem.TYPE_SEP):
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_divider, parent, false);
                break;
            default:
                v = null;
                break;
        }
        if (v != null) {
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else {
            // unexpected view
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch(holder.holderId) {
            case (NavDrawerItem.TYPE_ITEM):
                String title = data.get(position).getTitle();
                Drawable d = data.get(position).getImage();

                holder.textView.setText(title);
                holder.imageView.setImageDrawable(d);
                boolean selected = selectedItems.get(position, false);
                holder.itemView.setPressed(selected);
                holder.itemView.setSelected(selected);
                break;
            case (NavDrawerItem.TYPE_SEP):
                // no view objects to populate
                break;
            default:
                Log.e("ViewHolder", "Unexpected holderId " + String.valueOf(holder.holderId));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isDivider()) {
            return NavDrawerItem.TYPE_SEP;
        } else {
            return NavDrawerItem.TYPE_ITEM;
        }
    }

    public void toggleSelection(int pos) {
        clearSelection();
        selectedItems.put(pos, true);
        notifyItemChanged(pos);
    }

    public void clearSelection() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {
        // regular item
        TextView textView;
        ImageView imageView;

        // holder type
        int holderId;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch(viewType) {
                case (NavDrawerItem.TYPE_ITEM):
                    textView = (TextView) itemView.findViewById(R.id.drawerText);
                    imageView = (ImageView) itemView.findViewById(R.id.drawerIcon);
                    holderId = viewType;
                    break;
                case (NavDrawerItem.TYPE_SEP):
                    holderId = viewType;
                    break;
                default:
                    break;
            }
        }
    }
}
