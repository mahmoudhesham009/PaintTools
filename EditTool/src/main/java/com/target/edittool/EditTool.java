package com.target.edittool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.net.URL;

public class EditTool {
    public static final int DRAW_RESULT = 645;
    private int mToolBarColor= android.R.color.darker_gray;
    private String mTitle="Paint Tool";
    private Uri mImage;
    private Activity mActivity;


    private EditTool(Activity activity, Uri image){
        this.mActivity=activity;
        mImage=image;
    }



    public static EditTool with(Activity a, Uri uri){
        return new EditTool(a,uri);
    }



    public EditTool setToolBarTitle(String title){
        mTitle=title;
        return this;
    }

    public EditTool setToolBarColor(int color) {
        mToolBarColor=color;
        return this;
    }


    public void show(){
        Intent i=new Intent(mActivity,EditActivity.class);
        i.putExtra("title",mTitle);
        i.putExtra("TBC",mToolBarColor);
        i.putExtra("imageSource",mImage);
        mActivity.startActivityForResult(i,DRAW_RESULT);
    }

    public static Bitmap getDrawResult(Intent data) {
        byte[] b = data.getByteArrayExtra("result");
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

}
