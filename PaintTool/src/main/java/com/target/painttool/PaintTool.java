package com.target.painttool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class PaintTool {
    public static final int DRAW_RESULT = 645;
    private int mBackgroundColor=Color.WHITE;
    private int mToolBarColor= android.R.color.darker_gray;
    private String mTitle="Paint Tool";
    private Activity mActivity;

    private PaintTool(Activity activity){
        this.mActivity=activity;
    }

    public static PaintTool with(Activity a){
        return new PaintTool(a);
    }

    public PaintTool setBackgroundColor(int color){
        mBackgroundColor=color;
        return this;
    }

    public PaintTool setToolBarTitle(String title){
        mTitle=title;
        return this;
    }

    public PaintTool setToolBarColor(int color) {
        mToolBarColor=color;
        return this;
    }


    public void show(){
        Intent i=new Intent(mActivity,PaintActivity.class);
        i.putExtra("BGC",mBackgroundColor);
        i.putExtra("title",mTitle);
        i.putExtra("TBC",mToolBarColor);
        mActivity.startActivityForResult(i,DRAW_RESULT);
    }

    public static Bitmap getDrawResult(Intent data) {
        byte[] b = data.getByteArrayExtra("result");
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }


}
