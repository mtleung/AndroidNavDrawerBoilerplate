package com.example.apptemplate.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Marco on 10/11/2015.
 */
public class NavDrawerItem {
    // Item only
    private String navTitle;
    private Drawable navImage;

    // Nav types
    private int navType;
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_SEP = 2;

    public NavDrawerItem() {
        this.navType = TYPE_SEP;
    }

    public NavDrawerItem(Context context, int stringId, int drawableId) {
        this.navTitle = context.getString(stringId);
        this.navImage = context.getResources().getDrawable(drawableId);
    }

    // Constructor for items
    public NavDrawerItem(String navTitle, Drawable navImage) {
        this.navTitle = navTitle;
        this.navImage = navImage;
        this.navType = navTitle.equals("") && navImage == null ? TYPE_SEP : TYPE_ITEM;
    }

    public String getTitle() {
        return this.navTitle;
    }
    public Drawable getImage() {
        return this.navImage;
    }

    public boolean isDivider() {
        return navType == TYPE_SEP;
    }
    public boolean isHeader() {
        return navType == TYPE_HEAD;
    }

}
